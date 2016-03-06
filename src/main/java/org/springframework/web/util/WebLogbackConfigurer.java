package org.springframework.web.util;

import ch.qos.logback.core.joran.spi.JoranException;
import org.springframework.web.util.LogbackConfigurer;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;

public class WebLogbackConfigurer {

    public static final String CONFIG_LOCATION_PARAM = "logbackConfigLocation";
    public static final String EXPOSE_WEB_APP_ROOT_PARAM = "logbackExposeWebAppRoot";

    private WebLogbackConfigurer() {
    }

    public static void initLogging(ServletContext servletContext) {
        // Expose the web app root system property.
        if (exposeWebAppRoot(servletContext)) {
            WebUtils.setWebAppRootSystemProperty(servletContext);
        }

        // Only perform custom Logback initialization in case of a config file.
        String location = servletContext.getInitParameter(CONFIG_LOCATION_PARAM);
        if (location != null) {
            // Perform actual Logback initialization; else rely on Logback's default initialization.
            try {
                // Resolve system property placeholders before potentially resolving real path.
                location = SystemPropertyUtils.resolvePlaceholders(location);
                // Return a URL (e.g. "classpath:" or "file:") as-is;
                // consider a plain file path as relative to the web application root directory.
                if (!ResourceUtils.isUrl(location)) {
                    location = WebUtils.getRealPath(servletContext, location);
                }

                // Write log message to server log.
                servletContext.log("Initializing Logback from [" + location + "]");

                // Initialize
                LogbackConfigurer.initLogging(location);
            } catch (FileNotFoundException ex) {
                throw new IllegalArgumentException("Invalid 'logbackConfigLocation' parameter: " + ex.getMessage());
            } catch (JoranException e) {
                throw new RuntimeException("Unexpected error while configuring logback", e);
            }
        }

        //If SLF4J's java.util.logging bridge is available in the classpath, install it. This will direct any messages
        //from the Java Logging framework into SLF4J. When logging is terminated, the bridge will need to be uninstalled
        try {
            Class<?> julBridge = ClassUtils.forName("org.slf4j.bridge.SLF4JBridgeHandler", ClassUtils.getDefaultClassLoader());
            
            Method removeHandlers = ReflectionUtils.findMethod(julBridge, "removeHandlersForRootLogger");
            if (removeHandlers != null) {
                servletContext.log("Removing all previous handlers for JUL to SLF4J bridge");
                ReflectionUtils.invokeMethod(removeHandlers, null);
            }
            
            Method install = ReflectionUtils.findMethod(julBridge, "install");
            if (install != null) {
                servletContext.log("Installing JUL to SLF4J bridge");
                ReflectionUtils.invokeMethod(install, null);
            }
        } catch (ClassNotFoundException ignored) {
            //Indicates the java.util.logging bridge is not in the classpath. This is not an indication of a problem.
            servletContext.log("JUL to SLF4J bridge is not available on the classpath");
        }
    }

    /**
     * Shut down Logback, properly releasing all file locks
     * and resetting the web app root system property.
     *
     * @param servletContext the current ServletContext
     * @see WebUtils#removeWebAppRootSystemProperty
     */
    public static void shutdownLogging(ServletContext servletContext) {
        //Uninstall the SLF4J java.util.logging bridge *before* shutting down the Logback framework.
        try {
            Class<?> julBridge = ClassUtils.forName("org.slf4j.bridge.SLF4JBridgeHandler", ClassUtils.getDefaultClassLoader());
            Method uninstall = ReflectionUtils.findMethod(julBridge, "uninstall");
            if (uninstall != null) {
                servletContext.log("Uninstalling JUL to SLF4J bridge");
                ReflectionUtils.invokeMethod(uninstall, null);
            }
        } catch (ClassNotFoundException ignored) {
            //No need to shutdown the java.util.logging bridge. If it's not on the classpath, it wasn't started either.
        }

        try {
            servletContext.log("Shutting down Logback");
            LogbackConfigurer.shutdownLogging();
        } finally {
            // Remove the web app root system property.
            if (exposeWebAppRoot(servletContext)) {
                WebUtils.removeWebAppRootSystemProperty(servletContext);
            }
        }
    }


    @SuppressWarnings({"BooleanMethodNameMustStartWithQuestion"})
    private static boolean exposeWebAppRoot(ServletContext servletContext) {
        String exposeWebAppRootParam = servletContext.getInitParameter(EXPOSE_WEB_APP_ROOT_PARAM);
        return (exposeWebAppRootParam == null || Boolean.valueOf(exposeWebAppRootParam));
    }
}
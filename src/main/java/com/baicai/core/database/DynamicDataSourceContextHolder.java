package com.baicai.core.database;

import java.util.ArrayList;
import java.util.List;

public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static List<String> dataSourceIds = new ArrayList<>();
    public static final String DATA_SOURCE_1 = "defaultDataSource";  
    public static final String DATA_SOURCE_2 = "DataSource2"; 
    
    public enum DS {DATA_SOURCE_1,DATA_SOURCE_2};
    	
    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return  contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}

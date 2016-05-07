package com.baicai.core;

import com.baicai.util.PropertiesTool;

public interface Constant {
	public static final String CHARSET = "UTF-8";
    public static final int CONCURRENT_CAPACITY_SIZE = 500;
    public static final int LOCKER_SLEEP_UNIT_TIME = 20;

    public static final int BUFFER_BYTE_SIZE = 8192;
    public static final String DOT=".";
    public static final String NULL = "null";
    public static final String SPACE = " ";
    public static final String QUOTE = "\"";
    public static int ioBufferSize = 16384;
    
    public static final String USER_SALT=PropertiesTool.get("system", "USER_SALT");
}

/**
 * Created on 2012-9-7, DebugLog.java
 *
 * @Author: XuGuobiao
 * @Comment:
 */
package com.everlast.qtt.manager.common;


public class DebugLog {

    public static final boolean isDebugEnable = true;
    public static final String TAG = "BeautyShow";

    public static void log(String logString) {
        if (isDebugEnable) {
            System.out.println(logString);
        }
    }
    
     public static void e(String logString) {
        if (isDebugEnable) {
            System.err.println(logString);
        }
    }

}

package leifu.mvc.utils;

import android.util.Log;

import java.util.HashMap;

/**
 * 创建人: 雷富
 * 创建时间: 2018/1/29 16:00
 * 描述:打印日志类,可打印超过2048长度的日志
 */
public class Logger {
    private static boolean isShowLog = true;
    static int p = 2048;

    public static void error(String tag, String msg) {
        if (isShowLog) {
            long length = msg.length();
            if (length < p || length == p)
                Log.e(tag, msg);
            else {
                while (msg.length() > p) {
                    String logContent = msg.substring(0, p);
                    msg = msg.replace(logContent, "");
                    Log.e(tag, logContent);
                }
                Log.e(tag, msg);
            }
        }
    }

    public static void map(HashMap<String, String> map) {
        if (isShowLog) {
            for (String s : map.keySet()) {
                Logger.gson(s + "  --> " + map.get(s));
            }
        }
    }

    public static void e(String msg) {
        if (isShowLog) {
            long length = msg.length();
            if (length < p || length == p)
                Log.e("error", msg);
            else {
                while (msg.length() > p) {
                    String logContent = msg.substring(0, p);
                    msg = msg.replace(logContent, "");
                    Log.e("error", logContent);
                }
                Log.e("error", msg);
            }
        }
    }

    public static void a(String msg) {
        if (isShowLog) {
            long length = msg.length();
            if (length < p || length == p)
                Log.e("aaa", msg);
            else {
                while (msg.length() > p) {
                    String logContent = msg.substring(0, p);
                    msg = msg.replace(logContent, "");
                    Log.e("aaa", logContent);
                }
                Log.e("aaa", msg);
            }
        }
    }

    public static void gson(String msg) {
        if (isShowLog) {
            long length = msg.length();
            if (length < p || length == p)
                Log.e("gson", msg);
            else {
                while (msg.length() > p) {
                    String logContent = msg.substring(0, p);
                    msg = msg.replace(logContent, "");
                    Log.e("gson", logContent);
                }
                Log.e("gson", msg);
            }
        }
    }

    public static void printCallStatck(Throwable e) {
        StackTraceElement[] stackElements = e.getStackTrace();//通过Throwable获得堆栈信息
        if (stackElements != null) {
            for (int i = 0; i < stackElements.length; i++) {
                Logger.a(stackElements[i].getClassName() + "/t");
                //打印classname
                Logger.a(stackElements[i].getFileName() + "/t");
                //打印filename
                Logger.a(stackElements[i].getLineNumber() + "/t");
                //打印linenumber
                Logger.a(stackElements[i].getMethodName());
            }
        }
    }
}

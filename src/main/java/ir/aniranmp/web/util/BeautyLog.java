package ir.aniranmp.web.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class BeautyLog {


    public static void println() {
        beautyItLn("");
    }

    public static void println(boolean x) {
        beautyItLn(x + "");
    }

    public static void println(char x) {
        beautyItLn(x + "");
    }

    public static void println(int x) {
        beautyItLn(x + "");
    }

    public static void println(long x) {
        beautyItLn(x + "");
    }

    public static void println(float x) {
        beautyItLn(x + "");
    }

    public static void println(double x) {
        beautyItLn(x + "");
    }

    public static void println(char[] x) {
        beautyItLn(Arrays.toString(x) + "");
    }

    public static void println(String x) {
        beautyItLn(x + "");
    }

    public static void println(Object x) {
        beautyItLn(x.toString() + "");
    }

    private static void beautyItLn(String message) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        StackTraceElement element = elements[3];
        Logger logger = LoggerFactory.getLogger(element.getClassName());

        logger.info(element.getMethodName() + "(" + element.getClassName().substring(element.getClassName().lastIndexOf('.') + 1) + ".java:" + element.getLineNumber() + ") : " + message);

    }
}
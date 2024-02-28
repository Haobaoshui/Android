package xyz.hbs.huarongdao.utility;

import java.util.Date;

/**
 * 输出调试信息
 */
public class DebugDump {
    public static void printDebugInfo(){
        System.out.println(getTraceInfo());
    }
    public static void printDebugInfo(String val){
        System.out.println(getTraceInfo(val));
    }
    public static void printDebugInfo(Object object){
        System.out.println(getTraceInfo(object));
    }
    public static void printDebugInfo(String tag, String val){
        System.out.println(getTraceInfo(tag,val));
    }
    private static String getTraceInfo() {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        int stacksLen = stacks.length;
        sb.append("class: ").append(stacks[2].getClassName())
                .append(";\n\tmethod: ").append(stacks[2].getMethodName()+"()")
                .append(";\n\tline: ").append(stacks[2].getLineNumber())
                .append(";\n\ttime: ").append(new Date());
        return sb.toString();
    }

    private static String getTraceInfo(String val) {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        int stacksLen = stacks.length;
        sb.append(val+"\n\tclass: ").append(stacks[2].getClassName())
                .append(";\n\tmethod: ").append(stacks[2].getMethodName()+"()")
                .append(";\n\tline: ").append(stacks[2].getLineNumber())
                .append(";\n\ttime: ").append(new Date());
        return sb.toString();
    }

    private static String getTraceInfo(Object object) {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        int stacksLen = stacks.length;
        String stringObj=null;
        if(object==null)stringObj="null";
        else stringObj=object.toString();
        sb.append(stringObj+"\n\tclass: ").append(stacks[2].getClassName())
                .append(";\n\tmethod: ").append(stacks[2].getMethodName()+"()")
                .append(";\n\tline: ").append(stacks[2].getLineNumber())
                .append(";\n\ttime: ").append(new Date());
        return sb.toString();
    }



    private static String getTraceInfo(String tag, String val) {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        int stacksLen = stacks.length;
        sb.append(tag+":"+val+"\n\tclass: ").append(stacks[2].getClassName())
                .append(";\n\tmethod: ").append(stacks[2].getMethodName()+"()")
                .append(";\n\tline: ").append(stacks[2].getLineNumber())
                .append(";\n\ttime: ").append(new Date());
        return sb.toString();
    }




    private static String getLineInfo() {
        StackTraceElement ste = new Throwable().getStackTrace()[2];
        return ste.getFileName() + ": Line " + ste.getLineNumber();
    }
}

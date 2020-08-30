package com.ningwenqiang.glory.toollibrary.log;

import android.text.TextUtils;

import org.w3c.dom.Text;

import java.util.Date;

/**
 * 注意这里的
 * 日志总控制台
 */
public class L {

    public  static final  int ConsoleType = 1;
    public  static final  int FileType = 2;
    public  static final  int ViewType = 4;

    public static final String LogName="nwq";
    public static final Date LogDate= new Date("2020/9/30");//("2020/8/30");

    //用来控制日志输出类型
    public static int Type =ConsoleType;

    /**
     * @param content
     * @param methodName
     * @param className
     * @param userName
     * @param dateString
     */
    public static void d(String content,String methodName,String className,String userName,String dateString) {
        if(!TextUtils.isEmpty(LogName) && !LogName.equals(userName))
            return;
        if(!TextUtils.isEmpty(dateString))
        {
            Date date  =new Date(dateString);
            if(date.before(LogDate))
                return;
        }
        if((Type & ConsoleType)==ConsoleType){
            ConsoleLog.d(className,methodName+": "+content);
        }
        if((Type & FileType)==FileType){
            FileLog.d(className,methodName+": "+content);
        }
        if((Type & ViewType)==ViewType){
            ViewLog.d(className,methodName+": "+content);
        }
    }

    /**
     * @param content
     * @param methodName
     * @param className
     * @param userName
     * @param dateString
     */
    public static void i(String content,String methodName,String className,String userName,String dateString) {
        if(!TextUtils.isEmpty(LogName) && !LogName.equals(userName))
            return;
        if(!TextUtils.isEmpty(dateString))
        {
            Date date  =new Date(dateString);
            if(date.before(LogDate))
                return;
        }
        if((Type & ConsoleType)==ConsoleType){
            ConsoleLog.i(className,methodName+": "+content);
        }
        if((Type & FileType)==FileType){
            FileLog.i(className,methodName+": "+content);
        }
        if((Type & ViewType)==ViewType){
            ViewLog.i(className,methodName+": "+content);
        }
    }


    /**
     * @param content
     * @param methodName
     * @param className
     * @param userName
     * @param dateString
     */
    public static void e(String content,String methodName,String className,String userName,String dateString) {
        if(!TextUtils.isEmpty(LogName) && !LogName.equals(userName))
            return;
        if(!TextUtils.isEmpty(dateString))
        {
            Date date  =new Date(dateString);
            if(date.before(LogDate))
                return;
        }

        if((Type & ConsoleType)==ConsoleType){
            ConsoleLog.e(className,methodName+": "+content);
        }
        if((Type & FileType)==FileType){
            FileLog.e(className,methodName+": "+content);
        }
        if((Type & ViewType)==ViewType){
            ViewLog.e(className,methodName+": "+content);
        }
    }


}

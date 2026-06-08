package com.ems.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @program: ems-vue3
 * Comentario en espanol
 * @author: starao
 * @create: 2023-07-04 21:21
 **/
public class ExceptionUtil {

    public static String getStackTraceInfo(Exception e) {

        StringWriter sw = null;
        PrintWriter pw = null;

        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);// Imprime el stack trace en el writer
            pw.flush();
            sw.flush();

            return sw.toString();
        } catch (Exception ex) {

            return "Ocurrio un error";
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }

    }
}

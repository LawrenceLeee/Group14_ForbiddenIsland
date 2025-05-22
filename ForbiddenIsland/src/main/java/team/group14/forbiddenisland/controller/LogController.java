package team.group14.forbiddenisland.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogController {

        public static final SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        public static final Date date = new Date(System.currentTimeMillis());

        public static void printLog (String state, String funcName) {
            System.out.println("print log: " + formatter.format(date) + "| " + state + "| invoke:" + funcName);
        }
}

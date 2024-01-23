package org.klozevitz.classwork.servlets.task5;

import jakarta.servlet.http.HttpServletRequest;
import org.klozevitz.classwork.model.Notepad;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

public class Util {
    public static String completeQuery(HttpServletRequest request){
        StringBuilder builder = new StringBuilder("UPDATE note SET ");
        StringBuilder fields = new StringBuilder();
        StringBuilder values  = new StringBuilder();
        Arrays.stream(Notepad.class.getDeclaredFields()).forEach(field ->{

            if(!request.getParameter(field.getName()).equals("") && !field.getName().equals("id")){
                System.out.println(request.getParameter(field.getName()));
                fields.append(field.getName()).append(", ");
                if(field.getType().equals(String.class)){
                    values.append("'").append(request.getParameter(field.getName())).append("', ");
                }
                else {
                    values.append(request.getParameter(field.getName())).append(", ");
                }
            }

        } );
        fields.replace(fields.length()-2, fields.length(), "");
        values.replace(values.length() - 2 , values.length(), "");
        return builder.append(" (").append(fields).append(") = (")
                .append(values).append(") where id =").append(request.getParameter("id")).toString();

    }




}

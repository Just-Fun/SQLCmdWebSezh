package ua.com.juja.sqlcmd.Help;

import java.lang.reflect.Field;

/**
 * Created by serzh on 6/23/16.
 */
public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        PrivateObject object = new PrivateObject("Java!!!");

        Field field = PrivateObject.class.getDeclaredField("privateString");

        field.setAccessible(true);

        String fieldValue = (String)field.get(object);
        System.out.println(fieldValue);
    }
}

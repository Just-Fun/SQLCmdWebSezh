package ua.com.juja.sqlcmd.Help;

import java.lang.reflect.Field;

/**
 * Created by serzh on 6/24/16.
 */
public class ReflectioFocus {

    public static void main(String args[]) {
        AccessibilityTest test = new AccessibilityTest();

        System.out.println(test.getName());

        try {
            Field f = test.getClass().getDeclaredField("name");
            f.setAccessible(true);
            f.set(test, "AccessibilityTestChanged");
            System.out.println(test.getName());
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}

package app;

import org.eclipse.swt.widgets.Display;

public class ScreenSize {  
	  
    public static int getScreenWidth() {  
        int screenWidth = 0;  
        try {  
            screenWidth = ((int) java.awt.Toolkit.getDefaultToolkit()  
                    .getScreenSize().width);  
        } catch (Exception e) {  
            screenWidth = Display.getCurrent().getActiveShell().getMonitor()  
                    .getBounds().width;  
        }  
        return screenWidth;  
    }  
  
    public static int getScreenHeight() {  
        int screenHeight = 0;  
        try {  
            screenHeight = ((int) java.awt.Toolkit.getDefaultToolkit()  
                    .getScreenSize().height);  
        } catch (Exception e) {  
            screenHeight = Display.getCurrent().getActiveShell().getMonitor()  
                    .getBounds().height;  
        }  
        return screenHeight;  
    }  
  
    /** 
     * 测试 
     */  
    public static void main(String[] args) {  
        System.out.println(getScreenWidth());  
        System.out.println(getScreenHeight());  
    }  
  
}  

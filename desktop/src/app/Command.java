package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
  
public class Command {  
    public static String exeCmd(String commandStr) {  
        BufferedReader br = null; 
        String rs = "error";
        try {  
            Process p = Runtime.getRuntime().exec(commandStr);  
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));  
            String line = null;  
            StringBuilder sb = new StringBuilder();  
            while ((line = br.readLine()) != null) {  
                sb.append(line + "\n");  
            }  
            rs = sb.toString();  
            return rs;
        } catch (Exception e) {  
            e.printStackTrace(); 
            return rs;
        }   
        finally  
        {  
            if (br != null)  
            {  
                try {  
                    br.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
  
    public static void main(String[] args) {  
        String commandStr = "ping www.taobao.com";  
        //String commandStr = "ipconfig";  
        String rs = Command.exeCmd(commandStr);  
        System.out.println(rs);
    }  
}  

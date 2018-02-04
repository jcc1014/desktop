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
  
    public static String upCommand(String ip,String path){
    	//path = "C:/Users/jingcc/Desktop/电子 秤/PLU.txt";
    	String commandStr = "AclasSDKCOnsole.exe -h "+ip+" -t Down -n \""+path+"\" -b PLU";
    	String rs = exeCmd(commandStr);
    	return rs;
    }
    public static String downCommand(String ip,String path){
    	//path = "C:/Users/jingcc/Desktop/电子 秤/PLU.txt";
    	String commandStr = "AclasSDKCOnsole.exe -h "+ip+" -t UP -n \""+path+"\" -b PLU -f Unicode";
    	String rs = exeCmd(commandStr);
    	return rs;
    }
    
    public static void main(String[] args) {  
        String commandStr = "ping www.taobao.com";  
        //String commandStr = "ipconfig";  
        String rs = Command.exeCmd(commandStr);  
        System.out.println(rs);
    }  
}  

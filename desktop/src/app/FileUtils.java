package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FileUtils {

	public static boolean createFile(String path,String filecontent){
        Boolean bool = false;
        File file = new File(path);
        try {
            //如果文件不存在，则创建新的文件
            if(!file.exists()){
                file.createNewFile();
                bool = true;
                System.out.println("success create file,the file is "+path);
                //创建文件成功后，写入内容到文件里
                writeFileContent(path, filecontent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return bool;
    }
	
	 public static boolean writeFileContent(String filepath,String newstr) throws IOException{
	        Boolean bool = false;
	        String filein = newstr+"\r\n";//新写入的行，换行
	        String temp  = "";
	        
	        FileInputStream fis = null;
	        InputStreamReader isr = null;
	        BufferedReader br = null;
	        FileOutputStream fos  = null;
	        PrintWriter pw = null;
	        try {
	            File file = new File(filepath);//文件路径(包括文件名称)
	            //将文件读入输入流
	            fis = new FileInputStream(file);
	            isr = new InputStreamReader(fis);
	            br = new BufferedReader(isr);
	            StringBuffer buffer = new StringBuffer();
	            
	            //文件原有内容
	            for(int i=0;(temp =br.readLine())!=null;i++){
	                buffer.append(temp);
	                // 行与行之间的分隔符 相当于“\n”
	                buffer = buffer.append("\n");
	            }
	            buffer.append(filein);
	            
	            fos = new FileOutputStream(file);
	            pw = new PrintWriter(fos);
	            pw.write(buffer.toString().toCharArray());
	            pw.flush();
	            bool = true;
	        } catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	        }finally {
	            //不要忘记关闭
	            if (pw != null) {
	                pw.close();
	            }
	            if (fos != null) {
	                fos.close();
	            }
	            if (br != null) {
	                br.close();
	            }
	            if (isr != null) {
	                isr.close();
	            }
	            if (fis != null) {
	                fis.close();
	            }
	        }
	        return bool;
	    }
	    
	    /**
	     * 删除文件
	     * @param fileName 文件名称
	     * @return
	     */
	    public static boolean delFile(String path){
	        Boolean bool = false;
	        File file  = new File(path);
	        try {
	            if(file.exists()){
	                file.delete();
	                bool = true;
	            }
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return bool;
	    }
}

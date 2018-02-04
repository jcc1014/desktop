package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtils
{

  public static Connection conn = null;

  public static synchronized Connection getnterfaceDB()
  {
    try
    {
      if(conn != null && !conn.isClosed())  
             return conn;
      //String className = "com.mysql.jdbc.Driver";
      String className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
      String url = "jdbc:sqlserver://192.168.22.131:1433;database=sjgl";
      String userName = "sa";
      String password = "3edc$RFV";
      Class.forName(className);
      conn = DriverManager.getConnection(url, userName, password);
      if(conn == null)
    	  throw new Exception("获取数据库连接失败！");
   	    
    }
    catch (Exception e) {
      System.out.println("获取数据库连接失败!错误信息:[" + e.getMessage() + "]");
      e.printStackTrace();
    }
    return conn;
  }
  

  public static void closeConnection(Connection conn)
  {
    if (conn != null)
      try
      {
        conn.close();
      }
     catch (SQLException e) {
    	 System.out.println("关闭数据库连接失败!错误信息:[" + e.getMessage() + "]");
       e.printStackTrace();
      }
  }
}


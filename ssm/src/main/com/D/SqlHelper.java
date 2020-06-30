package D;

import java.sql.*;


public class SqlHelper {
    public static Connection  getCoonection()
    {
        String tburl="jdbc:sqlserver://localhost:1433;database=database_zhanlinfei";//数据源
        String name="sa";
        String password="lfcnl";
        try
        {
            //数据库引擎
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(tburl,name,password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.print("连接失败");
        }
        return null;
    }
    public static ResultSet executeQuery(String SQL)
    {
        Statement stmt=null;
        try {
            Connection connection=getCoonection();//连接数据库
             stmt  = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            return  rs;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    //g关闭
    public static void Close(Statement statement, Connection connection) {
        if (statement != null) {
            try {
                statement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
    public static boolean  executeUpdate(String SQL) {
        try {
            Connection conn = getCoonection();
            System.out.println("---------------连接数据库成功");

            Statement stmt = conn.createStatement();
            int result = stmt.executeUpdate(SQL);
            if (result > 0)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("----------------更新失败");
        }
        return false;
    }
}

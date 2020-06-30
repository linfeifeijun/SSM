package Service;

import D.SqlHelper;
import Tb.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public boolean QueryUser(User user)
    {
     String sql = "select * from tb_user where username='"+user.getUsername()+"'and password='"+user.getPassword()+"'";
        ResultSet rs = SqlHelper.executeQuery(sql);
       try{
           if (rs.next())
           {
               return true;
           }
       }catch (SQLException e)
       {
           e.printStackTrace();
       }
       return false;
    }
}

package Controller;

import Tb.User;
import Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = new User();
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        UserService userService = new UserService();
        if(userService.QueryUser(user))
        {
            Cookie cookien = new Cookie("username", user.getUsername());
            cookien.setMaxAge(60*60*24*30);
            response.addCookie(cookien);
            Cookie cookiep= new Cookie("password", user.getPassword());
            cookiep.setMaxAge(60*60*24*30);
            response.addCookie(cookiep);
            response.sendRedirect("page/index.html");

        }

        else{
            out.println("错误的用户名和密码");
            response.sendRedirect("login.html");
        }

    }
}

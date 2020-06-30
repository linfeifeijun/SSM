package Controller;
import Service.NewsService;
import Tb.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "AddNewsServlet")
public class AddNewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        request.setCharacterEncoding("utf-8");
        News news = new News();
        news.setId(Integer.valueOf( request.getParameter("id")));
        news.setTitle(request.getParameter("title"));
        news.setContent(request.getParameter("content"));
        news.setAuthor(request.getParameter("author"));
        news.setDate(Date.valueOf(request.getParameter("date")));
        NewsService newsService=new NewsService();
        System.out.println(newsService.AddNews(news));
        request.getRequestDispatcher("ShowNewsListServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {


    }
}

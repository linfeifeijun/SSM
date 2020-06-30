package com.Controller;

import com.Entity.News;
import com.Service.NewsService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class NewsController {
    @RequestMapping("/Show")
    protected void ShowNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        NewsService newsService = new NewsService();
            List<News> listNews  = newsService.QueryNews();
            request.setAttribute("listNews",listNews);
            request.getRequestDispatcher("ShowNewsList.jsp").forward(request,response);
    }
    @RequestMapping("/Add")
    protected void AddNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.sendRedirect("AddNews.jsp");
        Date date= null;
        Map parameterMap = request.getParameterMap();
        News news = new News();
        BeanUtils.populate(news,parameterMap);
        news.setDate(date);
        if(news.getId() != 0){
            newsService.updateNews(news);
            response.sendRedirect( request.getContextPath() + "/show");
            return;
        }
        newsService.saveNews(news);
        response.sendRedirect(request.getContextPath() + "/show");

    }

    @RequestMapping("/Delete")
    protected void DeleteNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id=Integer.valueOf( request.getParameter("id"));
        NewsService newsService=new NewsService();
        newsService.DeleteNews(id);
        request.getRequestDispatcher("ShowNewsListServlet").forward(request,response);
    }
    @RequestMapping("/View")
    protected void ViewNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        int id=Integer.valueOf(request.getParameter("id"));
        NewsService newsService=new NewsService();
            News news=newsService.GetNews(id);
            request.setAttribute("news",news);
            request.getRequestDispatcher("ViewNews.jsp").forward(request,response);
    }
    @RequestMapping("/Edit")
    protected void EditNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        int id=Integer.valueOf( request.getParameter("id"));
        System.out.println("newsID:"+id);
        NewsService service= new NewsService();
            News news=service.GetNews(id);
            request.setAttribute("news", news);
            request.getRequestDispatcher("EditNews.jsp").forward(request,response);
    }
}

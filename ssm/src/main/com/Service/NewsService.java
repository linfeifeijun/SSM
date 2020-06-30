package Service;
import D.SqlHelper;
import Tb.News;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class NewsService {
    public void AddNews(){}
    public void DeleteNews(){}
    public void UpdateNews(){}
    public List<News> QueryNews() throws SQLException {
        String sql="Select * from News";
        ResultSet rs=SqlHelper.executeQuery(sql);;
        List<News> listNews=new ArrayList<News>();
        while (rs.next()){
            News news = new News();
            news.setId(rs.getInt("id"));
            news.setTitle(rs.getString("title"));
            news.setContent(rs.getString("content"));
            news.setAuthor(rs.getString("author"));
            news.setDate(rs.getDate("date"));
            listNews.add(news);
        }
        return listNews;
    }
    public boolean AddNews(News news){
        String sql="Insert into tb_News (id,title,content,author,date) "
                +"values ('" + news.getId() +"','"
                + news.getTitle() +"','"
                + news.getContent() +"','"
                + news.getAuthor() +"','"
                + String.valueOf(news.getDate()) +"');";
        System.out.println(sql);
        return SqlHelper.executeUpdate(sql);

    }

    public boolean DeleteNews(int id){
        String sql="Delete from tb_News where newsid="+id;
        System.out.println(sql);
        boolean result= SqlHelper.executeUpdate(sql);
        return result;
    }

    public boolean UpdateNews(News news ){
        String sql="Update tb_News set id ='" + news.getId() + "' ,title = '"
                + news.getTitle() + "',content = '"
                + news.getContent() + "',author = '"
                + news.getAuthor() + "' where id = " + news.getId()+"";
        System.out.println(sql);
        boolean result= SqlHelper.executeUpdate(sql);
        return result;
    }

    public News GetNews(int id) throws SQLException {
        String sql="Select * from tb_News where id="+id;
        ResultSet rs;
        rs= SqlHelper.executeQuery(sql);
        News  news=new News();
        if (rs.next()){
            news.setId(rs.getInt("id"));
            news.setTitle(rs.getString("title"));
            news.setContent(rs.getString("content"));
            news.setAuthor(rs.getString("author"));
            news.setDate(rs.getDate("date"));
        }
        return news;

    }
}

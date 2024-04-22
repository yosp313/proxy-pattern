package com.youssef_ayman.demo1.servlets;

import com.youssef_ayman.demo1.interfaces.BlogPostDAO;
import com.youssef_ayman.demo1.models.BlogPost;
import com.youssef_ayman.demo1.services.CachedBlogPost;
import com.youssef_ayman.demo1.services.MySQLBlogPostDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/blogs")
public class BlogsServlet extends HttpServlet {
    private static BlogPostDAO cacheDao;
    private static MySQLBlogPostDAO dao;

    public BlogsServlet(){
        this.cacheDao = new CachedBlogPost(new MySQLBlogPostDAO());
        this.dao = new MySQLBlogPostDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        List<BlogPost> blogs;

        try {
            blogs = cacheDao.getBlogs();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (blogs.isEmpty()){
            req.setAttribute("blogError", "No blogs found");
        }else {
            req.setAttribute("blogs", blogs);
        }


        req.getRequestDispatcher("/blogs.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        try{
            dao.postBlog(title, content);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        res.sendRedirect("/blogs");
    }
}

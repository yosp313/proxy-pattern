package com.youssef_ayman.demo1.servlets;

import com.youssef_ayman.demo1.interfaces.BlogPostDAO;
import com.youssef_ayman.demo1.models.BlogPost;
import com.youssef_ayman.demo1.services.CashedBlogPost;
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
    private static BlogPostDAO dao;

    public BlogsServlet(){
        this.dao = new CashedBlogPost(new MySQLBlogPostDAO());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        List<BlogPost> blogs;

        try {
            blogs = dao.getBlogs();
            System.out.println(blogs);
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
}

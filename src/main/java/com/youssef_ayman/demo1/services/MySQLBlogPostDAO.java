package com.youssef_ayman.demo1.services;



import com.youssef_ayman.demo1.interfaces.BlogPostDAO;
import com.youssef_ayman.demo1.models.BlogPost;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLBlogPostDAO implements BlogPostDAO {
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/proxy_pattern";
    static final String USERNAME = "root";
    static final String PASSWORD = "yoyo3133";

    @Override
    public List<BlogPost> getBlogs() throws SQLException {
        List<BlogPost> blogs = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM blogs");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String content = resultSet.getString("content");
            blogs.add(new BlogPost(id, title, content));
        }
        resultSet.close();
        statement.close();
        connection.close();

        return blogs;
    }

    public void postBlog(String title, String content) throws SQLException {
        BlogPost blog = new BlogPost(3,title, content);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        var sql = "INSERT INTO blogs (id, title, content) VALUES (?, ?, ?)";
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, String.valueOf(blog.getId()));
        statement.setString(2, blog.getTitle());
        statement.setString(3, blog.getContent());

        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}
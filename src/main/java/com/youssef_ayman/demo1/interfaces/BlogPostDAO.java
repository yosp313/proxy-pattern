package com.youssef_ayman.demo1.interfaces;

import com.youssef_ayman.demo1.models.BlogPost;

import java.sql.SQLException;
import java.util.List;

public interface BlogPostDAO {
    List<BlogPost> getBlogs() throws SQLException;
}
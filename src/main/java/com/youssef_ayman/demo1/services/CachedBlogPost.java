package com.youssef_ayman.demo1.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.youssef_ayman.demo1.interfaces.BlogPostDAO;
import com.youssef_ayman.demo1.models.BlogPost;

public class CachedBlogPost implements BlogPostDAO {
    BlogPostDAO blogService;
    Map<Integer, BlogPost> blogCache;

    public CachedBlogPost(BlogPostDAO blogService) {
        this.blogService = blogService;
        this.blogCache = new HashMap<>();
    }

    @Override
    public List<BlogPost> getBlogs() {

        if (blogCache.isEmpty()) {
            try {
                List<BlogPost> blogs = blogService.getBlogs();
                blogCache.putAll(blogs.stream().collect(Collectors.toMap(BlogPost::getId, blog -> blog)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new ArrayList<>(blogCache.values());
    }

}

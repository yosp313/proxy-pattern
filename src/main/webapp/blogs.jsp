<%@ page import="com.youssef_ayman.demo1.models.BlogPost" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>Blogs</title>
    </head>
    <body>
        <h1>Blogs</h1>

        <h2>${blogEroor}</h2>

        <ul>
            <% List<BlogPost> blogs = (List<BlogPost>) request.getAttribute("blogs"); %>
            <% for (BlogPost blog : blogs) {%>
                <li>
                    <h2><%= blog.getTitle() %></h2>
                    <p><%= blog.getContent() %></p>
                </li>
            <%}%>
        </ul>
    </body>
</html>


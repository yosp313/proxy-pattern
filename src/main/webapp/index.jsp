<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<a href="blogs">Blogs page</a>
<form action="/blogs" method="post">
    <label for="title-input">Title</label>
    <input id="title-input" name="title" placeholder="awesome sauce">
    <label for="content-input">Content</label>
    <input id="content-input" name="content" placeholder="awesome sauce">

    <button type="submit">Submit</button>
</form>
</body>
</html>
<%@ page import="java.util.*" %>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Beer</title>
</head>
<body>
<h1 align="center">Random pictures of kittens</h1>
<p>
    <% List<String> styles = (List<String>)request.getAttribute("styles");
        for (String photo : styles){
            out.print("<br><a><img src=\"" + photo +"\"" + "/></a>");
        }
    %>
</p>
</body>
</html>
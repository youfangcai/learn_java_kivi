<%@ page import="java.util.*" %>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Beer</title>
</head>
<body>
<h1 align="center">Beer Recommendations JSP</h1>
<p>
    <% List<String> styles = (List<String>)request.getAttribute("styles");
        for (String brand : styles){
            out.print("<br>try: " + brand);
        }
    %>
</p>
</body>
</html>
<%@ page import="java.util.*" %>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Beer</title>
</head>
<body>
<h1 align="center">The friends who share yours hobby of
<%= request.getParameter("hobby")%>:are:<br>
</h1>
<p>
    <%! int count = 0;%>
    <%! public int getCount(int count){
        return ++count;
    }
    %>
    <%="访问次数:"%>
    <%=count=getCount(count)%>
    <br>
</p>
<p>
    <% ArrayList al = (ArrayList) request.getAttribute("hobby");%>
    <% Iterator it = al.iterator();
    while (it.hasNext()){
    %>
    <%=it.next()%>
    <br>
    <%}%>
</p>
</body>
</html>
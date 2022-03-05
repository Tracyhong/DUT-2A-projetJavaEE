<%@ page import="mediatek2022.Utilisateur" %>
<%@ page import="mediatek2022.Document" %>
<%@ page import="fr.projetjavaee.projetjavaee.persistance.Doc" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: tracy
  Date: 04/03/2022
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Abonnee Retour</title>
    <link rel="stylesheet" href="./style/style.css">
    <% Utilisateur user = (Utilisateur)request.getSession().getAttribute("user");
      /*  response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");*/
        ArrayList<Document> docs = (ArrayList<Document>)request.getSession().getAttribute("listDoc");
        StringBuilder sb = new StringBuilder();
        sb.append("<select class=\"select\" name=\"document\">");
        for(Document document:docs){
            Doc doc= (Doc) document;
            sb.append("<option value=\"").append(doc.getId()).append("\">").append(document.toString()).append("</option>");
        }
        sb.append("</select>");
        /*response.getWriter().println(sb);*/ //ecrit du html direct pas la où on veut le placer
    %>

</head>
<body>
<nav class="navbar background">
    <ul class="nav-list">
        <div class="logo"> Mediatek </div>
        <li><a href="#">Accueil</a></li>
    </ul>

    <a class="btn" href="/projetJavaEE_war_exploded/LogoutServlet">Déconnexion</a>
</nav>

<header>
    <h1>Rendre un document ? </h1>

</header>
<div class="container">
    <h3>Bonjour <%= user.name()%></h3>

    <p style="color: blue">${success}</p>
    </br></br>
    <form action="/projetJavaEE_war_exploded/AboRetourServlet" method="post">

        <%= sb%>
        </br>
        <input type="submit" value="Retourner" class="log">
    </form>
    </br></br>
    <a class="btn" href="/projetJavaEE_war_exploded/AboServlet">Retour</a>

</div>
</body>
</html>

<%@ page import="fr.projetjavaee.projetjavaee.persistance.User" %>
<%--
  Created by IntelliJ IDEA.
  User: tracy
  Date: 03/03/2022
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Biblio</title>
    <link rel="stylesheet" href="./style/style.css">
    <% User user = (User)request.getSession().getAttribute("user");%>
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
        <h1>Bienvenue sur la page Bibliothécaire ! </h1>
    </header>
    <div class="container">
        <h3>Bonjour <%= user.name()%></h3>
        <p style="color: blue">${success}</p>
        <form action="/projetJavaEE_war_exploded/BiblioServlet" method="post">

            <%--@declare id="titredoc"--%>
            <%--@declare id="auteurdoc"--%>
            <%--@declare id="typedoc"--%>
                <div for="typeDoc" class="text">Type de document: </div>
                <div class="radioBtnDoc">
                    <input style="margin-left: 2em" type="radio" name="typeDoc" value="1" required> DVD</br>
                    <input style="margin-left: 2em" type="radio" name="typeDoc" value="2"> Livre</br>
                    <input style="margin-left: 2em" type="radio" name="typeDoc" value="3">  CD
                </div>
                </br></br>

            <label for="titreDoc">Titre:</label>
            <input type="text" name="titreDoc" required/>
            </br>

            <label for="auteurDoc">Auteur:</label>
            <input type="text" name="auteurDoc" required/>
            </br></br>

            <input type="submit" value="Ajouter" class="log">
        </form>
    </div>

</body>
</html>

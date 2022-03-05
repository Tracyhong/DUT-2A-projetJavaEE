<%@ page import="mediatek2022.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Abonne</title>
    <link rel="stylesheet" href="./style/style.css">
    <% Utilisateur user = (Utilisateur)request.getSession().getAttribute("user");%>
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
        <h1>Bienvenue sur la page Abonnée !</h1>
    </header>
    <div class="container">
        <h3>Bonjour <%= user.name()%></h3>
        </br></br></br>
        <a class="btn" href="/projetJavaEE_war_exploded/AboEmpruntServlet">Emprunter un document</a>
    </br></br></br>
        <a class="btn" href="/projetJavaEE_war_exploded/AboRetourServlet">Retourner un document</a>
    </div>

</body>
</html>

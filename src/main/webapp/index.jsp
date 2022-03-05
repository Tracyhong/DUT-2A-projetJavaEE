<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="style/accueil.css">
        <title>Mediatek</title>
    </head>
    <body>
        <%--@declare id="identifiant"--%>
        <%--@declare id="mdp"--%>
        <header>
            <h1>Bienvenue dans notre Mediatek !</h1>

        </header>
         <div class="login-form">
             <h3>CONNEXION</h3>
             <form action="/projetJavaEE_war_exploded/LoginServlet" method="post">
                 <br>
                 <label for="identifiant">Identifiant</label>
                 <input class="login-input" type="text" id="identifiant" name="identifiant" required>
                 <br>
                 <label for="mdp">Mot de passe</label>
                 <input class="login-input" type="password" id="mdp" name="mdp" required>
                 <br></br>
                 <input type="submit" id="log">
                 <br>
                 <p style="color: red">${error}</p>
             </form>
         </div>
    </body>
</html>


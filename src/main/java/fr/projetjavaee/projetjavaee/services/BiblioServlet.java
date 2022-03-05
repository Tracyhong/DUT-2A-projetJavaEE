package fr.projetjavaee.projetjavaee.services;

import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "BiblioServlet", value = "/BiblioServlet")
public class BiblioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession(true);
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user==null||!user.isBibliothecaire()){
            response.sendRedirect("/projetJavaEE_war_exploded/LoginServlet");
        }else{
            getServletContext().getRequestDispatcher("/WEB-INF/Bibliothecaire.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession(true);
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user==null||!user.isBibliothecaire()){
            response.sendRedirect("/projetJavaEE_war_exploded/LoginServlet");
        }

        int type = Integer.parseInt(request.getParameter("typeDoc"));
        String titre = request.getParameter("titreDoc");
        String auteur = request.getParameter("auteurDoc");

        Mediatheque.getInstance().ajoutDocument(type,titre,auteur);
        request.setAttribute("success","le document est ajouté!");
        getServletContext().getRequestDispatcher("/WEB-INF/Bibliothecaire.jsp").forward(request,response);

    }


}

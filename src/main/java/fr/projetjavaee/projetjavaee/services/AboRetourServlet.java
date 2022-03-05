package fr.projetjavaee.projetjavaee.services;

import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AboRetourServlet", value = "/AboRetourServlet")
public class AboRetourServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession(true);
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user==null||user.isBibliothecaire()){
            response.sendRedirect("/projetJavaEE_war_exploded/LoginServlet");
        }else{
            Utilisateur utilisateur=Mediatheque.getInstance().getUser(user.name(), (String) user.data()[2]);
            session.setAttribute("user",utilisateur);
            List<Document> listDoc= (List<Document>) utilisateur.data()[1];
            session.setAttribute("listDoc",listDoc);
            getServletContext().getRequestDispatcher("/WEB-INF/AboRetour.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession(true);
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user==null||user.isBibliothecaire()){
            response.sendRedirect("/projetJavaEE_war_exploded/LoginServlet");
        }
        String idDoc = request.getParameter("document");

        Document doc = Mediatheque.getInstance().getDocument(Integer.parseInt(idDoc));
        try {
            doc.retour();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("success","le document est retourné!");
        response.sendRedirect("/projetJavaEE_war_exploded/AboRetourServlet");
    }
}

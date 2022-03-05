package fr.projetjavaee.projetjavaee.services;

import fr.projetjavaee.projetjavaee.persistance.Doc;
import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AboEmpruntServlet", value = "/AboEmpruntServlet")
public class AboEmpruntServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession(true);

        Utilisateur user = (Utilisateur) session.getAttribute("user");

        if (user==null||user.isBibliothecaire()){
            response.sendRedirect("/projetJavaEE_war_exploded/LoginServlet");
        }else{
            List<Document> listDoc= Mediatheque.getInstance().tousLesDocumentsDisponibles();
            session.setAttribute("listDoc",listDoc);
            getServletContext().getRequestDispatcher("/WEB-INF/AboEmprunt.jsp").forward(request,response);
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
            doc.emprunt(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("success","le document est emprunté!");
        response.sendRedirect("/projetJavaEE_war_exploded/AboEmpruntServlet");
    }
}

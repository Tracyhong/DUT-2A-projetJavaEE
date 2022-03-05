package fr.projetjavaee.projetjavaee.services;

import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String username = request.getParameter("identifiant");
        String password = request.getParameter("mdp");
        System.out.println(username+password);

        //dans le listener ? pour instancier mediathequeData
       /* try {
            Class.forName("fr.projetjavaee.projetjavaee.persistance.MediathequeData");
            System.out.println("init persistance mediatheque");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        //
        Utilisateur user = Mediatheque.getInstance().getUser(username, password);
        if (user != null) {
            System.out.println("YES");
            session.setAttribute("user",user);
            if(user.isBibliothecaire())
                getServletContext().getRequestDispatcher("/WEB-INF/Bibliothecaire.jsp").forward(request,response);
            else getServletContext().getRequestDispatcher("/WEB-INF/Abonne.jsp").forward(request,response);

        }else{
            System.out.println("NO");
            request.setAttribute("error", "identifiant ou mdp incorrect");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
        }
    }
}
package iut2.brunetqu_projet_mi4.controller;

import iut2.brunetqu_projet_mi4.data.Etudiant;
import iut2.brunetqu_projet_mi4.data.GestionFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "servletindex", value = "/servletindex")
public class ServletIndex extends HttpServlet {

    public ServletIndex() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Collection<Etudiant> etudiants = GestionFactory.getEtudiants();

        request.setAttribute("etudiants", etudiants);

        loadJSP("/WEB-INF/index.jsp", request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void loadJSP(String url, HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(url);
        rd.forward(request, response);
    }

}

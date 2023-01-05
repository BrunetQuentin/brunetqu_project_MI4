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

/**
 * Servlet implementation class controleur
 */
@WebServlet(name = "servlettraitementdetails", value = "/servlettraitementdetails")
public class ServletTraitementDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTraitementDetails() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Récupérer l'id
        int id = Integer.valueOf(request.getParameter("id"));

        // Récupérer les détails
        Etudiant etudiant = GestionFactory.getEtudiantById(id);
        Integer nbAbsences = GestionFactory.getAbsencesByEtudiantId(id).length;

        // Mettre les détails dans la requête
        request.setAttribute("etudiant", etudiant);
        request.setAttribute("nbAbsences", nbAbsences);

        // Forward vers details.jsp
        loadJSP("/WEB-INF/details.jsp", request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Charge la JSP indiquée en paramètre
     *
     * @param url
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void loadJSP(String url, HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(url);
        rd.forward(request, response);
    }

}

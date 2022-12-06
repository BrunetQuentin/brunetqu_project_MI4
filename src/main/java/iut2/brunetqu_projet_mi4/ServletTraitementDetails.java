package iut2.brunetqu_projet_mi4;

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
        Integer nbAbsences = GestionFactory.getAbsencesByEtudiantId(id);

        // Mettre les détails dans la requête
        request.setAttribute("etudiant", etudiant);
        request.setAttribute("nbAbsences", nbAbsences);

        // Forward vers details.jsp
        loadJSP("/details.jsp", request, response);

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

//		L'interface RequestDispatcher permet de transférer le contrôle à une autre servlet
//		Deux méthodes possibles :
//		- forward() : donne le contrôle à une autre servlet. Annule le flux de sortie de la servlet courante
//		- include() : inclus dynamiquement une autre servlet
//			+ le contrôle est donné à une autre servlet puis revient à la servlet courante (sorte d'appel de fonction). 
//			+ Le flux de sortie n'est pas supprimé et les deux se cumulent

        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(url);
        rd.forward(request, response);
    }

}

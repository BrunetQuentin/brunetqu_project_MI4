package iut2.brunetqu_projet_mi4.controller;

import iut2.brunetqu_projet_mi4.DAO.AbsenceDAO;
import iut2.brunetqu_projet_mi4.DAO.EtudiantDAO;
import iut2.brunetqu_projet_mi4.DAO.GroupeDAO;
import iut2.brunetqu_projet_mi4.DAO.NoteDAO;
import iut2.brunetqu_projet_mi4.data.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

/**
 * @author hb
 */

@SuppressWarnings("serial")
public class Controleur extends HttpServlet {

    private String urlAcceuil;
    //// ETUDIANT ////
    private String urlEtudiants;
    private String urlEtudiant;
    private String urlEditionEtudiant;
    private String urlAjoutEtudiant;

    //// GROUPE ////
    private String urlGroupes;
    private  String urlEditionGroupe;
    private String urlAjoutGroupe;

    //// ABSENCE ////
    private String urlAbsences;
    private String urlAjoutAbsence;
    private String urlEditionAbsence;

    //// NOTE ////
    private String urlNotes;
    private String urlAjoutNote;
    private String urlEditionNote;

    // INIT
    @Override
    public void init() throws ServletException {
        // Récupération des URLs en paramètre du web.xml
        urlAcceuil = getServletConfig().getInitParameter("urlAcceuil");
        //// ETUDIANT ////
        urlEtudiants = getServletConfig().getInitParameter("urlEtudiants");
        urlEtudiant = getServletConfig().getInitParameter("urlEtudiant");
        urlEditionEtudiant = getServletConfig().getInitParameter("urlEditionEtudiant");
        urlAjoutEtudiant = getServletConfig().getInitParameter("urlAjoutEtudiant");
        //// GROUPE ////
        urlGroupes = getServletConfig().getInitParameter("urlGroupes");
        urlEditionGroupe = getServletConfig().getInitParameter("urlEditionGroupe");
        urlAjoutGroupe = getServletConfig().getInitParameter("urlAjoutGroupe");
        //// ABSENCE ////
        urlAbsences = getServletConfig().getInitParameter("urlAbsences");
        urlAjoutAbsence = getServletConfig().getInitParameter("urlAjoutAbsence");
        urlEditionAbsence = getServletConfig().getInitParameter("urlEditionAbsence");
        //// NOTE ////
        urlNotes = getServletConfig().getInitParameter("urlNotes");
        urlAjoutNote = getServletConfig().getInitParameter("urlAjoutNote");
        urlEditionNote = getServletConfig().getInitParameter("urlEditionNote");

        // Création de la factory permettant la création d'EntityManager
        // (gestion des transactions)
        GestionFactory.open();

        ///// INITIALISATION DE LA BD
        // Normalement l'initialisation se fait directement dans la base de données
        if ((GroupeDAO.getAll().size() == 0) && (EtudiantDAO.getAll().size() == 0)) {
            // Creation des groupes
            Groupe MIAM = GroupeDAO.create("miam");
            Groupe SIMO = GroupeDAO.create("SIMO");
            Groupe MESSI = GroupeDAO.create("MESSI");

            // Creation des étudiants
            EtudiantDAO.create("Francis", "Brunet-Manquat", MIAM);
            EtudiantDAO.create("Philippe", "Martin", MIAM);
            EtudiantDAO.create("Mario", "Cortes-Cornax", MIAM);
            EtudiantDAO.create("Françoise", "Coat", SIMO);
            EtudiantDAO.create("Laurent", "Bonnaud", MESSI);
            EtudiantDAO.create("Sébastien", "Bourdon", MESSI);
            EtudiantDAO.create("Mathieu", "Gatumel", SIMO);
        }
    }

    @Override
    public void destroy() {
        super.destroy();

        // Fermeture de la factory
        GestionFactory.close();
    }

    // POST Where there is a form involved
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getPathInfo();

        if (action == null) {
            action = "/etudiants";
        }

        ArrayList<String> actions = new ArrayList<String>();
        Collections.addAll(actions, action.split("/"));
        actions.remove(0);
        String firstAction = actions.get(0);
        actions.remove(0);

        Map<String, String[]> params = request.getParameterMap();
        System.out.println("post: " + firstAction);
        switch(firstAction){
            /////// ETUDIANT //////
            case "editionetudiant":
                editionEtudiant(actions, params);
                response.sendRedirect(request.getContextPath() + "/do/etudiants");
                break;
            case "ajouteretudiant":
                ajoutEtudiant(params);
                response.sendRedirect(request.getContextPath() + "/do/etudiants");
                break;
            /////// GROUPE //////
            case "editiongroupe":
                editionGroupe(actions, params);
                response.sendRedirect(request.getContextPath() + "/do/groupes");
                break;
            case "ajoutergroupe":
                ajoutGroupe(params);
                response.sendRedirect(request.getContextPath() + "/do/groupes");
                break;
            /////// NOTE //////
            case "editionnote":
                editionNote(actions, params);
                response.sendRedirect(request.getContextPath() + "/do/notes");
                break;
            case "ajouternote":
                ajoutNote(params);
                response.sendRedirect(request.getContextPath() + "/do/notes");
                break;
            /////// ABSENCE //////
            case "editionabsence":
                editionAbsence(actions, params);
                response.sendRedirect(request.getContextPath() + "/do/absences");
                break;
            case "ajouterabsence":
                ajoutAbsence(params);
                response.sendRedirect(request.getContextPath() + "/do/absences");
                break;
        }
    }

    // All NON POST request, event the delete
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // On récupère le path
        String action = request.getPathInfo();

        if (action == null) {
            action = "/etudiants";
        }

        ArrayList<String> actions = new ArrayList<String>();
        Collections.addAll(actions, action.split("/"));
        actions.remove(0);
        String firstAction = actions.get(0);
        actions.remove(0);

        request.setAttribute("firstAction", firstAction);
        System.out.println("get: " + firstAction);
        switch(firstAction){
            case "acceuil":
                doAcceuil(request, response);
                break;
                /////// ETUDIANT //////
            case "etudiants":
                doEtudiants(request, response);
                break;
            case "etudiant":
                doEtudiant(request, response, actions);
                break;
            case "editionetudiant":
                doEditionEtudiant(request, response, actions);
                break;
            case "ajouteretudiant":
                doAjoutEtudiant(request, response);
                break;
            case "suppressionetudiant":
                enleverEtudiant(actions);
                response.sendRedirect(request.getContextPath() + "/do/etudiants");
                break;
                /////// GROUPE //////
            case "groupes":
                doGroupes(request, response);
                break;
            case "editiongroupe":
                doEditionGroupe(request, response, actions);
                break;
            case "ajoutergroupe":
                doAjoutGroupe(request, response);
                break;
            case "suppressiongroupe":
                enleverGroupe(actions);
                response.sendRedirect(request.getContextPath() + "/do/groupes");
                break;
                /////// ABSENCE //////
            case "absences":
                doAbsences(request, response);
                break;
            case "ajouterabsence":
                doAjoutAbsence(request, response);
                break;
            case "editionabsence":
                doEditionAbsence(request, response, actions);
                break;
            case "suppressionabsence":
                enleverAbsence(actions);
                response.sendRedirect(request.getContextPath() + "/do/absences");
                break;
            /////// NOTE //////
            case "notes":
                doNotes(request, response);
                break;
            case "ajouternote":
                doAjoutNote(request, response);
                break;
            case "editionnote":
                doEditionNote(request, response, actions);
                break;
            case "suppressionnote":
                enleverNote(actions);
                response.sendRedirect(request.getContextPath() + "/do/notes");
                break;
            default:
                doEtudiants(request, response);
                break;
        }
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
    public void loadJSP(String url, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // L'interface RequestDispatcher permet de transférer le contrôle à une
        // autre servlet
        // Deux méthodes possibles :
        // - forward() : donne le contrôle à une autre servlet. Annule le flux
        // de sortie de la servlet courante
        // - include() : inclus dynamiquement une autre servlet
        // + le contrôle est donné à une autre servlet puis revient à la servlet
        // courante (sorte d'appel de fonction).
        // + Le flux de sortie n'est pas supprimé et les deux se cumulent

        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    public void doAcceuil (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Etudiant> etudiants = EtudiantDAO.getAll();
        List<Groupe> groupes = GroupeDAO.getAll();
        List<Absence> absences = AbsenceDAO.getAll();
        List<Note> notes = NoteDAO.getAll();

        request.setAttribute("etudiants", etudiants);
        request.setAttribute("groupes", groupes);
        request.setAttribute("absences", absences);
        request.setAttribute("notes", notes);

        loadJSP(urlAcceuil, request, response);
    }

    /////////////////////////////////////////////////////ETUDIANT METHODE ////////////////////
    private void doEtudiants(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Collection<Etudiant> etudiants = EtudiantDAO.getAll();

        request.setAttribute("etudiants", etudiants);

        loadJSP("/WEB-INF/etudiants.jsp", request, response);

        loadJSP(urlEtudiants, request, response);
    }

    private void doEtudiant(HttpServletRequest request, HttpServletResponse response, ArrayList<String> parameter)
            throws ServletException, IOException {

        int idEtudiant = Integer.valueOf(parameter.get(0));
        parameter.remove(0);

        Etudiant etudiant = EtudiantDAO.retrieveById(idEtudiant);

        List<Absence> absences = AbsenceDAO.getAbsenceByUserId(idEtudiant);

        request.setAttribute("etudiant", etudiant);

        loadJSP(urlEtudiant, request, response);
    }
    private void doEditionEtudiant(HttpServletRequest request, HttpServletResponse response, ArrayList<String> actions)
            throws ServletException, IOException {

        int idEtudiant = Integer.valueOf(actions.get(0));
        actions.remove(0);

        System.out.println("idEtudiant: " + idEtudiant);

        Etudiant etudiant = EtudiantDAO.retrieveById(idEtudiant);
        request.setAttribute("etudiant", etudiant);

        List<Groupe> groupes = GroupeDAO.getAll();
        request.setAttribute("groupes", groupes);

        loadJSP(urlEditionEtudiant, request, response);
    }
    private void doAjoutEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Groupe> groupes = GroupeDAO.getAll();
        request.setAttribute("groupes", groupes);
        loadJSP(urlAjoutEtudiant, request, response);
    }

    public void editionEtudiant(ArrayList<String> actions, Map<String,String[]>params){

        int idEtudiant = Integer.valueOf(actions.get(0));

        EtudiantDAO.editFormEtudiant(params, idEtudiant);
    }

    public void ajoutEtudiant(Map<String,String[]>params){
        EtudiantDAO.editFormEtudiant(params, -1);
    }

    public void enleverEtudiant(ArrayList<String> actions){

        int idEtudiant = Integer.valueOf(actions.get(0));

        EtudiantDAO.remove(idEtudiant);
    }

    /////////////////////////////////////////////GROUPE METHODE//////////////////////////////////
    private void doGroupes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Groupe> groupes = GroupeDAO.getAll();

        request.setAttribute("groupes", groupes);

        loadJSP(urlGroupes, request, response);
    }
    private void doEditionGroupe(HttpServletRequest request, HttpServletResponse response, ArrayList<String> actions)
            throws ServletException, IOException {

        int groupeId = Integer.valueOf(actions.get(0));
        actions.remove(0);

        Groupe groupe = GroupeDAO.retrieveById(groupeId);

        request.setAttribute("groupe", groupe);

        loadJSP(urlEditionGroupe, request, response);
    }
    private void doAjoutGroupe(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        loadJSP(urlAjoutGroupe, request, response);
    }

    public void editionGroupe(ArrayList<String> actions, Map<String,String[]>params){

        int idGroupe = Integer.valueOf(actions.get(0));

        GroupeDAO.editFormGroupe(params, idGroupe);
    }

    public void ajoutGroupe(Map<String,String[]>params){
        GroupeDAO.editFormGroupe(params, -1);
    }

    public void enleverGroupe(ArrayList<String> actions){

        int isGroupe = Integer.valueOf(actions.get(0));

        GroupeDAO.remove(isGroupe);
    }

    /////////////////////////////////////////////ABSENCE METHODE//////////////////////////////////
    private void doAbsences(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Absence> absences = AbsenceDAO.getAll();
        request.setAttribute("absences", absences);

        loadJSP(urlAbsences, request, response);
    }
    private void doAjoutAbsence(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Etudiant> etudiants = EtudiantDAO.getAll();
        request.setAttribute("etudiants", etudiants);

        loadJSP(urlAjoutAbsence, request, response);
    }

    private void doEditionAbsence(HttpServletRequest request, HttpServletResponse response, ArrayList<String> actions)
            throws ServletException, IOException {

        int absenceId = Integer.valueOf(actions.get(0));
        actions.remove(0);

        Absence absence = AbsenceDAO.retrieveById(absenceId);
        request.setAttribute("absence", absence);

        List<Etudiant> etudiants = EtudiantDAO.getAll();
        request.setAttribute("etudiants", etudiants);

        loadJSP(urlEditionAbsence, request, response);
    }

    public void editionAbsence(ArrayList<String> actions, Map<String,String[]>params){

        int idAbsence = Integer.valueOf(actions.get(0));

        AbsenceDAO.editFormAbsence(params, idAbsence);
    }

    public void enleverAbsence(ArrayList<String> actions){

        int idAbsence = Integer.valueOf(actions.get(0));

        AbsenceDAO.remove(idAbsence);
    }

    public void ajoutAbsence(Map<String,String[]>params){
        AbsenceDAO.editFormAbsence(params, -1);
    }

    /////////////////////////////////////////////NOTE METHODE//////////////////////////////////
    private void doNotes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Note> notes = NoteDAO.getAll();
        request.setAttribute("notes", notes);

        loadJSP(urlNotes, request, response);
    }

    private void doAjoutNote(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Etudiant> etudiants = EtudiantDAO.getAll();
        request.setAttribute("etudiants", etudiants);

        loadJSP(urlAjoutNote, request, response);
    }

    private void doEditionNote(HttpServletRequest request, HttpServletResponse response, ArrayList<String> actions)
            throws ServletException, IOException {

        int noteId = Integer.valueOf(actions.get(0));
        actions.remove(0);

        Note note = NoteDAO.retrieveById(noteId);
        request.setAttribute("note", note);

        List<Etudiant> etudiants = EtudiantDAO.getAll();
        request.setAttribute("etudiants", etudiants);

        loadJSP(urlEditionNote, request, response);
    }

    public void editionNote(ArrayList<String> actions, Map<String,String[]>params){

        int idNote = Integer.valueOf(actions.get(0));

        NoteDAO.editFormNote(params, idNote);
    }

    public void ajoutNote(Map<String,String[]>params){
        NoteDAO.editFormNote(params, -1);
    }

    public void enleverNote(ArrayList<String> actions){

        int idNote = Integer.valueOf(actions.get(0));

        NoteDAO.remove(idNote);
    }
}

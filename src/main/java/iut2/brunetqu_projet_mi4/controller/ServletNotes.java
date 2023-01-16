package iut2.brunetqu_projet_mi4.controller;

import iut2.brunetqu_projet_mi4.DAO.EtudiantDAO;
import iut2.brunetqu_projet_mi4.DAO.NoteDAO;
import iut2.brunetqu_projet_mi4.data.Etudiant;
import iut2.brunetqu_projet_mi4.data.GestionFactory;
import iut2.brunetqu_projet_mi4.data.Note;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "servletnotes", value = "/servletnotes")
public class ServletNotes extends HttpServlet {

    public ServletNotes() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Note> notes = NoteDAO.getAll();
        request.setAttribute("notes", notes);

        Collection<Etudiant> etudiants = EtudiantDAO.getAll();
        request.setAttribute("etudiants", etudiants);

        loadJSP("/WEB-INF/note/note.jsp", request, response);
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

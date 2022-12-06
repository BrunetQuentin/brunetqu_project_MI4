<%@ page import="iut2.brunetqu_projet_mi4.GestionFactory" %>
<%@ page import="iut2.brunetqu_projet_mi4.Etudiant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Projet - étape 2</h1>
    <h2>Fiche détaillée d'un étudiant</h2>
    <%
        int id = Integer.valueOf(request.getParameter("id"));
        Etudiant etudiant = GestionFactory.getEtudiantById(id);
    %>
    <div>
        Nom :
        <%=etudiant.getNom() %>
    </div>
    <div>
        Prénom :
        <%=etudiant.getPrenom() %>
    </div>
    <div>
        Nombre d'abscence :
        <%= GestionFactory.getAbsencesByEtudiantId(etudiant.getId()) %>
    </div>

    <br/>
    <a href="index.jsp">Retour à la liste d'étudiants</a>
</body>
</html>

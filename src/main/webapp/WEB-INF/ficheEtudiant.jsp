<%@ page import="iut2.brunetqu_projet_mi4.data.GestionFactory" %>
<%@ page import="iut2.brunetqu_projet_mi4.data.Etudiant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="etudiant" class="iut2.brunetqu_projet_mi4.data.Etudiant" scope="request"/>
<jsp:useBean id="nbAbsences" type="java.lang.Integer" scope="request"/>

<html>
<head>
    <title><%= etudiant.getNom() %> <%= etudiant.getPrenom() %></title>
</head>
<body>
    <h1>Projet - étape 2</h1>
    <h2>Fiche détaillée d'un étudiant</h2>
    <div>
        Nom :
        <%= etudiant.getNom() %>
    </div>
    <div>
        Prénom :
        <%= etudiant.getPrenom() %>
    </div>
    <div>
        Nombre d'abscence :
        <%= nbAbsences %>
    </div>

    <br/>
    <a href="servletindex">Retour à la liste d'étudiants</a>
</body>
</html>

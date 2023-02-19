<%@ page import="iut2.brunetqu_projet_mi4.data.GestionFactory" %>
<%@ page import="iut2.brunetqu_projet_mi4.data.Etudiant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="etudiant" class="iut2.brunetqu_projet_mi4.data.Etudiant" scope="request"/>
<jsp:useBean id="nbAbsences" type="java.lang.Integer" scope="request"/>

<html>
<head>
    <title><%= etudiant.getNom() %> <%= etudiant.getPrenom() %></title>
    <jsp:include page='<%= application.getInitParameter("head")%>' />
</head>
<body>
    <jsp:include page='<%= application.getInitParameter("header")%>' />
    <main>
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
    </main>
</body>
</html>

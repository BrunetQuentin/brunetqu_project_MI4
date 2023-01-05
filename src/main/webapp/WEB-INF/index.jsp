<%@ page import="iut2.brunetqu_projet_mi4.data.Etudiant" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:useBean id="etudiants" type="java.util.Collection<iut2.brunetqu_projet_mi4.data.Etudiant>" scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <title>Projet</title>
    </head>
    <body>
        <jsp:include page='<%= application.getInitParameter("header")%>' />

        <h2>Liste des étudiants</h2>

        <%for(Etudiant etudiant : etudiants) {%>
        <div>
            <a href="servlettraitementdetails?id=<%=etudiant.getId()%>">
                <%=etudiant.getPrenom() %>
                <%=etudiant.getNom() %>
            </a>
        </div>
        <%}%>

    </body>
</html>
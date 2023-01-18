<%@ page import="iut2.brunetqu_projet_mi4.data.Etudiant" %>
<%@ page import="iut2.brunetqu_projet_mi4.data.Note" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:useBean id="notes" type="java.util.HashMap<java.lang.Integer, iut2.brunetqu_projet_mi4.data.Note[]>" scope="request"/>
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
    <p><%=etudiant.getNom() + " " + etudiant.getPrenom()%></p>
    <%for(Note note : notes.get(etudiant.getId())){ %>
        <span><%= note.getNote() %></span>
    <%}%>
</div>
<%}%>

</body>
</html>
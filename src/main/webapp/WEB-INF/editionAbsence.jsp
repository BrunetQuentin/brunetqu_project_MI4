<%@ page import="iut2.brunetqu_projet_mi4.data.Groupe" %>
<%@ page import="iut2.brunetqu_projet_mi4.data.Etudiant" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %><%--
  Created by IntelliJ IDEA.
  User: quentin
  Date: 01/02/2023
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="absence" type="iut2.brunetqu_projet_mi4.data.Absence" scope="request"/>
<jsp:useBean id="etudiants" type="java.util.Collection<iut2.brunetqu_projet_mi4.data.Etudiant>" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
%>
<html>
<head>
    <title>Edition absence</title>
    <jsp:include page='<%= application.getInitParameter("head")%>' />
</head>
<body>
<jsp:include page='<%=application.getInitParameter("header")%>' />
<main>
    <h2>Editer une absence</h2>
    <form method="post">
        <fieldset>
            <legend>Informations de l'absence</legend>
            <div>
                <label for="startAt">Commence le :</label>
                <input type="datetime-local" id="startAt" required value="<%=dateFormat.format(absence.getStartAt())%>" name="startAt">
            </div>

            <div>
                <label for="endAt">Finis le :</label>
                <input type="datetime-local" id="endAt" value="<%=absence.getEndAt() != null ? dateFormat.format(absence.getEndAt()) : ""%>%>" name="endAt">
            </div>

            <div>
                <label>Est justifié ? :</label>
                <label for="oui">Oui</label>
                <input type="radio" id="oui" name="justified" value="oui" <%=absence.isJustify() ? "checked" : ""%>>
                <label for="non">Non</label>
                <input type="radio" id="non" name="justified" value="non" <%=absence.isJustify() ? "" : "checked"%>>
            </div>
            <div>
                <label for="etudiant">Etudiant :</label>
                <select id="etudiant" name="etudiant" required>
                    <%for(Etudiant etudiant : etudiants) {%>
                    <option value="<%=etudiant.getId()%>" <%= absence.getEtudiant().getId().equals(etudiant.getId()) ? "selected" : ""%>><%=etudiant.getPrenom() + " " + etudiant.getNom()%></option>
                    <%}%>
                </select>
            </div>

            <button type="submit">Valider</button>
        </fieldset>
    </form>
</main>
</body>
</html>

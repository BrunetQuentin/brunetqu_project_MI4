<%@ page import="iut2.brunetqu_projet_mi4.data.Groupe" %><%--
  Created by IntelliJ IDEA.
  User: quentin
  Date: 01/02/2023
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="groupes" type="java.util.Collection<iut2.brunetqu_projet_mi4.data.Groupe>" scope="request"/>
<jsp:useBean id="etudiant" type="iut2.brunetqu_projet_mi4.data.Etudiant" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edition etudiant</title>
    <jsp:include page='<%= application.getInitParameter("head")%>' />
</head>
<body>
<jsp:include page='<%=application.getInitParameter("header")%>' />
<main>
    <h2>Edition de l'étudiant</h2>
    <form method="post">
        <fieldset>
            <legend>Informations de l'étudiant</legend>
            <div>
                <label for="nom">Nom :</label>
                <input name="nom" id="nom" value="<%=etudiant.getNom()%>">
            </div>

            <div>
                <label for="prenom">Prenom :</label>
                <input name="prenom" id="prenom" value="<%=etudiant.getPrenom()%>">
            </div>

            <div>
                <label for="groupe">Groupe :</label>
                <select id="groupe" name="groupe">
                    <option value="" <%=etudiant.getGroupe() == null ? "selected" : ""%>/>
                    <%for(Groupe groupe : groupes) {%>
                    <option value="<%=groupe.getId()%>" <%=(etudiant.getGroupe() != null && groupe.getId() == etudiant.getGroupe().getId()) ? "selected" : ""%>><%=groupe.getNom()%></option>
                    <%}%>
                </select>
            </div>

            <button type="submit">Valider</button>
        </fieldset>
    </form>
</main>
</body>
</html>

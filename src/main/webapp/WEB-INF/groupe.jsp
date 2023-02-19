<%@ page import="iut2.brunetqu_projet_mi4.data.Groupe" %>
<%@ page import="iut2.brunetqu_projet_mi4.data.Etudiant" %><%--
  Created by IntelliJ IDEA.
  User: quentin
  Date: 18/01/2023
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="groupes" type="java.util.Collection<iut2.brunetqu_projet_mi4.data.Groupe>" scope="request"/>
<html>
<head>
    <title>Groupes</title>
    <jsp:include page='<%= application.getInitParameter("head")%>' />
</head>
<body>
<jsp:include page='<%= application.getInitParameter("header")%>' />
    <main>
        <h2>Liste des Groupes</h2>
        <Table>
            <thead>
                <tr>
                    <th>
                        N° Groupe
                    </th>
                    <th>
                        Intitulé
                    </th>
                    <th>
                        Etudiants
                    </th>
                    <th>
                        Actions
                    </th>
                </tr>
            </thead>
            <tbody>
            <%for(Groupe groupe : groupes) {%>
                <tr>
                    <td>
                        <%=groupe.getId() %>
                    </td>
                    <td>
                        <%=groupe.getNom() %>
                    </td>
                    <td>
                        <%for(Etudiant etudiant : groupe.getEtudiants()) {%>
                            <%=etudiant.getPrenom() + " " + etudiant.getNom() + ", "%>
                        <%}%>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/do/editiongroupe/<%=groupe.getId() %>">
                            <i class="fas fa-pen-to-square" title="Editer"></i>
                        </a>
                        <a href="${pageContext.request.contextPath}/do/suppressiongroupe/<%=groupe.getId() %>">
                            <i class="fas fa-trash-can" title="Supprimer"></i>
                        </a>
                    </td>
                </tr>
            <%}%>
            </tbody>
        </Table>

        <div class="plus-button"><a href="${pageContext.request.contextPath}/do/ajoutergroupe/"><i class="fas fa-plus" title="Ajouter un groupe"></i></a></div>
    </main>
</body>
</html>

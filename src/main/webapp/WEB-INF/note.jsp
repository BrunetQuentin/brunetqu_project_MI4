
<%@ page import="iut2.brunetqu_projet_mi4.data.Note" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:useBean id="notes" type="java.util.Collection<iut2.brunetqu_projet_mi4.data.Note>" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <title>Notes</title>
    <jsp:include page='<%= application.getInitParameter("head")%>' />
</head>
<body>
<jsp:include page='<%= application.getInitParameter("header")%>' />

<main>
    <h2>Liste des étudiants</h2>

    <Table>
        <thead>
        <tr>
            <th>
                N° Note
            </th>
            <th>
                Etudiant
            </th>
            <th>
                Note
            </th>
            <th>
                Actions
            </th>
        </tr>
        </thead>
        <tbody>
        <%for(Note note : notes) {%>
        <tr>
            <td>
                <%=note.getId() %>
            </td>
            <td>
                <%=note.getEtudiant() != null ? (note.getEtudiant().getPrenom() + " " + note.getEtudiant().getNom()) : "Pb suppression cascade"%>
            </td>
            <td>
                <%=note.getNote()%>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/do/editionnote/<%=note.getId() %>">
                    <i class="fas fa-pen-to-square" title="Editer"></i>
                </a>
                <a href="${pageContext.request.contextPath}/do/suppressionnote/<%=note.getId() %>">
                    <i class="fas fa-trash-can" title="Supprimer"></i>
                </a>
            </td>
        </tr>
        <%}%>
        </tbody>
    </Table>

    <div class="plus-button"><a href="${pageContext.request.contextPath}/do/ajouternote/"><i class="fas fa-plus" title="Ajouter une note"></i></a></div>
</main>

</body>
</html>

<jsp:useBean id="firstAction" type="java.lang.String" scope="request"/>
<div class="header">
    <img src="${pageContext.request.contextPath}/images/mobiteach.png"/>
    <h1>Gestion de notes et d'absences MobiNote</h1>
</div>

<nav>
    <ul>
        <li class="${firstAction == "etudiants" ? "active" : "" }"><a href="${pageContext.request.contextPath}/do/etudiants"><i class="fas fa-house"></i>Accueil</a></li>
        <li class="${firstAction == "etudiants" ? "active" : "" }"><a href="${pageContext.request.contextPath}/do/etudiants"><i class="fas fa-graduation-cap"></i>Etudiants</a></li>
        <li class="${firstAction == "groupes" ? "active" : "" }"><a href="${pageContext.request.contextPath}/do/groupes"><i class="fas fa-people-group"></i>Groupes</a></li>
        <li class="${firstAction == "absences" ? "active" : "" }"><a href="${pageContext.request.contextPath}/do/absences"><i class="fas fa-person-circle-question"></i>Absences</a></li>
        <li class="${firstAction == "notes" ? "active" : "" }"><a href="${pageContext.request.contextPath}/do/notes"><i class="fas fa-star-half-stroke"></i>Notes</a></li>
    </ul>
</nav>

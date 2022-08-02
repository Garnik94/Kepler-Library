<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="graphic/icon.png"
          type="image/x-icon">
    <title>Kepler library</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body class="standardPageBody">

<jsp:include page="UserProfile.jsp"/>

<br><br><br><br><br><br><br><br><br><br><br>

<form action="searchManageableUser" method="get">
    <label class="textStyle">Search user<br>
        <input class="inputAreaStyle" type="text" name="searchingUser">
    </label><br>
    <input class="searchButton" type="submit" value="Search">
</form>

<%
    if (session.getAttribute("ManageableUser") != null) {
        User manageableUser = (User) (session.getAttribute("ManageableUser"));
        String disabled = "";
        if (manageableUser.getId() == 1) {
            disabled = "disabled";
        }
        if (session.getAttribute("ConfirmPermitUser") != null ||
                session.getAttribute("ConfirmForbidUser") != null ||
                session.getAttribute("ConfirmDeletedUser") != null) {
            disabled = "disabled";
        }
%>

<span class="textStyle"><%=manageableUser.getUsername()%></span><br>
<span class="textStyle"><%=manageableUser.getEmail()%></span><br><br>

<form action="permitUser" method="post">
    <input <%=disabled%> class="loginButtonStyle" type="submit" value="Permit user">
</form>

<form action="forbidUser" method="post">
    <input <%=disabled%> class="deleteButtonStyle" type="submit" value="Forbid user">
</form>

<form action="deleteUser" method="post">
    <input <%=disabled%> class="deleteButtonStyle" type="submit" value="Delete user">
</form>

<%
    }
%>

<%
    if (session.getAttribute("searchingUserNotFound") != null &&
            session.getAttribute("searchingUserNotFound").equals("true")) {
%>

<span class="errorMessageStyle">User is not found</span>

<%
        session.removeAttribute("searchingUserNotFound");
    }
%>

<%--<%--%>
<%--    if (session.getAttribute("AdminDeleteError") != null &&--%>
<%--            session.getAttribute("AdminDeleteError").equals("true")) {--%>
<%--%>--%>

<%--<span class="errorMessageStyle">Admin is not deletable</span>--%>

<%--<%--%>
<%--        session.removeAttribute("AdminDeleteError");--%>
<%--    }--%>
<%--%>--%>


<%--<%--%>
<%--    if (session.getAttribute("AdminForbidError") != null &&--%>
<%--            session.getAttribute("AdminForbidError").equals("true")) {--%>
<%--%>--%>

<%--<span class="errorMessageStyle">You cant forbid Admin</span>--%>

<%--<%--%>
<%--        session.removeAttribute("AdminForbidError");--%>
<%--    }--%>
<%--%>--%>

<% if (session.getAttribute("ConfirmPermitUser") != null) {%>
<%--        <jsp:include page="ConfirmDeleteBook.jsp"/>--%>
<span class="errorMessageStyle">Are you really going to permit user</span>

<form>
    <label>
        <input class="navigationButtons" type="submit" formaction="permitUser?confirmPermitUser=yes" formmethod="post"
               value="Yes">
    </label>
    <label>
        <input class="navigationButtons" type="submit" formaction="permitUser?confirmPermitUser=no" formmethod="post"
               value="No">
    </label>
</form>
<%
    }
%>

<% if (session.getAttribute("ConfirmForbidUser") != null) {%>
<%--        <jsp:include page="ConfirmDeleteBook.jsp"/>--%>
<span class="errorMessageStyle">Are you really going to forbid user</span>

<form>
    <label>
        <input class="navigationButtons" type="submit" formaction="forbidUser?confirmForbidUser=yes" formmethod="post"
               value="Yes">
    </label>
    <label>
        <input class="navigationButtons" type="submit" formaction="forbidUser?confirmForbidUser=no" formmethod="post"
               value="No">
    </label>
</form>
<%
    }
%>

<% if (session.getAttribute("ConfirmDeleteUser") != null) {%>
<%--        <jsp:include page="ConfirmDeleteBook.jsp"/>--%>
<span class="errorMessageStyle">Are you really going to delete user</span>

<br>

<form>
    <label>
        <input class="navigationButtons" type="submit" formaction="deleteUser?confirmDeleteUser=yes" formmethod="post"
               value="Yes">
    </label>
    <label>
        <input class="navigationButtons" type="submit" formaction="deleteUser?confirmDeleteUser=no" formmethod="post"
               value="No">
    </label>
</form>
<%
    }
%>

</body>
</html>

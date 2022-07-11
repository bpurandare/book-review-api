<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head></head>
    <body>
        <div style="text-align: right; margin: auto; width: 60%;">
            <c:choose>
                <c:when test="${user==null || !isLoginSuccess}">
                    <a href="/login">Login</a>
                </c:when>
                <c:otherwise>
                    <a href="/logout">Logout from ${user.username}</a>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
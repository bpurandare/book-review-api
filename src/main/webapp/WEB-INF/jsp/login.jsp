<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<html>
    <head>
        <title>Login</title>
    </head>
    <body style="display: flex">
        <div style="margin: auto; width: 80%;">

            <c:if test="${errorMessage!=null}">
                <div style="color: red; text-align: center;">${errorMessage}</div>
            </c:if>
            <br>

            <springForm:form style="margin: auto; width: 50%;" method="post" action="/login" modelAttribute="user">
                <table>
                    <tr>
                        <td>User Name</td>
                        <td>:<springForm:input path="username"/></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td>:<springForm:password path="password"/></td>
                    </tr>
                    <tr><td><button>Submit</button></td></tr>
                    <tr><td>&nbsp;</td><td>&nbsp</td></tr>
                    <tr><td><a href="/register">If not Registered, Register here</a></td></tr>
                </table>
            </springForm:form>
        </div>
    </body>
</html>
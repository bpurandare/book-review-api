<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<html>
    <head>
        <title> Registration </title>
    </head>
    <body style="display: flex;">
        <div style="margin: auto; width: 80%;">

            <c:if test="${errorMessage!=null}">
                <div style="color: red; text-align: center;">${errorMessage}</div>
            </c:if>
            <br>
            <springForm:form style="margin: auto; width: 50%;" method="post" modelAttribute="user" action="/register">
                <table style="padding-left: 100px;">
                <tr><td>Username    </td> <td>: <springForm:input path="username" required="required"/></td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>Email       </td> <td>: <springForm:input path="email" required="required"/></td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>City        </td> <td>: <springForm:input path="city" required="required"/></td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>Password    </td> <td>: <springForm:password path="password" required="required"/></td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td><button> Register </button></td></tr>
                </table>
            </springForm:form>
        </div>
    </body>
</html>
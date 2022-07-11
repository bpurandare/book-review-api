<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<html>
    <head>
        <title> ${book.name} </title>
    </head>
    <body style="display: flex;">
        <div style="margin: auto; width: 80%;">
            <springForm:form style="margin: auto; width: 80%;" method="post" modelAttribute="review" action="/addReview">
                <table>
                <caption>Comments on <b>${book.name}</b></caption>
                <tr><td>Comments    </td> <td style="padding-left: 10px;"> <springForm:textarea path="reviewContent" required="required" rows="4" cols="100"/></td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td><button> Post </button></td></tr>
                </table>
            </springForm:form>
        </div>
    </body>
</html>
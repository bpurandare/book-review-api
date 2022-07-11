<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<html>
    <head>
        <title> Add Book </title>
    </head>
    <body style="display: flex;">
        <div style="margin: auto; width: 80%;">
            <springForm:form style="margin: auto; width: 50%;" method="post" modelAttribute="newBook" action="/addBook">
                <table style="padding-left: 100px;">
                <tr><td>ISIN    </td> <td>: <springForm:input path="isin" required="required"/></td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>Title       </td> <td>: <springForm:input path="name" required="required"/></td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>Author        </td> <td>: <springForm:input path="author" required="required"/></td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>Publisher    </td> <td>: <springForm:input path="publisher" required="required"/></td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>Search Tags (comma seperated) </td> <td>: <springForm:input path="tags" required="required"/></td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td><button> Add </button></td></tr>
                </table>
            </springForm:form>
        </div>
    </body>
</html>
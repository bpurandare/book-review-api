<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<html>
    <head>
        <title>Book Shelf</title>
        <style>
            .completeBorder {
                border: 1px solid black;
            }
            .container{
                margin: auto;
                width: 90%;
                padding: 10px;
            }
        </style>
    </head>
    <body>

        <jsp:include page="header.jsp"/>
        <div class="container">

            <c:if test="${errorMessage!=null}">
                <div style="color: red; text-align: center;">${errorMessage}</div>
                <br>
            </c:if>


            <div style="margin: auto; width: 80%;">
                <springForm:form method="post" modelAttribute="search" action="/search">
                    <table>
                        <tr> <td>Book Name  </td> <td>: <springForm:input path="bookName"/> </td> </tr>
                        <tr> <td>Author Name</td> <td>: <springForm:input path="author"/> </td> </tr>
                        <tr> <td>Publisher  </td> <td>: <springForm:input path="publisher"/> </td> </tr>
                        <tr> <td>Tags       </td> <td>: <springForm:input path="tags"/> </td> </tr>
                        <tr> <td> <button> Search </button> </td> </tr>
                    </table>
                </springForm:form>
            </div>

            <br>

            <c:if test="${user!=null && isLoginSuccess}">
                <div style="margin: auto; width: 80%; text-align: right;">
                    <a href="/addBook">Add Book</a>
                </div>
                <br>
            </c:if>



            <table style="border:1px solid black; margin: auto; width: 80%">
                <caption>Book Shelf</caption>
                <thead>
                    <tr>
                        <th class="completeBorder">ISIN Code</th>
                        <th class="completeBorder">Book Name</th>
                        <th class="completeBorder">Author Name</th>
                        <th class="completeBorder">Publisher</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${pagedBooks}" var="book">
                        <tr>
                        <td class="completeBorder"><a href="/review?id=${book.bookId}"> ${book.isin} </a> </td>
                        <td class="completeBorder">${book.name}</td>
                        <td class="completeBorder">${book.author}</td>
                        <td class="completeBorder">${book.publisher}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div style="text-align: right; width: 90%">Showing page <b>${page}</b> out of <b>${totalPages}</b>, Total Records: <b>${totalRecords}</b> </div>
            <br>

            <div style="text-align: center;">
            <c:if test="${page>1}"> <span style="margin: 10px;"> <a href="/nav/prev">Previous</a> </span> </c:if>
            <span style="margin: 10px;"> <a href="/reload">Refresh</a> </span>
            <c:if test="${totalPages>page}"> <span style="margin: 10px;"> <a href="/nav/next">Next</a> </span> </c:if>
            </div>


        </div>

    </body>
</html>
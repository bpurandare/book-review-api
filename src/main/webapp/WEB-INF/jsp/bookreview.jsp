<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>${book.name} Book Reviews</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div style="padding: 20px;">
        <div style="margin: auto; width: 60%; padding: 20px;">
            <table style="margin: auto; width: 90%;">
                <tr>
                    <td><a href="/back">Back</a></td>

                    <td style="text-align: right;">
                        <c:if test="${user!=null && isLoginSuccess}">
                            <a href="/addReview">Write Review</a>
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
            <table style="margin: auto; width: 80%; padding: 20px;">
                <caption><b>${book.name}</b> by ${book.author}</caption>
                <c:forEach items="${book.bookReviews}" var="bookReview">
                <tr>
                    <td style="padding-top: 2px;">
                        <table style="border: 1px solid black; margin: auto; width: 80%">
                            <tr> <td>${bookReview.reviewContent}</td> </tr>
                            <tr> <td style="text-align: right;"><b>${bookReview.user.username}</b> from ${bookReview.user.city} (${bookReview.reviewDate})</td> </tr>
                        </table>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
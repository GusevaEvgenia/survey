<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row-fluid">
    <div class="span10 offset1">
        <div class="thumbnail">
            <div class="page-header">
                <h1 class="text-center">${form.title}<br>
                    <small>
                        ${form.description}
                    </small>
                </h1>
            </div>

            <div class="popup_form">
                <c:forEach var="i" begin="0" end="${designer.size}">
                    <c:choose>
                        <c:when test="${designer.questions[i].idType == 1}">
                           <%@include file="/WEB-INF/pages/forms/questions/_single_option.jsp"%>
                        </c:when>
                        <c:when test="${designer.questions[i].idType == 2}">
                            <%@include file="/WEB-INF/pages/forms/questions/_multiple_option.jsp"%>
                        </c:when>
                        <c:when test="${designer.questions[i].idType == 3}">
                            <%@include file="/WEB-INF/pages/forms/questions/_number.jsp"%>
                        </c:when>
                        <c:when test="${designer.questions[i].idType == 4}">
                            <%@include file="/WEB-INF/pages/forms/questions/_select_option.jsp"%>
                        </c:when>
                        <c:when test="${designer.questions[i].idType == 5}">
                            <%@include file="/WEB-INF/pages/forms/questions/_matrix_single_option.jsp"%>
                        </c:when>
                        <c:when test="${designer.questions[i].idType == 6}">
                            <%@include file="/WEB-INF/pages/forms/questions/_matrix_multiple_option.jsp"%>
                        </c:when>
                        <c:otherwise>
                           Неизвестный тип<br>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>

        </div>
    </div>
</div>
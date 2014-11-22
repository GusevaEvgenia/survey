<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Подключение хейдера--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value="Анкета"/>
</jsp:include>

<jsp:include page="/WEB-INF/pages/forms/form/_menu.jsp"/>

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

            <c:forEach items='${form.questionsesByIdForm}' var="question">
                <c:choose>
                    <c:when test="${question.idQtype == 1}">
                        <%@include file="/WEB-INF/pages/forms/questions/_single_option.jsp"%>
                    </c:when>
                    <c:when test="${question.idQtype == 2}">
                        <%@include file="/WEB-INF/pages/forms/questions/_multiple_option.jsp"%>
                    </c:when>
                    <c:when test="${question.idQtype == 3}">
                        <%@include file="/WEB-INF/pages/forms/questions/_number.jsp"%>
                    </c:when>
                </c:choose>
            </c:forEach>

        </div>
    </div>
</div>


    </div>
</div>
<%--Подключение футера--%>
<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>

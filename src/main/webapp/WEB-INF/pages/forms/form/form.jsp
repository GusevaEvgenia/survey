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
            <p class="startText">${form.startText}</p><hr>
            <form action="/forms/link/${form.link}" method="post">
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
                                <%@include file="/WEB-INF/pages/forms/questions/_select_option.jsp"%>
                            </c:when>
                            <c:when test="${designer.questions[i].idType == 4}">
                                <%@include file="/WEB-INF/pages/forms/questions/_matrix_single_option.jsp"%>
                            </c:when>
                            <c:when test="${designer.questions[i].idType == 5}">
                                <%@include file="/WEB-INF/pages/forms/questions/_matrix_multiple_option.jsp"%>
                            </c:when>
                            <c:otherwise>
                                <div class="text-center">
                                    <h4>У Вас пока нет вопросов в анкете!</h4>
                                    Можете изменить это перейдя в констурктор.
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
                <div class="text-center">
                    <button class="btn btn-primary ${btnSave==1 ? "" : "disabled"}" ${btnSave==1 ? "" : "disabled"} type="submit">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</div>
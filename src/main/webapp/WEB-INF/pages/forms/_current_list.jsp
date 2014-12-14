<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--Отображение каталога анкет--%>
<%--<%int type = Integer.parseInt(request.getParameter("type"));%>--%>
<%
    pageContext.setAttribute("data", pageContext.findAttribute(request.getParameter("type")));
%>
<div class="thumbnails">
    <div class="row-fluid">
        <%int j = 0;%>
        <c:forEach items='${data}' var="i">
            <% j++;%>
            <div class="span3">
                <div class="thumbnail">
                    <a href="/forms/${i.idForm}"><img src="${i.picture}" alt=""></a>
                    <p></p>
                    <div class="caption">
                        <h4>${i.title}</h4>
                        <p>${i.description}</p>
                        <p>
                            <a href="/forms/${i.idForm}" class="btn btn-primary">Подробнее</a>
                            <a href="#" class="btn btn-primary pull-right remove-btn"  data-id="${i.idForm}">Удалить</a>
                        </p>
                    </div>
                </div>
            </div>
            <%--Переход на новую строку если уже есть 4 анкеты в строке--%>
            <% if (j % 4 == 0) {%>
    </div>
    <br>
    <div class="row-fluid">
            <% }%>
        </c:forEach>
    </div>
</div>
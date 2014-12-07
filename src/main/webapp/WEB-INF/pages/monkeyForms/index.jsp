<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--Подключение хейдера--%>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value="Каталог анкет"/>
</jsp:include>

<div class="row-fluid">
    <div class="span10 offset1">
        <%--Заголовок страницы--%>
        <div class="hero-unit hero">
            <h2>Создавайте свои анкеты и управляйте ими прямо сейчас!<!--br><small>Каталог сохраненных анкет:</small--></h2>
        </div>
        <%--Меню страницы--%>
        <div class="row-fluid">
            <div class="span6">
                <ul class="nav nav-pills">
                    <li><a href="/forms#tab1" data-toggle="tab">Активные анкеты</a></li>
                    <li><a href="/forms#tab2" data-toggle="tab">Новые анкеты</a></li>
                    <li><a href="/forms#tab3" data-toggle="tab">Архив</a></li>
                    <li class="active"><a  href="/monkey-forms/" data-toggle="tab">Анкеты SurveyMonkey</a></li>
                </ul>
            </div>
            <div class="span3 offset3">
                <a class="btn btn-primary pull-right" href="/forms/new">
                    Новая анкета
                </a>
            </div>
        </div>


<div class="thumbnails">
    <div class="row-fluid">
        <%int j = 0;%>
        <c:forEach items='${forms}' var="form">
        <% j++;%>
        <div class="span3">
            <div class="thumbnail">
                <a href="/monkey-forms/${form.id}"><img src="/images/monkeyForm.jpg" alt=""></a>
                <p></p>
                <div class="caption">
                    <h4>${form.title}</h4>
                    <p>
                        <a href="/monkey-forms/${form.id}" class="btn btn-primary">Подробнее</a>
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <div class="span5">
                <ul class="nav nav-pills">
                    <li class="active"><a href="#tab1" data-toggle="tab">Каталог новых анкет</a></li>
                    <li><a class="" href="#tab2" data-toggle="tab">Архив</a></li>
                    <li><a class="" href="#tab3" data-toggle="tab">Анкеты SurveyMonkey</a></li>
                </ul>
            </div>
            <div class="span3 offset4">
                <a class="btn btn-primary pull-right" href="/forms/new">
                    Новая анкета
                </a>
            </div>
        </div>
        <%--Каталог анкет--%>
        <div class="tab-content">
            <%--Новые анкеты--%>
            <div class="tab-pane active" id="tab1">
                <jsp:include page="/WEB-INF/pages/forms/_current_list.jsp?size=${new}"  />
            </div>
            <%--Архив--%>
            <div class="tab-pane" id="tab2">
                <jsp:include page="/WEB-INF/pages/forms/_current_list.jsp?size=${archive}" />
            </div>
            <%--Анкеты SurveyMonkey--%>
            <div class="tab-pane" id="tab3">
                <jsp:include page="/WEB-INF/pages/forms/_current_list.jsp?size=${monkey}" />
            </div>
        </div>
    </div>
</div>
<%--Подключение футера--%>
<jsp:include page="/WEB-INF/pages/partials/footer.jsp" />
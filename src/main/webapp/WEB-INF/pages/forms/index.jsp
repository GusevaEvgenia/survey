<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--ИЗМЕНИТЬ ТИТЛ СТРАНИЦЫ-->
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value="Главная страница"/>
</jsp:include>

<!--Колонки страницы-->
<div class="row-fluid">
    <!--УДАЛИТЬ СТИЛЬ-->
    <div class="span10 offset1">
        <div class="page-header" style="background-image: url(/images/background.jpg); padding: 30px; color: #ffffff">
            <h1>Создавайте свои анкеты и управляйте ими прямо сейчас!<!--br><small>Каталог сохраненных анкет:</small--></h1>
        </div>
        <div class="row-fluid">
            <div class="span5">
                <ul class="nav nav-pills">
                    <li class="active"><a class="" href="#tab1" data-toggle="tab">Каталог новых анкет</a></li>
                    <li><a class="" href="#tab2" data-toggle="tab">Архив</a></li>
                </ul>
            </div>
            <div class="span3 offset4">
                <a class="btn btn-primary pull-right" href="/forms/new">
                    Новая анкета
                </a>
            </div>
        </div>
        <div class="tab-content">
            <div class="tab-pane active" id="tab1">
                <jsp:include page="/WEB-INF/pages/forms/_current_list.jsp" >
                    <jsp:param name="type" value="1"/>
                </jsp:include>
            </div>
            <div class="tab-pane" id="tab2">
                <jsp:include page="/WEB-INF/pages/forms/_current_list.jsp?type=2" />
            </div>
        </div>

    </div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp" />
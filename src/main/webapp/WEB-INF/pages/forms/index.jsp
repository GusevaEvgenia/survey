<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Подключение хейдера--%>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value="Каталог анкет"/>
</jsp:include>

<script>
    $(document).ready(function(){
        var url = document.location.toString();
        if (url.match('#')) {
            $('.nav-pills a[href=#'+url.split('#')[1]+']').tab('show') ;
        }
        $('.nav-pills a[data-toggle="tab"]').on('shown', function (e) {
            window.location.hash = e.target.hash;
        })
    });
    </script>

<div class="row-fluid">
    <div class="span10 offset1">
        <%--Заголовок страницы--%>
        <div class="hero-unit hero">
            <h2>Создавайте свои анкеты и управляйте ими прямо сейчас!<!--br><small>Каталог сохраненных анкет:</small--></h2>
        </div>
        <div id="menu-tab">
            <%--Меню страницы--%>
            <div class="row-fluid">
                <div class="span6">
                    <ul class="nav nav-pills">
                        <li class="active"><a href="#tab1" data-toggle="tab">Активные анкеты</a></li>
                        <li><a class="" href="#tab2" data-toggle="tab">Новые анкеты</a></li>
                        <li><a class="" href="#tab3" data-toggle="tab">Архив</a></li>
                        <li><a class="${user.token==null ? "hidden" : ""}" href="/monkey-forms/">Анкеты SurveyMonkey</a></li>
                    </ul>
                </div>
                <div class="span3 offset3">
                    <a class="btn btn-primary pull-right" href="/forms/new">
                        Новая анкета
                    </a>
                </div>
            </div>
            <%--Каталог анкет--%>
            <div class="tab-content">
                <%--Активные анкеты--%>
                <div class="tab-pane active" id="tab1">
                    <jsp:include page="/WEB-INF/pages/forms/_current_list.jsp">
                        <jsp:param name="type" value="active" />
                    </jsp:include>
                </div>
                <%--Новые анкеты--%>
                <div class="tab-pane" id="tab2">
                    <jsp:include page="/WEB-INF/pages/forms/_current_list.jsp">
                        <jsp:param name="type" value="new" />
                    </jsp:include>
                </div>
                <%--Архив--%>
                <div class="tab-pane" id="tab3">
                    <jsp:include page="/WEB-INF/pages/forms/_current_list.jsp" >
                        <jsp:param name="type" value="archive" />
                    </jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>
<%--Подключение футера--%>
<jsp:include page="/WEB-INF/pages/partials/footer.jsp" />
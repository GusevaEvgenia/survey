<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Подключение хейдера--%>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value="Анкета"/>
</jsp:include>

<div class="row-fluid">
    <div class="span10 offset1">
        <%--Заголовок страницы--%>
        <div class="hero-unit hero">
            <h2>Управляние анкетой<br>
                <small id="black_color">
                    Легко и быстро просматривайте свои анкеты, редактируйте и за ненадобностью удаляйте их.
                </small>
            </h2>
        </div>
        <!--Предупреждение о новых ответах-->
        <div class="alert <%if (false) {%>hidden<%}%>" >
            <button type="button" class="close" data-dismiss="alert">х</button>
            <strong>Внимание!</strong> У Вас есть новые ответы по текущей анкете.
            Чтобы их обработать перейдите по ссылке "Ответы".
        </div>
        <!--Кнопки управления анкетой-->
        <!--div class="row-fluid">
            <div class="span7">
                Текущая версия опроса:
                <select>
                    <option>Версия№3 От 25.05.2014</option>
                    <option>Версия№2 От 20.05.2014</option>
                    <option>Версия№1 От 14.05.2014</option>
                </select>
            </div>
            <div class="span5 margin">
                Последняя версия опроса: 3!
            </div>
        </div-->
        <div class="row-fluid">
            <div class="span7">
                <ul class="nav nav-pills">
                    <li class="active"><a href="#tab1" data-toggle="tab"><i class="icon-list-alt"></i> Анкета</a></li>
                    <li class="${SMform=="true"? "hidden": ""}"><a href="#tab2" data-toggle="tab"><i class="icon-pencil"></i> Конструктор</a></li>
                    <li><a href="#tab3" data-toggle="tab"><i class="icon-check"></i> Ответы</a></li>
                    <li class="${SMform=="true"? "hidden": ""}"><a href="#tab4" data-toggle="tab"><i class="icon-wrench"></i> Настройки</a></li>
                    <li><a href="/forms/123/analysis/basic"><i class="icon-tasks"></i> Анализ</a></li>
                </ul>
            </div>
            <div class="span5">
                <div class="btn-group pull-right">
                    ${SMform=="true"?
                    "<a class=\"btn btn-info\" href=\"#\"><i class=\"icon-globe\"></i> Открыть в SM</a>" :
                    "<a class=\"btn btn-info\" href=\"#\"><i class=\"icon-remove\"></i> Удалить</a>"}
                    <a class="btn btn-info" href=""><i class="icon-globe"></i> Получить ссылку</a>
                    <a class="btn btn-info" href=""><i class="icon-download-alt"></i> Сохранить в файл</a>
                </div>
            </div>
        </div>
        <%--Управление анкетой--%>
        <div class="tab-content">
            <div class="tab-pane active" id="tab1">
                <jsp:include page="/WEB-INF/pages/forms/_preview.jsp" />
            </div>
            <div class="tab-pane" id="tab2">
                <jsp:include page="/WEB-INF/pages/forms/_designer.jsp" />
            </div>
            <div class="tab-pane" id="tab3">
                <jsp:include page="/WEB-INF/pages/forms/_answers.jsp" />
            </div>
            <div class="tab-pane" id="tab4">
                <jsp:include page="/WEB-INF/pages/forms/_settings.jsp" />
            </div>
        </div>
    </div>
</div>
<%--Подключение футера--%>
<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>

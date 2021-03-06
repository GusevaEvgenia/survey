<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Подключение хейдера--%>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value="Главная страница"/>
</jsp:include>
<%--Элемент Hero--%>
<div class="row-fluid">
    <div class="span10 offset1 hero-unit hero">
        <h1>Проведи опрос легко с нами!</h1>
        <p>Зарегистрируйтесь и начните работать прямо сейчас.</p>
        <p>
            <a href="#interview" class="btn btn-large">
                Узнать больше
            </a>
        </p>
    </div>
</div>
<%--Колонки страницы--%>
<div class="row-fluid">
    <div class="span10 offset1">
        <div class="firstText" >
            <p>
                <strong>Опрос</strong> – это метод сбора первичной информации, основанный на непосредственном (беседа, интервью)
                или опосредованном (анкета) социально-психологическом взаимодействии исследователя и опрашиваемого.
                Источником информации в данном случае служит словесное или письменное суждение человека.
            </p>
            <p>
                Широкое использование данного метода объясняется его универсальностью,
                сравнительной легкостью применения и обработки данных. Исследователь в
                короткий срок может получить информацию о реальной деятельности, поступках опрашиваемого,
                информацию о его настроениях, намерениях, оценках окружающей действительности.
            </p>
        </div>
        <div id="interview" class="text-center">
            <div class="page-header">
                <h2>Этапы опроса<br><small>Как это работает?</small></h2>
            </div>
            <img src="/images/firstPage1.png">
            <img src="/images/firstPage2.png">
            <img src="/images/firstPage3.png">
        </div>
    </div>
</div>

<%--форма решистрации--%>
<form class="margin-button0 hidden" id="form-reg" action="/" method="POST">
    <table class="width">
        <tr>
            <td><legend>Ваше имя </legend></td>
            <td><input class="input-large" name="name" type="text" size="100"></td>
        </tr>
        <tr>
            <td><legend>Логин для входа в систему</legend></td>
            <td><input class="input-large" name="login" type="text" size="100"></td>
        </tr>
        <tr>
            <td><legend>Email </legend></td>
            <td><input class="input-large" name="email" type="email" size="100"></td>
        </tr>
        <tr>
            <td><legend>Введите пароль </legend></td>
            <td><input class="input-large" name="pass1" type="password" size="100"></td>
        </tr>
        <tr>
            <td><legend>Павторите пароль </legend></td>
            <td><input class="input-large" name="pass2" type="password" size="100"></td>
        </tr>
    </table>
</form>
<%--всплывающее окно регистрации--%>
<div class="modal fade" id="reg-popup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="text-center font-size21" id="myModalLabel">Регистрация</h4>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button class="btn btn-primary btn-block " type="submit" form="form-reg">Создать</button>
            </div>
        </div>
    </div>
</div>

<%--Подключение футера--%>
<jsp:include page="/WEB-INF/pages/partials/footer.jsp" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Подключение хейдера--%>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value="Главная страница"/>
</jsp:include>
<%--Элемент Hero--%>
<div class="hero-unit hero">
    <h1>Проведи опрос легко с нами!</h1>
    <p>Зарегистрируйтесь и начните работать прямо сейчас.</p>
    <p>
        <a href="#interview" class="btn btn-large">
            Узнать больше
        </a>
    </p>
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
<%--Подключение футера--%>
<jsp:include page="/WEB-INF/pages/partials/footer.jsp" />
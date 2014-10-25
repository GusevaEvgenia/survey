<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

<div class="row-fluid">
    <div class="span10 offset1">
        <%--Заголовок страницы--%>
        <div class="hero-unit hero">
            <h2>Зарегистрируйся и начни свое исследование!</h2>
        </div>
            <div class="span10 offset2">
                <form class="margine-botton0" id="save-form" action="/register" method="POST">
                    <div class="margine-botton15">
                        <h4>Введите Ваше имя</h4>
                        <p>
                            <input class="input-large" name="name" type="type" size="100" name="#">
                        </p>
                    </div>
                    <div class="margine-botton15">
                        <h4>Введите логин для входа в систему</h4>
                        <p>
                            <input class="input-large" name="login" type="text" size="100" name="#">
                        </p>
                    </div>
                    <div class="margine-botton15">
                        <h4>Введите email</h4>
                        <p>
                            <input class="input-large" name="email" type="text" size="100" name="#">
                        </p>
                    </div>
                    <div class="margine-botton15">
                        <h4>Введите пароль</h4>
                        <p>
                            <input class="input-large" name="pass1" type="text" size="100" name="#">
                        </p>
                    </div>
                    <div class="margine-botton15">
                        <h4>Павторите пароль</h4>
                        <p>
                            <input class="input-large" name="pass2" type="password" size="100" name="#">
                        </p>
                    </div>
                </form>
                <div class="row-fluid" >
                    <div class="span2">
                        <button class="btn btn-primary btn-block" type="submit" form="save-form">Создать</button>
                    </div>
                </div>
            </div>
        </div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>

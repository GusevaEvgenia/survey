<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

<div class="row-fluid">
    <div class="span10 offset1">
        <%--Заголовок страницы--%>
        <div class="hero-unit hero">
            <h2>Персанальная страничка пользователя<!--br><small>Каталог сохраненных анкет:</small--></h2>
        </div>
        <div>
            <form class="margin-button0" id="" action="" method="POST">
                <div class="row-fluid">
                    <div class="span8 offset2">
                        <legend>Информация о Вас</legend>
                        <div class="span6 offset3">
                            <table class="width">
                                <tr>
                                    <td><strong>Ваш логин: </strong></td>
                                    <td><input class="input-large" name="login" type="text" size="100" value="EvgeniaG" disabled></td>
                                </tr>
                                <tr>
                                    <td><strong>Ваше имя: </strong></td>
                                    <td><input class="input-large" name="name" type="text" size="100" value="Евгения"></td>
                                </tr>
                                <tr>
                                    <td><strong>Ваш email: </strong></td>
                                    <td><input class="input-large" name="email" type="text" size="100" value="evgenia_@email.ua"></td>
                                </tr>
                            </table>
                            <div class="span3 offset5">
                                <button class="btn btn-primary " type="submit" form="">Сохранить</button>
                            </div>
                        </div>
                    </div>
                </div>
                    <div class="row-fluid">
                        <div class="span8 offset2">
                            <legend>Смена пароля</legend>
                            <div class="span8 offset2">
                                <table class="width">
                                    <tr>
                                        <td><strong>Введите старый пароль: </strong></td>
                                        <td><input class="input-large" name="old_pass" type="password" size="100"></td>
                                    </tr>
                                    <tr>
                                        <td><strong>Введите новый пароль: </strong></td>
                                        <td><input class="input-large" name="pass1" type="password" size="100"></td>
                                    </tr>
                                    <tr>
                                        <td><strong>Павторите новый пароль: </strong></td>
                                        <td><input class="input-large" name="pass2" type="password" size="100"></td>
                                    </tr>
                                </table>
                                <div class="span3 offset5">
                                    <button class="btn btn-primary " type="submit" form="">Сохранить</button>
                                </div>
                            </div>
                        </div>
                    </div>
                <div class="row-fluid">
                    <div class="span8 offset2">
                        <legend>Подключение к SurveyMonkey</legend>
                        <div class="span8 offset4">
                            <strong>${conectSM=="true"? "Вы уже подключились к системе <br><a class=\"btn\" href=\"#\">Удалить привязку</a>":
                            "<a href='/'>Подключится к системе</a>"}</strong>
                        </div>
                    </div>
                </div>
            </form>
        </div>
   </div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>

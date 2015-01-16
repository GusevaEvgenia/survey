<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <script language="JavaScript">
                $(document).ready(function(){
                    $("#unassign").click(function(e){
                        e.preventDefault();
                        $.ajax("/auth/${user.idUser}/unassign", {
                            method: "delete",
                            success:function(){
                                window.location.reload();
                            }
                        })
                    });
                    $("#assign").click(function(e){
                        e.preventDefault();
                        window.open('auth/${user.idUser}/assign',null, "width=1020,height=800,resizable=yes,scrollbars=yes");
                        //TODO we should reload page after popup will be closed
                    });
                });
            </script>
            <%--<form class="margin-button0" id="" action="/user/update" method="POST" commandName="updateUser">--%>
                <div class="row-fluid">
                    <div class="span10 offset2">
                        <legend>Информация о Вас</legend>
                        <div class="span8 offset2">
                            <form onsubmit='return validate()' id="validUserData" method="POST" action="">
                                    <table class="width">
                                        <tr>
                                            <td style="width: 100px"><strong>Ваш логин: </strong></td>
                                            <td><input class="input-large" name="login" type="text" size="100" value="${user.login}" disabled></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Ваше имя: </strong></td>
                                            <td>
                                                <span style='color:red' class="hidden" id="name"></span>
                                                <input class="input-large" name="name" type="text" size="100" value="${user.name}">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><strong>Ваш email: </strong></td>
                                            <td>
                                                <span style='color:red' class="" id="email"></span>
                                                <input class="input-large" name="email" type="text" size="100" value="${user.email}">
                                            </td>
                                        </tr>
                                    </table>
                                    <div class="span3 offset2">
                                        <button class="btn btn-primary btn-val" type="submit" form="validUserData">Сохранить</button>
                                    </div>
                            </form>
                            <script>
                                function validate(){
                                    //Считаем значения из полей name и email в переменные x и y
                                    var name=document.getElementsByName("name")[0].value;
                                    var email=document.getElementsByName("email")[0].value
                                    //Если поле name пустое выведем сообщение и предотвратим отправку формы
                                    if (name.length==0){
                                        $("#name").removeClass("hidden");
                                        document.getElementById('name').innerHTML='Введите Ваше имя';
                                        return false;
                                    }else{
                                        $("#name").addClass("hidden");
                                    }
                                    //Если поле email пустое выведем сообщение и предотвратим отправку формы
                                    if (email.length==0){
                                        $("#email").removeClass("hidden");
                                        document.getElementById('email').innerHTML='Введите Вашу электронную почту';
                                        return false;
                                    }else{
                                        $("#email").addClass("hidden");
                                    }
                                    //Проверим содержит ли значение введенное в поле email символы @ и .
                                    at=email.indexOf("@");
                                    dot=email.indexOf(".");
                                    //Если поле не содержит эти символы знач email введен не верно
                                    if (at<1 || dot <1){
                                        $("#email").removeClass("hidden");
                                        document.getElementById('email').innerHTML='Вы неправильно заполнили поле';
                                        return false;
                                    }else{
                                        $("#email").addClass("hidden");
                                    }
                                }

                                function validatePass() {
                                    //Считаем значения из полей name и email в переменные x и y
                                    var old_pass = document.getElementsByName("old_pass")[0].value;
                                    var pass1 = document.getElementsByName("pass1")[0].value;
                                    var pass2 = document.getElementsByName("pass2")[0].value
                                    //Если поле name пустое выведем сообщение и предотвратим отправку формы
                                    if (old_pass.length == 0) {
                                        $("#old_pass").removeClass("hidden");
                                        document.getElementById('old_pass').innerHTML = 'Поле не должно быть пустым';
                                        return false;
                                    } else {
                                        $("#old_pass").addClass("hidden");
                                    }
                                    //Если поле email пустое выведем сообщение и предотвратим отправку формы
                                    if (pass1.length == 0) {
                                        $("#pass1").removeClass("hidden");
                                        document.getElementById('pass1').innerHTML = 'Поле не должно быть пустым';
                                        return false;
                                    } else {
                                        $("#pass1").addClass("hidden");
                                    }
                                    if (pass2.length == 0) {
                                        $("#pass2").removeClass("hidden");
                                        document.getElementById('pass2').innerHTML = 'Поле не должно быть пустым';
                                        return false;
                                    } else {
                                        $("#pass2").addClass("hidden");
                                    }
                                    if (old_pass != $("#btnSavePass").data("pass")) {
                                        $("#old_pass").removeClass("hidden");
                                        document.getElementById('old_pass').innerHTML = 'Вы указали неверный текущий пароль';
                                        return false;
                                    } else {
                                        $("#old_pass").addClass("hidden");
                                    }
                                    if (pass1 != pass2) {
                                        $("#pass1").removeClass("hidden");
                                        document.getElementById('pass1').innerHTML = 'Пароли должны совпадать';
                                        return false;
                                    } else {
                                        $("#pass1").addClass("hidden");
                                    }
                                }
                            </script>
                        </div>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span8 offset2">
                        <legend>Смена пароля</legend>
                        <div class="span8 offset2">
                            <form onsubmit='return validatePass()' id="validPassData" method="POST" action="">
                                <table class="width">
                                    <tr>
                                        <td style="width: 215px"><strong>Введите старый пароль: </strong></td>
                                        <td>
                                            <span style='color:red' class="hidden" id="old_pass"></span>
                                            <input class="input-large" name="old_pass" type="password" size="100">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><strong>Введите новый пароль: </strong></td>
                                        <td>
                                            <span style='color:red' class="hidden" id="pass1"></span>
                                            <input class="input-large" name="pass1" type="password" size="100">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><strong>Павторите новый пароль: </strong></td>
                                        <td>
                                            <span style='color:red' class="hidden" id="pass2"></span>
                                            <input class="input-large" name="pass2" type="password" size="100">
                                        </td>
                                    </tr>
                                </table>
                                <div class="span3 offset4">
                                    <button class="btn btn-primary" id="btnSavePass" type="submit" form="validPassData" data-pass="${user.password}">Сохранить</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span8 offset2">
                        <legend>Подключение к SurveyMonkey</legend>
                        <div class="span8 offset4">
                            <strong>
                                <c:if test="${user.token!=null}">
                                    Вы уже подключились к системе <br><a class="btn" href="#" id="unassign">Удалить привязку</a>
                                </c:if>

                                <c:if test="${user.token==null}">
                                    <a  id="assign" href='/'>Подключится к системе</a>
                                </c:if>
                            </strong>
                        </div>
                    </div>
                </div>
            <%--</form>--%>
        </div>
   </div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>

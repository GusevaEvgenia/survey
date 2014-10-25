<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${param.title}</title>
        <meta charset="UTF-8">
        <link href="/css/bootstrap.css" rel="stylesheet">
        <link href="/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="/css/my.css" rel="stylesheet">
        <script type="text/javascript" src="/js/jquery.js"></script>

        <script>
            $(document).ready(function() {
                $("#form-registration").click(function() {
                    var name = $("input[name='name']").val();

                    var form = $("#form-reg").html();
                    $('#reg-popup').find(".modal-body").html("<h2>" + name + "</h2>" + form);
                    $('#reg-popup').modal('show');
                });
            });
        </script>
    </head>

    <body style="padding: 30px">
        <div class="container-fluid">
            <!--Основная навигационная панель-->
            <div class="navbar navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container-fluid offset1">
                                <a class="brand margin" href="/">Главная</a>
                                <ul class="nav hidden">
                                    <li class="vertical-line"></li>
                                    <li class="${param.active=="forms-index"? "active": ""} margin"><a href="/forms">Анкеты</a></li>
                                    <li class="vertical-line"></li>
                                    <li class="${param.active=="analysis"? "active": ""} margin"><a href="/analysis">Анализ данных</a></li>
                                    <li class="vertical-line"></li>
                                </ul>
                                <form class="navbar-form pull-right inline">
                                    <input type="text" class="span2" placeholder="Введите email">
                                    <input type="text" class="span2" placeholder="Введите пароль">
                                    <button type="submit" class="btn">Войти</button>
                                    <div>
                                        <a class="pull-right" id="form-registration" >Зарегистрироватся</a>
                                    </div>
                                </form>
                            <%--<p class="navbar-text pull-right margin">Привет, <a class="" href="/"> Евгения!</a> &nbsp;--%>
                                <%--<a class="navbar-link pull-right" href="/">Выйти</a>--%>
                            <%--</p>--%>
                    </div>
                </div>
            </div>
        </div>


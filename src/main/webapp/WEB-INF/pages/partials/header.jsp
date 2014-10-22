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
    </head>
    <!--ВЫНЕСТИ СТИЛЬ
        ДОРАБОТАТЬ ФОРМУ АВТОРИЗАЦИИ-->
    <body style="padding: 40px">
        <div class="container-fluid">
            <!--Основная навигационная панель-->
            <div class="navbar navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container-fluid offset1">
                        <a class="brand" href="/">Главная</a>
                        <ul class="nav">
                            <li class="divider-vertical"></li>
                            <li class="${param.active=="forms-index"? "active": ""}"><a href="/forms">Анкеты</a></li>
                            <li class="divider-vertical"></li>
                            <li class="${param.active=="analysis"? "active": ""}"><a href="/analysis">Анализ данных</a></li>
                            <li class="divider-vertical"></li>
                        </ul>
                        <!--<form class="navbar-form pull-right inline">
                            <input type="text" class="span2" placeholder="Введите email">
                            <input type="text" class="span2" placeholder="Введите пароль">
                            <button type="submit" class="btn">Войти</button>
                        </form-->
                        <p class="navbar-text pull-right">Привет, admin! &nbsp;
                            <a class="navbar-link" href="/">Выйти</a>
                        </p>
                    </div>
                </div>
            </div>

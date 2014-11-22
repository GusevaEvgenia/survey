<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String requestURI = request.getRequestURI(); %>

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
                    <li class="<%= requestURI.contains("/forms/${form.idForm}")? "active": "" %>">
                        <a href="/forms/${form.idForm}">
                            <i class="icon-list-alt"></i> Анкета
                        </a>
                    </li>
                    <li class="${user.token!=null ? "hidden": ""} ${form.status=="archive" ? "hidden": ""}
                    <%= requestURI.contains("forms/${form.idForm}/designer")? "active": "" %>">
                        <a href="/forms/${form.idForm}/designer">
                            <i class="icon-pencil"></i> Конструктор
                        </a>
                    </li>
                    <li class="<%= requestURI.contains("forms/${form.idForm}/answers")? "active": "" %>">
                        <a href="/forms/${form.idForm}/answers">
                            <i class="icon-check"></i> Ответы
                        </a>
                    </li>
                    <li class="${user.token!=null ? "hidden": ""} ${form.status=="archive" ? "hidden": ""}
                     <%= requestURI.contains("forms/${form.idForm}/settings")? "active": "" %>">
                        <a href="/forms/${form.idForm}/settings">
                            <i class="icon-wrench"></i> Настройки
                        </a>
                    </li>
                    <li><a href="/forms/123/analysis/basic"><i class="icon-tasks"></i> Анализ</a></li>
                </ul>
            </div>
            <div class="span5">
                <div class="btn-group pull-right">
                    <c:if test='${user.token!=null}'>
                        <a class="btn btn-info" href="#"><i class="icon-globe"></i> Открыть в SM</a>
                    </c:if>
                    <c:if test='${user.token==null}'>
                        <a class="btn btn-info remove-btn1" href="#" data-id="${form.idForm}"><i class="icon-remove"></i> Удалить</a>
                    </c:if>
                    <a class="btn btn-info" href=""><i class="icon-globe"></i> Получить ссылку</a>
                    <a class="btn btn-info" href=""><i class="icon-download-alt"></i> Сохранить в файл</a>
                </div>
            </div>
        </div>
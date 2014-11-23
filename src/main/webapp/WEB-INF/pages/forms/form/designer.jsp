<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%--Подключение хейдера--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value="Анкета"/>
</jsp:include>
<jsp:include page="/WEB-INF/pages/forms/form/_menu.jsp"/>

<div class="thumbnail">
    <div class="row-fluid">
        <%--Выбор вопроса--%>
        <div class="span3">
            <ul class="nav nav-list margin-top20 template-list">
                <li class="nav-header">Типы вопросов</li>
                <li><a data-type="single-option" href="#"><!--i class="icon-search"></i-->Вопрос с единичным выбором</a></li>
                <li><a data-type="multiple-option" href="#">Вопрос с множественным выбором</a></li>
                <li><a data-type="single-option" href="#">Числовой ответ</a></li>
                <li><a data-type="single-option" href="#">Вопрос "список"</a></li>
                <li><a data-type="single-option" href="#">Вопрос "матрица" с единичным выбором</a></li>
                <li><a data-type="single-option" href="#">Вопрос "матрица" с множественным выбором</a></li>
                <li><a data-type="single-option" href="#">Вопрос "шкала"</a></li>
            </ul>
        </div>
        <%--Создание анкеты--%>
        <div class="span9">
            <div class="btn-group pull-right">
                <a class="btn btn-info" id="form-preview" href="#">Просмотр анкеты</a>
                <a class="btn btn-info" href="#">Опубликовать</a>
            </div>
            <div class="row-fluid">
                <div class="span9 offset1" id="form-constructor">
                    <div class="hidden" id="template-container">
                        <div id="main-template">
                            <!--Общая "коробочка" для всех типов вопросов-->
                            <div class="designer-item">
                                <div class="designer-item-header">
                                    <span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
                                    <span class="icon-remove"></span>
                                    <strong>Введите сдесь текст вашего вопроса...</strong>
                                </div>
                                <div class="designer-item-body">

                                </div>
                            </div>
                        </div>
                        <div id="single-option">
                            <!--Вопрос с единичным выбором-->
                            <p>
                                <label class="radio">
                                    <input type="radio" name="optionsRadios" value="option1" checked>
                                    Можно выбрать только один вариант ответа
                                </label>
                            </p>
                            <p>
                                <label class="radio">
                                    <input type="radio" name="optionsRadios" value="option2">
                                    Можно выбрать только один вариант ответа
                                </label>
                            </p>
                        </div>
                        <div id="multiple-option">
                            <!--Вопрос с множественным выбором-->
                            <p>
                                <label class="checkbox">
                                    <input type="checkbox" value="option1" checked>
                                    Можно выбрать несколько вариантов ответа
                                </label>
                            </p>
                            <p>
                                <label class="checkbox">
                                    <input type="checkbox" value="option2">
                                    Можно выбрать несколько вариантов ответа
                                </label>
                            </p>
                        </div>
                    </div>
                    <ul id="question-list">
                        <li>

                        </li>
                        <li>
                            <!--Вопрос "список"-->
                            <div>
                                <p><span class="ui-icon ui-icon-arrowthick-2-n-s"></span><strong>Введите сдесь текст вашего вопроса...</strong></p>
                                <p>
                                    <select>
                                        <option>Вариант ответа №1</option>
                                        <option>Вариант ответа №2</option>
                                        <option>Вариант ответа №3</option>
                                        <option>Вариант ответа №4</option>
                                        <option>Вариант ответа №5</option>
                                    </select>
                                </p>
                            </div>
                        </li>
                        <li>
                            <!--Вопрос "матрица"-->
                            <div>
                                <p><strong>Введите сдесь текст вашего вопроса...</strong></p>
                                <div class="row-fluid">
                                    <table>
                                        <tr>
                                            <td></td>
                                            <td><input type="text" class="input-mini" value="1"></td>
                                            <td><input type="text" class="input-mini" value="2"></td>
                                            <td><input type="text" class="input-mini" value="3"></td>
                                        </tr>
                                        <tr>
                                            <td><input type="text" class="input-mini" value="Ответ"></td>
                                            <td>
                                                <label class="radio text-center">
                                                    <input type="radio" name="opt" id="optionsRadios1" value="option1" checked>
                                                </label>

                                            </td>
                                            <td>
                                                <label class="radio">
                                                    <input type="radio" name="opt" id="optionsRadios2" value="option2">
                                                </label>
                                            </td>
                                            <td>
                                                <label class="radio">
                                                    <input type="radio" name="opt" id="optionsRadios2" value="option2">
                                                </label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="text" class="input-mini" value="Ответ"></td>
                                            <td>
                                                <label class="radio">
                                                    <input type="radio" name="opt1" id="optionsRadios1" value="option1" checked>
                                                </label>

                                            </td>
                                            <td>
                                                <label class="radio">
                                                    <input type="radio" name="opt1" id="optionsRadios2" value="option2">
                                                </label>
                                            </td>
                                            <td>
                                                <label class="radio ">
                                                    <input type="radio" name="opt1" id="optionsRadios2" value="option2">
                                                </label>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </li>
                        <li>
                            <!--Вопрос "матрица" с множественным выбором-->
                            <div>
                                <p><strong>Введите сдесь текст вашего вопроса...</strong></p>
                                <div class="row-fluid">
                                    <table>
                                        <tr>
                                            <td></td>
                                            <td><input type="text" class="input-mini" value="1"></td>
                                            <td><input type="text" class="input-mini" value="2"></td>
                                            <td><input type="text" class="input-mini" value="3"></td>
                                        </tr>
                                        <tr>
                                            <td><input type="text" class="input-mini" value="Ответ"></td>
                                            <td>
                                                <label class="checkbox text-center">
                                                    <input type="checkbox" name="opt" id="optionsRadios1" value="option1" checked>
                                                </label>

                                            </td>
                                            <td>
                                                <label class="checkbox">
                                                    <input type="checkbox" name="opt" id="optionsRadios2" value="option2">
                                                </label>
                                            </td>
                                            <td>
                                                <label class="checkbox">
                                                    <input type="checkbox" name="opt" id="optionsRadios2" value="option2">
                                                </label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="text" class="input-mini" value="Ответ"></td>
                                            <td>
                                                <label class="checkbox">
                                                    <input type="checkbox" name="opt1" id="optionsRadios1" value="option1" checked>
                                                </label>

                                            </td>
                                            <td>
                                                <label class="checkbox">
                                                    <input type="checkbox" name="opt1" id="optionsRadios2" value="option2">
                                                </label>
                                            </td>
                                            <td>
                                                <label class="checkbox">
                                                    <input type="checkbox" name="opt1" id="optionsRadios2" value="option2">
                                                </label>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <br>

                    <br>
                   <br>

                    <br>

                    <!--Вопрос "шкала"-->
                </div>
            </div>
        </div>
    </div>
</div>

<%--всплывающее окно просмотра анкеты--%>
<div class="modal fade" id="preview-popup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Предпросмотр анкеты</h4>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


</div>
</div>
<%--Подключение футера--%>
<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>
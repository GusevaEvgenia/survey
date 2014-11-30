<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%--Подключение хейдера--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <li><a data-type="number-option" href="#">Числовой ответ</a></li>
                <li><a data-type="select-option" href="#">Вопрос "список"</a></li>
                <li><a data-type="matrix-single-option" href="#">Вопрос "матрица" с единичным выбором</a></li>
                <li><a data-type="matrix-multiple-option" href="#">Вопрос "матрица" с множественным выбором</a></li>
                <%--<li><a data-type="single-option" href="#">Вопрос "шкала"</a></li>--%>
            </ul>
        </div>
        <%--Создание анкеты--%>
        <div class="span9">
            <div class="btn-group pull-right">
                <button class="btn btn-info" form="question-list" type="submit">Сохранить</button>
                <a class="btn btn-info" id="form-preview" href="#">Просмотр анкеты</a>
                <a class="btn btn-info" href="#">Опубликовать</a>
            </div><br><br>
            <div class="row-fluid">
                <div class="span9 offset1" id="form-constructor">
                    <div class="hidden" id="template-container">
                        <div id="main-template">
                            <!--Общая "коробочка" для всех типов вопросов-->
                            <div class="designer-item thumbnail">
                                <div class="designer-item-header">
                                    <div class="clearfix">
                                        <span class="icon-resize-vertical"></span>
                                        <strong class="title muted"></strong>
                                        <span class="icon-remove icon-remove-option pull-right"></span>
                                    </div>
                                    <textarea class="question" name="questions[0].text" placeholder=" Введите текст вашего вопроса"></textarea>
                                </div>
                                <div class="designer-item-body">

                                </div>
                            </div>
                        </div>
                        <div id="single-option" data-title="Вопрос с единичным выбором">
                            <!--Вопрос с единичным выбором-->
                            <label class="radio option-index">
                                <input type="radio" checked>
                                <textarea class="option" name="questions[0].options[0].text" placeholder=" Введите текст варианта ответа"></textarea>
                                <span class="icon-plus"></span>
                                <span class="icon-minus"></span>`
                            </label>
                            <label class="radio option-index">
                                <input type="radio">
                                <textarea class="option" name="questions[0].options[2].text" placeholder="Введите текст варианта ответа"></textarea>
                                <span class="icon-plus"></span>
                                <span class="icon-minus"></span>
                            </label>
                        </div>
                        <div id="multiple-option" data-title="Вопрос с множественным выбором">
                            <!--Вопрос с множественным выбором-->
                            <label class="checkbox">
                                <input type="checkbox" checked>
                                <textarea class="option" name="checkbox" placeholder=" Введите текст варианта ответа"></textarea>
                                <span class="icon-plus"></span>
                                <span class="icon-minus"></span>
                            </label>
                            <label class="checkbox">
                                <input type="checkbox">
                                <textarea class="option" name="checkbox" placeholder=" Введите текст варианта ответа"></textarea>
                                <span class="icon-plus"></span>
                                <span class="icon-minus"></span>
                            </label>
                        </div>
                        <div id="number-option" data-title="Вопрос с числовым ответом">
                            <!--Вопрос с числовым ответом-->
                            <label>
                                <input type="number" placeholder="Поле для ввода чисел">
                            </label>
                        </div>
                        <div id="select-option" data-title="Вопрос-список">
                            <!--Вопрос-список-->
                            <label>
                                <textarea class="option" name="select" placeholder=" Введите текст варианта ответа"></textarea>
                                <span class="icon-plus"></span>
                                <span class="icon-minus"></span>
                            </label>
                            <label>
                                <textarea class="option" name="select" placeholder=" Введите текст варианта ответа"></textarea>
                                <span class="icon-plus"></span>
                                <span class="icon-minus"></span>
                            </label>
                        </div>
                        <div id="matrix-single-option" data-title='Вопрос "матрица" с единичным выбором'>
                            <!--Вопрос "матрица" с единичным выбором-->
                            <div class="clearfix">
                                <div class="span4">
                                    <strong>Строки матрицы</strong>
                                    <label>
                                        <textarea class="matrix-option" name="text" placeholder="Текст строки"></textarea>
                                        <span class="icon-plus"></span>
                                        <span class="icon-minus"></span>
                                    </label>
                                    <label>
                                        <textarea class="matrix-option" name="text" placeholder="Текст строки"></textarea>
                                        <span class="icon-plus"></span>
                                        <span class="icon-minus"></span>
                                    </label>
                                </div>
                                <div class="span4">
                                    <strong>Столбцы матрицы</strong>
                                    <label>
                                        <textarea class="matrix-option" name="text" placeholder="Текст столбца"></textarea>
                                        <span class="icon-plus"></span>
                                        <span class="icon-minus"></span>
                                    </label>
                                    <label>
                                        <textarea class="matrix-option" name="text" placeholder="Текст столбца"></textarea>
                                        <span class="icon-plus"></span>
                                        <span class="icon-minus"></span>
                                    </label>
                                </div>
                            </div>
                               <%-- <table>
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
                                </table>--%>
                        </div>
                        <div id="matrix-multiple-option" data-title='Вопрос "матрица" с множественным выбором'>
                            <!--Вопрос "матрица" с множественным выбором-->
                            <div class="clearfix">
                                <div class="span4">
                                    <strong>Строки матрицы</strong>
                                    <label>
                                        <textarea class="matrix-option" name="text" placeholder="Текст строки"></textarea>
                                        <span class="icon-plus"></span>
                                        <span class="icon-minus"></span>
                                    </label>
                                    <label>
                                        <textarea class="matrix-option" name="text" placeholder="Текст строки"></textarea>
                                        <span class="icon-plus"></span>
                                        <span class="icon-minus"></span>
                                    </label>
                                </div>
                                <div class="span4">
                                    <strong>Столбцы матрицы</strong>
                                    <label>
                                        <textarea class="matrix-option" name="text" placeholder="Текст столбца"></textarea>
                                        <span class="icon-plus"></span>
                                        <span class="icon-minus"></span>
                                    </label>
                                    <label>
                                        <textarea class="matrix-option" name="text" placeholder="Текст столбца"></textarea>
                                        <span class="icon-plus"></span>
                                        <span class="icon-minus"></span>
                                    </label>
                                </div>
                            </div>
                            <%--<table>
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
                            </table>--%>
                        </div>
                    </div>
                    <form:form id="question-list" action="/forms/${form.idForm}/designer" method="post" commandName="form">
                    </form:form>
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
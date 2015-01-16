<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%--Подключение хейдера--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value="Анкета"/>
</jsp:include>
<jsp:include page="/WEB-INF/pages/forms/form/_menu.jsp"/>
<script>
    $(document).ready(function() {
        Designer.init();
    });
</script>

<div class="thumbnail">
    <div class="row-fluid">
        <%--Выбор вопроса--%>
        <div class="span3">
            <ul class="nav nav-list margin-top20 template-list">
                <li class="nav-header">Типы вопросов</li>
                <li><a data-type="single-option" href="#"><!--i class="icon-search"></i-->Вопрос с единичным выбором</a></li>
                <li><a data-type="multiple-option" href="#">Вопрос с множественным выбором</a></li>
                <li><a data-type="select-option" href="#">Вопрос "список"</a></li>
                <li><a data-type="matrix-single-option" href="#">Вопрос "матрица" с единичным выбором</a></li>
                <li><a data-type="matrix-multiple-option" href="#">Вопрос "матрица" с множественным выбором</a></li>
            </ul>
        </div>
        <%--Создание анкеты--%>
        <div class="span9">
            <div class="btn-group pull-right">
                <button class="btn btn-info" form="question-list" <%--id="save-form"  data-id="${form.idForm}" --%>type="submit">Сохранить</button>
                <a class="btn btn-info" id="form-preview" data-id="${form.idForm}" >Просмотр анкеты</a>
            </div><br><br>
            <div class="alert alert-error ${flag ? "" : "hidden"}" >
                <button type="button" class="close" data-dismiss="alert">х</button>
                <strong>Внимание!</strong> Вы изменили анкеты и изменения были сохранены в новой анкете.
                Перейдити к ней по <a href="/forms/${newForm.idForm}" target="_blank">ссылке</a>
            </div>
            <div class="row-fluid">
                <div class="span9 offset1" id="form-constructor" data-init='${form.json}'>
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
                                    <textarea class="question" name="questions[0].text" placeholder="Введите текст вашего вопроса"></textarea>
                                </div>
                                <div class="designer-item-body">

                                </div>
                                <div>
                                    <select class="question-scale" name="question[0].scale">
                                        <option value="nominal">номинальная</option>
                                        <option value="ordinal">порядковая</option>
                                        <option value="interval">интервальная</option>
                                        <option value="ratio">абсолютная</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div id="single-option" data-title="Вопрос с единичным выбором">
                            <input class="type-question" type="hidden" name="questions[0].idType" value="1">
                            <!--Вопрос с единичным выбором-->
                            <div class="options-container">
                                <label class="radio option-index">
                                    <input type="radio">
                                    <textarea class="option" name="questions[0].options[0].text" placeholder=" Введите текст варианта ответа"></textarea>
                                    <span class="icon-plus"></span>
                                    <span class="icon-minus"></span>`
                                </label>
                                <label class="radio option-index">
                                    <input type="radio">
                                    <textarea class="option" name="questions[0].options[1].text" placeholder="Введите текст варианта ответа"></textarea>
                                    <span class="icon-plus"></span>
                                    <span class="icon-minus"></span>
                                </label>
                            </div>
                        </div>
                        <div id="multiple-option" data-title="Вопрос с множественным выбором">
                            <input class="type-question" type="hidden" name="questions[0].idType" value="2">
                            <!--Вопрос с множественным выбором-->
                            <div class="options-container">
                                <label class="checkbox option-index">
                                    <input type="checkbox">
                                    <textarea class="option" name="questions[0].options[0].text" placeholder=" Введите текст варианта ответа"></textarea>
                                    <span class="icon-plus"></span>
                                    <span class="icon-minus"></span>
                                </label>
                                <label class="checkbox option-index">
                                    <input type="checkbox">
                                    <textarea class="option" name="questions[0].options[1].text" placeholder=" Введите текст варианта ответа"></textarea>
                                    <span class="icon-plus"></span>
                                    <span class="icon-minus"></span>
                                </label>
                            </div>
                        </div>
                        <div id="select-option" data-title="Вопрос-список">
                            <input class="type-question" type="hidden" name="questions[0].idType" value="3">
                            <!--Вопрос-список-->
                            <div class="options-container">
                                <label class="option-index">
                                    <textarea class="option" name="questions[0].options[0].text" placeholder=" Введите текст варианта ответа"></textarea>
                                    <span class="icon-plus"></span>
                                    <span class="icon-minus"></span>
                                </label>
                                <label class="option-index">
                                    <textarea class="option" name="questions[0].options[1].text" placeholder=" Введите текст варианта ответа"></textarea>
                                    <span class="icon-plus"></span>
                                    <span class="icon-minus"></span>
                                </label>
                            </div>
                        </div>
                        <div id="matrix-single-option" data-title='Вопрос "матрица" с единичным выбором'>
                            <input class="type-question" type="hidden" name="questions[0].idType" value="4">
                            <!--Вопрос "матрица" с единичным выбором-->
                            <div class="clearfix">
                                <div class="span4">
                                    <strong>Строки матрицы</strong>
                                    <div class="options-container">
                                        <label class="option-index">
                                            <textarea class="matrix-option" name="questions[0].options[0].text" placeholder="Текст строки"></textarea>
                                            <span class="icon-plus"></span>
                                            <span class="icon-minus"></span>
                                        </label>
                                        <label class="option-index">
                                            <textarea class="matrix-option" name="questions[0].options[1].text" placeholder="Текст строки"></textarea>
                                            <span class="icon-plus"></span>
                                            <span class="icon-minus"></span>
                                        </label>
                                    </div>
                                </div>
                                <div class="span4">
                                    <strong>Столбцы матрицы</strong>
                                    <div class="matrix-options-container">
                                        <label class="option-index">
                                            <textarea class="matrix-option" name="questions[0].options[0].textMatrix" placeholder="Текст столбца"></textarea>
                                            <span class="icon-plus"></span>
                                            <span class="icon-minus"></span>
                                        </label>
                                        <label class="option-index">
                                            <textarea class="matrix-option" name="questions[0].options[1].textMatrix" placeholder="Текст столбца"></textarea>
                                            <span class="icon-plus"></span>
                                            <span class="icon-minus"></span>
                                        </label>
                                    </div>
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
                            <input class="type-question" type="hidden" name="questions[0].idType" value="5">
                            <!--Вопрос "матрица" с множественным выбором-->
                            <div class="clearfix">
                                <div class="span4">
                                    <strong>Строки матрицы</strong>
                                    <div class="options-container">
                                        <label class="option-index">
                                            <textarea class="matrix-option" name="text" placeholder="Текст строки"></textarea>
                                            <span class="icon-plus"></span>
                                            <span class="icon-minus"></span>
                                        </label>
                                        <label class="option-index">
                                            <textarea class="matrix-option" name="text" placeholder="Текст строки"></textarea>
                                            <span class="icon-plus"></span>
                                            <span class="icon-minus"></span>
                                        </label>
                                    </div>
                                </div>
                                <div class="span4">
                                    <strong>Столбцы матрицы</strong>
                                    <div class="matrix-options-container">
                                        <label class="option-index">
                                            <textarea class="matrix-option" name="text" placeholder="Текст столбца"></textarea>
                                            <span class="icon-plus"></span>
                                            <span class="icon-minus"></span>
                                        </label>
                                        <label class="option-index">
                                            <textarea class="matrix-option" name="text" placeholder="Текст столбца"></textarea>
                                            <span class="icon-plus"></span>
                                            <span class="icon-minus"></span>
                                        </label>
                                    </div>
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
                    <%--<form:form method="post" action="/forms/${form.idForm}/preview" commandName="form" />--%>
                    <%--method="post"  action="/forms/${form.idForm}/designer"--%>
                    <form:form id="question-list" method="post" commandName="form">
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
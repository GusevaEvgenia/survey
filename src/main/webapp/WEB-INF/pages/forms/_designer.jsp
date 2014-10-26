<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    $(document).ready(function() {
        $("#form-preview").click(function() {
            var name = $("input[name='name']").val();

            var form = $("#form-constructor").html();
            $('#preview-popup').find(".modal-body").html("<h2>" + name + "</h2>" + form);
            $('#preview-popup').modal('show');
        });
    });
</script>

<div class="thumbnail">
    <div class="row-fluid">
        <%--Выбор вопроса--%>
        <div class="span3">
            <ul class="nav nav-list margin-top20">
                <li class="nav-header">Типы вопросов</li>
                <li><a href="#"><!--i class="icon-search"></i-->Вопрос с единичным выбором</a></li>
                <li><a href="#">Вопрос с множественным выбором</a></li>
                <li class=""><a href="#">Числовой ответ</a></li>
                <li><a href="#">Вопрос "список"</a></li>
                <li><a href="#">Вопрос "матрица" с единичным выбором</a></li>
                <li><a href="#">Вопрос "матрица" с множественным выбором</a></li>
                <li><a href="#">Вопрос "шкала"</a></li>
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
                    <!--Открытый вопрос-->

                    <!--Вопрос с единичным выбором-->
                    <br>
                    <div>
                        <p><strong>Введите сдесь текст вашего вопроса...</strong></p>
                        <p>
                            <label class="radio">
                                <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
                                Можно выбрать только один вариант ответа
                            </label>
                        </p>
                        <p>
                            <label class="radio">
                                <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                                Можно выбрать только один вариант ответа
                            </label>
                        </p>
                    </div><br>
                    <!--Вопрос с множественным выбором-->
                    <div >
                        <p><strong>Введите сдесь текст вашего вопроса...</strong></p>
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
                    <br>
                    <!--Вопрос "список"-->
                    <div>
                        <p><strong>Введите сдесь текст вашего вопроса...</strong></p>
                        <p>
                            <select>
                                <option>Вариант ответа №1</option>
                                <option>Вариант ответа №2</option>
                                <option>Вариант ответа №3</option>
                                <option>Вариант ответа №4</option>
                                <option>Вариант ответа №5</option>
                            </select>
                        </p>
                    </div><br>
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
                    <br>
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
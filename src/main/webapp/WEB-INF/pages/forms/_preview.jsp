<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row-fluid">
    <div class="span10 offset1">
        <div class="thumbnail">
            <div class="page-header">
                <h1 class="text-center">Название анкеты<br>
                    <small>
                        Описание анкеты
                    </small>
                </h1>
            </div>
            <!--Вопрос с одним вариантом ответа-->
            <div class="row-fluid margine-botton15">
                <div class="span10 offset1">
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
                </div>
            </div>
            <!--Вопрос с несколькими вариантами ответа-->
            <div class="row-fluid">
                <div class="span10 offset1 margine-botton15">
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
            </div>
            <!--Открытый вопрос-->
            <div class="row-fluid">
                <div class="span10 offset1 margine-botton15">
                    <p><strong>Введите сдесь текст вашего вопроса...</strong></p>
                    <p>
                        <input type="type">
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
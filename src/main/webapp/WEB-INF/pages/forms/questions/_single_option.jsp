<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--Вопрос с одним вариантом ответа-->
<div class="row-fluid margin-button15">
    <div class="span10 offset1">
        <p><strong>${question.text}</strong></p>
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

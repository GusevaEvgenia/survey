<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--Вопрос с несколькими вариантами ответа-->
<div class="row-fluid">
    <div class="span10 offset1 margin-button15">
        <p><strong>${question.text}</strong></p>
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

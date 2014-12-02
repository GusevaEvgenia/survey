<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--Открытый вопрос-->
<div class="row-fluid">
    <div class="span10 offset1 margin-button15">
        <p><strong>${designer.questions[i].text}</strong></p>
        <p>
            <input type="number" name="option[${designer.questions[i].idQuestion}]">
        </p>
    </div>
</div>

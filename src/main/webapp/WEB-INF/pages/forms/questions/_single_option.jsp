<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--Вопрос с одним вариантом ответа-->
<div class="row-fluid margin-button15">
    <div class="span10 offset1">
        <p><strong>${question.text}</strong></p>
        <div>
            <c:forEach items='${question.answerOptionsesByIdQuestion}' var="option">
                <p>
                    <label class="radio">
                        <input type="radio" name="option[${question.idQuestion}]" value="${option.idOption}">
                            ${option.text}
                    </label>
                </p>
            </c:forEach>
        </div>
    </div>
</div>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--Вопрос с несколькими вариантами ответа-->
<div class="row-fluid">
    <div class="span10 offset1 margin-button15">
        <p><strong>${question.text}</strong></p>
        <div>
            <c:forEach items='${question.answerOptionsesByIdQuestion}' var="option">
                <p>
                    <label class="radio">
                        <input type="checkbox" name="option[${question.idQuestion}][${option.idOption}]" value="${option.idOption}">
                            ${option.text}
                    </label>
                </p>
            </c:forEach>
        </div>
    </div>
</div>

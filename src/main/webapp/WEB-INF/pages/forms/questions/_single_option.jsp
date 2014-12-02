<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--Вопрос с одним вариантом ответа-->
<div class="row-fluid margin-button15">
    <div class="span10 offset1">
        <p><strong>${designer.questions[i].text}</strong></p>
        <div>
            <c:forEach var="j" begin="0" end="${designer.questions[i].size}">
                <p>
                    <label class="radio">
                        <input type="radio" name="option[${designer.questions[i].idQuestion}]" value="${designer.questions[i].options[j].idOption}">
                            ${designer.questions[i].options[j].text}
                    </label>
                </p>
            </c:forEach>
        </div>
    </div>
</div>

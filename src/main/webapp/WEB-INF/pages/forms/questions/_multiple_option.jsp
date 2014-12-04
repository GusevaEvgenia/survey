<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--Вопрос с несколькими вариантами ответа-->
<div class="row-fluid">
    <div class="span10 offset1 margin-button15">
        <p><strong>${designer.questions[i].text}</strong></p>
        <div>
            <c:forEach var="j" begin="0" end="${designer.questions[i].size}">
                <p>
                    <label class="radio">
                        <input
                                type="checkbox"
                                name="option[${designer.questions[i].idQuestion}][${designer.questions[i].options[j].idOption}]"
                                value="${designer.questions[i].options[j].idOption}">
                        ${designer.questions[i].options[j].text}
                    </label>
                </p>
            </c:forEach>
        </div>
    </div>
</div>

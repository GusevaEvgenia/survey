<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

<div class="row-fluid">
    <div class="span10 offset1">
        <div class="hero-unit hero">
            <h2>Анализ собранных данных по выбранной анкете<!--br><small>Каталог сохраненных анкет:</small--></h2>
        </div>

        <jsp:include page="/WEB-INF/pages/analysis/_menu.jsp"/>

        <script>
            $(document).ready(function () {
                $(".regOrProg").change(function () {
                    if ($(this).val() === "1") {
                        $("#regress-analys").removeClass("hidden");
                        $("#progress").addClass("hidden");
                    } else {
                        $("#progress").removeClass("hidden");
                        $("#regress-analys").addClass("hidden");
                    }
                });

                /*$(".btn-regress").click(function() {
                 var id = $(this).data('id');
                 window.location.href = "/forms/"+id+"/analysis/regression";
                 });*/
                /*$(".btn-prognoz").click(function() {
                 var id = $(this).data('id');
                 window.location.href = "/forms/"+id+"/analysis/regression/prognoz";
                 });*/
                $('#table-reg-q').submit(function(e) {
                    if($("select[name='main_parameter']").val() == $("select[name='first_parameter']").val()) {
                        e.preventDefault();
                        alert("Выберите пожайлуста разные вопросы");
                        return false;
                    }
                });
            });
        </script>
        <%--<input type="hidden" name="page" value="2">--%>
        <label class="radio">
            <input class="regOrProg" type="radio" name="regOrProg" value="1" checked>
            <h4>Выберите параметры для проведения регрессионого анализа</h4>
        </label>

        <form action="/forms/${form.idForm}/analysis/regression/result" method="post" id="table-reg-q">
            <div class="" id="regress-analys">
                <strong>Выберите переменные:</strong>
                <table>
                    <tr>
                        <td class="width220 ">Зависимая переменная</td>
                        <td>
                            <select name="main_parameter">
                                <c:forEach items='${questions}' var="question">
                                    <option value="${question.idQuestion}">
                                            ${question.text}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="width220">Независимая переменная</td>
                        <td>
                            <select name="first_parameter">
                                <c:forEach items='${questions}' var="question">
                                    <option value="${question.idQuestion}">
                                            ${question.text}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
                <button class="btn btn-primary">Провести анализ</button>
            </div>
        </form>
        <label class="radio">
            <input class="regOrProg" type="radio" name="regOrProg" value="2">
            <h4>Провести прогнозирование по существующей модели</h4>
        </label>
        <form action="/forms/${form.idForm}/analysis/regression/prognoz" method="post">
            <div class="hidden" id="progress">
                Модель парной регрессии<br>
                Уравнение в виде линейной регресии имеет вид<br>
                yi = a + b * xi<br><br>
                Введите параметры модели<br><br>
                a = <input class="input-mini" type="text" name="paramA"><br>
                b = <input class="input-mini" type="text" name="paramB"><br>
                <button class="btn btn-primary">Прогноз</button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>

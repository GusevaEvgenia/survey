<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

<div class="row-fluid">
    <div class="span10 offset1">
        <div class="page-header height120" style="background-image: url(/images/background.jpg); padding: 30px; color: #ffffff">
            <h1>Анализ собранных данных по выбранной анкете<!--br><small>Каталог сохраненных анкет:</small--></h1>
        </div>

        <jsp:include page="/WEB-INF/pages/analysis/_menu.jsp"/>

        <script>
            $(document).ready(function() {
                $(".regOrProg").change(function() {
                    if ($(this).val() === "1") {
                        $("#regress-analys").removeClass("hidden");
                        $("#progress").addClass("hidden");
                    } else {
                        $("#progress").removeClass("hidden");
                        $("#regress-analys").addClass("hidden");
                    }
                });

                $(".btn-regress").click(function() {
                    window.location.href = "/forms/123/analysis/regression";
                });
                $(".btn-prognoz").click(function() {
                    window.location.href = "/forms/123/analysis/regression/prognoz";
                });
            });
        </script>
        <form action="/forms/123/analysis/regression/result">
            <input type="hidden" name="page" value="2">
            <label class="radio">
                <input class="regOrProg"  type="radio" name="regOrProg" value="1" checked>
                <h4>Выберите параметры для проведения регрессионого анализа</h4>
            </label>
            <div class="" id="regress-analys">
                <strong>Выберите переменные:</strong>
                <table>
                    <tr>
                        <td class="width220 ">Зависимая переменная</td>
                        <td>
                            <select name="main_parameter">
                                <% for (int i = 1; i < 6; i++) {%>
                                <option value="<%=i%>">
                                    Вопрос№<%=i%>
                                </option>
                                <%}%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="width220">Независимая переменная</td>
                        <td>
                            <select name="first_parameter">
                                <% for (int i = 1; i < 6; i++) {%>
                                <option value="<%=i%>">
                                    Вопрос№<%=i%>
                                </option>
                                <%}%>
                            </select>
                        </td>
                    </tr>
                </table><br>
                <button class="btn btn-primary btn-regress" data-id="1">Провести анализ</button>
            </div>

            <label class="radio">
                <input class="regOrProg" type="radio" name="regOrProg" value="2">
                <h4>Провести прогнозирование по существующей модели</h4>
            </label>
        </form>
        <div class="hidden" id="progress">
            Модель парной регрессии<br>
            Уравнение в виде линейной регресии имеет вид<br><br>
            yi = a + b * xi<br><br>
            Введите параметры модели<br><br>
            a = <input class="input-mini" type="text"><br>
            b = <input class="input-mini" type="text"><br>
            <button class="btn btn-primary btn-prognoz" data-id="2">Прогноз</button>
        </div>
        <br>
    </div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            $(document).ready(function() {
                function tableToggle(size) {
                    if (size === "2") {
                        $(".table_2parameter").removeClass("hidden");
                        $(".table_3parameter").addClass("hidden");
                    } else if (size === "3") {
                        $(".table_3parameter").removeClass("hidden");
                        $(".table_2parameter").addClass("hidden");
                    }
                }
                tableToggle(getQueryVariable('parameter_size'));

                function dependence(tstatictic) {
                    if (tstatictic === "1") {
                        $(".dependence").removeClass("hidden");
                    } else {
                        $(".dependence").addClass("hidden");
                    }
                }
                dependence(getQueryVariable('dependence'));

                function strength_links(tstatictic) {
                    if (tstatictic === "2") {
                        $(".strength_links").removeClass("hidden");
                    } else {
                        $(".strength_links").addClass("hidden");
                    }
                }
                strength_links(getQueryVariable('strength_links'));

            });

            function getQueryVariable(variable) {
                var query = window.location.search.substring(1);
                var vars = query.split("&");
                for (var i = 0; i < vars.length; i++) {
                    var pair = vars[i].split("=");
                    if (pair[0] === variable) {
                        return pair[1];
                    }
                }
                return null;
            }
        </script>
        <% int size_first_param = 2;
            int size_second_param = 2;
            int size_fird_param = 2;
        %>
        <table class="table table-bordered table_2parameter">
            <tr>
                <th></th>
                <th colspan="<%=size_second_param%>">Вопрос№${param.first_parameter} (Независимая переменная)</th>
            </tr>
            <tr>
                <th>Вопрос№${param.main_parameter}(Зависимая переменная)</th>
                <%for (int i = 1; i <= size_second_param; i++) {%>
                <td>Вариант ответа№<%=i%></td>
                <%}%>
            </tr>
            <%for (int i = 1; i <= size_first_param; i++) {%>
            <tr>
                <td>Вариант ответа№<%=i%></td>
                <%for (int j = 1; j <= size_second_param; j++) {%>
                <td>Значение <%=i%><%=j%></td>
                <%}%>
            </tr>
            <%}%>
            <tr>
                <th>Итого</th>
                <%for (int i = 1; i <= size_second_param; i++) {%>
                <td>Сумма</td>
                <%}%>
            </tr>
        </table>
        <table class="table table-bordered table_3parameter">
            <tr>
                <td></td>
                <th colspan="<%=size_fird_param * 2%>">Вопрос№${param.first_parameter} (Независимая переменная 1)</th>
            </tr>
            <tr>
                <td></td>
                <%for (int i = 1; i <= size_second_param; i++) {%>
                <td colspan="<%=size_fird_param%>">Вариант ответа№<%=i%></td>
                <%}%>
            </tr>
            <tr>
                <td></td>
                <%for (int i = 1; i <= size_second_param; i++) {%>
                <th colspan="<%=size_fird_param%>">Вопрос№${param.second_parameter} (Независимая переменная 2)</th>
                <%}%>
            </tr>
            <tr>
                <th>Вопрос№${param.main_parameter} (Зависимая переменная)</th>
                <%for (int i = 1; i <= size_second_param; i++) {%>
                <%for (int j = 1; j <= size_fird_param; j++) {%>
                <td>Вариант ответа№<%=i%></td>
                <%}%>
                <%}%>
            </tr>
            <%for (int i = 1; i <= size_first_param; i++) {%>
            <tr>
                <td>Вариант ответа№<%=i%></td>
                <%for (int j = 1; j <= size_second_param; j++) {%>
                <%for (int l = 1; l <= size_fird_param; l++) {%>
                <td>Значение<%=j%><%=l%></td>
                <%}%>
                <%}%>
            </tr>
            <%}%>
            <tr>
                <th>Итого</th>
                <%for (int i = 1; i <= size_second_param; i++) {%>
                <%for (int j = 1; j <= size_fird_param; j++) {%>
                <td>Сумма</td>
                <%}%>
                <%}%>
            </tr>
        </table>
        <div class="dependence hidden margine-botton15">
            <strong>Зависимость наблюдаемой связи</strong><br>
            Расчитаное значение хи-квадрата = 3.88<br>
            Критического значение хи-квадрата = 3.77<br>
            3.77>3.88
        </div>

        <div class="strength_links hidden margine-botton15">
            <strong>Теснота связи</strong><br>
            Теснота связи = 0.55<br>

        </div>

        <a class="tb pull-left btn btn-primary" href="/forms/123/analysis/table">Назад</a><br>

        <br>
    </div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>

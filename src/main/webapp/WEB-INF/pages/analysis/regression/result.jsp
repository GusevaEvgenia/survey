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
    $(document).ready(function () {
        var type_changed = false;

        $(".next a").click(function () {
            switch ($(this).data("step")) {
                case 1:
                    reset_results();
                    switch_tab();
                    break;
                case 2:
                    reset_results();
                    if (Math.abs(parseFloat($("#corelation").text())) >= 0.8) {
                        switch_tab();
                    } else {
                        $(".regression-tabs li#finish-handler").find("a").click();
                        $(".regression-tabs li.active").removeClass("hidden");
                    }
                    break;
                case 3:
                    if (true) {
                        switch_tab();
                    } else {
                        $(".regression-tabs li#finish-handler").find("a").click();
                        $(".regression-tabs li.active").removeClass("hidden");
                    }
                    break;
                default:
                    switch_tab();
            }


        });
        $("select[name='important_level']").change(function () {
            type_changed = true;
        });
        $(".previous a").click(function () {
            switch ($(this).data("step")) {
                case 1:
                    window.location.href = "/forms/123/analysis/regression";
                    break;
                case "finish":
                    $(".regression-tabs li.active").prevAll(':visible').first().find("a").click();
                    break;
                default:
                    $(".regression-tabs li.active").prev().find("a").click();
            }
        });
        $(".function-type").change(function () {
            type_changed = true;
            $(".model-regression").addClass("hidden");
            $("#" + $(this).val()).removeClass("hidden");

            if ($(this).val() === "function-2") {
                $(".unline_form").removeClass("hidden");
            } else {
                $(".unline_form").addClass("hidden");
            }
        });
        $("#prognoz_value").keypress(function () {
            $("#prognoz_result").text(prognoz($(this).val()));
        });


        function switch_tab() {
            $(".regression-tabs li.active").next().find("a").click();
            $(".regression-tabs li.active").removeClass("hidden");
        }

        function reset_results() {
            if (type_changed) {
                $(".regression-tabs li.active").nextAll().addClass("hidden");
                type_changed = false;
            }
        }

        function prognoz(x) {
            return x * 12 + 0.45;
        }
    });
</script>
<div class="tabbable">
    <ul class="nav nav-tabs regression-tabs">
        <li class="active"><a href="#step1" data-toggle="tab">Шаг 1</a></li>
        <% int size = 7;
            for (int i = 2; i <= size; i++) {%>
        <li class="hidden"><a href="#step<%=i%>" data-toggle="tab">Шаг <%=i%>
        </a></li>
        <%}%>
        <li class="hidden" id="finish-handler"><a href="#finish" data-toggle="tab">Конец</a></li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="step1">
            <h3>Поле корреляции</h3>

            <div class="thumbnail margin-cenrto">
                <img src="/images/graf2.png" alt="">
            </div>
            <br>
            Определите форму зависимости между переменными
            <div class="row-fluid">
                <div class="span4">
                    <label class="radio">
                        <input class="function-type" type="radio" name="depend_form" value="function-1" checked>
                        Линейная
                    </label>
                    <img src="/images/line-regression.png">
                </div>
                <div class="span4">
                    <label class="radio">
                        <input class="function-type" type="radio" name="depend_form" value="function-2">
                        Нелинейная
                    </label>
                    <img src="/images/unline-regression.png">
                </div>
                <div class="span4">
                    <label class="radio">
                        <input class="function-type" type="radio" name="depend_form" value="3">
                        Не определена <br>
                        <img src="/images/non-regression.png">
                    </label>

                </div>
            </div>
            <div class="row-fluid hidden unline_form">
                <div class="span4">
                    <label class="radio">
                        <input class="" type="radio" name="unline_form" value="function-1" checked>
                        Линейная
                    </label>
                    <img src="/images/line-regression.png">
                </div>
                <div class="span4">
                    <label class="radio">
                        <input class="" type="radio" name="unline_form" value="function-2">
                        Нелинейная
                    </label>
                    <img src="/images/unline-regression.png">
                </div>
                <div class="span4">
                    <label class="radio">
                        <input class="" type="radio" name="unline_form" value="3">
                        Не определена
                    </label>
                    <img src="/images/non-regression.png">
                </div>
            </div>
            <ul class="pager">
                <li class="previous">
                    <a data-step="1" href="#">&larr; Назад</a>
                </li>
                <li class="next">
                    <a href="#" data-step="1">Далее &rarr;</a>
                </li>
            </ul>
        </div>
        <div class="tab-pane" id="step2">
            <div class="model-regression" id="function-1">
                <h3>Модель парной регрессии</h3>
                Уравнение в виде линейной регресии имеет вид<br><br>
                yi = a + b * xi<br><br>
            </div>
            <div class="model-regression hidden" id="function-2">
                <h3>Модель парной регрессии</h3>
                Нелинейная регрресия относится к степенной и имеет уравнение вида:<br>
                yi = a + b * xi<br>

                Уравнение в виде линейной регресии имеет вид<br>
                yi = a + b * xi
                <small>i</small>
                <br>
            </div>
            <div>
                где a = 0.5<br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; b = 4<br><br>

                Выберите уровень значимости l
                <select name="important_level">
                    <% for (double i = 1; i < 6; i++) {%>
                    <option value="<%=i / 16%>">
                        <%=i / 16%>
                    </option>
                    <%}%>
                </select>
            </div>
            <ul class="pager">
                <li class="previous">
                    <a href="#" data-step="2">&larr; Назад</a>
                </li>
                <li class="next">
                    <a href="#" data-step="2">Далее &rarr;</a>
                </li>
            </ul>
        </div>
        <div class="tab-pane" id="step3">
            <h3>Проверка значимости с помощью t-критерия</h3><br>
            <strong>Гипотеза</strong><br><br>
            H0:β1=0<br>
            H1:β1≠0<br><br>
            Нулевая гипотеза предполагает, что между Х и У не существует линейной зависимости.<br>
            Альтернативная гипотеза утверждает, что между X и У существует зависимость, либо положительная, либо
            отрицательная.<br><br>
            t-вычисленное = 8,414<br>
            t-расчетное = 2,414<br><br>
            8,414>2,414 Следовательно нулевую гипотезу отклоняют
            <ul class="pager">
                <li class="previous">
                    <a href="#" data-step="3">&larr; Назад</a>
                </li>
                <li class="next">
                    <a href="#" data-step="3">Далее &rarr;</a>
                </li>
            </ul>
        </div>
        <div class="tab-pane" id="step4">
            <h3>Оценка тесноты и оценка значимости уравнения</h3><br>
            Коэффициентом детерминации г2 (теснота связи) = 0,87<br><br>
            Проверка значимости коэффициента детерминации с помощью F-критерия.<br>

            F-вычисленное = 8,414<br>
            F-расчетное = 2,414<br><br>
            8,414>2,414 Следовательно нулевую гипотезу отклоняют <br>
            <ul class="pager">
                <li class="previous">
                    <a href="#" data-step="4">&larr; Назад</a>
                </li>
                <li class="next">
                    <a href="#" data-step="4">Далее &rarr;</a>
                </li>
            </ul>
        </div>
        <div class="tab-pane" id="step5">
            <h3>Проверка устойчивости корреляции и условий по остаткам</h3>
            Кореляция = <span id="corelation">0.85</span><br>
            <br>
            Сумма остатков = 0,5<br>
            <ul class="pager">
                <li class="previous">
                    <a href="#" data-step="5">&larr; Назад</a>
                </li>
                <li class="next">
                    <a href="#" data-step="5">Далее &rarr;</a>
                </li>
            </ul>
        </div>
        <div class="tab-pane" id="step6">
            <h3>Стандартная ошибка оценки уравнения регрессии SEE</h3>
            Эта статистика представляет собой стандартное отклонение фактических значений У от предсказанных значений
            У<br><br>
            SEE = 0.3 <br>
            <ul class="pager">
                <li class="previous">
                    <a href="#" data-step="6">&larr; Назад</a>
                </li>
                <li class="next">
                    <a href="#" data-step="6">Далее &rarr;</a>
                </li>
            </ul>
        </div>
        <div class="tab-pane" id="step7">
            <jsp:include page="/WEB-INF/pages/analysis/regression/_prognoz.jsp"/>

            <ul class="pager">
                <li class="previous">
                    <a href="#" data-step="7">&larr; Назад</a>
                </li>
                <!--li class="next">
                    <a href="#" data-step="7">Далее &rarr;</a>
                </li-->
            </ul>
        </div>
        <div class="tab-pane" id="finish">
            <h3>Результаты</h3>

            <ul class="pager">
                <li class="previous">
                    <a href="#" data-step="finish">&larr; Назад</a>
                </li>
                <li class="next">
                    <a href="#">Далее &rarr;</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<br>
</div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>

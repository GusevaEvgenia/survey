<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row-fluid">
<div class="span10 offset1">
<div class="hero-unit hero">
    <h2>Анализ собранных данных по выбранной анкете<!--br><small>Каталог сохраненных анкет:</small--></h2>
</div>

<jsp:include page="/WEB-INF/pages/analysis/_menu.jsp"/>

<script type="text/javascript" src="/js/regression.js">

</script>

<div class="tabbable">
    <%--шаги анализа--%>
    <ul class="nav nav-tabs regression-tabs">
        <li class="active"><a href="#step1" data-toggle="tab">Шаг 1</a></li>
        <c:forEach  begin="2" end="8" var="i">
            <li class="hidden">
                <a href="#step${i}" data-toggle="tab">Шаг ${i}</a>
            </li>
        </c:forEach>


        <li class="hidden" id="finish-handler">
            <a href="#finish" data-toggle="tab">Конец</a>
        </li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="step1">
            <%--TODO график--%>
            <h3>Поле корреляции</h3>
            <div class="thumbnail margin-cenrto" id="corelation-field" style="width: 700px">
            </div>
                <script>
                    $(function () {
                        var data = [
                            <c:forEach items='${regress.answers}' var="answers">
                                [${answers["codeX"]}, ${answers["codeY"]}],
                            </c:forEach>

                        ];

                        $('#corelation-field').highcharts({
                            chart: {
                                type: 'scatter',
                                zoomType: 'xy'
                            },
                            title: {
                                text: ''
                            },
                            xAxis: {
                                title: {
                                    enabled: true,
                                    text: ''
                                },
                                startOnTick: true,
                                endOnTick: true,
                                showLastLabel: true
                            },
                            yAxis: {
                                title: {
                                    text: ''
                                }
                            },
                            legend: {
                                layout: 'vertical',
                                align: 'left',
                                verticalAlign: 'top',
                                x: 100,
                                y: 70,
                                floating: true,
                                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF',
                                borderWidth: 1
                            },
                            plotOptions: {
                                scatter: {
                                    marker: {
                                        radius: 5,
                                        states: {
                                            hover: {
                                                enabled: true,
                                                lineColor: 'rgb(100,100,100)'
                                            }
                                        }
                                    },
                                    states: {
                                        hover: {
                                            marker: {
                                                enabled: false
                                            }
                                        }
                                    },
                                    tooltip: {
                                        headerFormat: 'Точка ',
                                        pointFormat: '[{point.x},{point.y}]'
                                    }
                                }
                            },
                            series: [{
                                name: 'Точки',
                                color: 'rgba(223, 83, 83, .5)',
                                data: data/*[
                                    [161.0, 53.6], [151.1, 73.2], [168.2, 53.4], [168.9, 69.0], [173.2, 58.4],

                                    [176.5, 71.8], [164.4, 55.5], [160.7, 48.6], [174.0, 66.4], [163.8, 67.3]]*/

                            }]
                        });
                    });
                </script>
            <br>
            <%--&lt;%&ndash;TODO рисунок нелинейных форм, если неопределена то выход&ndash;%&gt;
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
            </div>--%>
            <ul class="pager">
                <li class="previous">
                    <a data-step="1" href="#">&larr; Назад</a>
                </li>
                <li class="next">
                    <a href="#" data-step="1">Далее &rarr;</a>
                </li>
            </ul>
        </div>
        <div class="tab-pane" id="step2" data-target="/forms/${form.idForm}/analysis/regression/step/2"></div>
        <div class="tab-pane" id="step3" data-target="/forms/${form.idForm}/analysis/regression/step/3"></div>
        <div class="tab-pane" id="step4" data-target="/forms/${form.idForm}/analysis/regression/step/5"></div>
        <div class="tab-pane" id="step5" data-target="/forms/${form.idForm}/analysis/regression/step/4"></div>
        <div class="tab-pane" id="step6" data-target="/forms/${form.idForm}/analysis/regression/step/6"></div>
        <div class="tab-pane" id="step7" data-target="/forms/${form.idForm}/analysis/regression/step/7"></div>
        <div class="tab-pane" id="step8" data-target="/forms/${form.idForm}/analysis/regression/step/8"></div>
        <div class="tab-pane" id="finish">
            <h3>Результаты</h3>

            <ul class="pager">
                <li class="previous">
                    <a href="#" data-step="finish">&larr; Назад</a>
                </li>
                <%--<li class="next">
                    <a href="#">Далее &rarr;</a>
                </li>--%>
            </ul>
        </div>
    </div>
</div>
<br>
</div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>

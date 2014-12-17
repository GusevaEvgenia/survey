<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script>
    $(document).ready(function() {
        $(".bb").click(function() {
            var tab = $(this).parents('.ba-quation-tab');
            $(tab).find(".ba-settings").removeClass("hidden");
            $(tab).find('.ba-analyse-result').addClass("hidden");
        });
    });
</script>
<button class="bb pull-left btn btn-primary">Назад</button><br><br>
<c:if test="${fn:contains(basic.types,1)}">
    <!--Вариационны ряд-->
    <div>
        <h5>Вариационны ряд:</h5>
        <table class="table table-bordered">
            <tr>
                <th>№</th>
                <th>Вариант ответа</th>
                <th>Выбранное количество</th>
                <th>Выбрано(%)</th>
            </tr>
            <c:forEach items='${basic.variationLine}' var="answer" varStatus="loop">
                <tr>
                    <td>${loop.index+1}</td>
                    <td>${answer.get(0)}</td>
                    <td>${answer.get(1)}</td>
                    <td>${answer.get(2)}</td>
                </tr>
            </c:forEach>
        </table>
        <div id="variation-line-${basic.question.id}" style="width: 500px; height: 400px;"></div>
        <script>
            $(function () {
                var data = [
                    <c:forEach items='${basic.variationLine}' var="answer" varStatus="loop">
                        <c:if test="${!loop.last}">
                            ['${answer.get(0)}', ${answer.get(1)}],
                        </c:if>
                    </c:forEach>

                ];

                $('#variation-line-${basic.question.id}').highcharts({
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: 'Вариационны ряд'
                    },
                    xAxis: {
                        type: 'category',
                        labels: {
                            rotation: -45,
                            style: {
                                fontSize: '13px',
                                fontFamily: 'Verdana, sans-serif'
                            }
                        }
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: 'Выбранное количество'
                        }
                    },
                    legend: {
                        enabled: false
                    },
                    tooltip: {
                        pointFormat: 'Вариант был выбран {point.y} раз(а)</b>'
                    },
                    series: [{
                        name: 'Варианты ответа',
                        data: data,
                        dataLabels: {
                            enabled: true,
                            rotation: -90,
                            color: '#FFFFFF',
                            align: 'right',
                            x: 4,
                            y: 4,
                            style: {
                                fontSize: '13px',
                                fontFamily: 'Verdana, sans-serif',
                                textShadow: '0 0 3px black'
                            }
                        }
                    }]
                });
            });
        </script>
    </div>
</c:if>
<table id="result-analys" class="table">
    <c:if test="${fn:contains(basic.types,2)}">
        <tr>
            <th>Среднее арифметическое</th>
            <td>${basic.average}</td>
        </tr>
    </c:if>
    <c:if test="${fn:contains(basic.types,3)}">
        <tr>
            <th>Мода</th>
            <td>${basic.moda}</td>
        </tr>
    </c:if>
    <c:if test="${fn:contains(basic.types,4)}">
        <tr>
            <th>Медиана</th>
            <td>${basic.median}</td>
        </tr>
    </c:if>
    <c:if test="${fn:contains(basic.types,5)}">
        <tr>
            <th>Размах вариации</th>
            <td>${basic.variationScale}</td>
        </tr>
    </c:if>
    <c:if test="${fn:contains(basic.types,6)}">
        <tr>
            <th>Межкватериальный размах</th>
            <td>${basic.interQuartile}</td>
        </tr>
    </c:if>
    <c:if test="${fn:contains(basic.types,7)}">
        <tr>
            <th>Дисперсия</th>
            <td>${basic.dispersion}</td>
        </tr>
    </c:if>
    <c:if test="${fn:contains(basic.types,8)}">
        <tr>
            <th>Среднекватериальное отклонение</th>
            <td>${basic.deviation}</td>
        </tr>
    </c:if>
    <c:if test="${fn:contains(basic.types,9)}">
        <tr>
            <th>Коэфициент вариации</th>
            <td>${basic.variation}</td>
        </tr>
    </c:if>
    <c:if test="${fn:contains(basic.types,10)}">
        <tr>
            <th>Ассиметрия</th>
            <td>${basic.asymmetry}</td>
        </tr>
        <%--<tr>
            <td colspan="2">Коментарий</td>
        </tr>
        <tr>
            <td colspan="2">
                <div id="asymmetry-${basic.question.id}" style="width: 500px; height: 400px;"></div>
                <script>
                    $(function () {
                        $('#asymmetry-${basic.question.id}').highcharts({
                            title: {
                                text: ''
                            },
                            xAxis: {
                                title: ""
                            },
                            yAxis: {
                                startOnTick: false,
                                title: {
                                    text: ''
                                },
                                plotLines: [{
                                    value: 0,
                                    width: 1,
                                    color: '#808080'
                                }]
                            },
                            tooltip: {
                                enabled: false
                            },
                            legend: {
                                enabled: false
                            },
                            series: [{
                                data: [[-7.0, 6.9], [9.5, 14.5], [18.2, 21.5]]
                            }
                            ]
                        });
                    });
                </script>
            </td>
        </tr>--%>
    </c:if>
    <c:if test="${fn:contains(basic.types,11)}">
        <tr>
            <th>Эксцесс</th>
            <td>${basic.excess}</td>
        </tr>
        <%--<tr>
            <td colspan="2">Коментарий</td>
        </tr>
        <tr>
            <td colspan="2">
                <div id="excess-${basic.question.id}" style="width: 500px; height: 400px;"></div>
                <script>
                    $(function () {
                        $('#excess-${basic.question.id}').highcharts({
                            title: {
                                text: ''
                            },
                            xAxis: {
                                title: ""
                            },
                            yAxis: {
                                startOnTick: false,
                                title: {
                                    text: ''
                                },
                                plotLines: [{
                                    value: 0,
                                    width: 1,
                                    color: '#808080'
                                }]
                            },
                            tooltip: {
                                enabled: false
                            },
                            legend: {
                                enabled: false
                            },
                            series: [{
                                data: [[-7.0, 6.9], [9.5, 14.5], [18.2, 21.5]]
                            }
                            ]
                        });
                    });
                </script>
            </td>
        </tr>--%>
    </c:if>
</table>
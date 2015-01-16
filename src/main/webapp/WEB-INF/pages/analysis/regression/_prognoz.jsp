<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>Прогнозирование</h3>
<div class="row-fluid">
    <div class="span4">
        Модель y = <span id="parA">${regress.a}</span> + <span id="parB">${regress.b}</span>x<br><br>
        Расчитать у<br>
        <table>
            <tr>
                <th>x  </th>
                <td><input type="text" id="prognoz_value"></td>
            </tr>
            <tr>
                <th>y  </th>
                <td id="prognoz_result"></td>
            </tr>
        </table><br>
    </div>
    <div class="span6 offset1">
        <div id="prognoz-graf" style="width: 500px; height: 400px;"></div>
        <script>
            $(function () {
                var data = [
                    <c:forEach items='${regress.result}' var="result">
                        [${result[0]}, ${result[1]}],
                    </c:forEach>

                ];
                $('#prognoz-graf').highcharts({
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
                        data: data
                    }
                    ]
                });
            });
        </script>
    </div>
</div>

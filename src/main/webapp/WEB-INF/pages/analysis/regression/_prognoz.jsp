<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3>Прогнозирование</h3>
<div class="row-fluid">
    <div class="span4">
        Модель y = ${prognoz.a} + ${prognoz.b}x<br><br>
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
    <div class="span8">
        <div id="prognoz-graf" style="width: 500px; height: 400px;"></div>
        <script>
            $(function () {
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
                        data: [[-7.0, 6.9], [9.5, 14.5], [18.2, 21.5]]
                    }
                    ]
                });
            });
        </script>
    </div>
</div>

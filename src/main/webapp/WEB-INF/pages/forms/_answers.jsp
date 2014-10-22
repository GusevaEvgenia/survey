<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="thumbnail">
    Статус ответа
    <select>
        <option>Новый</option>
        <option>Просмотренный</option>
        <option>Игнорировать при анализе</option>
    </select>
    <table class="table table-bordered">
        <tr>
            <th>№</th>
            <% for (int i = 1; i < 6; i++) {%>
            <th>Вопрос№<%=i%></th>
            <%}%>
            <th>Статус</th>
            <th></th>
        </tr>
        <tr>
            <td>1</td>
            <% for (int i = 1; i < 6; i++) {%>
            <td>Ответ№<%=i%></td>
            <%}%>
            <td>Новый</td>
            <td><a href="#">Посмотреть</a></td>
        </tr>
    </table>
</div>

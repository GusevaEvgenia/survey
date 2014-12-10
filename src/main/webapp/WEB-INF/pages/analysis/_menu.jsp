<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String requestURI = request.getRequestURI(); %>

<ul class="nav nav-pills">
    <li class="<%= requestURI.contains("analysis/basic")? "active": "" %>"><a href="/forms/${form.idForm}/analysis/basic">Базовые статистики</a></li>
    <li class="<%= requestURI.contains("analysis/table")? "active": "" %>"><a href="/forms/${form.idForm}/analysis/table">Таблицы сопряженности</a></li>
    <li class="<%= requestURI.contains("analysis/regression")? "active": ""%>"><a href="/forms/${form.idForm}/analysis/regression">Регрессионны анализ</a></li>
</ul>

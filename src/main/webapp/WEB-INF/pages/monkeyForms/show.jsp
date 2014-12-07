<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Подключение хейдера--%>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value="Анкета"/>
</jsp:include>

<jsp:include page="/WEB-INF/pages/monkeyForms/_menu.jsp"/>

<iframe src="${form.preview_url}" width="1066" height="1500">
</iframe>

</div>
</div>
<%--Подключение футера--%>
<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>
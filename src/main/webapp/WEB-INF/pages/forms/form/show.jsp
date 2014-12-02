<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Подключение хейдера--%>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value="Анкета"/>
</jsp:include>

<jsp:include page="/WEB-INF/pages/forms/form/_menu.jsp"/>
<jsp:include page="/WEB-INF/pages/forms/form/form.jsp"/>

    </div>
</div>
<%--Подключение футера--%>
<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>

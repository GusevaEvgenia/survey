<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Подключение хейдера--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value="Анкета"/>
</jsp:include>

<script>
    $(document).ready(function() {
        $(".show-answer").click(function(e) {
            var statusCell = $(this).parents('tr').find('.status-cell');
            getAnswer($(this).parents('tr'));
            e.preventDefault();
            var url = /\/forms\/([\d]+)\/answers/.exec(window.location.pathname)[0];
            $.ajax(url + "/" + $(this).data('id'),{
                method: 'POST',
                data: "status=viewed",
                success: function(){
                    statusCell.text('просмотрено');
                    $('#answer-popup').modal('show');
                }
            });
        });
    });

    function getAnswer(answerRow) {
        var answers = answerRow.find('.answer');
        var template = $('.answer-template').clone();
        var answerContainer = $('#popup-answers');
        template.removeClass('hidden').removeClass('answer-template');
        answerContainer.empty();
        $('.questions').find('.question').each(function(i) {
            var localTemplate = template.clone();
            localTemplate.find('b').text($(this).text());
            localTemplate.find('span').text($(answers[i]).text());
            localTemplate.appendTo(answerContainer);
        });
    }
</script>
<span class="a b"></span>
<div class="a"><span class="b"></span></div>
<jsp:include page="/WEB-INF/pages/forms/form/_menu.jsp"/>

        <div class="thumbnail">
            <div class="${token!=null ? "hidden": ""}">
                Статус ответа
                <select id="select_type">
                    <c:forEach items='${statuses}' var="s">
                        <option value="${s.key}" ${s.key == currentStatus ? "selected='selected'" : ''}>${s.value}</option>
                    </c:forEach>
                </select>
            </div>
            <c:if test='${answers.size()>0}'>
                <table class="table table-bordered">
                    <tr class="questions">
                        <th>№</th>
                        <c:forEach items='${questions}' var="q">
                            <th class="question">${q.text}</th>
                        </c:forEach>
                        <th>Статус</th>
                        <th></th>
                    </tr>
                    <c:forEach items='${answers}' var="cForm" varStatus="loop">
                        <tr>
                            <td>${loop.index+1}</td>
                            <c:forEach items='${cForm.answers}' var="answer">
                                <td class="answer">
                                    <c:forEach items='${answer.text}' var="text">
                                        ${text}<c:if test='${answer.text.size()>2}'>; <br></c:if>
                                    </c:forEach>
                                </td>
                            </c:forEach>
                            <td class="status-cell">${cForm.status=="new" ? "новый" : "просмотрено"}</td>
                            <td><a class="show-answer" data-id="${cForm.id}" href="#">Посмотреть</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test='${answers.size()==0}'>
                <h4 class="text-center">У вас нет ответов на анкету.</h4>
            </c:if>
        </div>

    </div>
</div>

<%--всплывающее окно--%>
<div class="modal fade" id="answer-popup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Ответы респондента</h4>
            </div>
            <div class="modal-body">
                <div class="hidden answer-template qa-row">
                    <b></b><br>
                    <span></span><br>
                </div>
                <div id="popup-answers"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>

<%--Подключение футера--%>
<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>
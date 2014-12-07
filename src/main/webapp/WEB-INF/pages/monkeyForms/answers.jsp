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
                    statusCell.text('viewed');
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
<jsp:include page="/WEB-INF/pages/monkeyForms/_menu.jsp"/>

        <div class="thumbnail">
            <table class="table table-bordered">
                <tr class="questions">
                    <th>№</th>
                    <c:forEach items='${form.questions}' var="q">
                        <th class="question">${q.value.text}</th>
                    </c:forEach>
                    <th></th>
                </tr>
                <c:forEach items='${answers}' var="cForm">
                    <tr>
                        <td></td>
                        <c:forEach items='${cForm.answers}' var="answ">
                             <td class="answer">${answ.text}</td>
                        </c:forEach>
                        <td><a class="show-answer" data-id="${cForm.id}" href="#">Посмотреть</a></td>
                    </tr>
                </c:forEach>
            </table>
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
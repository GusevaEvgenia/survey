var Designer = {
    modalPopup: null,
    context: null,
    questionList: null,
    questions: [],

    init: function() {
        this.modalPopup  = $('#preview-popup');
        this.context = $('#form-constructor');
        this.templateContainer = this.$('#template-container');
        this.questionList = this.$('#question-list');
        this.initEvents();
        this.initItems(this.context.data('init'));
    },

    initEvents: function() {
        var designer = this;
        $("#form-preview").click(function() {
            var id = $(this).data('id');
            designer.preview(id);
        });
        /*$("#save-form").click(function() {
            var id = $(this).data('id');
            designer.save(id);
        });*/
        $(this.questionList).sortable({
            handle: ".icon-resize-vertical",
            revert: true,
            update: function() { designer.reindex(); }
        });
        $('.template-list a').draggable({
            cursor: "move",
            revert: true,
            connectToSortable: $(this.questionList),
            helper: function( event ) {
                return designer.getTemplate($(this).data('type'));
            },
            stop: function(event, position) {
                designer.createQuestion(position.helper);
            }
        });


    },

    initItems: function(data) {
        for (var i in data['questions']) {
            var question = data['questions'][i];
            var node = this.getTemplate(question['templateName']);
            $(this.questionList).append(node);
            this.createQuestion(node, question);
        }
        this.reindex();
    },

    $: function(selector) {
        return $(this.context).find(selector);
    },
    //TODO сделать это=)
    createQuestion: function(node, question) {
        return new DesignerTemplate(node, question);
    },

    //TODO Сделаьть индекс вопроса
    getTemplate: function(name) {
        var main_template = $(this.templateContainer).find('#main-template').clone();
        var template = $(this.templateContainer).find('#'+name).clone();
        main_template.find('.title').html(template.data('title'));
        main_template.find('.designer-item-body').append(template.children());
        main_template.removeAttr('id');
        return main_template.find('.designer-item');
    },

    reindex: function(){
        $(this.questionList).find(".designer-item").each(function(i){
            $(this).data('instance').reindex(i);
        });
    },

    preview: function(id) {
        $.ajax('/forms/' + id + '/designer/preview', {
            method: 'POST',
            commandName: 'form',
            success: function() {
                // var name = $("input[name='name']").val();
                var form = $(".popup_form").html();
                this.modalPopup.find(".modal-body").html(/*"<h2>" + name + "</h2>" + */form);
                this.modalPopup.modal('show');
            }
        });
    }

   /* save: function(id){
        $.ajax('/forms/' + id + '/designer', {
            method: 'POST',
            commandName: 'form'
        });
    }*/
};

$(document).ready(function() {
    Designer.init();
});
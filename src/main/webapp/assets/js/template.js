function DesignerTemplate(node) {
    this.context = $(node);
    this.type = $(this.context).data('type');
    this.context.css('height', 'auto');
    this.OPTION_ROW= '.option-index';
    this.initEvents();
}
DesignerTemplate.prototype.initEvents = function() {
    var question = this;
    this.$('.icon-remove').click(function() {
        question.context.fadeOut(300, function(){ question.remove()});
    });
    this.context.delegate('.icon-minus', 'click', function(){
        if(question.$(question.OPTION_ROW).length > 1) {
            $(this).parents('label').remove();
            question.reindex();
        }
    });
    this.context.delegate('.icon-plus', 'click', function(){
        var parent = $(this).parents('label');
        var template = parent.clone();
//TODO clean template
        parent.after(template);
        question.reindex();
    })
};
DesignerTemplate.prototype.reindex = function(question){
    this.$(".option-index").each(function(i){
        var option = $(this).find(".option, .matrix-option");
        var name = option.attr("name");
        var questionId = typeof question == "undefined" ? "$1" : question;
        var newName = name.replace(/questions\[(\d+)\].options\[(\d+)\](\..*)/, "questions[" + questionId + "].options[" + i + "]$3");
        option.attr("name", newName);
    });

    if (typeof question != "undefined") {
        this.$(".question").attr("name", "questions[" + question + "].text");
    }
};
DesignerTemplate.prototype.remove = function() {
    this.context.remove();
};
DesignerTemplate.prototype.$ = function(selector) {
    return $(this.context).find(selector);
};
function DesignerTemplate(node) {
    this.context = $(node);
    this.type = $(this.context).data('type');
    this.initEvents();
}

DesignerTemplate.prototype.toggleDrag = function() {

};

DesignerTemplate.prototype.initEvents = function() {
    var question = this;
    this.$('.icon-remove').click(function() {
        question.context.fadeOut(300, function(){ question.remove()});
    });
};
DesignerTemplate.prototype.remove = function() {
    this.context.remove();
};
DesignerTemplate.prototype.$ = function(selector) {
    return $(this.context).find(selector);
};
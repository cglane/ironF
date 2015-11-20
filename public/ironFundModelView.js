var Backbone = require('backbone');
var _ = require('underscore');
var $ = require('jquery');
Backbone.$ = $;
var tmpl = require('./templates');
// this file contains markup for the template

module.exports = Backbone.View.extend({
  tagName: 'article',
  model: null, // just a placeholder
  initialize: function () {},
  template: _.template(tmpl.project),

  events: {
    'click .deleteProject': 'deleteProject',
    'click .editProject' : 'editProject',
    "click .confirm-edit"  : "close",
  },

  deleteProject: function(event) {
    event.preventDefault();
    this.model.destroy();
    this.$el.remove();
  },
  editProject:function(event){
    event.preventDefault();
    this.$el.attr('contenteditable','true');
    this.$el.find('.confirm-edit').removeClass('display-none');
  },
  close:function(event){
    event.preventDefault();
    // console.log( $(event.target.closest('h3')));
        var object = {
          title: this.$el.find('.title').text(),
          // startdate: this.$el.find('input[id="startDate"]').val(),
          // startDate: new Date.getTime(),
          // photo: this.$el.find('input[id="image"]').val(),
          finishdate: this.$el.find('.finish-date').text(),
          description: this.$el.find('.description').text(),
          // balance: this.$el.find('input[name="balance"]').val(),
          goal: this.$el.find('.goal').text()
        }
        console.log(object);
        this.model.destroy();
       this.model.set(object);
       this.model.save();
       console.log(this.model);
       this.$el.find('.confirm-edit').addClass('display-none');

  },
  render: function () {
    var markup = this.template(this.model.toJSON());
    this.$el.append(markup);
    // in order to call .el off of render we need to return this
    // projectViewInstance.render().el - yields all markup and data from model
    return this;
  }
});

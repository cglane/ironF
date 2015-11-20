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
    "keypress article"  : "updateOnEnter",
  },

  deleteProject: function(event) {
    event.preventDefault();
    this.model.destroy();
    this.$el.remove();
  },
  editProject:function(event){
    event.preventDefault();
    this.$el.attr('contenteditable','true');
    console.log(this.$el.find('h3'));
  },
  updateOnEnter:function(e){
    if(e.keycode ==13){
      this.close();
    }
  },
  close:function(){
        var object = {
          title: this.$el.find('h3').val(),
          // startdate: this.$el.find('input[id="startDate"]').val(),
          // startDate: new Date.getTime(),
          // photo: this.$el.find('input[id="image"]').val(),
          finishdate: this.$el.find('.finish-date').val(),
          description: this.$el.find('.description').val(),
          // balance: this.$el.find('input[name="balance"]').val(),
          goal: this.$el.find('.goal').val()
        }
        console.log(object);
      //  this.model.save({title: value});
     
  },
  render: function () {
    var markup = this.template(this.model.toJSON());
    this.$el.append(markup);
    // in order to call .el off of render we need to return this
    // projectViewInstance.render().el - yields all markup and data from model
    return this;
  }
});

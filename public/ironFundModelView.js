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
  },

  deleteProject: function(event) {
    event.preventDefault();
    console.log('this is being deleted');
    console.log('the model is: ', this.model);

    this.$el.remove();
    this.model.destroy({success: function(model, response) {
      console.log('response', response);
    }});
  },

  render: function () {
    var markup = this.template(this.model.toJSON());
    this.$el.append(markup);
    // in order to call .el off of render we need to return this
    // projectViewInstance.render().el - yields all markup and data from model
    return this;
  }
});

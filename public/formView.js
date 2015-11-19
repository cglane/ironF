var Backbone = require('backbone');
var $ = require('jquery');
Backbone.$ = $;
var _ = require('underscore');
var tmpl = require('./templates');
var IronFundModel = require('./ironFundModel');

module.exports = Backbone.View.extend({
  className: 'addProject',
  model: null, // just here as placeholder, but need a model up on instantiation
  events: {
    'submit form': 'onAddProject'
  },
  initialize: function () {
    if(!this.model) {
      this.model = new IronFundModel();
    }
  },
  onAddProject: function (evt) {
    evt.preventDefault();
    var newProject = {
      title: this.$el.find('input[name="title"]').val(),
      releasedate: this.$el.find('input[name="releasedate"]').val(),
      cover: this.$el.find('input[name="coverPhoto"]').val(),
      rating: this.$el.find('input[name="rating"]').val(),
      plot: this.$el.find('textarea[name="plot"]').val()
    };
    this.model.set(newProject);
    this.model.save();
    this.$el.find('input, textarea').val('');

  },
  template: _.template(tmpl.form),
  render: function () {
    var markup = this.template(this.model.toJSON());
    this.$el.html(markup);
    // in order to call .el off of render we need to return this
    // projectViewInstance.render().el - yields all markup and data from model
    return this;
  }
});

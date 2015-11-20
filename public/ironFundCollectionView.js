var Backbone = require('backbone');
var _ = require('underscore');
var $ = require('jquery');
Backbone.$ = $;
var IronFundView = require('./ironFundModelView');
var IronFundCollection = require('./IronFundCollection');
module.exports = Backbone.View.extend({
  el: '.content',
  collection: null,

 // just a placeholder

  initialize: function () {
    // console.log(this.collection);
    this.addAll();
  },

  addOne: function (ironFundModel) {
    var ironFundView = new IronFundView({model: ironFundModel});
    this.$el.append(ironFundView.render().el);
  },
  addAll: function () {
    _.each(this.collection.models, this.addOne, this);
  }
});

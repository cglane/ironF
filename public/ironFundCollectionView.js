var Backbone = require('backbone');
var _ = require('underscore');
var $ = require('jquery');
Backbone.$ = $;
var IronFundView = require('./ironFundModelView');
var IronFundCollection = require('./IronFundCollection');
var IronFundModel = require('./ironFundModel');
module.exports = Backbone.View.extend({
  el: '.content',
  collection: null,

 // just a placeholder

  initialize: function () {
    // console.log(this.collection);
    this.addAll();
    this.listenTo(this.collection, 'add',this.addOne);
  },

  addOne: function (ironFundModel) {
    console.log('AddOne');
    var ironFundView = new IronFundView({model: ironFundModel});
    this.$el.append(ironFundView.render().el);
  },
  addAll: function () {
    _.each(this.collection.models, this.addOne, this);
  }
});

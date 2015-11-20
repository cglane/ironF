var Backbone = require('backbone');
var $ = require('jquery');
Backbone.$ = $;
var _ = require('underscore');
var HeaderView = require('./headerView');
var FooterView = require('./footerView');
var FormView = require('./formView');
var IronFundView = require('./ironFundCollectionView');
var IronFundCollection = require('./ironFundCollection');
var ModelView = require('./ironFundModelView');

module.exports = Backbone.View.extend({
  el: '#layoutView',
  initialize: function () {
    var self = this;
    var headerHTML = new HeaderView();
    var footerHTML = new FooterView();
    var ironFundCollection = new IronFundCollection();
    ironFundCollection.fetch().then(function () {
      var ironFundView = new IronFundView({collection: ironFundCollection});
      var formHTML = new FormView({collection:ironFundCollection});
      self.$el.find('section').html();
      self.$el.find('header').html(headerHTML.render().el);
      self.$el.find('footer').html(footerHTML.render().el);
      self.$el.find('aside').html(formHTML.render().el);
    });


  }

});

var Backbone = require('backbone');
var $ = require('jquery');
Backbone.$ = $;
var _ = require('underscore');
var HeaderView = require('./headerView');
var FooterView = require('./footerView');
var FormView = require('./formView');
var IronFundView = require('./ironFundCollectionView');
var IronFundCollection = require('./ironFundCollection');


module.exports = Backbone.View.extend({
  el: '#layoutView',
  initialize: function () {
    var self = this;
    console.log(HeaderView);
    var headerHTML = new HeaderView();
    var footerHTML = new FooterView();
    var formHTML = new FormView();
    var ironFundCollection = new IronFundCollection();
    ironFundCollection.fetch().then(function () {
      var ironFundView = new IronFundView({collection: ironFundCollection});
      self.$el.find('section').html();
      self.$el.find('header').html(headerHTML.render().el);
      self.$el.find('footer').html(footerHTML.render().el);
      self.$el.find('.col-md-4').html(formHTML.render().el);
    });


  }

});

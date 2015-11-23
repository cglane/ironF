var Backbone = require('backbone');
var $ = require('jquery');
Backbone.$ = $;
var _ = require('underscore');
var tmpl = require('./templates');

module.exports = Backbone.View.extend({

  initialize: function () {},
  template: _.template(tmpl.login),
  events: {
    'click .signIn' : 'onSignIn',
  },

  onSignIn: function (event) {
    event.preventDefault();
      console.log('user logged in, showing body-container');
      $('.body-container').removeClass('display-none');
      $('.placeholder-login-form').addClass('display-none');
      $('header').html('');
      // $('.signin-create-acct').addClass('display-none');
    },

  render: function () {
    var markup = this.template({});
    this.$el.html(markup);
    // in order to call .el off of render we need to return this
    // projectViewInstance.render().el - yields all markup and data from model
    return this;
  }
});

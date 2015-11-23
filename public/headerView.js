var Backbone = require('backbone');
var $ = require('jquery');
Backbone.$ = $;
var _ = require('underscore');
var tmpl = require('./templates');
var LoginView = require('./loginView');
var LayoutView = require('./layoutView');
var CreateUserView = require('./createUserView');

module.exports = Backbone.View.extend({
  initialize: function () {},
  template: _.template(tmpl.header),
  events: {
    'click .signin-create-acct' : 'onSigninCreateAcct',
  },

  onSigninCreateAcct: function (event) {
    event.preventDefault();
    $('.body-container').addClass('display-none');
    $('.placeholder-login-form').removeClass('display-none');
    console.log('this is hiding the body-container NOW');
  },

  render: function () {
    var markup = this.template({});
    this.$el.html(markup);
    // in order to call .el off of render we need to return this
    // projectViewInstance.render().el - yields all markup and data from model
    return this;
  }
});

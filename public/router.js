var Backbone = require('backbone');
var $ = require('jquery');
var _ = require('underscore');

module.exports = Backbone.Router.extend({
  routes: {
    '': 'homePage',
    'about': 'aboutPage',
    'blahblah': 'someShit'
  },
  initialize: function (options) {
    // if(!this.layout) {
    //   this.layout = options.layout;
    // }
  },
  someShit: function () {
    console.log("some shits");
  },
  homePage: function () {
    console.log("you've made it to home!!");
  },
  aboutPage: function () {
    console.log("you've made it to the about page");
  }




});

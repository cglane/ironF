var Backbone = require('backbone');
var IronFundModel = require('./ironFundModel');

module.exports = Backbone.Collection.extend({
  url: 'http://tiny-tiny.herokuapp.com/collections/bbjetchs2015',
  model: IronFundModel,
  initialize: function () {

  }
});

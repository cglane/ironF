var Backbone = require('backbone');
var IronFundModel = require('./ironFundModel');

module.exports = Backbone.Collection.extend({
  url: '/all',
  model: IronFundModel,
  initialize: function () {

  }
});

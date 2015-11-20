var Backbone = require('backbone');
var IronFundModel = require('./ironFundModel');

module.exports = Backbone.Collection.extend({
  url: 'http://tiny-tiny.herokuapp.com/collections/ironfund2020',
  model: IronFundModel,
  initialize: function () {

  }
});

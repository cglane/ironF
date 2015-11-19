var Backbone = require('backbone');
// this file contains the shape of our data

module.exports = Backbone.Model.extend({
  urlRoot: 'http://tiny-tiny.herokuapp.com/collections/ironfund2015',
  // idAttribute: '_id',
  // defaults: function () {
  //   // write your if statement here
  //   return {
  //     cover: "https://upload.wikimedia.org/wikipedia/en/d/d2/Back_to_the_Future.jpg",
  //     title: "Back to the Future",
  //     releasedate: "1985",
  //     plot: "In this 1980s sci-fi classic, small-town California teen Marty McFly (Michael J. Fox) is thrown back into the '50s when an experiment by his eccentric scientist friend Doc Brown (Christopher Lloyd) goes awry. Traveling through time in a modified DeLorean car, Marty encounters young versions of his parents (Crispin Glover, Lea Thompson), and must make sure that they fall in love or he'll cease to exist. Even more dauntingly, Marty has to return to his own time and save the life of Doc Brown.",
  //     rating: "5 stars"
  //   };
  // },
  initialize: function () {

  }
});

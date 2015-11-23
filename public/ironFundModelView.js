var Backbone = require('backbone');
var _ = require('underscore');
var $ = require('jquery');
Backbone.$ = $;
var tmpl = require('./templates');
// this file contains markup for the template

module.exports = Backbone.View.extend({
  tagName: 'article',
  model: null, // just a placeholder
  initialize: function () {},
  template: _.template(tmpl.project),

  events: {
    'click .deleteProject': 'deleteProject',
    'click .editProject' : 'editProject',
    "click .confirm-edit"  : "close",
    'click .donateNow':'onDonateNow',
  },

  deleteProject: function(event) {
    event.preventDefault();
    console.log('this is being deleted');
    console.log('the model is: ', this.model);

    this.$el.remove();
    this.model.destroy({success: function(model, response) {
      console.log('response', response);
    }});
  },
  editProject:function(event){
    event.preventDefault();
    this.$el.find('.titles').attr('contenteditable','true');
    this.$el.find('.finish-date').attr('contenteditable','true');
    this.$el.find('.description').attr('contenteditable','true');
    this.$el.find('.goal').attr('contenteditable','true');
    this.$el.find('.confirm-edit').removeClass('display-none');
  },
  close:function(event){
    event.preventDefault();
    // console.log( $(event.target.closest('h3')));
        var object = {
          title: this.$el.find('.titles').text(),
          // startdate: this.$el.find('input[id="startDate"]').val(),
          // startDate: new Date.getTime(),
          // photo: this.$el.find('input[id="image"]').val(),
          finishDate: this.$el.find('.finish-date').text(),
          description: this.$el.find('.description').text(),
          // balance: this.$el.find('input[name="balance"]').val(),
          goal: this.$el.find('.goal').text()
        };
        console.log(object);
       this.model.set(object);
       this.model.save();
       this.$el.find('.titles').attr('contenteditable','false');
       this.$el.find('.finish-date').attr('contenteditable','false');
       this.$el.find('.description').attr('contenteditable','false');
       this.$el.find('.goal').attr('contenteditable','false');
       this.$el.find('.confirm-edit').addClass('display-none');
  },
  onDonateNow:function(event){
    // this.$el.off('click', '.donateNow');
    // this.undelegateEvents();
    var currModel = this.model;
    event.preventDefault();
    $('.placeholder-for-donate').stop(true,false).removeClass('display-none', {duration:500});
    $('.body-container').addClass('blur');
    $('.donate-btn').on('click',function(event){
        event.preventDefault();
        $('.body-container').removeClass('blur');
        $('.placeholder-for-donate').addClass('display-none');
        var donation;
        var updatedBalance;
        var balance=currModel.get('balance');
        // console.log('balance');
        var id = this.id;
        balance = currModel.get('balance');
        if(id === "ten"){
          donation = 10;
          updatedBalance = balance + donation;
          currModel.save('balance',updatedBalance);
        }else if (id === "twenty") {
          donation = 20;
          updatedBalance = balance + donation;
          currModel.save('balance',updatedBalance);
        }else if(id === "fifty"){
          donation = 50;
          updatedBalance = balance + donation;
          currModel.save('balance', updatedBalance);
      }
        // console.log('current model',currModel);
        // console.log(currModel.get('balance'));
        // console.log(this.find('input[id = "donation-input"]'))
    });
    $('.placeholder-for-donate').on('keypress',function(e){
      var value = parseInt($(this).closest('div').find('input').val());
      var balance = currModel.get('balance');
      if(e.which == 13){
        if(value === '' || isNaN(value)) {
          console.log('invalid input');
        }else{
          var updatedBalance = balance + value;
          currModel.save('balance',updatedBalance);
          $('.body-container').removeClass('blur');
          $('.placeholder-for-donate').addClass('display-none');
          console.log("updated balance amount",currModel.get('balance'));
          $('.body-container').removeClass('blur');
          $('.placeholder-for-donate').addClass('display-none');
          $(this).closest('div').find('input').val('');

          console.log('thank you for your donation');
          console.log(currModel.get('balance'));
        }
      }
  });
},
  render: function () {
    var markup = this.template(this.model.toJSON());
    this.$el.append(markup);
    // in order to call .el off of render we need to return this
    // projectViewInstance.render().el - yields all markup and data from model
    return this;
  }
});

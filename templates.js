module.exports = {
  project: [
      // "<div class='<%= \"row\" %>'>",
      // "<div class='<%= \"col-sm-6 col-md-4\" %>'>",
      "<div class='<%= \"thumbnail\" %>'>",
      "<img src='<%= cover %>' >",
      "<div class='<%= \"caption\" %>'>",
      "<h3><%= title %></h3>",
      "<h4><%= releasedate %></h4>",
      "<h5><%= rating %></h5>",
      "<p><%= plot %></p>",
      "<p>",
      "<button class='<%= \"btn btn-primary editProject\" %>' role='<%= \"button\"%>' type='<%= \"submit\"%>' name='<%= \"edit\"%>'> <%= \"Edit\" %>",
      "</button>  ",
      "<button class='<%= \"btn btn-danger deleteProject\" %>' role='<%= \"button\"%>' type='<%= \"submit\"%>' name='<%= \"delete\"%>'> <%= \"Delete\" %>",
      "</button>",
      "</p>",
      "</div>",
      "</div>",
      // "</div>",
      // "</div>",

  ].join(""),
  form: [
    "<form>",
      "<p><input type='text' class='form-control' placeholder='Title' name='title'></p>",
      "<p><input type='text' class='form-control' placeholder='Release Date' name='releasedate'></p>",
      "<p><input type='text' class='form-control' placeholder='Image URL' name='coverPhoto'></p>",
      "<p><input type='text' class='form-control' placeholder='Rating' name='rating'></p>",
      "<p><textarea class='form-control' rows='3' name='plot' placeholder='Summary'></textarea></p>",
      "<p><input type='submit' class='btn btn-warning' value='add project'></p>",
    "</form>"
  ].join(""),
  header: [
    "<h2>Iron Fund</h2>",
    // "<nav>",
    // "<ul>",
    // "<li>home</li>",
    // "</ul>",
    // "</nav>"
  ].join(""),
  footer: [
    "<div class='<%= \"footer-links\" %>'>",
    "<a href='<%= \"#\" %>'><%= \"Home\" %></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;",
    "<a href='<%= \"#\" %>'><%= \"Add a Project\" %></a>",
    // "<h2>Footer</h2>",
    // "<nav>",
    // "<ul>",
    // "<li>home</li>",
    // "</ul>",
    // "</nav>"
  ].join(""),
};

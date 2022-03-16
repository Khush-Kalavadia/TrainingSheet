let Trial = {
  buttonClick: function () {
    $("#main").on("click", "input[type='button']", function (event) {
      //here, is the list of ways how we can access the value and properties
      //we get a this which is the complete object and now we can extract any property out of it

      $("input[type='text']").val(this.value);

      //   alert(this.value);
      //   alert(this.localName);
      //   alert($(this).attr("value"));
      //   alert($(this).val());
      //   alert(event.currentTarget.value);
      //   alert(event.currentTarget.localName);
      //   alert($("input[type='button']").attr("value"));
      //   alert($("input[type='button']").val());
    });
  },

  newButtonClick: function () {
    $("#main").on("click", "#newButton", function () {
      if (!this.done) {
        //   we have created a new button so it won't be binded with the function solution bind on parent
        $("p").append(
          '<br><input type="button" value="Newly created Button" />'
        );
        this.done = true;
      }

      //this will disable or off all click operations on button solution done above
      //   $("#newButton").off("click");
    });
  },
};

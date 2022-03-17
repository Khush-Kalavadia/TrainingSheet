let CheckSelected = {
  buttonClick: function () {
    $("#main").on("click", "#checkButton", function () {
      //for only even elements
      //   $("input:even").each(function () {
      //     this.checked = !this.checked;
      //   });

      var arr = [1, 3, 4];
      //filter
      $("input").filter(function (index) {
        for (let i = 0; i < arr.length; i++) {
          if (index == arr[i]) {
            this.checked = !this.checked;
          }
        }
      });
    });
  },
};

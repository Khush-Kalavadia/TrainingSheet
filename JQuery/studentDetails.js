let index = {

    onSubmit: function ()
    {
        $("[name=studentDetails]").on("submit", function (event)
        {
            console.log(event);

            console.log(event.target);          //event called using this

            console.log(event.currentTarget);   //on which element was the event attached

            var dataArray = $(event.target).serializeArray();   //array of objects

            console.log(dataArray);

            var userExistResult = index.userExist(dataArray[0].value);

            if (userExistResult !== null)
            {
                $("#UserPresent").show().fadeOut("slow");
            }
            else
            {
                var trText = "<tr>";

                dataArray.forEach(function (currentValue)
                {
                    // console.log(typeof currentValue);    //will be an object whose name and value can be accessed

                    trText += "<td>" + currentValue.value + "</td>"
                });

                trText += "</tr>";

                $("table").append(trText);
            }

            event.preventDefault();             //if not kept then console print would be lost
        });
    },

    userExist: function (rollCheck)
    {
        var returnObj = null;

        $("table tr").find("td:eq(0)").each(function (index, element)
        {
            // console.log($(element).parent());    //it is jquery object
            // console.log(this.parentElement);     //it is just text

            var elementText = ($(element).text());

            if (elementText === rollCheck)
            {
                returnObj = $(element).parent();
            }
        });

        return returnObj;
    },

    onUpdate: function ()
    {
        $("[name=updateDetails]").on("submit", function (event)
        {
            var updateDataArray = $(event.target).serializeArray();

            var rollUpdate = updateDataArray[0].value;

            var userExistResult = (index.userExist(rollUpdate));

            if (userExistResult === null)
            {
                $("#noUpdateUser").show().fadeOut("slow");
            }
            else
            {
                var textUpdate = '<td>' + updateDataArray[0].value + '</td><td>' + updateDataArray[1].value + '</td><td>' + updateDataArray[2].value + '</td><td>' + updateDataArray[3].value + '</td>';

                userExistResult.html(textUpdate);
            }

            event.preventDefault();
        });

    },

    onDelete: function ()
    {
        $("#main").on("click", "#deleteButton", function ()
        {
            var rollDelete = $("[name='deleteRoll']").val();

            var userExistResult = (index.userExist(rollDelete));

            if (userExistResult === null)
            {
                $("#noDeleteUser").show().fadeOut("slow");
            }
            else
            {
                userExistResult.remove();
            }

        });
    },

};


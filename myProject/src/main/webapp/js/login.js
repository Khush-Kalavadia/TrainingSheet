let login = {
    getDataFromForm: function ()        //practically different jsp would have such functions which
        // would be evoked and at the end everything would be transfered to postajax call
    {
        $("form").submit(function (event)
        {
            event.preventDefault();

            //array.reduce(function(total, currentValue), initialValue)
            let param = $('form').serializeArray().reduce(function(finalParam, currentValue) { finalParam[currentValue.name] = currentValue.value; return finalParam; }, {});

            let request =
                {
                    url: "login",

                    param: param,

                    callback: login.getDataFromFormSuccess
                };

            ajaxCalls.ajaxPostCall(request);

        });
    },

    getDataFromFormSuccess: function (request)
    {
        //testme try keeping 5sec and check for callback or response on continuous clicks. work on postcall and ajax part

        // function sleep(seconds)
        // {
        //     var e = new Date().getTime() + (seconds * 1000);
        //     while (new Date().getTime() <= e)
        //     {
        //     }
        // }
        //
        // sleep(5);

        if (request.bean.login === true)
        {
            window.location.href = "mainPage";
        }
        else
        {
            var returnedObject = $("#loginUnsuccessfulAlert").html('<div class="alert alert-danger" role="alert"><strong>Incorrect login credentials!</strong> Check your username and password.</div></div>').show(100);

            setTimeout(function ()
            {
                returnedObject.slideUp(200);
            }, 3000);
        }
    },
};
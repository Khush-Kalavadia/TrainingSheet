let login = {
    getDataFromForm: function ()
    {
        $("form").submit(function (event)
        {
            event.preventDefault();

            let param = $('form').serialize();

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
        if (request && request.bean && request.bean.login)
        {
            window.location.href = "mainPage";
        }
        else
        {
            let returnedObject = $("#loginUnsuccessfulAlert").html('<div class="alert alert-danger" role="alert"><strong>Incorrect login credentials!</strong> Check your username and password.</div></div>').show(100);

            setTimeout(function ()
            {
                returnedObject.slideUp(200);
            }, 3000);
        }
    },
};
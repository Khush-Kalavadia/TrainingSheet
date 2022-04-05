let ajaxCalls = {

    ajaxPostCall: function (request)
    {
        $.ajax({
            url: request.url,

            type: "POST",

            cache: false,

            data: request.param,

            timeout: 180000,

            success: function (json)            //why this? cos login provided in url worked successfully.
            {
                var callbacks;

                if (request.callback != undefined)
                {
                    callbacks = $.Callbacks();

                    callbacks.add(request.callback);

                    request.bean = json;

                    callbacks.fire(request);            //this directly moves to the callback given in request

                    callbacks.remove(request.callback);
                }
            },

            error: function (request, error, status)       //A function to be called if the request fails.
            {
                alert("Error -> " + request + " || " + error + " || " + status);
            },

            dataType: "json"
        });
    },

    getDataFromForm: function ()        //practically different jsp would have such functions which
        // would be evoked and at the end everything would be transfered to postajax call
    {
        $("#form_submit").click(function ()
        {
            let user = $("#loginInputUsername").val();

            let pass = $("#loginInputPassword").val();

            let param =
                {
                    username: user,

                    password: pass
                };

            let request =
                {
                    url: "login",

                    param: param,

                    callback: ajaxCalls.getDataFromFormSuccess
                };

            ajaxCalls.ajaxPostCall(request);

            // event.preventDefault();

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
            alert("Correct credentials");
        }
        else
        {
            alert("Incorrect credentials");
        }
    }
};



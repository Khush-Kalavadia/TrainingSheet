let ajaxCalls = {

    ajaxPostCall: function (request)
    {
        $.ajax({
            url: request.url,

            type: "POST",

            cache: false,

            data: request.param,

            timeout: 180000,

            // error:    //A function to be called if the request fails.

            success: function (bean)            //why this? cos login provided in url worked successfully.
            {
                var callbacks;

                if (request.callback != undefined)
                {
                    callbacks = $.Callbacks();

                    callbacks.add(request.callback);

                    request.bean = bean;

                    callbacks.fire(request);            //this directly moves to the callback given in request

                    callbacks.remove(request.callback);
                }
            }
        });
    },

    getDataFromForm: function ()        //practically different jsp would have such functions which
        // would be evoked and at the end everything would be transfered to postajax call
    {
        $("input[type='button']").click(function ()
        {
            let user = $("input[name='username']").val();

            let pass = $("input[name='password']").val();

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

        });
    },

    getDataFromFormSuccess: function (request)
    {
        alert(request.bean.login);
    }
};



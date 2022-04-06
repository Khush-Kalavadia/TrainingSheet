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
                alert("Error -> " + request + " || " + error + " || " + status);    //todo 3rd party notification
            },

            dataType: "json"
        });
    },

    getDataFromForm: function ()        //practically different jsp would have such functions which
        // would be evoked and at the end everything would be transfered to postajax call
    {
        $("#form_submit").click(function (event)
        {
            event.preventDefault();

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

    mainPageHtmlLoader: function ()
    {
        $(".horizontal-menu ul li:first").css("color", "var(--primary-color)");

        // $("#main-area").html("hello");

        ajaxCalls.discoveryOnClick()
    },

    discoveryOnClick: function ()
    {
        $("#discoveryLi").on("click", function ()
        {
            $('.horizontal-menu ul li:first').css("color", "black");

            $('.horizontal-menu ul li:last').css("color", "var(--primary-color)");

            $(".horizontal-menu ul li:eq(1)").css("color", "var(--primary-color)");
        });

    }


};



let ajaxCalls = {

    ajaxPostCall: function (request)
    {
        $.ajax({
            url: request.url,

            type: "POST",

            cache: false,

            data: request.param,

            timeout: 180000,

            success: function (bean)            //url worked successfully
            {
                let callbacks;

                if (request.callback !== undefined)
                {
                    callbacks = $.Callbacks();

                    callbacks.add(request.callback);

                    request.bean = bean;

                    callbacks.fire(request);            //this directly moves to the callback given in request

                    callbacks.remove(request.callback);
                }
            },

            error: function (request, error, status)       //A function to be called if the request fails.
            {
                toastr.error("Error -> " + request + " || " + error + " || " + status);
            },

            dataType: "json"
        });
    },
};



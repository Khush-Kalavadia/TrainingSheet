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
};



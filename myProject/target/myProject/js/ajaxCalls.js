let ajaxCalls = {

    ajaxPostCall: function (request)
    {
        /*
         $("input[type='button']").click(function ()
         {
         let user = $("input[name='username']").val();

         let pass = $("input[name='password']").val();

         let param = {
         username: user,
         password: pass
         }

         console.log(param);
         */

        $.ajax({
            url: request.url,

            type: "POST",

            cache: false,

            data: request.param,

            timeout: 180000,

            success: function (bean)            //why this? cos login worked successfully?
            {
                var callbacks;

                if (request.callback != undefined)
                {
                    callbacks = $.Callbacks();          //what $.Callbacks() does

                    callbacks.add(request.callback);

                    request.bean = bean;

                    callbacks.fire(request);            //this directly moves to the callback given in request

                    callbacks.remove(request.callback);
                }
            }

        });
        /*        });*/

    },

    getDataFromForm: function ()
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
        alert(request.bean.message);
    }
};
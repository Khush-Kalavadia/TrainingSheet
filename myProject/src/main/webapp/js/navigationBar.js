let hoverLiColor;

let websocket;

let clickedObject = $("#dashboardLi");

let navigationBar = {
    horizontalMenuLoader: function ()
    {
        clickedObject.css("color", "var(--primary-color)");

        navigationBar.loadHtml(clickedObject);

        navigationBar.loadUsername();

        //mention dynamically added functionality to hover and click on menu item
        navigationBar.hoverHorizontalMenuLi();

        navigationBar.onClickHorizontalMenuLi();

        navigationBar.logOutOnClick();

        // monitor.destroyChartOnModalClose();
    },

    hoverHorizontalMenuLi: function ()
    {
        $(".horizontal-menu ul li").hover(function (event)
        {
            $(event.currentTarget).css("cursor", "pointer");

            hoverLiColor = $(event.currentTarget).css("color");

            $(event.currentTarget).css("color", "var(--primary-color)");
        }, function (event)
        {
            if (clickedObject !== null)
            {
                if (clickedObject.text() !== $(event.currentTarget).text())
                {
                    $(event.currentTarget).css("color", hoverLiColor);
                }
            }
        });
    },

    onClickHorizontalMenuLi: function ()
    {
        $(".horizontal-menu ul li").on("click", function (event)
        {
            clickedObject = $(event.currentTarget);

            clickedObject.css("color", "var(--primary-color)");

            $(".horizontal-menu ul li").not(clickedObject).css("color", "black");

            navigationBar.loadHtml(clickedObject);
        });
    },

    loadHtml: function (clickedObject)
    {
        switch (clickedObject.text().trim())
        {
            case "Dashboard":
            {
                dashboard.dashboardHtmlLoader();

                break;
            }
            case "Discovery":
            {
                if (typeof websocket === 'undefined' || websocket.readyState === WebSocket.CLOSE)
                {
                    navigationBar.webSocket();
                }

                discovery.discoveryHtmlLoader();

                break;
            }
            case "Monitors":
            {
                monitor.monitorHtmlLoader();

                break;
            }
        }
    },

    loadUsername: function ()
    {
        $("#usernameLabel").html("admin");
    },

    logOutOnClick: function ()
    {
        $(".dropdown-menu span").on("click", function ()
        {
            let request = {
                url: "logout",

                callback: navigationBar.logOutSuccess
            };

            ajaxCalls.ajaxPostCall(request);
        });
    },

    logOutSuccess: function ()
    {
        window.location.href = "loginPage";
    },

    webSocket: function ()
    {
        let protocol = (window.location.protocol === 'https:') ? 'wss://' : 'ws://';

        let host = window.location.host;

        let endpoint = '/server-endpoint';

        websocket = new WebSocket(protocol + host + endpoint);

        // websocket = new WebSocket("wss://localhost:8443/server-endpoint");

        websocket.onopen = function ()
        {
            toastr.success("Frontend: Websocket started");
        };

        websocket.onmessage = function (message)
        {
            discovery.runDiscoveryResponse(message.data);
        };

        websocket.onclose = function ()
        {
            toastr.warning("Frontend: Websocket closed");
        };

        websocket.onerror = function ()         //todo not catching timeout error
        {
            toastr.error("Frontend: Error from websocket");

            navigationBar.webSocket();
        };
    }
};

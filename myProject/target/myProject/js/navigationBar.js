let hoverLiColor;

let websocket;

let clickedObject = $("#dashboardLi");

let navigationBar = {
    horizontalMenuLoader: function ()
    {
        clickedObject.css("color", "var(--primary-color)");

        navigationBar.loadHtml(clickedObject);

        navigationBar.loadUsername();

        navigationBar.hoverHorizontalMenuLi();          //dynamically added functionality to hover and click on menu item

        navigationBar.onClickHorizontalMenuLi();

        navigationBar.logOutOnClick();
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
                    discovery.webSocket();
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
};

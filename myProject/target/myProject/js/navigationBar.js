let hoverLiColor;

let websocket;

let clickedObject;

let navigationBar = {
    horizontalMenuLoader: function ()
    {
        let currentTab = sessionStorage.getItem("currentTab");

        if (currentTab)
        {
            clickedObject = $('#' + currentTab + 'Li');
        }
        else
        {
            clickedObject = $("#dashboardLi");
        }

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
        switch (clickedObject.data("tab"))
        {
            case "dashboardTab":
            {
                dashboard.dashboardHtmlLoader();

                sessionStorage.setItem("currentTab", "dashboard");

                break;
            }
            case "discoveryTab":
            {
                if (websocket === undefined || websocket.readyState === WebSocket.CLOSE)
                {
                    discovery.webSocket();
                }

                discovery.discoveryHtmlLoader();

                sessionStorage.setItem("currentTab", "discovery");

                break;
            }
            case "monitorsTab":
            {
                monitor.monitorHtmlLoader();

                sessionStorage.setItem("currentTab", "monitors");

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

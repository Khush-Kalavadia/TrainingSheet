let hoverLiColor;

let clickedObject = $("#dashboardLi");

let navigationBar = {
    horizontalMenuLoader: function ()
    {
        $(clickedObject).css("color", "var(--primary-color)");

        navigationBar.loadHtml(clickedObject);

        navigationBar.loadUsername();

        //mention dynamically added functionality to hover and click on menu item
        navigationBar.hoverHorizontalMenuLi();

        navigationBar.onClickHorizontalMenuLi();

        navigationBar.logOutOnClick();

        discovery.webSocket();

        monitor.destroyChartOnModalClose();
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
        $("#usernameLabel").html("admin");              //fixme get user from session
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
    }
};

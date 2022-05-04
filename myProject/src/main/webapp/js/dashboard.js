let dashboard = {

    dashboardHtmlLoader: function ()
    {
        let request =
            {
                url: "loadDashboardDetails",

                callback: dashboard.dashboardHtmlLoaderSuccess
            };

        $("#main-area").html('<div class="row"> <div class="col-12 mt-5"> <div class="card" id="dashboardPanel"> <div class="card-body"> <h4 class="header-title">Dashboard Details</h4> <div class="grid-container-3-inline"> <div class="grid-item" id="totalDevice">Total Polling Devices<hr /> <span></span></div> <div class="grid-item" id="upDevice"> Up Devices <hr /> <span></span></div> <div class="grid-item" id="downDevice"> Down Devices<hr /> <span></span> </div> </div> <h6>- Top 3 CPU Using Devices in Last Polling -</h6> <div class="topCpuContainer"> <table> <thead> <tr> <th>ID</th> <th>Name</th> <th>IP/Hostname</th>  <th>CPU Usage</th> </tr> </thead> <tbody> <tr id="row1"> <td>1</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> <tr id="row2"> <td>2</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> <tr id="row3"> <td>3</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> </tbody> </table> </div> <h6>- Top 3 Memory Using Devices in Last Polling -</h6> <div class="topMemoryContainer"> <table> <thead> <tr> <th>ID</th> <th>Name</th> <th>IP/Hostname</th>  <th>Memory Usage</th> </tr> </thead> <tbody> <tr id="row1"> <td>1</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> <tr id="row2"> <td>2</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> <tr id="row3"> <td>3</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> </tbody> </table> </div> <h6>- Top 3 Disk Using Devices in Last Polling -</h6> <div class="topDiskContainer"> <table> <thead> <tr> <th>ID</th> <th>Name</th> <th>IP/Hostname</th>  <th>Disk Usage</th> </tr> </thead> <tbody> <tr id="row1"> <td>1</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> <tr id="row2"> <td>2</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> <tr id="row3"> <td>3</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> </tbody> </table> </div>  </div> </div> </div> </div>');

        ajaxCalls.ajaxPostCall(request);
    },

    dashboardHtmlLoaderSuccess: function (request)
    {
        console.log(request);

        let dashboardDivSelector = $("#dashboardPanel");

        let requestBean = request.bean;

        dashboardDivSelector.find("#totalDevice span").html(requestBean.totalDevice);

        dashboardDivSelector.find("#upDevice span").html(requestBean.upDevice);

        dashboardDivSelector.find("#downDevice span").html(requestBean.downDevice);

        dashboard.updateTopCpuTable(dashboardDivSelector.find(".topCpuContainer table"), requestBean.topCpuUsageDevice);

        dashboard.updateTopCpuTable(dashboardDivSelector.find(".topMemoryContainer table"), requestBean.topMemoryUsageDevice);

        dashboard.updateTopCpuTable(dashboardDivSelector.find(".topDiskContainer table"), requestBean.topDiskUsageDevice);
    },

    updateTopCpuTable: function (selector, tableData)
    {
        $.each(tableData, function (rowNumber, tableRow)
        {
            selector.find("#row" + (rowNumber + 1) + " #name").html(tableRow.name);

            selector.find("#row" + (rowNumber + 1) + " #ipHostname").html(tableRow.ip_hostname);

            selector.find("#row" + (rowNumber + 1) + " #measuringUnit").html(tableRow.measuring_unit);
        });
    }

};
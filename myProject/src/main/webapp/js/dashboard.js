let dashboard = {

    dashboardHtmlLoader: function ()
    {
        let request =
            {
                url: "loadDashboardDetails",

                callback: dashboard.dashboardHtmlLoaderSuccess
            };

        $("#main-area").html('<div class="row"> <div class="col-12 mt-5"> <div class="card" id="dashboardPanel"> <div class="card-body"> <h4 class="header-title">Dashboard Details</h4> <div class="grid-container-3-inline"> <div class="grid-item" id="totalDevice">Total Polling Devices<hr /> <span></span></div> <div class="grid-item" id="upDevice"> Up Devices <hr /> <span></span></div> <div class="grid-item" id="downDevice"> Down Devices<hr /> <span></span> </div> </div> <h6>- Top 3 CPU Using Devices in Last Polling -</h6> <div class="topCpuContainer"> <table> <thead> <tr> <th>ID</th> <th>Name</th> <th>IP/Hostname</th>  <th>CPU Usage (%)</th> </tr> </thead> <tbody> <tr id="row1"> <td>1</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> <tr id="row2"> <td>2</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> <tr id="row3"> <td>3</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> </tbody> </table> </div> <h6>- Top 3 Memory Using Devices in Last Polling -</h6> <div class="topMemoryContainer"> <table> <thead> <tr> <th>ID</th> <th>Name</th> <th>IP/Hostname</th>  <th>Memory Usage (%)</th> </tr> </thead> <tbody> <tr id="row1"> <td>1</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> <tr id="row2"> <td>2</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> <tr id="row3"> <td>3</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> </tbody> </table> </div> <h6>- Top 3 Disk Using Devices in Last Polling -</h6> <div class="topDiskContainer"> <table> <thead> <tr> <th>ID</th> <th>Name</th> <th>IP/Hostname</th>  <th>Disk Usage (%)</th> </tr> </thead> <tbody> <tr id="row1"> <td>1</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> <tr id="row2"> <td>2</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> <tr id="row3"> <td>3</td> <td id="name"></td> <td id="ipHostname"></td> <td id="measuringUnit"></td> </tr> </tbody> </table> </div>  </div> </div> </div> </div>');

        ajaxCalls.ajaxPostCall(request);
    },

    dashboardHtmlLoaderSuccess: function (request)
    {
        let dashboardDivSelector = $("#dashboardPanel");

        if (request && request.bean)
        {
            let requestBean = request.bean;

            let spanSelector = dashboardDivSelector.find("#totalDevice span");

            if (requestBean.totalDevice)
            {
                spanSelector.html(requestBean.totalDevice);
            }
            else
            {
                spanSelector.html("Data unavailable");
            }

            spanSelector = dashboardDivSelector.find("#upDevice span");

            if (requestBean.upDevice)
            {
                spanSelector.html(requestBean.upDevice);
            }
            else
            {
                spanSelector.html("Data unavailable");
            }

            spanSelector = dashboardDivSelector.find("#downDevice span");

            if (requestBean.downDevice)
            {
                spanSelector.html(requestBean.downDevice);
            }
            else
            {
                spanSelector.html("Data unavailable");
            }

            dashboard.updateTopCpuTable(dashboardDivSelector.find(".topCpuContainer table"), requestBean.topCpuUsageDevice);

            dashboard.updateTopCpuTable(dashboardDivSelector.find(".topMemoryContainer table"), requestBean.topMemoryUsageDevice);

            dashboard.updateTopCpuTable(dashboardDivSelector.find(".topDiskContainer table"), requestBean.topDiskUsageDevice);
        }
        else
        {
            toastr.error("Dashboard data unavailable");
        }
    },

    updateTopCpuTable: function (tableSelector, tableData)
    {
        if (tableData)
        {
            for (let rowNumber = 1; rowNumber <= 3; rowNumber++)
            {
                if (tableData[rowNumber - 1])
                {
                    dashboard.printTableElement(rowNumber, tableSelector, "name", tableData[rowNumber - 1].name);

                    dashboard.printTableElement(rowNumber, tableSelector, "ipHostname", tableData[rowNumber - 1].ip_hostname);

                    dashboard.printTableElement(rowNumber, tableSelector, "measuringUnit", tableData[rowNumber - 1].measuring_unit);
                }
                else
                {
                    dashboard.printTableRowDataUnavailable(rowNumber, tableSelector);
                }
            }
        }
        else
        {
            for (let rowNumber = 1; rowNumber <= 3; rowNumber++)
            {
                dashboard.printTableRowDataUnavailable(rowNumber, tableSelector);
            }

            toastr.error("Null table data returned");
        }
    },

    printTableElement: function (rowNumber, tableSelector, cellSelector, value)
    {
        if (value)
        {
            tableSelector.find("#row" + rowNumber + " #" + cellSelector).html(value);
        }
        else
        {
            tableSelector.find("#row" + rowNumber + " #" + cellSelector).html("Data unavailable");
        }
    },

    printTableRowDataUnavailable: function (rowNumber, tableSelector)
    {
        dashboard.printTableElement(rowNumber, tableSelector, "name", "Data unavailable");

        dashboard.printTableElement(rowNumber, tableSelector, "ipHostname", "Data unavailable");

        dashboard.printTableElement(rowNumber, tableSelector, "measuringUnit", "Data unavailable");
    }
};
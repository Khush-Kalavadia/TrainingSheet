let monitorDataTable;

let deleteMonitorEventTarget;

let monitor = {

    monitorHtmlLoader: function ()
    {
        let request =
            {
                url: "loadMonitorTable",

                callback: monitor.monitorHtmlLoaderSuccess
            };

        $("#main-area").html('<div class="row"> <div class="col-12 mt-5"> <div class="card"> <div class="card-body"> <h4 class="header-title">Monitored Devices</h4> <div class="data-tables"> <div class="data-tables"> <table id="dataTableMonitor" class="text-center"> <thead class="bg-light text-capitalize"> <tr> <th>Sr. No.</th> <th>Name</th> <th>IP or Hostname</th> <th>Type</th> <th>Availability</th> <th>Operations</th> </tr> </thead> </table> </div> </div> <div class="modal fade" id="deleteMonitorPopupModal"> <div class="modal-dialog modal-dialog-centered" role="document"> <div class="modal-content"> <div class="modal-header"> <h5 class="modal-title">Delete confirmation</h5> <button type="button" class="close" data-dismiss="modal"> <span>&times;</span> </button> </div> <div class="modal-body"> <p>Would you like to definitely delete the device from monitoring?</p> </div> <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-dismiss="modal" > Close </button> <button type="button" class="btn btn-primary" id="deleteDeviceConfirmationButton" > Delete device </button> </div> </div> </div> </div> </div> </div> </div> </div> <div class="modal fade" id="editMonitorModal"> <div class="modal-dialog modal-dialog-centered" role="document"> <div class="modal-content"> <form id="editMonitorDeviceForm"> <div class="modal-header"> <h5 class="modal-title">Device details</h5> <button type="button" class="close" data-dismiss="modal" > <span>&times;</span> </button> </div> <div class="modal-body"> <input type="text" name="id" class="deviceId" style="display: none" /> <label>Name</label> <input type="text" name="name" class="deviceName" required /> <br /> <label>IP address or hostname</label> <input type="text" name="ipHostname" class="deviceIpHostname" required /> <br /> <label>Type</label> <select name="type" class="deviceType" disabled="disabled"> <option value="ping">Ping</option> <option value="ssh">SSH</option> </select> <div id="sshFields"> <label>Username</label> <input type="text" name="username" class="deviceUsername" /> <br /> <label>Password</label> <input type="password" name="password" /> </div> </div> <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-dismiss="modal" > Close </button> <button type="submit" class="btn btn-primary" id="editDeviceButton" > Edit device </button> </div> </form> </div> </div> </div> <div class="modal fade bd-example-modal-lg" id="pingMonitorDetailsModal"> <div class="modal-dialog modal-lg"> <div class="modal-content"> <div class="modal-header"> <h5 class="modal-title">Monitor device details</h5> <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button> </div> <div class="modal-body"> <h6 id="ipHostname">Ip/Hostname: <span>#pingMonitorDetailsModal ipHostname span</span></h6> <h6 id="type">Type: <span>#pingMonitorDetailsModal type span</span></h6> <hr> <h6 class="modalTitle">- Data of last polling time -</h6> <div class="grid-container"> <div class="grid-item" id="packetLoss"> Packet loss (%)<hr /> <span>100</span></div> <div class="grid-item" id="rtt"> Average RTT (ms)<hr /> <span>16.345</span></div> <div class="grid-item" id="transmittedPackets"> Transmitted Packets <hr /> <span>3</span> </div> <div class="grid-item" id="receivedPackets"> Received Packets <hr /> <span>0</span> </div> </div>  <div class="grid-container-2-inline"> <div class="grid-item" id="pingAvailabilityDonutChartDiv"> </div> <div class="grid-item"> <div id="currentAvailability">Current Availablility <hr /> <span>Unknown</span></div> </div> </div>  <div class="col-lg-10 mt-4" id="pingChartDiv"> </div> <div id="sshDeviceDetailsContainer">  </div> <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> </div> </div> </div> </div>');

        ajaxCalls.ajaxPostCall(request);

        monitor.deleteIconClick();

        monitor.deleteDeviceConfirmationClick();

        monitor.monitorDetailIconClick();

        monitor.editIconClick();

        monitor.editDeviceButtonClick();
    },

    monitorHtmlLoaderSuccess: function (request)
    {
        monitorDataTable = $('#dataTableMonitor').DataTable();

        if (request && request.bean)
        {
            monitor.tableLoader(request.bean.monitorTableData);
        }
    },

    tableLoader: function (table)
    {
        if (table)
        {
            monitorDataTable.rows().remove();

            monitorDataTable.rows.add(table).draw();

            monitor.iconHover();
        }
        else
        {
            toastr.error('Monitor devices data-table not loaded');
        }
    },

    iconHover: function ()
    {
        $("#dataTableMonitor").find(".monitorOperationsCell .fa").hover(function (event)
        {
            $(event.currentTarget).css("cursor", "pointer");

            $(event.currentTarget).css("color", "var(--primary-color)");
        }, function (event)
        {
            $(event.currentTarget).css("color", "black");
        });
    },

    deleteIconClick: function ()
    {
        $("#dataTableMonitor").on("click", ".fa-archive", function (event)
        {
            deleteMonitorEventTarget = event.target;                  //to provide the table row

            $('#deleteMonitorPopupModal').modal('show');
        });
    },

    deleteDeviceConfirmationClick: function ()
    {
        $("#deleteMonitorPopupModal").on("click", "#deleteDeviceConfirmationButton", function ()
        {
            $('#deleteMonitorPopupModal').modal('hide');

            let deleteRowId = $(deleteMonitorEventTarget).parent().data("database_table_id");

            let param = {
                id: deleteRowId
            };

            let request = {
                url: "deleteMonitorDevice",

                param: param,

                callback: monitor.deleteRowSuccess
            };

            ajaxCalls.ajaxPostCall(request);
        });
    },

    deleteRowSuccess: function (request)
    {
        if (request && request.bean && request.bean.operationSuccess)
        {
            monitor.tableLoader(request.bean.monitorTableData);

            toastr.success("Device deleted successfully");
        }
        else
        {
            toastr.error("Deletion unsuccessful");
        }
    },

    monitorDetailIconClick: function ()
    {
        $("#dataTableMonitor").on("click", ".fa-desktop", function (event)
        {
            let monitorRowId = $(event.target).parent().data("database_table_id");

            let param = {
                id: monitorRowId
            };

            let request = {
                url: "getMonitorDeviceDetail",

                param: param,

                callback: monitor.detailFetchSuccess
            };

            ajaxCalls.ajaxPostCall(request);
        });
    },

    detailFetchSuccess: function (request)
    {
        if (request && request.bean)
        {
            let deviceDetail = request.bean;

            monitor.pingUpdateIndices(deviceDetail.ipHostname, deviceDetail.type, deviceDetail.packetLoss, deviceDetail.rttAvg, deviceDetail.packetsTransmitted, deviceDetail.packetsReceived, deviceDetail.availability);

            monitor.commonDonutChart($("#pingAvailabilityDonutChartDiv"), "Availability of last 24 hours", "Available (%)", "Unavailable (%)", deviceDetail.pastAvailabilityPercent, 100 - deviceDetail.pastAvailabilityPercent);

            monitor.loadPingPacketLossChart(deviceDetail.timeChartData, deviceDetail.packetLossChartData);

            if (request.bean.type === "ping")
            {
                $("#sshDeviceDetailsContainer").html(null);
            }
            else if (request.bean.type === "ssh")
            {
                let sshDetailContainer = $("#sshDeviceDetailsContainer");

                if (deviceDetail.availability === "up")
                {
                    sshDetailContainer.html('<h6 class="modalTitle">- SSH device data of last polling time -</h6>');
                }
                else
                {
                    sshDetailContainer.html("<h6 class='modalTitle unsuccessMessage' >- Showing last not null SSH device details at <span></span> -</h6>");

                    sshDetailContainer.find("span").html(deviceDetail.lastNotNullSshTime);
                }

                sshDetailContainer.append('<div class="grid-container-3-inline"> <div class="grid-item" id="totalMemory">Total memory (GB)<hr /> <span>5.5</span></div> <div class="grid-item" id="totalDisk"> Total Disk (GB)<hr /> <span>64</span></div> <div class="grid-item" id="upTime"> System up time <hr /> <span>2 days, 4 hours, 13 minutes</span></div> </div> <div class="grid-container-3-chart-inline"> <div class="grid-item" id="memoryDonutChartDiv"> </div> <div class="grid-item" id="diskDonutChartDiv"> </div> <div class="grid-item" id="cpuUsageDonutChartDiv"> </div> </div>');

                monitor.sshUpdateIndices(deviceDetail.totalMemoryGb, deviceDetail.totalDiskGb, deviceDetail.uptime);

                monitor.commonDonutChart($("#memoryDonutChartDiv"), "Memory", "Free (GB)", "Used (GB)", deviceDetail.totalMemoryGb - deviceDetail.usedMemoryGb, deviceDetail.usedMemoryGb);

                monitor.commonDonutChart($("#diskDonutChartDiv"), "Disk", "Free (GB)", "Used (GB)", deviceDetail.totalDiskGb - deviceDetail.usedDiskGb, deviceDetail.usedDiskGb);

                monitor.commonDonutChart($("#cpuUsageDonutChartDiv"), "CPU Usage", "Idle (%)", "Used (%)", deviceDetail.idleCpuPercentage, 100 - deviceDetail.idleCpuPercentage);
            }

            $('#pingMonitorDetailsModal').modal('show');

            monitor.tableLoader(deviceDetail.monitorTableData);
        }
        else
        {
            toastr.error("Fetching device details unsuccessful");
        }
    },

    loadPingPacketLossChart: function (xaxisLabel, data)
    {
        if (xaxisLabel && data)
        {
            $("#pingChartDiv").html('<h6 class="modalTitle">- Last 10 Packet Loss -</h6><canvas id="pingPacketLossChart"></canvas>');

            const ctx = $("#pingPacketLossChart");
            new Chart(ctx, {
                type: "bar",
                data: {
                    labels: xaxisLabel,
                    datasets: [
                        {
                            data: data,
                            backgroundColor: [
                                "rgba(255, 99, 132, 0.7)",
                                "rgba(54, 162, 235, 0.7)",
                                "rgba(255, 206, 86, 0.7)",
                                "rgba(75, 192, 192, 0.7)",
                                "rgba(153, 102, 255, 0.7)",
                                "rgba(255, 159, 64, 0.7)",
                                "rgba(255, 99, 132, 0.7)",
                                "rgba(54, 162, 235, 0.7)",
                                "rgba(255, 206, 86, 0.7)",
                            ],
                            borderColor: [
                                "rgba(255, 99, 132, 1)",
                                "rgba(54, 162, 235, 1)",
                                "rgba(255, 206, 86, 1)",
                                "rgba(75, 192, 192, 1)",
                                "rgba(153, 102, 255, 1)",
                                "rgba(255, 159, 64, 1)",
                                "rgba(255, 99, 132, 1)",
                                "rgba(54, 162, 235, 1)",
                                "rgba(255, 206, 86, 1)",
                            ],
                            borderWidth: 1,
                            datalabels: {
                                anchor: 'end',
                                align: 'top'
                            }
                        },
                    ],
                },
                plugins: [ChartDataLabels],
                options: {
                    plugins: {
                        title: {
                            display: true,
                            //add spacing to resolve label overlap
                        },
                        legend: {
                            display: false
                        }
                    },
                    scales: {
                        y: {
                            beginAtZero: true,
                            title: {
                                display: true,
                                text: 'Packet loss (%)'
                            }
                        },
                        x: {
                            title: {
                                display: true,
                                text: 'Occasions when packets send'
                            },
                        }
                    },
                },
            });
        }
        else
        {
            $("#pingChartDiv").html('<h6 class="modalTitle unsuccessMessage">Not showing ping packet loss chart as still packets are not send.</h6>');
        }
    },

    pingUpdateIndices: function (ipHostname, type, packetLoss, rttAvg, packetsTransmitted, packetsReceived, availability)
    {
        let modalSelector = $("#pingMonitorDetailsModal");

        if (ipHostname)
        {
            modalSelector.find("#ipHostname span").html(ipHostname);
        }
        else
        {
            modalSelector.find("#ipHostname span").html("Data unavailable");
        }

        if (type)
        {
            modalSelector.find("#type span").html(type);
        }
        else
        {
            modalSelector.find("#type span").html("Data unavailable");
        }

        if (packetLoss)
        {
            modalSelector.find("#packetLoss span").html(packetLoss);
        }
        else
        {
            modalSelector.find("#packetLoss span").html("Data unavailable");
        }

        if (rttAvg)
        {
            modalSelector.find("#rtt span").html(rttAvg);
        }
        else
        {
            modalSelector.find("#rtt span").html("Data unavailable");
        }

        if (packetsTransmitted)
        {
            modalSelector.find("#transmittedPackets span").html(packetsTransmitted);
        }
        else
        {
            modalSelector.find("#transmittedPackets span").html("Data unavailable");
        }

        if (packetsReceived)
        {
            modalSelector.find("#receivedPackets span").html(packetsReceived);
        }
        else
        {
            modalSelector.find("#receivedPackets span").html("Data unavailable");
        }

        if (availability)
        {
            modalSelector.find("#currentAvailability span").html(availability);

            if (availability === "up")
            {
                modalSelector.find("#currentAvailability").css("background-color", "#00b909");
            }
            else if (availability === "down")
            {
                modalSelector.find("#currentAvailability").css("background-color", "#ff6739");
            }
            else
            {
                modalSelector.find("#currentAvailability").css("background-color", "#999999");
            }
        }
        else
        {
            modalSelector.find("#currentAvailability span").html("Data unavailable");
        }
    },

    sshUpdateIndices: function (totalMemory, totalDisk, uptime)
    {
        let modalSelector = $("#pingMonitorDetailsModal");

        if (totalDisk !== -1)
        {
            modalSelector.find("#totalDisk span").html(totalDisk);
        }
        else
        {
            modalSelector.find("#totalDisk span").html("Data unavailable");
        }

        if (totalMemory !== -1)
        {
            modalSelector.find("#totalMemory span").html(totalMemory);
        }
        else
        {
            modalSelector.find("#totalMemory span").html("Data unavailable");
        }

        if (uptime)
        {
            modalSelector.find("#upTime span").html(uptime);
        }
        else
        {
            modalSelector.find("#upTime span").html("Data unavailable");
        }
    },

    commonDonutChart: function (insertDivSelector, title, label1, label2, value1, value2)
    {
        if (value1 >= 0 && value2 >= 0)
        {
            insertDivSelector.html('<canvas></canvas>');

            new Chart(insertDivSelector.find("canvas"), {
                type: 'doughnut',
                data: {
                    labels: [label1, label2],
                    datasets: [{
                        data: [value1, value2],
                        backgroundColor: [
                            'rgb(255, 205, 86)',
                            'rgb(255, 99, 132)'
                        ],
                        hoverOffset: 4
                    }]
                },
                options: {
                    plugins: {
                        title: {
                            display: true,
                            text: title
                        }
                    }
                }
            });
        }
        else
        {
            insertDivSelector.html("<h6 style='color: #636363'>Donut chart is not available.</h6>");
        }
    },


    editIconClick: function ()
    {
        $("#dataTableMonitor").on("click", ".fa-edit", function (event)
        {
            editEventTarget = event.target;

            let editRowId = $(event.target).parent().data("database_table_id");

            let param = {
                id: editRowId
            };

            let request = {
                url: "getMonitorData",

                param: param,

                callback: monitor.fetchEditRowSuccess
            };

            ajaxCalls.ajaxPostCall(request);
        });
    },

    fetchEditRowSuccess: function (request)
    {
        if (request && request.bean && request.bean.operationSuccess)
        {
            let modalSelector = $("#editMonitorModal");

            let sshFieldSelector = modalSelector.find("#sshFields");

            let receivedBean = request.bean;

            modalSelector.find('.deviceId').val(receivedBean.id);

            modalSelector.find('.deviceName').val(receivedBean.name);

            modalSelector.find('.deviceIpHostname').val(receivedBean.ipHostname);

            if (receivedBean.type === "ping")
            {
                sshFieldSelector.hide();

                sshFieldSelector.find("input").prop("required", false);
            }
            else
            {
                sshFieldSelector.show();

                sshFieldSelector.find("input").prop("required", true);
            }

            modalSelector.find('.deviceType').val(receivedBean.type);

            deviceType = receivedBean.type;

            modalSelector.find('.deviceUsername').val(receivedBean.username);

            modalSelector.find(':password').val(null);

            modalSelector.modal('show');
        }
        else
        {
            toastr.error("Null device data returned")
        }
    },

    editDeviceButtonClick: function ()
    {
        $("#editMonitorModal").find("form").submit(function (event)
        {
            event.preventDefault();

            toastr.info("Please wait. Discovering the device before editing");

            let param = $("#editMonitorModal").find("form").serialize() + "&type=" + deviceType;

            let request = {
                url: "editMonitorDevice",

                param: param,

                callback: monitor.editDeviceButtonClickSuccess
            };

            ajaxCalls.ajaxPostCall(request);
        });
    },

    editDeviceButtonClickSuccess: function (request)
    {
        if (request && request.bean)
        {
            let requestBean = request.bean;

            if (requestBean.operationSuccess)
            {
                let modalSelector = $("#editMonitorModal");

                modalSelector.modal('hide');

                monitor.tableLoader(requestBean.monitorTableData);

                toastr.success("Device discovered and edited successfully");
            }
            else
            {
                if (requestBean.emptyInputEntry)
                {
                    toastr.error("All required fields should have non-space characters");
                }
                else if (requestBean.availability === "down")
                {
                    toastr.error("Edit unsuccessful. Cannot discover this monitor device");
                }
                else if (requestBean.duplicateEntry)
                {
                    toastr.error("Device having ip/hostname and type already present");
                }
                else
                {
                    toastr.error("Device edit unsuccessful");
                }
            }
        }
        else
        {
            toastr.error("Device edit unsuccessful");
        }
    },
};
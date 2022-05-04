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

        $("#main-area").html('<div class="row"> <div class="col-12 mt-5"> <div class="card"> <div class="card-body"> <h4 class="header-title">Monitored Devices</h4> <div class="data-tables"> <div class="data-tables"> <table id="dataTableMonitor" class="text-center"> <thead class="bg-light text-capitalize"> <tr> <th>ID</th> <th>Name</th> <th>IP or Hostname</th> <th>Type</th> <th>Availability</th> <th>Operations</th> </tr> </thead> </table> </div> </div> <div class="modal fade" id="deleteMonitorPopupModal"> <div class="modal-dialog modal-dialog-centered" role="document"> <div class="modal-content"> <div class="modal-header"> <h5 class="modal-title">Delete confirmation</h5> <button type="button" class="close" data-dismiss="modal"> <span>&times;</span> </button> </div> <div class="modal-body"> <p>Would you like to definitely delete the device from monitoring?</p> </div> <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-dismiss="modal" > Close </button> <button type="button" class="btn btn-primary" id="deleteDeviceConfirmationButton" > Delete device </button> </div> </div> </div> </div> </div> </div> </div> </div> <div class="modal fade bd-example-modal-lg" id="pingMonitorDetailsModal"> <div class="modal-dialog modal-lg"> <div class="modal-content"> <div class="modal-header"> <h5 class="modal-title">Monitor device details</h5> <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button> </div> <div class="modal-body"> <h6 id="ipHostname">Ip/Hostname: <span>#pingMonitorDetailsModal ipHostname span</span></h6> <h6 id="type">Type: <span>#pingMonitorDetailsModal type span</span></h6> <hr> <h6 align="center">Data of last polling time</h6> <div class="grid-container"> <div class="grid-item" id="packetLoss"> Packet loss <hr /> <span>100</span> % </div> <div class="grid-item" id="rtt"> Minimum RTT <hr /> <span>16.345</span> ms </div> <div class="grid-item" id="transmittedPackets"> Transmitted Packets <hr /> <span>3</span> </div> <div class="grid-item" id="receivedPackets"> Received Packets <hr /> <span>0</span> </div> </div>  <div class="grid-container-2-inline"> <div class="grid-item"> </div> <div class="grid-item"> <div id="currentAvailability">Current Availablility <hr /> <span>Unknown</span></div> </div> </div>  <div class="col-lg-10 mt-4" id="pingChartDiv"> </div> <div id="sshDeviceDetailsContainer">  </div> <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> </div> </div> </div> </div>');


        // $(".modal-body label").css("font-weight", "bold");

        ajaxCalls.ajaxPostCall(request);

        monitor.deleteIconClick();

        monitor.deleteDeviceConfirmationClick();

        monitor.monitorDetailIconClick();
    },

    monitorHtmlLoaderSuccess: function (request)
    {
        monitorDataTable = $('#dataTableMonitor').DataTable();

        let table = request.bean.monitorTableData;

        monitor.tableLoader(table);
    },

    tableLoader: function (table)
    {
        if (table !== null)
        {
            monitorDataTable.rows().remove();

            $.each(table, function (rowNumber, tableRow)
            {
                let rowHtml = [];

                rowHtml[0] = rowNumber + 1;

                rowHtml[1] = tableRow.name;

                rowHtml[2] = tableRow.ip_hostname;

                rowHtml[3] = tableRow.type;

                rowHtml[4] = tableRow.availability_status;

                rowHtml[5] = '<div class="monitorOperationsCell" data-database_table_id = "' + tableRow.id + '"> <i class="fa fa-desktop" title="Show monitor details"></i> <i class="fa fa-archive" title="Delete monitor"></i> </div>';

                monitorDataTable.row.add(rowHtml).draw();
            });

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
        if (request.bean.operationSuccess)
        {
            monitorDataTable.row(deleteMonitorEventTarget.parentNode.parentNode.parentNode).remove().draw();

            if (monitorDataTable.rows().count() === 0)
            {
                maxTableRow = 0;
            }

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
        console.log(request);

        let deviceDetail = request.bean;

        monitor.pingUpdateIndices(deviceDetail.ipHostname, deviceDetail.type, deviceDetail.packetLoss, deviceDetail.rttMin, deviceDetail.packetsTransmitted, deviceDetail.packetsReceived, deviceDetail.availability);

        if (deviceDetail.pastAvailabilityPercent === -1)
        {
            $(".grid-container-2-inline .grid-item:first").html("<h6 style='color: #636363'>Availability chart not available because current availability is unknown.</h6>");
        }
        else
        {
            $(".grid-container-2-inline .grid-item:first").html('<canvas id="pingAvailabilityDonutChart"></canvas>');

            monitor.loadPingAvailabilityDonutChart(deviceDetail.pastAvailabilityPercent);
        }

        monitor.loadPingPacketLossChart(deviceDetail.timeChartData, deviceDetail.packetLossChartData);

        if (request.bean.type === "ping")
        {
            $("#sshDeviceDetailsContainer").html(null);
        }
        else if (request.bean.type === "ssh")
        {
            if (deviceDetail.availability === "up")
            {
                $("#sshDeviceDetailsContainer").html('<div class="grid-container-3-inline"> <div class="grid-item" id="totalMemory">Total memory<hr /> <span>5.5</span> GB</div> <div class="grid-item" id="totalDisk"> Total Disk <hr /> <span>64</span> GB</div> <div class="grid-item" id="upTime"> System up time <hr /> <span>2 days, 4 hours, 13 minutes</span></div> </div> <div class="grid-container-3-chart-inline"> <div class="grid-item"> <canvas id="memoryDonutChart"></canvas> </div> <div class="grid-item"> <canvas id="diskDonutChart"></canvas> </div> <div class="grid-item"> <canvas id="cpuUsageDonutChart"></canvas> </div> </div>  ');

                monitor.sshUpdateIndices(deviceDetail.totalMemoryGb, deviceDetail.totalDiskGb, deviceDetail.uptime);

                monitor.loadMemoryUsageDonutChart(deviceDetail.totalMemoryGb - deviceDetail.usedMemoryGb, deviceDetail.usedMemoryGb);

                monitor.loadDiskUsageDonutChart(deviceDetail.totalDiskGb - deviceDetail.usedDiskGb, deviceDetail.usedDiskGb);

                monitor.loadCpuUsageDonutChart(deviceDetail.idleCpuPercentage);
            }
            else
            {
                $("#sshDeviceDetailsContainer").html("<h6>Not showing SSH device details because the device is unavailable as per last pooling data.</h6>");
            }
        }

        $('#pingMonitorDetailsModal').modal('show');
    },

    loadPingPacketLossChart: function (xaxisLabel, data)
    {
        if (xaxisLabel[0] !== null)
        {
            $("#pingChartDiv").html('<canvas id="pingPacketLossChart"></canvas>');

            const ctx = $("#pingPacketLossChart");
            let pingPacketLossBarChart = new Chart(ctx, {
                type: "bar",
                data: {
                    labels: xaxisLabel,
                    datasets: [
                        {
                            data: data,
                            // data: [33.33, 66.66, 0, 100, 33.33, 66.66, 0, 100, 33.33, 66.66, 0, 100, 33.33, 66.66, 0, 100, 33.33, 66.66, 0, 100],
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
                        // title: {
                        //     display: true,
                        //     text: 'Last 10 packet loss'
                        // },
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
                            ticks: {
                                autoSkip: false,
                                minRotation: 45,
                                maxRotation: 90
                            },
                            barPercentage: 0.2
                        }
                    },
                },
            });
        }
        else
        {
            $("#pingChartDiv").html('<h6>Not showing ping packet loss chart as still packets are not send.</h6>');
        }
    },

    loadPingAvailabilityDonutChart: function (pingAvailability)
    {
        const selector = $("#pingAvailabilityDonutChart");
        let pingAvailabilityDonutChart = new Chart(selector, {
            type: 'doughnut',
            data: {
                labels: [
                    'Available (%)',
                    'Unavailable (%)'
                ],
                datasets: [{
                    data: [pingAvailability, 100 - pingAvailability],
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
                        text: 'Availability of last 24 hours'
                    }
                }
            }
        });
    },

    // destroyChartOnModalClose: function ()
    // {
    //     $(document).on('hide.bs.modal', '#pingMonitorDetailsModal', function ()
    //     {
    //         if (pingPacketLossBarChart !== null)                //it shows chart is being used if not destroyed
    //         {
    //             pingPacketLossBarChart.destroy();
    //         }
    //     });
    // },

    pingUpdateIndices: function (ipHostname, type, packetLoss, rttMin, packetsTransmitted, packetsReceived, availability)
    {
        let modalSelector = $("#pingMonitorDetailsModal");

        if (ipHostname !== null)
        {
            modalSelector.find("#ipHostname span").html(ipHostname);
        }
        else
        {
            modalSelector.find("#ipHostname span").html("-");
        }

        if (type !== null)
        {
            modalSelector.find("#type span").html(type);
        }
        else
        {
            modalSelector.find("#type span").html("-");
        }

        if (packetLoss !== -1)
        {
            modalSelector.find("#packetLoss span").html(packetLoss);
        }
        else
        {
            modalSelector.find("#packetLoss span").html("-");
        }

        if (rttMin !== -1)
        {
            modalSelector.find("#rtt span").html(rttMin);
        }
        else
        {
            modalSelector.find("#rtt span").html("-");
        }

        if (packetsTransmitted !== -1)
        {
            modalSelector.find("#transmittedPackets span").html(packetsTransmitted);
        }
        else
        {
            modalSelector.find("#transmittedPackets span").html("-");
        }

        if (packetsReceived !== -1)
        {
            modalSelector.find("#receivedPackets span").html(packetsReceived);
        }
        else
        {
            modalSelector.find("#receivedPackets span").html("-");
        }

        if (availability !== null)
        {
            modalSelector.find("#currentAvailability span").html(availability);
        }
        else
        {
            modalSelector.find("#currentAvailability span").html("-");
        }

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
    },

    loadMemoryUsageDonutChart: function (freeMemory, usedMemory)
    {
        const selector = $("#memoryDonutChart");
        let memoryDonutChart = new Chart(selector, {
            type: 'doughnut',
            data: {
                labels: [
                    'Free (GB)',
                    'Used (GB)'
                ],
                datasets: [{
                    data: [freeMemory, usedMemory],
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
                        text: 'Memory'
                    }
                }
            }
        });
    },

    loadDiskUsageDonutChart: function (freeDisk, usedDisk)
    {
        const selector = $("#diskDonutChart");
        let diskDonutChart = new Chart(selector, {
            type: 'doughnut',
            data: {
                labels: [
                    'Free (GB)',
                    'Used (GB)'
                ],
                datasets: [{
                    data: [freeDisk, usedDisk],
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
                        text: 'Disk'
                    }
                }
            }
        });
    },

    loadCpuUsageDonutChart: function (idleCPUPercent)
    {
        const selector = $("#cpuUsageDonutChart");
        let cpuUsageDonutChart = new Chart(selector, {
            type: 'doughnut',
            data: {
                labels: [
                    'Idle (%)',
                    'Used (%)'
                ],
                datasets: [{
                    data: [idleCPUPercent, 100 - idleCPUPercent],
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
                        text: 'CPU'
                    }
                }
            }
        });
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
            modalSelector.find("#totalDisk span").html("-");
        }

        if (totalMemory !== -1)
        {
            modalSelector.find("#totalMemory span").html(totalMemory);
        }
        else
        {
            modalSelector.find("#totalMemory span").html("-");
        }

        if (uptime !== null)
        {
            modalSelector.find("#upTime span").html(uptime);
        }
        else
        {
            modalSelector.find("#upTime span").html("-");
        }
    }

};
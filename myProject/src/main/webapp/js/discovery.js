let discoveryDataTable;

let deleteEventTarget;      //fixme is this the right way to keep variable global and pass to success method of delete on click

let editEventTarget;

let maxTableRow;

let websocket;

let discovery = {

    discoveryHtmlLoader: function ()
    {
        let request =
            {
                url: "loadDiscoveryTable",

                callback: discovery.discoveryHtmlLoaderSuccess
            };

        $("#main-area").html('<div class="row"> <div class="col-12 mt-5"> <div class="card"> <div class="card-body"> <h4 class="header-title">Discovered Devices</h4> <input type="button" value="Add device" id="deviceAdder" data-toggle="modal" data-target="#addModal" /> <div class="modal fade" id="addModal"> <div class="modal-dialog modal-dialog-centered" role="document"> <div class="modal-content"> <div class="modal-header"> <h5 class="modal-title">Device details</h5> <button type="button" class="close" data-dismiss="modal"> <span>&times;</span> </button> </div> <form id="discoveryDeviceForm"> <div class="modal-body"> <label>Name</label> <input type="text" name="name" class="deviceName" required /> <br /> <label>IP address or hostname</label> <input type="text" name="ipHostname" class="deviceIpHostname" required /> <br /> <label>Type</label> <select name="type" class="deviceType"> <option value="ping">Ping</option> <option value="ssh">SSH</option> </select> <div id="sshFields"> <label>Username</label> <input type="text" name="username" class="deviceUsername" /> <br /> <label>Password</label> <input type="password" name="password" class="devicePassword"/> </div> </div> <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-dismiss="modal" > Close </button> <button type="submit" class="btn btn-primary" id="addDeviceButton" > Add device </button> </div> </form> </div> </div> </div> <div class="modal fade" id="editModal"> <div class="modal-dialog modal-dialog-centered" role="document"> <div class="modal-content"> <form id="editDiscoveryDeviceForm"> <div class="modal-header"> <h5 class="modal-title">Device details</h5> <button type="button" class="close" data-dismiss="modal" > <span>&times;</span> </button> </div> <div class="modal-body"> <input type="text" name="id" class="deviceId" style="display: none" /> <label>Name</label> <input type="text" name="name" class="deviceName" required /> <br /> <label>IP address or hostname</label> <input type="text" name="ipHostname" class="deviceIpHostname" required /> <br /> <label>Type</label> <select name="type" class="deviceType"> <option value="ping">Ping</option> <option value="ssh">SSH</option> </select> <div id="sshFields"> <label>Username</label> <input type="text" name="username" class="deviceUsername" /> <br /> <label>Password</label> <input type="password" name="password" /> </div> </div> <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-dismiss="modal" > Close </button> <button type="submit" class="btn btn-primary" id="editDeviceButton" > Edit device </button> </div> </form> </div> </div> </div> <div class="data-tables"> <div class="data-tables"> <table id="dataTableDiscovery" class="text-center"> <thead class="bg-light text-capitalize"> <tr> <th>ID</th> <th>Name</th> <th>IP or Hostname</th> <th>Type</th> <th>Operations</th> </tr> </thead> </table> </div> </div> <div class="modal fade" id="deleteDiscoveryPopupModal"> <div class="modal-dialog modal-dialog-centered" role="document"> <div class="modal-content"> <div class="modal-header"> <h5 class="modal-title">Delete confirmation</h5> <button type="button" class="close" data-dismiss="modal"> <span>&times;</span> </button> </div> <div class="modal-body"> <p>Would you like to definitely delete the device?</p> </div> <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-dismiss="modal" > Close </button> <button type="button" class="btn btn-primary" id="deleteDeviceConfirmationButton" > Delete device </button> </div> </div> </div> </div> </div> </div> </div> </div>');

        $(".modal-body label").css("font-weight", "bold");

        ajaxCalls.ajaxPostCall(request);

        discovery.onClicks();

        discovery.preventSelectChangeEditModal();
    },

    discoveryHtmlLoaderSuccess: function (request)          //todo can create list<list<String>> and directly send to frontend to be added. Add the icons also at the backend side
    {
        discoveryDataTable = $('#dataTableDiscovery').DataTable();

        let table = request.bean.discoveryTableData;

        discovery.tableLoader(table);
    },

    tableLoader: function (table)
    {
        if (table !== null)
        {
            discoveryDataTable.rows().remove();

            $.each(table, function (rowNumber, tableRow)            //check using table=null
            {
                let rowHtml = [];

                maxTableRow = rowNumber + 1;

                rowHtml[0] = maxTableRow;

                rowHtml[1] = tableRow.name;

                rowHtml[2] = tableRow.ip_hostname;

                rowHtml[3] = tableRow.type;

                rowHtml[4] = '<div class="discoveryOperationsCell" data-database_table_id = "' + tableRow.id + '" data-datatable_id = "' + maxTableRow + '"><i class="fa fa-play" title="Run Discovery"></i><i class="fa fa-edit" title="Edit device"></i><i class="fa fa-archive" title="Delete Device"></i>';

                if (tableRow.provision === 1)
                {
                    rowHtml[4] += '<i class="fa fa-plus" title="Provision Device"></i>'
                }

                rowHtml[4] += '</div>';

                discoveryDataTable.row.add(rowHtml).draw();
            });

            discovery.iconHover();
        }
        else
        {
            toastr.error('Discovered devices data-table not loaded');
        }
    },

    addModalTypeClick: function ()
    {
        let modalSelector = $("#addModal");

        let sshFieldsSelector = modalSelector.find("#sshFields");

        sshFieldsSelector.hide();

        modalSelector.find("select").on("change", function (event)
        {
            if ($(event.target).val() === "ssh")
            {
                sshFieldsSelector.find("input").prop('required', true);

                sshFieldsSelector.show();
            }
            else
            {
                sshFieldsSelector.find("input").prop('required', false);

                sshFieldsSelector.hide();

                sshFieldsSelector.find("input").val("");
            }
        });
    },

    onClicks: function ()
    {
        discovery.addModalTypeClick();

        discovery.addDeviceClick();

        discovery.deleteIconClick();

        discovery.editIconClick();

        discovery.discoverIconClick();

        discovery.provisionIconClick();

        discovery.editDeviceButtonClick();

        discovery.deleteDeviceConfirmationClick();
    },

    iconHover: function ()
    {
        $("#dataTableDiscovery").find(".discoveryOperationsCell .fa").hover(function (event)
        {
            $(event.currentTarget).css("cursor", "pointer");

            $(event.currentTarget).css("color", "var(--primary-color)");
        }, function (event)
        {
            $(event.currentTarget).css("color", "black");
        });
    },

    addDeviceClick: function ()
    {
        $("#addModal").find("form").submit(function (event)
        {
            event.preventDefault();

            let param = $("#discoveryDeviceForm").serializeArray().reduce(function (finalParam, currentValue)
            {
                finalParam[currentValue.name] = currentValue.value;

                return finalParam;
            }, {});

            let request =
                {
                    url: "insertDiscoveryDevice",

                    param: param,

                    callback: discovery.addDeviceSuccess
                };

            ajaxCalls.ajaxPostCall(request);
        });
    },

    addDeviceSuccess: function (request)
    {
        if (request.bean.operationSuccess)
        {
            let rowHtml = [];

            let discoveryBean = request.bean;

            rowHtml[0] = ++maxTableRow;

            rowHtml[1] = discoveryBean.name;

            rowHtml[2] = discoveryBean.ipHostname;

            rowHtml[3] = discoveryBean.type;

            rowHtml[4] = '<div class="discoveryOperationsCell" data-database_table_id = "' + discoveryBean.id + '" data-datatable_id = "' + maxTableRow + '"><i class="fa fa-play"></i><i class="fa fa-edit"></i><i class="fa fa-archive"></i>';

            if (discoveryBean.provision === 1)
            {
                rowHtml[4] += '<input class="provision" type="button" value="Provision">'
            }

            rowHtml[4] += '</div>';

            discoveryDataTable.row.add(rowHtml).draw();

            discovery.iconHover();

            toastr.success("Device added successfully");

            $('#addModal').modal('hide');

            $('#addModal').find('input').val("");
        }
        else
        {
            toastr.error("Device insertion unsuccessful");
        }
    },

    deleteIconClick: function ()
    {
        $("#dataTableDiscovery").on("click", ".fa-archive", function (event)
        {
            deleteEventTarget = event.target;                  //to provide the table row

            $('#deleteDiscoveryPopupModal').modal('show');
        });
    },

    deleteDeviceConfirmationClick: function ()
    {
        $("#deleteDiscoveryPopupModal").on("click", "#deleteDeviceConfirmationButton", function (event)
        {
            $('#deleteDiscoveryPopupModal').modal('hide');

            let deleteRowId = $(deleteEventTarget).parent().data("database_table_id");

            let param = {
                id: deleteRowId
            };

            let request = {
                url: "deleteDiscoveryDevice",

                param: param,

                callback: discovery.deleteRowSuccess
            };

            ajaxCalls.ajaxPostCall(request);
        });
    },

    deleteRowSuccess: function (request)
    {
        if (request.bean.operationSuccess)
        {
            discoveryDataTable.row(deleteEventTarget.parentNode.parentNode.parentNode).remove().draw();

            if (discoveryDataTable.rows().count() === 0)
            {
                maxTableRow = 0;
            }

            toastr.success("Device deleted successfully");
        }
        else
        {
            toastr.error("Deletion unsuccessful")
        }
    },

    preventSelectChangeEditModal: function ()
    {
        $("#editModal").find("select").mousedown(function (event)
        {
            event.preventDefault();
        });
    },

    editIconClick: function ()
    {
        $("#dataTableDiscovery").on("click", ".fa-edit", function (event)
        {
            editEventTarget = event.target;

            let editRowId = $(event.target).parent().data("database_table_id");

            let param = {
                id: editRowId
            };

            let request = {
                url: "getDiscoveryData",

                param: param,

                callback: discovery.fetchEditRowSuccess
            };

            ajaxCalls.ajaxPostCall(request);
        });
    },

    fetchEditRowSuccess: function (request)
    {
        if (request.bean.operationSuccess)
        {
            let modalSelector = $("#editModal");

            let sshFieldSelector = modalSelector.find("#sshFields");

            let receivedBean = request.bean;

            modalSelector.find('.deviceId').val(receivedBean.id);
            modalSelector.find('.deviceName').val(receivedBean.name);
            modalSelector.find('.deviceIpHostname').val(receivedBean.ipHostname);
            if (receivedBean.type === "ping")
            {
                sshFieldSelector.hide();
            }
            else
            {
                sshFieldSelector.show();

                sshFieldSelector.find("input").prop("required", true);
            }
            modalSelector.find('.deviceType').val(receivedBean.type);
            modalSelector.find('.deviceUsername').val(receivedBean.username);
            modalSelector.find('.devicePassword').val('');          //fixme if i enter password and open other ssh device the same password would be visible it is not clearing

            modalSelector.modal('show');
        }
        else
        {
            toastr.error("Null device data returned")
        }
    },

    editDeviceButtonClick: function ()
    {
        $("#editModal").find("form").submit(function (event)
        {
            event.preventDefault();

            let param = $("#editModal").find("form").serializeArray().reduce(function (finalParam, currentValue)
            {
                finalParam[currentValue.name] = currentValue.value;

                return finalParam;
            }, {});

            let request = {
                url: "editDiscoveryDevice",

                param: param,

                callback: discovery.editDeviceButtonClickSuccess
            };

            ajaxCalls.ajaxPostCall(request);
        });
    },

    editDeviceButtonClickSuccess: function (request)
    {
        if (request.bean.operationSuccess)
        {
            let modalSelector = $("#editModal");

            modalSelector.modal('hide');

            discovery.tableLoader(request.bean.discoveryTableData);

            toastr.success("Device updated successfully");
        }
        else
        {
            toastr.error("Update unsuccessful");
        }
    },

    discoverIconClick: function ()
    {
        $("#dataTableDiscovery").on("click", ".fa-play", function (event)
        {
            let discoverRowId = $(event.target).parent().data("database_table_id");

            websocket.send(discoverRowId);
        });
    },

    runDiscoveryResponse: function (text)
    {
        let ipHostname = text.substring(0, text.indexOf(";"));

        let type = text.substring(text.indexOf(";") + 1, text.lastIndexOf(";"));

        let response = text.substring(text.lastIndexOf(";") + 1, text.length);

        switch (response)
        {
            case "success":

                toastr.success(type + ' successful of device having IP/Hostname: ' + ipHostname);

                let request =
                    {
                        url: "loadDiscoveryTable",

                        callback: function ()
                        {
                            discovery.tableLoader(request.bean.discoveryTableData);
                        }
                    };

                ajaxCalls.ajaxPostCall(request);

                break;

            case "unsuccessful":

                toastr.warning(type + ' unsuccessful of device having IP/Hostname: ' + ipHostname);

                break;

            default:

                toastr.error('Error while doing ' + type + ' having IP/Hostname: ' + ipHostname);
        }
    },

    // discoverIconClick: function ()
    // {
    //     $("#dataTableDiscovery").on("click", ".fa-play", function (event)
    //     {
    //         let discoverRowId = $(event.target).parent().data("database_table_id");
    //
    //         let param = {
    //             id: discoverRowId
    //         };
    //
    //         let request = {
    //             url: "runDiscovery",
    //
    //             param: param,
    //
    //             callback: discovery.runDiscoverySuccess
    //         };
    //
    //         ajaxCalls.ajaxPostCall(request);
    //     });
    // },
    //
    // runDiscoverySuccess: function (request)
    // {
    //     if (request.bean.operationSuccess)
    //     {
    //         if (request.bean.provision)
    //         {
    //             if (request.bean.type === "ping")
    //             {
    //                 toastr.success('Ping successful of device having IP/Hostname: ' + request.bean.ipHostname);
    //             }
    //             else
    //             {
    //                 toastr.success('SSH successful of device having IP/Hostname: ' + request.bean.ipHostname);
    //             }
    //             discovery.tableLoader(request.bean.discoveryTableData);
    //         }
    //         else
    //         {
    //             if (request.bean.type === "ping")
    //             {
    //                 toastr.warning('Ping unsuccessful of device having IP/Hostname: ' + request.bean.ipHostname);
    //             }
    //             else
    //             {
    //                 toastr.warning('SSH unsuccessful of device having IP/Hostname: ' + request.bean.ipHostname);
    //             }
    //         }
    //     }
    //     else
    //     {
    //         toastr.error('Error while discovering device having IP/Hostname: ' + request.bean.ipHostname);
    //     }
    // },

    provisionIconClick: function ()
    {
        $("#dataTableDiscovery").on("click", ".fa-plus", function ()
        {
            let provisionRowId = $(event.target).parent().data("database_table_id");

            let param = {
                id: provisionRowId
            };

            let request = {
                url: "provisionDevice",

                callback: discovery.provisionSuccess,

                param: param
            };

            ajaxCalls.ajaxPostCall(request);
        });
    },

    provisionSuccess: function (request)
    {
        if (request.bean.operationSuccess)
        {
            toastr.success("Device added successfully to pooling monitors");
        }
        else
        {
            toastr.error("Device addition unsuccessful to pooling monitors");
        }
    },

    webSocket: function ()          //fixme increase the timeout from ideal time 30sec
    {
        websocket = new WebSocket("ws://localhost:8080/server-endpoint");

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

        websocket.onerror = function ()
        {
            toastr.error("Frontend: Error from websocket")
        };
    }
};
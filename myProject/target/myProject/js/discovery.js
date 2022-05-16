let discoveryDataTable;

let deleteEventTarget;

let editEventTarget;

let deviceType;

let discovery = {

    discoveryHtmlLoader: function ()
    {
        let request =
            {
                url: "loadDiscoveryTable",

                callback: discovery.discoveryHtmlLoaderSuccess
            };

        $("#main-area").html('<div class="row"> <div class="col-12 mt-5"> <div class="card"> <div class="card-body"> <h4 class="header-title">Discovered Devices</h4> <input type="button" value="Add device" id="deviceAdder" data-toggle="modal" data-target="#addModal" /> <div class="modal fade" id="addModal"> <div class="modal-dialog modal-dialog-centered" role="document"> <div class="modal-content"> <div class="modal-header"> <h5 class="modal-title">Device details</h5> <button type="button" class="close" data-dismiss="modal"> <span>&times;</span> </button> </div> <form id="discoveryDeviceForm"> <div class="modal-body"> <label>Name</label> <input type="text" name="name" class="deviceName" required /> <br /> <label>IP address or hostname</label> <input type="text" name="ipHostname" class="deviceIpHostname" required /> <br /> <label>Type</label> <select name="type" class="deviceType"> <option value="ping">Ping</option> <option value="ssh">SSH</option> </select> <div id="sshFields"> <label>Username</label> <input type="text" name="username" class="deviceUsername" /> <br /> <label>Password</label> <input type="password" name="password" class="devicePassword"/> </div> </div> <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-dismiss="modal" > Close </button> <button type="submit" class="btn btn-primary" id="addDeviceButton" > Add device </button> </div> </form> </div> </div> </div> <div class="modal fade" id="editModal"> <div class="modal-dialog modal-dialog-centered" role="document"> <div class="modal-content"> <form id="editDiscoveryDeviceForm"> <div class="modal-header"> <h5 class="modal-title">Device details</h5> <button type="button" class="close" data-dismiss="modal" > <span>&times;</span> </button> </div> <div class="modal-body"> <input type="text" name="id" class="deviceId" style="display: none" /> <label>Name</label> <input type="text" name="name" class="deviceName" required /> <br /> <label>IP address or hostname</label> <input type="text" name="ipHostname" class="deviceIpHostname" required /> <br /> <label>Type</label> <select name="type" class="deviceType" disabled="disabled"> <option value="ping">Ping</option> <option value="ssh">SSH</option> </select> <div id="sshFields"> <label>Username</label> <input type="text" name="username" class="deviceUsername" /> <br /> <label>Password</label> <input type="password" name="password" /> </div> </div> <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-dismiss="modal" > Close </button> <button type="submit" class="btn btn-primary" id="editDeviceButton" > Edit device </button> </div> </form> </div> </div> </div> <div class="data-tables"> <div class="data-tables"> <table id="dataTableDiscovery" class="text-center"> <thead class="bg-light text-capitalize"> <tr> <th>Sr. No.</th> <th>Name</th> <th>IP or Hostname</th> <th>Type</th> <th>Operations</th> </tr> </thead> </table> </div> </div> <div class="modal fade" id="deleteDiscoveryPopupModal"> <div class="modal-dialog modal-dialog-centered" role="document"> <div class="modal-content"> <div class="modal-header"> <h5 class="modal-title">Delete confirmation</h5> <button type="button" class="close" data-dismiss="modal"> <span>&times;</span> </button> </div> <div class="modal-body"> <p>Would you like to definitely delete the device?</p> </div> <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-dismiss="modal" > Close </button> <button type="button" class="btn btn-primary" id="deleteDeviceConfirmationButton" > Delete device </button> </div> </div> </div> </div> </div> </div> </div> </div>');

        $(".modal-body label").css("font-weight", "bold");

        ajaxCalls.ajaxPostCall(request);

        discovery.onClicks();
    },

    discoveryHtmlLoaderSuccess: function (request)
    {
        discoveryDataTable = $('#dataTableDiscovery').DataTable();

        if (request && request.bean)
        {
            discovery.tableLoader(request.bean.discoveryTableData);
        }
    },

    tableLoader: function (table)
    {
        if (table)
        {
            discoveryDataTable.rows().remove();

            discoveryDataTable.rows.add(table).draw();

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

                sshFieldsSelector.find("input").val(null);
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

        discovery.onPageChange();
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

            let param = $("#discoveryDeviceForm").serialize();

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
        if (request && request.bean)
        {
            let requestBean = request.bean;

            if (requestBean.operationSuccess)
            {
                discovery.tableLoader(requestBean.discoveryTableData);

                toastr.success("Device added successfully");

                let modalSelector = $('#addModal');

                modalSelector.modal('hide');

                modalSelector.find('form')[0].reset();

                let sshFieldSelector = modalSelector.find("#sshFields");

                sshFieldSelector.hide();

                sshFieldSelector.find("input").prop("required", false);
            }
            else
            {
                if (requestBean.duplicateEntry)
                {
                    toastr.error("Device having ip/hostname and type already present");
                }
                else if (requestBean.emptyInputEntry)
                {
                    toastr.error("All required fields should have non-space characters");
                }
                else
                {
                    toastr.error("Device insertion unsuccessful");
                }
            }
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
            deleteEventTarget = event.target;                  //to provide the table row to be deleted

            $('#deleteDiscoveryPopupModal').modal('show');
        });
    },

    deleteDeviceConfirmationClick: function ()
    {
        $("#deleteDiscoveryPopupModal").on("click", "#deleteDeviceConfirmationButton", function ()
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
        if (request && request.bean && request.bean.operationSuccess)
        {
            discovery.tableLoader(request.bean.discoveryTableData);

            toastr.success("Device deleted successfully");
        }
        else
        {
            toastr.error("Deletion unsuccessful");
        }
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
        if (request && request.bean && request.bean.operationSuccess)
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
        $("#editModal").find("form").submit(function (event)
        {
            event.preventDefault();

            let param = $("#editModal").find("form").serialize() + "&type=" + deviceType;

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
        if (request && request.bean)
        {
            let requestBean = request.bean;

            if (requestBean.operationSuccess)
            {
                let modalSelector = $("#editModal");

                modalSelector.modal('hide');

                discovery.tableLoader(requestBean.discoveryTableData);

                toastr.success("Device edited successfully");
            }
            else
            {
                if (requestBean.duplicateEntry
                )
                {
                    toastr.error("Device having ip/hostname and type already present");
                }
                else if (requestBean.emptyInputEntry)
                {
                    toastr.error("All required fields should have non-space characters");
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

    discoverIconClick: function ()
    {
        $("#dataTableDiscovery").on("click", ".fa-play", function (event)
        {
            let discoverRowId = $(event.target).parent().data("database_table_id");

            websocket.send(discoverRowId);

            toastr.info("Running discovery");
        });
    },

    runDiscoveryResponse: function (text)
    {
        if (text)
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

                case "userPasswordError":

                    toastr.warning('Username or password invalid of IP/Hostname: ' + ipHostname);

                    break;

                case "notLinuxDevice":

                    toastr.warning('IP/Hostname ' + ipHostname + ' is not a linux device');

                    break;

                default:

                    toastr.error('Cannot translate ' + ipHostname + ' into IP address. Check IP/Hostname.');
            }
        }
        else
        {
            toastr.error("Error while doing discovery.");
        }
    },

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
        if (request && request.bean)
        {
            let requestBean = request.bean;

            if (requestBean.operationSuccess)
            {
                toastr.success("Device added successfully to pooling monitors");
            }
            else
            {
                if (requestBean.duplicateEntry)
                {
                    toastr.error("Device IP/Hostname and type already present in monitors");
                }
                else
                {
                    toastr.error("Device addition unsuccessful to pooling monitors");
                }
            }
        }
        else
        {
            toastr.error("Device addition unsuccessful to pooling monitors");
        }
    },

    onPageChange: function ()
    {
        $('#main-area').on('page.dt', "#dataTableDiscovery", function ()
        {
            discovery.iconHover();
        });
    },

    webSocket: function ()
    {
        let protocol = (window.location.protocol === 'https:') ? 'wss://' : 'ws://';

        let host = window.location.host;

        let endpoint = '/server-endpoint';

        websocket = new WebSocket(protocol + host + endpoint);

        websocket.onopen = function ()
        {
            // toastr.success("Frontend: Websocket started");
        };

        websocket.onmessage = function (message)
        {
            if (message)
            {
                discovery.runDiscoveryResponse(message.data);
            }
        };

        websocket.onclose = function ()
        {
            // toastr.warning("Frontend: Websocket closed");
        };

        websocket.onerror = function ()
        {
            // toastr.error("Frontend: Error from websocket");

            discovery.webSocket();
        };
    }
};
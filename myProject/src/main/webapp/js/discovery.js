let dataTable;

let deleteEventTarget;      //fixme is this the right way to keep variable global and pass to success method of delete on click

let editEventTarget;

let tableRows;

let discovery = {

    discoveryHtmlLoader: function ()
    {
        let param =
            {
                tableName: "discovery"
            };

        let request =
            {
                url: "loadDiscoveryTable",

                param: param,

                callback: discovery.discoveryHtmlLoaderSuccess
            };

        $("#main-area").html('<div class="row"> <div class="col-12 mt-5"> <div class="card"> <div class="card-body"> <h4 class="header-title">Discovered IPs</h4> <input type="button" value="Add device" id="deviceAdder" data-toggle="modal" data-target="#addModal" /> <div class="modal fade" id="addModal"> <div class="modal-dialog modal-dialog-centered" role="document"> <div class="modal-content"> <div class="modal-header"> <h5 class="modal-title">Device details</h5> <button type="button" class="close" data-dismiss="modal"> <span>&times;</span> </button> </div> <form id="discoveryDeviceForm"> <div class="modal-body"> <label>Name</label> <input type="text" name="name" class="deviceName" required /> <br /> <label>IP address or hostname</label> <input type="text" name="ipHostname" class="deviceIpHostname" required /> <br /> <label>Type</label> <select name="type" class="deviceType"> <option value="ping">Ping</option> <option value="ssh">SSH</option> </select> <div id="sshFields"> <label>Username</label> <input type="text" name="username" class="deviceUsername"/> <br /> <label>Password</label> <input type="password" name="password"/> </div> </div> <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-dismiss="modal" > Close </button> <button type="submit" class="btn btn-primary" id="addDeviceButton" > Add device </button> </div> </form> </div> </div> </div> <div class="data-tables"> <div class="modal fade" id="editModal"> <div class="modal-dialog modal-dialog-centered" role="document"> <div class="modal-content"> <form id="editDiscoveryDeviceForm"> <div class="modal-header"> <h5 class="modal-title">Device details</h5> <button type="button" class="close" data-dismiss="modal"> <span>&times;</span> </button> </div> <div class="modal-body"> <input type="text" name="id" class="deviceId" style="display: none"/> <label>Name</label> <input type="text" name="name" class="deviceName" required/> <br /> <label>IP address or hostname</label> <input type="text" name="ipHostname" class="deviceIpHostname" required/> <br /> <label>Type</label> <select name="type" class="deviceType"> <option value="ping">Ping</option> <option value="ssh">SSH</option> </select> <div id="sshFields"> <label>Username</label> <input type="text" name="username" class="deviceUsername" /> <br /> <label>Password</label> <input type="password" name="password" /> </div> </div> <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-dismiss="modal" > Close </button> <button type="submit" class="btn btn-primary" id="editDeviceButton" > Edit device </button> </div> </form> </div> </div> </div> <div class="data-tables"> <table id="dataTableDiscovery" class="text-center"> <thead class="bg-light text-capitalize"> <tr> <th>ID</th> <th>Name</th> <th>IP or Hostname</th> <th>Type</th> <th>Operations</th> </tr> </thead> </table> </div> </div> </div> </div> </div> </div>');

        ajaxCalls.ajaxPostCall(request);

        $(".modal-body label").css("font-weight", "bold");

        discovery.onClicks();

        discovery.preventSelectChangeEditModal();
    },

    addModalTypeClick: function ()
    {
        let modalSelector = $("#addModal");

        let sshFieldsSelector = modalSelector.find("#sshFields");

        sshFieldsSelector.hide();

        modalSelector.find("select").on("click", function (event)
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

    discoveryHtmlLoaderSuccess: function (request)
    {
        dataTable = $('#dataTableDiscovery').DataTable();

        let table = request.bean.tableData;

        if (table !== null)
        {
            tableRows = table.length;

            let rowHtml, rowLength;

            //todo optimise using $.each
            for (let i = 0; i < tableRows; i++)
            {
                rowHtml = [];

                rowHtml[0] = i + 1;

                for (let j = 1; j < table[i].length - 1; j++)
                {
                    rowHtml[j] = table[i][j];
                }

                rowLength = table[i].length;

                rowHtml[rowLength - 1] = '<div class="discoveryOperationsCell" data-database_table_id = "' + table[i][0] +
                    '"><i class="fa fa-play"></i><i class="fa fa-edit"></i><i class="fa fa-archive"></i>';

                if (table[i][rowLength - 1] === 1)
                {
                    rowHtml[rowLength - 1] += '<input class="provision" type="button" value="Provision">'
                }

                rowHtml[rowLength - 1] += '</div>';

                dataTable.row.add(rowHtml).draw();
            }
            discovery.iconHover();
        }
        else
        {
            alert("Null table returned")
        }
    },

    onClicks: function ()                         //todo creating all methods
    {
        discovery.addModalTypeClick();

        discovery.addDeviceClick();

        discovery.deleteIconClick();

        discovery.editIconClick();

        discovery.discoverIconClick();

        discovery.provisionIconClick();

        discovery.editDeviceButtonClick();
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
                    url: "insertDiscoveryRow",

                    param: param,

                    callback: discovery.addDeviceSuccess
                };

            ajaxCalls.ajaxPostCall(request);
        });
    },

    addDeviceSuccess: function (request)
    {
        if (request.bean.operationSuccess === true)
        {
            let rowHtml = [];

            rowHtml[0] = ++tableRows;

            rowHtml[1] = request.bean.name;

            rowHtml[2] = request.bean.ipHostname;

            rowHtml[3] = request.bean.type;

            rowHtml[4] = '<div class="discoveryOperationsCell" data-database_table_id = "' + request.bean.id +
                '"><i class="fa fa-play"></i><i class="fa fa-edit"></i><i class="fa fa-archive"></i>';

            if (request.bean.provision === 1)
            {
                rowHtml[4] += '<input class="provision" type="button" value="Provision">'
            }

            rowHtml[4] += '</div>';

            dataTable.row.add(rowHtml).draw();

            discovery.iconHover();

            $('#addModal').modal('hide');

            $('#addModal').find('input').val("");
        }
        else
        {
            alert("Insertion unsuccessful");
        }
    },

    deleteIconClick: function ()
    {
        $("#main-area").on("click", "#dataTableDiscovery .fa-archive", function (event)
        {
            deleteEventTarget = event.target.parentNode.parentNode.parentNode;

            let deleteRowId = $(event.target).parent().data("database_table_id");

            let param = {
                tableName: "discovery",

                tableId: deleteRowId
            };

            let request = {
                url: "deleteDiscoveryRow",

                param: param,

                callback: discovery.deleteRowSuccess
            };

            ajaxCalls.ajaxPostCall(request);
        });
    },

    deleteRowSuccess: function (request)
    {
        if (request.bean.deletionStatus === true)
        {
            dataTable.row(deleteEventTarget).remove().draw();
        }
        else            //fixme how to handle unsuccessful delete operations
        {
            alert("Deletion unsuccessful")
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
        $("#main-area").on("click", "#dataTableDiscovery .fa-edit", function (event)
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
        if (request.bean.operationSuccess === true)
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

            modalSelector.modal('show');
        }
        else
        {
            alert("Null device data returned")
        }
    },

    // fetchEditRowSuccess: function (request)
    // {
    //     let returnRow = request.bean.returnRow;
    //
    //     if (returnRow !== null)
    //     {
    //         let modalSelector = $("#editModal");
    //
    //         let sshFieldSelector = modalSelector.find("#sshFields");
    //
    //         modalSelector.find('.deviceId').val(returnRow[0]);
    //         modalSelector.find('.deviceName').val(returnRow[1]);
    //         modalSelector.find('.deviceIpHostname').val(returnRow[2]);
    //         if (returnRow[3] === "ping")
    //         {
    //             sshFieldSelector.hide();
    //         }
    //         else
    //         {
    //             sshFieldSelector.show();
    //
    //             sshFieldSelector.find("input").prop("required", true);
    //         }
    //         modalSelector.find('.deviceType').val(returnRow[3]);
    //         modalSelector.find('.deviceUsername').val(returnRow[5]);
    //
    //         modalSelector.modal('show');
    //     }
    //     else
    //     {
    //         alert("Null device data returned")
    //     }
    // },

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

            console.log(param);

            let request = {
                url: "editDiscoveryDevice",

                param: param,

                success: discovery.editDeviceButtonClickSuccess
            };

            ajaxCalls.ajaxPostCall(request);
        });
    },

    editDeviceButtonClickSuccess: function (request)
    {
        if (request.bean.operationSuccess === true)
        {
            alert("Update successful");
        }
        else
        {
            alert("Update unsuccessful");
        }
    },

    discoverIconClick: function ()
    {
        $("#main-area").on("click", "#dataTableDiscovery .fa-play", function ()
        {
            alert("discover");
        });
    },

    provisionIconClick: function ()
    {
        $("#main-area").on("click", "#dataTableDiscovery .provision", function ()
        {
            alert("provision");
        });
    }


};
let monitorDataTable;

let monitor = {

    monitorHtmlLoader: function ()
    {
        let request =
            {
                url: "loadMonitorTable",

                callback: monitor.monitorHtmlLoaderSuccess
            };

        $("#main-area").html('<div class="row"> <div class="col-12 mt-5"> <div class="card"> <div class="card-body"> <h4 class="header-title">Monitored Devices</h4> <div class="data-tables"> <div class="data-tables"> <table id="dataTableMonitor" class="text-center"> <thead class="bg-light text-capitalize"> <tr> <th>ID</th> <th>Name</th> <th>IP or Hostname</th> <th>Type</th> <th>Availability</th> <th>Operations</th> </tr> </thead> </table> </div> </div> <div class="modal fade" id="deleteMonitorPopupModal"> <div class="modal-dialog modal-dialog-centered" role="document"> <div class="modal-content"> <div class="modal-header"> <h5 class="modal-title">Delete confirmation</h5> <button type="button" class="close" data-dismiss="modal"> <span>&times;</span> </button> </div> <div class="modal-body"> <p>Would you like to definitely delete the device from monitoring?</p> </div> <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-dismiss="modal" > Close </button> <button type="button" class="btn btn-primary" id="deleteDeviceConfirmationButton" > Delete device </button> </div> </div> </div> </div> </div> </div> </div> </div>');

        $(".modal-body label").css("font-weight", "bold");

        ajaxCalls.ajaxPostCall(request);
    },

    monitorHtmlLoaderSuccess: function (request)          //todo can create list<list<String>> and directly send to frontend to be added. Add the icons also at the backend side
    {
        monitorDataTable = $('#dataTableMonitor').DataTable();

        let table = request.bean.monitorTableData;

        monitor.tableLoader(table);
    },



};
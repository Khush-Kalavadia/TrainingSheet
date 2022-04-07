<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>Home page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../bootstrap/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="../bootstrap/css/themify-icons.css"/>
    <link rel="stylesheet" href="../bootstrap/css/metisMenu.css"/>
    <link rel="stylesheet" href="../bootstrap/css/owl.carousel.min.css"/>
    <link rel="stylesheet" href="../bootstrap/css/slicknav.min.css"/>
    <!-- amchart css -->
    <link
            rel="stylesheet"
            href="https://www.amcharts.com/lib/3/plugins/export/export.css"
            type="text/css"
            media="all"
    />
<%--
    <!--Start datatable css-->
    <link rel="stylesheet" href="../bootstrap/css/datatable.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.18/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.jqueryui.min.css">
--%>
    <!-- others css -->
    <link rel="stylesheet" href="../bootstrap/css/typography.css"/>
    <link rel="stylesheet" href="../bootstrap/css/default-css.css"/>
    <link rel="stylesheet" href="../bootstrap/css/styles.css"/>
    <link rel="stylesheet" href="../bootstrap/css/responsive.css"/>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">



    <!-- modernizr css -->
    <script src="../bootstrap/js/vendor/modernizr-2.8.3.min.js"></script>
</head>

<body class="body-bg">

<!-- preloader area start -->
<div id="preloader">
    <div class="loader"></div>
</div>
<!-- preloader area end -->
<!-- main wrapper start -->
<div class="horizontal-main-wrapper">
    <!-- main header area start -->
    <div class="mainheader-area">
        <div class="container">
            <div class="main align-items-center">
                <div class="col-md-3">

                    <div class="logo">
                        <%--<a href="mainPage"><img src="../bootstrap/images/icon/logo2.png" alt="logo"/></a>--%>
                        <h3>LiteNMS</h3>
                    </div>

                </div>
                <!-- profile info & task notification -->
                <div class="col-md-9 clearfix text-right">
                    <div class="d-md-inline-block d-block mr-md-4">
                        <ul class="notification-area">
                            <li id="full-view"><i class="ti-fullscreen"></i></li>
                            <li id="full-view-exit"><i class="ti-zoom-out"></i></li>
                            <li class="dropdown">
                                <i class="ti-bell dropdown-toggle" data-toggle="dropdown">
                                    <%--<span>2</span>--%>
                                </i>
                                <div class="dropdown-menu bell-notify-box notify-box">
                      <span class="notify-title"
                      >Notifications
                        </span>
                                    <div class="nofity-list">
                                        <a href="#" class="notify-item">
                                            <div class="notify-thumb">
                                                <i class="ti-key btn-danger"></i>
                                            </div>
                                            <div class="notify-text">
                                                <p>You have Changed Your Password</p>
                                                <span>Just Now</span>
                                            </div>
                                        </a>
                                        <a href="#" class="notify-item">
                                            <div class="notify-thumb">
                                                <i class="ti-comments-smiley btn-info"></i>
                                            </div>
                                            <div class="notify-text">
                                                <p>New Commetns On Post</p>
                                                <span>30 Seconds ago</span>
                                            </div>
                                        </a>
                                        <a href="#" class="notify-item">
                                            <div class="notify-thumb">
                                                <i class="ti-key btn-primary"></i>
                                            </div>
                                            <div class="notify-text">
                                                <p>Some special like you</p>
                                                <span>Just Now</span>
                                            </div>
                                        </a>
                                        <a href="#" class="notify-item">
                                            <div class="notify-thumb">
                                                <i class="ti-comments-smiley btn-info"></i>
                                            </div>
                                            <div class="notify-text">
                                                <p>New Commetns On Post</p>
                                                <span>30 Seconds ago</span>
                                            </div>
                                        </a>
                                        <a href="#" class="notify-item">
                                            <div class="notify-thumb">
                                                <i class="ti-key btn-primary"></i>
                                            </div>
                                            <div class="notify-text">
                                                <p>Some special like you</p>
                                                <span>Just Now</span>
                                            </div>
                                        </a>
                                        <a href="#" class="notify-item">
                                            <div class="notify-thumb">
                                                <i class="ti-key btn-danger"></i>
                                            </div>
                                            <div class="notify-text">
                                                <p>You have Changed Your Password</p>
                                                <span>Just Now</span>
                                            </div>
                                        </a>
                                        <a href="#" class="notify-item">
                                            <div class="notify-thumb">
                                                <i class="ti-key btn-danger"></i>
                                            </div>
                                            <div class="notify-text">
                                                <p>You have Changed Your Password</p>
                                                <span>Just Now</span>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="clearfix d-md-inline-block d-block">
                        <div class="user-profile m-0">
                            <img
                                    class="avatar user-thumb"
                                    src="../bootstrap/images/author/avatar.png"
                                    alt="avatar"
                            />
                            <h4 class="user-name dropdown-toggle" data-toggle="dropdown">
                                <span>admin</span> <i class="fa fa-angle-down"></i>
                                <!--todo add name of the user in span using the session get name-->
                            </h4>
                            <div class="dropdown-menu">
                                <%--<a class="dropdown-item" href="#">Log Out</a>--%>
                                <span class="dropdown-item">Log Out</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- main header area end -->
    <!-- header area start -->
    <div class="header-area header-bottom">
        <div class="container">
            <div class="main align-items-center">
                <div class="col-lg-9 d-none d-lg-block">
                    <div class="horizontal-menu">
                        <nav>
                            <ul id="nav_menu">
                                <li id="dashboardLi">
                                    <i class="ti-map-alt"></i> <span>Dashboard</span>
                                </li>
                                <li id="discoveryLi">
                                    <i class="ti-map-alt"></i> <span>Discovery</span>
                                </li>
                                <li id="monitorsLi">
                                    <i class="ti-map-alt"></i> <span>Monitors</span>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>

                <!-- mobile_menu -->
                <div class="col-12 d-block d-lg-none">
                    <div id="mobile_menu"></div>
                </div>
            </div>
        </div>
    </div>
    <!-- header area end -->
    <!-- page title area end -->
    <div class="main-content-inner">
        <div class="container" id="main-area">

        </div>
    </div>











    <!-- main content area end -->
</div>
<!-- offset area end -->

<!-- jquery latest version -->
<script src="../bootstrap/js/jquery-3.6.0.js"></script>
<!-- bootstrap 4 js -->
<script src="../bootstrap/js/popper.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script src="../bootstrap/js/owl.carousel.min.js"></script>
<script src="../bootstrap/js/metisMenu.min.js"></script>
<script src="../bootstrap/js/jquery.slimscroll.min.js"></script>
<script src="../bootstrap/js/jquery.slicknav.min.js"></script>

<!-- all line chart activation -->
<script src="../bootstrap/js/line-chart.js"></script>
<!-- all pie chart -->
<script src="../bootstrap/js/pie-chart.js"></script>
<!-- all bar chart -->
<script src="../bootstrap/js/bar-chart.js"></script>
<!-- all map chart -->
<script src="../bootstrap/js/maps.js"></script>
<!-- others plugins -->
<script src="../bootstrap/js/plugins.js"></script>
<script src="../bootstrap/js/scripts.js"></script>

<%--
<!-- Start datatable js -->
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.18/js/dataTables.bootstrap4.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap.min.js"></script>
--%>

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>


<script src="../js/navigationBar.js"></script>
<script src="../js/dashboard.js"></script>
<script src="../js/ajaxCalls.js"></script>
<script src="../js/discovery.js"></script>
<script src="../js/monitors.js"></script>
<script>
    $(document).ready(function ()
    {
        navigationBar.horizontalMenuLoader();
    });
</script>

</body>
</html>

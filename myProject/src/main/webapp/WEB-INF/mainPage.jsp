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
                            <%--<li class="dropdown">--%>
                                <%--<i class="ti-bell dropdown-toggle" data-toggle="dropdown">--%>
                                    <%--&lt;%&ndash;<span>2</span>&ndash;%&gt;--%>
                                <%--</i>--%>
                                <%--<div class="dropdown-menu bell-notify-box notify-box">--%>
                      <%--<span class="notify-title"--%>
                      <%-->Notifications--%>
                        <%--</span>--%>
                                    <%--<div class="nofity-list">--%>
                                        <%--<a href="#" class="notify-item">--%>
                                            <%--<div class="notify-thumb">--%>
                                                <%--<i class="ti-key btn-danger"></i>--%>
                                            <%--</div>--%>
                                            <%--<div class="notify-text">--%>
                                                <%--<p>You have Changed Your Password</p>--%>
                                                <%--<span>Just Now</span>--%>
                                            <%--</div>--%>
                                        <%--</a>--%>
                                        <%--<a href="#" class="notify-item">--%>
                                            <%--<div class="notify-thumb">--%>
                                                <%--<i class="ti-comments-smiley btn-info"></i>--%>
                                            <%--</div>--%>
                                            <%--<div class="notify-text">--%>
                                                <%--<p>New Commetns On Post</p>--%>
                                                <%--<span>30 Seconds ago</span>--%>
                                            <%--</div>--%>
                                        <%--</a>--%>
                                        <%--<a href="#" class="notify-item">--%>
                                            <%--<div class="notify-thumb">--%>
                                                <%--<i class="ti-key btn-primary"></i>--%>
                                            <%--</div>--%>
                                            <%--<div class="notify-text">--%>
                                                <%--<p>Some special like you</p>--%>
                                                <%--<span>Just Now</span>--%>
                                            <%--</div>--%>
                                        <%--</a>--%>
                                        <%--<a href="#" class="notify-item">--%>
                                            <%--<div class="notify-thumb">--%>
                                                <%--<i class="ti-comments-smiley btn-info"></i>--%>
                                            <%--</div>--%>
                                            <%--<div class="notify-text">--%>
                                                <%--<p>New Commetns On Post</p>--%>
                                                <%--<span>30 Seconds ago</span>--%>
                                            <%--</div>--%>
                                        <%--</a>--%>
                                        <%--<a href="#" class="notify-item">--%>
                                            <%--<div class="notify-thumb">--%>
                                                <%--<i class="ti-key btn-primary"></i>--%>
                                            <%--</div>--%>
                                            <%--<div class="notify-text">--%>
                                                <%--<p>Some special like you</p>--%>
                                                <%--<span>Just Now</span>--%>
                                            <%--</div>--%>
                                        <%--</a>--%>
                                        <%--<a href="#" class="notify-item">--%>
                                            <%--<div class="notify-thumb">--%>
                                                <%--<i class="ti-key btn-danger"></i>--%>
                                            <%--</div>--%>
                                            <%--<div class="notify-text">--%>
                                                <%--<p>You have Changed Your Password</p>--%>
                                                <%--<span>Just Now</span>--%>
                                            <%--</div>--%>
                                        <%--</a>--%>
                                        <%--<a href="#" class="notify-item">--%>
                                            <%--<div class="notify-thumb">--%>
                                                <%--<i class="ti-key btn-danger"></i>--%>
                                            <%--</div>--%>
                                            <%--<div class="notify-text">--%>
                                                <%--<p>You have Changed Your Password</p>--%>
                                                <%--<span>Just Now</span>--%>
                                            <%--</div>--%>
                                        <%--</a>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</li>--%>
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
                                <span id="usernameLabel"></span> <i class="fa fa-angle-down"></i>
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

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<%--<!-- start amchart js -->--%>
<%--<script src="https://www.amcharts.com/lib/3/amcharts.js"></script>--%>
<%--<script src="https://www.amcharts.com/lib/3/serial.js"></script>--%>
<%--<script src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>--%>
<%--<script src="https://www.amcharts.com/lib/3/themes/light.js"></script>--%>


<!-- bootstrap 4 js -->
<script src="../bootstrap/js/popper.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script src="../bootstrap/js/owl.carousel.min.js"></script>
<script src="../bootstrap/js/metisMenu.min.js"></script>
<script src="../bootstrap/js/jquery.slimscroll.min.js"></script>
<script src="../bootstrap/js/jquery.slicknav.min.js"></script>



<%--<!-- all line chart activation -->--%>
<%--<script src="../bootstrap/js/line-chart.js"></script>--%>
<%--<!-- all pie chart -->--%>
<%--<script src="../bootstrap/js/pie-chart.js"></script>--%>
<%--<!-- all bar chart -->--%>
<%--<script src="../bootstrap/js/bar-chart.js"></script>--%>
<%--<!-- all map chart -->--%>
<%--<script src="../bootstrap/js/maps.js"></script>--%>
<!-- others plugins -->
<script src="../bootstrap/js/plugins.js"></script>
<script src="../bootstrap/js/scripts.js"></script>

<%--<!-- start chart js -->--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>--%>
<%--<!-- start highcharts js -->--%>
<%--<script src="https://code.highcharts.com/highcharts.js"></script>--%>
<%--<!-- start zingchart js -->--%>
<%--<script src="https://cdn.zingchart.com/zingchart.min.js"></script>--%>
<%--<script>--%>
    <%--zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";--%>
    <%--ZC.LICENSE = [--%>
        <%--"569d52cefae586f634c54f86dc99e6a9",--%>
        <%--"ee6b7db5b51705a13dc2339db3edaf6d",--%>
    <%--];--%>
<%--</script>--%>


<script type="text/javascript" charset="utf8" src="../bootstrap/js/jquery.dataTables.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.0.0/dist/chart.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.0.0"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/chartjs-plugin-datalabels/2.0.0-rc.1/chartjs-plugin-datalabels.min.js" integrity="sha512-+UYTD5L/bU1sgAfWA0ELK5RlQ811q8wZIocqI7+K0Lhh8yVdIoAMEs96wJAIbgFvzynPm36ZCXtkydxu1cs27w==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script src="../js/navigationBar.js"></script>
<script src="../js/dashboard.js"></script>
<script src="../js/ajaxCalls.js"></script>
<script src="../js/discovery.js"></script>
<script src="../js/monitor.js"></script>

<!--For toastr notification-->
<script type="text/javascript" src="../bootstrap/js/toastr.min.js"></script>
<link rel="stylesheet" href="../bootstrap/css/toastr.min.css">

<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<script>
    $(document).ready(function ()
    {
        navigationBar.horizontalMenuLoader();
    });
</script>

</body>
</html>

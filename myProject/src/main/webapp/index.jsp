<%--<html>--%>
<%--<body>--%>
<%--<h1>NMS</h1>--%>
<%--<h2>Login Window</h2>--%>
<%--&lt;%&ndash;<form method="POST" action="loginCheck">&ndash;%&gt;--%>
<%--<form>--%>
<%--<label>Username<input type="text" name="username"></label>--%>
<%--<br>--%>
<%--<label>Password<input type="password" name="password"></label>--%>
<%--<br>--%>
<%--<input type="button" value="Submit">--%>
<%--</form>--%>
<%--&lt;%&ndash;<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>&ndash;%&gt;--%>
<%--<script src="bootstrap/js/jquery-3.6.0.js"></script>--%>
<%--<script src="js/ajaxCalls.js"></script>--%>
<%--<script>--%>
<%--$(document).ready(function ()--%>
<%--{--%>
<%--ajaxCalls.getDataFromForm();--%>
<%--});--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>

<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>Login screen</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <%--<link--%>
    <%--rel="shortcut icon"--%>
    <%--type="image/png"--%>
    <%--href="bootstrap/images/icon/favicon.ico"--%>
    <%--/>--%>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="bootstrap/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="bootstrap/css/themify-icons.css"/>
    <link rel="stylesheet" href="bootstrap/css/metisMenu.css"/>
    <link rel="stylesheet" href="bootstrap/css/owl.carousel.min.css"/>
    <link rel="stylesheet" href="bootstrap/css/slicknav.min.css"/>
    <!-- amchart css -->
    <link
            rel="stylesheet"
            href="https://www.amcharts.com/lib/3/plugins/export/export.css"
            type="text/css"
            media="all"
    />
    <!-- others css -->
    <link rel="stylesheet" href="bootstrap/css/typography.css"/>
    <link rel="stylesheet" href="bootstrap/css/default-css.css"/>
    <link rel="stylesheet" href="bootstrap/css/styles.css"/>
    <link rel="stylesheet" href="bootstrap/css/responsive.css"/>
    <!-- modernizr css -->
    <script src="bootstrap/js/vendor/modernizr-2.8.3.min.js"></script>
</head>

<body>
<!-- preloader area start -->
<div id="preloader">
    <div class="loader"></div>
</div>
<!-- preloader area end -->
<!-- login area start -->
<div class="login-area login-bg">
    <div class="container">
        <div class="login-box ptb--70">
            <form method="post">          <!--form action="login"-->
                <div class="login-form-head">
                    <h1>LiteNMS</h1>
                    <hr/>
                    <h5>Sign In</h5>
                    <p>Hello there, Sign in and start monitoring your network</p>
                </div>
                <div id="loginUnsuccessfulAlert"></div>
                <div class="login-form-body">
                    <div class="form-gp">
                        <label for="loginInputUsername">Username</label>
                        <input type="text" name="username" id="loginInputUsername"/>
                        <i class="ti-email"></i>
                    </div>
                    <div class="form-gp">
                        <label for="loginInputPassword">Password</label>
                        <input type="password" name="password" id="loginInputPassword"/>
                        <i class="ti-lock"></i>
                    </div>
                    <div class="submit-btn-area">
                        <%--<button id="form_submit" type="submit">--%>
                        <%--Submit <i class="ti-arrow-right"></i>--%>
                        <%--</button>--%>
                        <button id="form_submit">               <!--type="submit"-->
                            Login <i class="ti-arrow-right"></i>
                        </button>
                    </div>

                    <!-- uncomment for sign up -->
                    <!-- <div class="form-footer text-center mt-5">
                      <p class="text-muted">
                        Don't have an account? <a href="register.html">Sign up</a>
                      </p>
                    </div> -->
                </div>
            </form>
        </div>
    </div>
</div>
<!-- login area end -->

<!-- jquery latest version -->
<script src="bootstrap/js/jquery-3.6.0.js"></script>
<!-- bootstrap 4 js -->
<script src="bootstrap/js/popper.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/owl.carousel.min.js"></script>
<script src="bootstrap/js/metisMenu.min.js"></script>
<script src="bootstrap/js/jquery.slimscroll.min.js"></script>
<script src="bootstrap/js/jquery.slicknav.min.js"></script>

<!-- others plugins -->
<script src="bootstrap/js/plugins.js"></script>
<script src="bootstrap/js/scripts.js"></script>

<script src="js/ajaxCalls.js"></script>
<script>
    $(document).ready(function ()
    {
        ajaxCalls.getDataFromForm();
    });
</script>

</body>
</html>


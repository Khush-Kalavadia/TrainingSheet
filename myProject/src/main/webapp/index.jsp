<html>
<body>
<h1>NMS</h1>
<h2>Login Window</h2>
<%--<form method="POST" action="loginCheck">--%>
<form>
    <label>Username<input type="text" name="username"></label>
    <br>
    <label>Password<input type="password" name="password"></label>
    <br>
    <input type="button" value="Submit">
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="js/ajaxCalls.js"></script>
<script>
    $(document).ready(function ()
    {
        ajaxCalls.getDataFromForm();
    });
</script>
</body>
</html>

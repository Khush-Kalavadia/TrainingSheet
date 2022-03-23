<html>
<body>
<h2>Hello World!</h2>
<%--<form method="POST" action="login">--%>
<form>
    <label>Username<input type="text" name="username"></label>
    <label>Password<input type="password" name="password"></label>
    <input type="button" value="Submit">
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="js/ajaxCalls.js"></script>
<script>
    $(document).ready(function ()
    {
//       ajaxCalls.ajaxPostCall();
        ajaxCalls.getDataFromForm();
    });
</script>
</body>
</html>

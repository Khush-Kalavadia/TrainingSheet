<html>
<head>
    <title>File to enter data into a table</title>
</head>
<body>

<div id="main">
    <h1>Student data</h1>
    <div id="addStudent">
    <h2>Add student data</h2>
    <form name="studentDetails">
        <label>Roll number: <input type="text" name="roll"></label>
        <br>
        <label>First name: <input type="text" name="firstName"></label>
        <br>
        <label>Last name: <input type="text" name="lastName"></label>
        <br>
        <label>Degree: <select name="degree">
            <option value="btech">Btech</option>
            <option value="bsc">BSc</option>
            <option value="diploma">Diploma</option>
        </select></label>
        <br>
        <input type="submit" value="Click to add user">
    </form>
    </div>

    <div id="updateStudent">
        <h2>Update student data</h2>
        <form name="updateDetails">
            <label>Roll number: <input type="text" name="roll"></label>
            <br>
            <label>First name: <input type="text" name="firstName"></label>
            <br>
            <label>Last name: <input type="text" name="lastName"></label>
            <br>
            <label>Degree: <select name="degree">
                <option value="btech">Btech</option>
                <option value="bsc">BSc</option>
                <option value="diploma">Diploma</option>
            </select></label>
            <br>
            <input type="submit" value="Click to update user based on roll number">
            <span id="noUpdateUser">User does not exist</span>
        </form>
    </div>

    <div id="content">
    <h2>Delete student data</h2>
    <input type="text" name="deleteRoll">
    <input type="button" id="deleteButton" value="Click to delete user with given roll number">
    <span id="noDeleteUser">User not found</span>

    <h2>Student list</h2>
    <table>
        <tr>
            <th>Roll number</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Degree</th>
        </tr>
    </table>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
    * {
        padding: 8px 0 8px 0;
    }

    h1 {
        text-align: center;
    }
    #addStudent{
        width: 50%;
        float: left;
    }
    #updateStudent{
        width: 50%;
        float: left;
    }
    #noDeleteUser, #noUpdateUser{
        display: none;
    }
    #content{
        clear: both;
    }
    #main{
        padding: 15px;
    }
    table{
        border: 2px solid black;
        padding: 5px 15px;
        text-space: 5px;
    }
</style>
<script src="js/studentDetails.js"></script>
<script>
    $(document).ready(function ()
    {
        index.onSubmit();
        index.onDelete();
        index.onUpdate();
    });
</script>
</body>
</html>

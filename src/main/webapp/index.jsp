<html>
<head>
    <title>Create new</title>
</head>
<body>

<form action="add" method="post">
    <label for="name">Create new
        <input type="text" id="name" value="${client.uId}" uId="uId" />
    </label>  <br />
    <input type="hidden" name="id" value="${user.id}" />
    <input type="submit" value="Save" />
</form>

</body>
</html>

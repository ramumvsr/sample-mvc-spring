<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resources/css/styles.css">
<script  type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>

 <script type="text/javascript" src="./resources/js/login.js"></script>

</head>
<body>

<h2>Login Form</h2>

<form action="">
  <div class="imgcontainer">
    <img src="<%=request.getContextPath()%>/resources/images/profile.png" alt="Avatar" class="avatar" >
  </div>

  <div class="container">
    <label><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="uname" id="username">

    <label><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" id="password">
        
    <button type="submit" onclick="window.location.href='/second'" id="sign">Login</button>
    <input type="checkbox" > Remember me
  </div>

  <div class="container" style="background-color:#f1f1f1">
    <button type="button" class="cancelbtn">Cancel</button>
    <span class="psw">Forgot <a href="cancel">password?</a></span>
  </div>
</form>




</body>
</html>
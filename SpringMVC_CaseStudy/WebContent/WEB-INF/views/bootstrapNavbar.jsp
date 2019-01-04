<!DOCTYPE html>
<html>
<head>
<style>
.headerul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
}


li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  float: left;
}

li a:hover:not(.active) {
  background-color: #111;
}

.active {
  background-color: #4CAF50;
}
</style>
</head>
<body>

<ul class ="headerul">

  <li><a href="profile">Profile</a></li>
  <li><a href="search">Search Job</a></li>
  <li><a href="createJob">Post Job</a></li>
  <li style="float: right;"><a href="">Logout</a></li>
</ul>

</body>
</html>

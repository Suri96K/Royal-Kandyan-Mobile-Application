<?php
$db_name="royalkandyan";
$mysql_username="root";
$mysql_password="";
$server_name="localhost";
$connect=mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);

if($connect){
		echo"connection success";
}
else{
		echo"connection not success";
}
?>
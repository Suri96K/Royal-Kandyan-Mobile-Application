<?php

include 'DatabaseConfig.php' ;
 
 $con = mysqli_connectect($HostName,$HostUser,$HostPass,$DatabaseName);
 
 $name = $_POST['name'];
 $email = $_POST['email'];
 $feedback = $_POST['feedback'];
 

 $Sql_Query = "insert into feedback (name,email,feedback) values ('$name','$email','$feedback')";
 
 if(mysqli_query($con,$Sql_Query)){
 
 echo 'Data Submit Successfully';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($con);
?>
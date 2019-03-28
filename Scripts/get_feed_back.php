<?php

include 'DatabaseConfig.php' ;
 
 $connect = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 
 
 $name = $_POST['name'];
 $email = $_POST['email'];
 $feedback = $_POST['feedback'];
 

 $Sql_Query = "INSERT INTO feedback (name,email,feedback) VALUES ('$name','$email','$feedback')";
 
 if(mysqli_query($connect,$Sql_Query)){
 
 echo 'Data Submit Successfully';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($connect);
?>
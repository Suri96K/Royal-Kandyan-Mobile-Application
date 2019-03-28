<?php

include 'DatabaseConfig.php' ;
 
 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 
 $Sender = $_POST['Sender'];
 $Message = $_POST['Message'];
 $Receiver = $_POST['Receiver'];

 $Sql_Query = "insert into message (Sender,Message,Receiver) values ('$Sender','$Message','$Receiver')";
 
 if(mysqli_query($con,$Sql_Query)){
 
 echo 'Data Submit Successfully';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($con);
?>
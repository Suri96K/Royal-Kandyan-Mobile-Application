<?php

include 'DatabaseConfig.php' ;
 
 $connect = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 
 $Sender = $_POST['Sender'];
 $Receiver = $_POST['Receiver'];
 $Message = $_POST['Message'];
 

 $Sql_Query = "insert into message (Sender,Receiver,Message) values ('$Sender','$Receiver','$Message')";
 
 if(mysqli_query($connect,$Sql_Query)){
 
 echo 'Data Submit Successfully';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($connect);
?>
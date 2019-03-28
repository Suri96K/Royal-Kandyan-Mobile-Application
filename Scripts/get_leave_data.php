<?php

include 'DatabaseConfig.php' ;
 
 $connect = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 
 $Name = $_POST['Name'];
 $Reason = $_POST['Reason'];
 

 $Sql_Query = "insert into leaverequest (Name,Reason) values ('$Name','$Reason')";
 
 if(mysqli_query($connect,$Sql_Query)){
 
 echo 'Data Submit Successfully';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($connect);
?>
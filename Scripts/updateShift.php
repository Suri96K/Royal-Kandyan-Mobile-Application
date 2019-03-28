<?php

include 'DatabaseConfig.php' ;
 
 $connect = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 
 $name = $_POST['name'];
 $eid = $_POST['eid'];
 $shift = $_POST['shift'];
 

 $Sql_Query = "UPDATE shift SET name='$name',eid='$eid',shift='$shift'";
 
 if(mysqli_query($connect,$Sql_Query)){
 
 echo 'Data Submit Successfully';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($connect);
?>
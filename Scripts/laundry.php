<?php

include 'DatabaseConfig.php' ;
 
 $connect = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 
 $name = $_POST['name'];
 $roomno = $_POST['roomno'];
 $noofitems = $_POST['noofitems'];
 $itemdiscription = $_POST['itemdiscription'];
 

 $Sql_Query = "insert into laundry (name,roomno,noofitems,itemdiscription) values ('$name','$roomno','$noofitems','$itemdiscription')";
 
 if(mysqli_query($connect,$Sql_Query)){
 
 echo 'Data Submit Successfully';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($connect);
?>
<?php
include 'DatabaseConfig.php';

// Create connectection
$connect = new mysqli($HostName, $HostUser, $HostPass, $DatabaseName);

if ($connect->connect_error) {
 
 die("connectection failed: " . $connect->connect_error);
} 

$sql = "SELECT Sender,Message FROM message WHERE Receiver=hrm";

$result = $connect->query($sql);

if ($result->num_rows >0) {
 
 
 while($row[] = $result->fetch_assoc()) {
 
 $tem = $row;
 
 $json = json_encode($tem);
 
 }
 
} else {
 echo "No Results Found.";
}
 echo $json;
$connect->close();
?>
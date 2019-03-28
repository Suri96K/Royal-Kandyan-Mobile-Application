<?php
include 'ConfigDB.php' ;
 
 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['code'])) {
 
    // receiving the post params
    $code = $_POST['code'];
   
	
	// call your method to get the item
    $code = getItem($code);
 
    if ($code != false) {
        // Item found
		
		// Response fields are set as in android app
		// Check InventoryActivity.java line #112
        $response["error"] = FALSE;
        $response["item"]["code"] = $user["code"];			// change these fields to match your Database
        $response["code"]["cost"] = $user["cost"];
        $response["code"]["category"] = $user["category"];
		// add the rest fields here
       
        echo json_encode($response);
    } else {
        // Item is not found
        $response["error"] = TRUE;
        $response["error_msg"] = "Invalied Item Id. Please try again!";
        echo json_encode($response);
    }
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters missing!!!";
    echo json_encode($response);
}

?>
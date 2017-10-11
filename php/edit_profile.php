<?php
    $connect = mysqli_connect("localhost", "sababa", "sababauser", "Sababa");
    $connect->set_charset("utf8"); 

    $id = $_POST['id'];
    $name = $_POST['name'];
    $address = $_POST['address'];
    $number = $_POST['number'];
    $lat = $_POST['lat'];
    $lo = $_POST['lo'];
    
    $response = array();
    
   $response["success"] = false;
  
        
    $sql = "UPDATE profile SET Bname ='$name' , phoneN='$number' ,address ='$address' ,latitude ='$lat' ,longitude='$lo' WHERE id = '$id' ";
    
    
    if(mysqli_query($connect,$sql)){
        $response["success"] = true;  
    }
    
   echo json_encode($response);

?>
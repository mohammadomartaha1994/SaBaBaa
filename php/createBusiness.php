<?php

  $connect = mysqli_connect("localhost", "sababa", "sababauser", "Sababa");
  $connect->set_charset("utf8"); 
  
   
    
    $Bname = $_POST['name'];
    $cat = $_POST['category'];
    $Bcity = $_POST['city'];
    $phoneN = $_POST['number'];
    $address = $_POST['address'];
    $latitude = $_POST['lat'];  
    $longitude = $_POST['lo']; 
    $image = "http://mohammadomartaha.com/tampass/BusinessImages/tampass.png";
    $id_preson = $_POST['id_preson'];
    $publish = "false";
    
    
    
    $response = array();
    
   $response["success"] = false;
   
 $statement = mysqli_prepare($connect, " INSERT INTO profile(Bname,cat,Bcity,phoneN,address,latitude,longitude,image,id_preson,publish) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) ");
   mysqli_stmt_bind_param($statement, "ssssssssss", $Bname , $cat , $Bcity , $phoneN , $address , $latitude , $longitude , $image , $id_preson ,$publish);
   mysqli_stmt_execute($statement);
   
    $statement = mysqli_prepare($connect, "SELECT * FROM profile WHERE Bname= '".$Bname."' AND cat= '".$cat."'  AND Bcity= '".$Bcity."' ");
		    mysqli_stmt_bind_param($statement, "sss", $Bname, $cat, $Bcity);
		    mysqli_stmt_execute($statement);
		    
		    mysqli_stmt_store_result($statement);
		    mysqli_stmt_bind_result($statement, $id );
		    
		    
		    while(mysqli_stmt_fetch($statement)){
		        $response["success"] = true;  
		        $response["id"] = $id; 
		    
		    }

    	
   echo json_encode($response);
    
?>

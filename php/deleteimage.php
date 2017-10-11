 <?php
 
 
 
  $connect = mysqli_connect("localhost", "sababa", "sababauser", "Sababa");
  $connect->set_charset("utf8"); 
  
 
        $id=$_POST['id'];
        
    
  
	mysqli_query($connect,"DELETE FROM Events  WHERE id= $id ");
	
	$response = array();
        $response["success"] = true;  
    
        echo json_encode($response);
        
        ?>

<?php

  $connect = mysqli_connect("localhost", "sababa", "sababauser", "Sababa");
  $connect->set_charset("utf8"); 
  
    $username = $_POST["username"];
    $password = $_POST["password"];
    $mail= $_POST["mail"];
    $password=md5($password);

    $result = mysqli_query($connect , "SELECT username FROM CreateProfile WHERE username='".$username."' ");
    $row_cnt = mysqli_num_rows($result);
    
    $response = array();
             
             if($row_cnt >= 1){
                    $response["success"] = false;  
             
             }else
             {
		    $statement = mysqli_prepare($connect, " INSERT INTO CreateProfile(username,password,mail) VALUES ( ? , ? , ? ) ");
		    mysqli_stmt_bind_param($statement, "sss", $username , $password , $mail);
		    mysqli_stmt_execute($statement);
                    
                    
                    
		    $statement = mysqli_prepare($connect, "SELECT * FROM CreateProfile WHERE username = '".$username."' AND password = '".$password."' ");
		    mysqli_stmt_bind_param($statement, "ss", $username, $password);
		    mysqli_stmt_execute($statement);
		    
		    mysqli_stmt_store_result($statement);
		    mysqli_stmt_bind_result($statement, $id, $username, $password, $mail);
		    
		    
		    while(mysqli_stmt_fetch($statement)){
		        $response["success"] = true;  
		        $response["id"] = $id; 
		        $response["username"] = $username;
		        $response["mail"] = $mail;
		    }
    
    
             }

  
    
    
        
    	
        echo json_encode($response);
    
?>

<?php
    $connect = mysqli_connect("localhost", "sababa", "sababauser", "Sababa");
    $connect->set_charset("utf8"); 

  
    $username = $_POST["username"];
    $password = $_POST["password"];
    $password=md5($password);


    $statement = mysqli_prepare($connect, "SELECT * FROM CreateProfile WHERE username = ? AND password = ? ");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $id, $username, $password, $mail);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["id"] = $id; 
        $response["username"] = $username;
        $response["mail"] = $mail;
    }
    
    echo json_encode($response);


    
?>
<?php
    $connect = mysqli_connect("localhost", "sababa", "sababauser", "Sababa");
    $connect->set_charset("utf8"); 
    
    $name = $_POST['name'];  
    $score = $_POST['score'];


  
    $sql =  "UPDATE `GuessFigure` SET `name`=$name,`score`=$score ; ";
    

    if(mysqli_query($connect,$sql)){
        file_put_contents($path,base64_decode($photo));
        echo "Successfully";
    }
?>
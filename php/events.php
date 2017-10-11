<?php
    $connect = mysqli_connect("localhost", "sababa", "sababauser", "Sababa");
    $connect->set_charset("utf8"); 

    $milliseconds = round(microtime(true) * 1000);
    $id = $_GET['id'];
    $desc = $_POST['desc'];
    $photo = $_POST['image'];
    $name = $_POST['name'];
    
 
 
    $path = "BusinessImages/$milliseconds.png";
    $actualpath = "http://mohammadomartaha.com/tampass/$path";
    
    
    $sql =  "INSERT INTO  `Sababa`.`Events` (`descc` ,`image` ,`name`, `id_owner`) VALUES (  '$desc',  '$actualpath',  '$name',  '$id'); ";
    

    if(mysqli_query($connect,$sql)){
        file_put_contents($path,base64_decode($photo));
        echo "Successfully Uploaded";
    }

    
?>
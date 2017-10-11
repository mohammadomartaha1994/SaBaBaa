<?php
    $connect = mysqli_connect("localhost", "sababa", "sababauser", "Sababa");
    $connect->set_charset("utf8"); 

    $milliseconds = round(microtime(true) * 1000);
    $id = $_GET['id'];
    $photo = $_POST['image'];
 
    $path = "BusinessImages/$milliseconds.png";
    $actualpath = "http://mohammadomartaha.com/tampass/$path";
    
    $sql = "UPDATE profile SET image ='$actualpath' WHERE id = '$id' ";
    
    
    if(mysqli_query($connect,$sql)){
        file_put_contents($path,base64_decode($photo));
        echo "Successfully Uploaded";
    }
    mysqli_close($connect);
?>
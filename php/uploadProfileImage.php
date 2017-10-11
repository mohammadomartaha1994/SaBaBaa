<?php
    $connect = mysqli_connect("localhost", "sababa", "sababauser", "Sababa");
    $connect->set_charset("utf8"); 

  
    $milliseconds = round(microtime(true) * 1000);
    
    $image = $_POST['image'];  
    $Bname = $_POST['name'];
    $cat = $_POST['category'];
    $phoneN = $_POST['number'];
    $address = $_POST['address'];
    $Bcity = $_POST['city'];
    $latitude = $_POST['lat'];  
    $longitude = $_POST['lo']; 
    
    
  /*  
    
$statement = mysqli_prepare($connect, " INSERT INTO profile (Bname,cat,Bcity,phoneN,address,latitude,longitude,image) VALUES ( '$Bname' , '$cat' , '$Bcity' , '$phoneN' , '$address' , '$latitude' , '$longitude' , '$image' ) ");
mysqli_stmt_bind_param($statement, "ssssssss", $Bname , $cat , $Bcity , $phoneN , $address , $latitude , $longitude , $image );
mysqli_stmt_execute($statement);

      echo "Done";
    
    
  
  
   $pathhh = "http://mohammadomartaha.com/tampass/BusinessImages/tampass.png";
    
    $path = "BusinessImages/$milliseconds.png";
    $actualpath = "http://mohammadomartaha.com/tampass/$path";
    

    
    if(mysqli_query($connect, " INSERT INTO profile (Bname,cat,Bcity,phoneN,address,latitude,longitude,image) VALUES ( '$Bname' , '$cat' , '$Bcity' , '$phoneN' , '$address' , '$latitude' , '$longitude' , '$image' )) ")){
       file_put_contents($path,base64_decode($image));
        echo "Successfully Uploaded";
    }
    else
    {
   */
    $image= "http://mohammadomartaha.com/tampass/BusinessImages/tampass.png";
    
        $statement = mysqli_prepare($connect, " INSERT INTO profile (Bname,cat,Bcity,phoneN,address,latitude,longitude,image) VALUES ( '$Bname' , '$cat' , '$Bcity' , '$phoneN' , '$address' , '$latitude' , '$longitude' , '$image' ) ");
       
      mysqli_stmt_bind_param($statement, "ssssssss", $Bname , $cat , $Bcity , $phoneN , $address , $latitude , $longitude , $image );
      mysqli_stmt_execute($statement);

      echo " You Can Edit Your picture from your profile ";
    
    
    
    
    
    mysqli_close($connect);
    
    /*
?>
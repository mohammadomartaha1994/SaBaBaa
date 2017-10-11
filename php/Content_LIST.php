<?php


  $connect = mysqli_connect("localhost", "sababa", "sababauser", "Sababa");
  $connect->set_charset("utf8"); 
  
  $cat=$_GET["cat"];
  $city=$_GET["city"];
  
  
  //$query =" SELECT * FROM Events ORDER BY `id` DESC  WHERE  ";
  
  $query ="SELECT * FROM  `profile` WHERE  `cat` LIKE  '%".$cat."%' AND `Bcity` LIKE  '%".$city."%'  ORDER BY `id` DESC ";

  
  $result=  mysqli_query($connect, $query);
	if(! $result)
	{ die("Error in query");}
	 
	while($row=  mysqli_fetch_assoc($result))
	{
	 $output[]=$row; 
	}
	print(json_encode($output));
	mysqli_free_result($result);
	mysqli_close($connect);


?>

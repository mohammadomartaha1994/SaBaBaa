<?php


  $connect = mysqli_connect("localhost", "sababa", "sababauser", "Sababa");
  $connect->set_charset("utf8"); 
  
  $name=$_GET["name"];
  
  
  //$query =" SELECT * FROM Events ORDER BY `id` DESC  WHERE  ";
  
  $query ="SELECT * FROM  `profile` WHERE  `Bname` LIKE  '%".$name."%' OR `cat` LIKE  '%".$name."%'  ORDER BY `id` DESC  LIMIT 12 ";

  
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

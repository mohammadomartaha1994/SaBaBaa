<?php


  $connect = mysqli_connect("localhost", "sababa", "sababauser", "Sababa");
  $connect->set_charset("utf8"); 
  

  $id_preson= $_GET["id_preson"];
  
  $query =" SELECT * FROM profile WHERE id_preson= '".$id_preson."' ORDER BY `id` DESC ";
  
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

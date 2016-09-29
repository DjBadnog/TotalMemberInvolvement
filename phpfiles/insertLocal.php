<?php 

define("DB_HOST", "127.0.0.1");
define("DB_USER", "root");
define("DB_PASSWORD", "");
define("DB_DATABASE", "testingdb");
$con= mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_DATABASE);
      
mysqli_select_db($con,DB_DATABASE);

/*$_POST['name']="Jabu";
$_POST['surname']="Mkhize";
$_POST['email']="Jabu@gmail.com";
$_POST['password']="12345";
$_POST['mobile']="0718982323";
$_POST['address']="33 Johannesburg";*/


$name = $_POST["name"];
$surname=$_POST["surname"];
$email=$_POST["email"];
$password=$_POST["password"];
$mobile=$_POST["mobile"];
$physicalAddress=$_POST["address"];

$sql = "INSERT INTO user_table(NAME,SURNAME,EMAIL,PASSWORD,MOBILE,PHYSICAL_ADDRESS) VALUES('$name','$surname','$email','$password','$mobile','$physicalAddress');";

if(mysqli_query($con,$sql))
{
echo "Data inserted successfully";
}
else 
{
echo "Data insertion error..".mysqli_error($con);
}

?>
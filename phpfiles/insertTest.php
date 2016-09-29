<?php 
//require "config.php";
/*$session_start();
$ob_start();*/
/*$host="localhost";
$user="root";
$password="root";
$name=$_POST["name"];
$surname=$_POST["surname"];
$email=$_POST["email"];
$password=$_POST["password"];
$mobile=$_POST["mobile"];
$address=$_POST["physicalAddress"];
$name="Busi";
$surname="Nkosi";
$email="busi@gmail.com";
$password="12345";
$address="23 johannesburg";
$updatestatus="0";

$db_name="testingdb";
$conn = mysqli_connect($host,$user,$password,$db_name);

mysqli_select_db($con,$db_name);
$sql = "INSERT INTO user_table (NAME,SURNAME,EMAIL,PASSWORD,MOBILE,PHYSICAL_ADDRESS,UPDATESTATUS) VALUES('".$name."','".$surname."','".$email."','".$password."','".$mobile."','".$address."')";

if(mysqli_query($con,$sql))
{
	echo "Data insertion success...";
}
else {
	echo "Error while inserting...";
}
mysqli_close($con);*/

define("DB_HOST", "sql1.000webhost.com");
define("DB_USER", "a8875479_root");
define("DB_PASSWORD", "root");
define("DB_DATABASE", "a8875479_MemDB");

/*define("DB_HOST", "localhost");
define("DB_USER", "root");
define("DB_PASSWORD", "");
define("DB_DATABASE", "testingdb");*/
$con= mysql_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_DATABASE);

//$con=mysqli_connect("31.170.160.73","a8875479_root","root","a8875479_MemDB");

/*if(!$con){
	echo "Connection Error...".mysql_connection_error();
}
else{
	echo "Database connection ";
}*/
       
mysqli_select_db($con,DB_DATABASE);

$_POST['name']="Jabu";
$_POST['surname']="Mkhize";
$_POST['email']="Jabu@gmail.com";
$_POST['password']="12345";
$_POST['mobile']="0718982323";
$_POST['address']="33 Johannesburg";


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
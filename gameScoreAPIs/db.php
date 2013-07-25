<?
if($protect_key!="yuniz_lansy"){exit("Invalid Operation! Please contact system administrator.");} // include this on all modules page 1st line for script protection.
//setup SQL connection
    $connection = mysql_connect($database_hostaddress,$database_username,$database_password);
    mysql_select_db($database_name,$connection);
$databaseerrcheck=mysql_error();

mysql_query("SET character_set_client=utf8");
mysql_query("SET character_set_results=utf8");
mysql_query("SET character_set_connection=utf8");
?>
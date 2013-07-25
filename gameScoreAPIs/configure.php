<?
/*===========================================================================
Please set the correct system informations below.

Note:
- Create a blank database before you process this.
- This system only support MySQL database 5.x and above.
- This system only support PHP5.1.x and above.
- Please refer to server administrator if facing system issue.
===========================================================================*/

//---------define MySQL database connection details-----------------
$database_setting_file = "database_settings.php";
include $database_setting_file;
//---------define MySQL database connection details-----------------

//---------system details setting--------------
$system_name = "LansyChat - WikiBrain";
$system_version = "V1.0";
//---------system details setting--------------

//---------system debug setting----------------
error_reporting(0); // set 1 for display PHP error for debugging, set 0 to disable the error displaying.
//---------system debug setting----------------

//---------scripts attack protection-----------
$protect_key="yuniz_lansy"; // DO NOT CHANGE THIS
//---------scripts attack protection-----------
?>
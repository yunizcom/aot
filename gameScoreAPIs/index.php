<?
header('Content-Type: text/html; charset=big5'); 
ob_start("ob_gzhandler");
//-----------load main system files----------------
include "configure.php";
include "db.php";
//-----------load main system files----------------

//-----------functions---------------
$mod = $_GET['mod'];
//-----------functions---------------

if($mod == "1"){
	include "modules/saveGame.php";
}else if($mod == "2"){
	include "modules/gameScores.php";
}

//-----------load main system files----------------
include "db_close.php";
//-----------load main system files----------------
ob_end_flush();
?>
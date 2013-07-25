<?
if($protect_key!="yuniz_lansy"){exit("Invalid Operation! Please contact system administrator.");} // include this on all modules page 1st line for script protection.
//----------module own page setting--------------
$page = $_GET['page'];
if($page == null){$page = 1;}
$page--;
$page = $page * 10;
//----------module own page setting--------------
echo "{\"reply\": [";
$results= mysql_query("SELECT nickname,scores from t_scores GROUP BY nickname ORDER BY scores desc,tdate desc LIMIT ".$page.",10");
if(mysql_Numrows($results)>0){
	while ($row = mysql_fetch_array($results)) {
		echo "[\"".$row['nickname']."\",\"".$row['scores']."\"],";
	}
	echo "[\"\",\"\"]";
}
?>
]
}
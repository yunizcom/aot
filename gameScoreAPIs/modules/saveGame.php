<?
if($protect_key!="yuniz_lansy"){exit("Invalid Operation! Please contact system administrator.");} // include this on all modules page 1st line for script protection.
//----------module own page setting--------------
$nickname = $_GET['nickname'];
$score = $_GET['score'];
$reply = "saved";
//----------module own page setting--------------
if($nickname != "" && $score != ""){
	$curTimeStamp = time();
	$results= mysql_query("SELECT scores from t_scores WHERE nickname = '".$nickname."'");
	if(mysql_Numrows($results)>0){
		while ($row = mysql_fetch_array($results)) {
			$curTopScore = $row['scores'];
		}
		$breakRecords = "1";
		if($curTopScore <= $score){
			$breakRecords = "2";
			$curTopScore = $score;
			mysql_query("UPDATE t_scores 
						SET scores = '".$score."',
							tdate = '".$curTimeStamp."'
						WHERE nickname = '".$nickname."' ");
		}			
	}else{
		$curTopScore = $score;
		$breakRecords = "0";
		mysql_query("INSERT INTO `t_scores` (
										`nickname` ,
										`scores` ,
										`tdate`
									)
									VALUES (
										'".$nickname."',
										'".$score."',
										'".$curTimeStamp."'
									)");
	}
	
	//--------get the current position----------
	$results= mysql_query("SELECT (SELECT COUNT(*) FROM `t_scores` WHERE `scores` >= ".$curTopScore." ORDER BY tdate desc) AS `position`
							FROM `t_scores`
							WHERE `nickname` = '".$nickname."'");
	while ($row = mysql_fetch_array($results)) {
		$curPosistion = $row['position'];
	}
	$curPage = floor( $curPosistion / 10 ) + 1;
	//--------get the current position----------
	
	$reply = '[['.$curPosistion.'],['.$curPage.'],['.$breakRecords.']]';
}
?>
{
    "reply": <?=$reply?>
}
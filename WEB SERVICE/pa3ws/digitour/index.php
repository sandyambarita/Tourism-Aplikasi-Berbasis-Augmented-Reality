<?php
 
require_once 'include/DbHandler.php';
require_once 'include/PassHash.php';
require 'libs/Slim/Slim.php';

\Slim\Slim::registerAutoloader();

$app = new \Slim\Slim();

/**
 * ----------- METHODS WITHOUT AUTHENTICATION ---------------------------------
 */
 
//hasil

$app->post('/tambahLeaderboard/:challenge_type_name/:checkpoint_name/:user/:score/:date_time', 
	function($challenge_type_name, $checkpoint_name, $user, $score, $date_time){
	$response = array(); 
	$db = new DbHandler(); 
	try 
    {
		$response = $db->tambahLeaderboard($challenge_type_name, $checkpoint_name, $user, $score, $date_time); 
		echoRespnse(200, $response);
    } catch(PDOException $e) {
        $app->response()->setStatus(404);
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }
});

$app->post('/tambahAchievementUser/:user/:checkpoint_name/:challenge_type_name/:hadiah', 
	function($user, $checkpoint_name, $challenge_type_name, $hadiah){
	$response = array(); 
	$db = new DbHandler(); 
	try 
    {
		$response = $db->tambahAchievementUser($user, $checkpoint_name, $challenge_type_name, $hadiah); 
		echoRespnse(200, $response);
    } catch(PDOException $e) {
        $app->response()->setStatus(404);
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }
});


$app->post('/dataLocation', function() use ($app) {
		$response = array(); 

		$db = new DbHandler(); 

		// fetching all institusi
		$result = $db->getAllDataLocation(); 
		

		$response["error"] = false;
		$response["allLocation"] = array();
		
		for ($i=0; $i<count($result); $i++) {
		    $tmp = array();
			$tmp["location_id"] =	$result[$i]["location_id"];
			$tmp["location_name"] =	$result[$i]["location_name"];
			$tmp["city_name"] =	$result[$i]["city_name"];
			$tmp["path_gambar"] =	$result[$i]["path_gambar"];
			

		    array_push($response["allLocation"], $tmp);
	    }
		echoRespnse(200, $response);
});

$app->post('/dataChallengeType', function() use ($app) {
		$response = array(); 

		$db = new DbHandler(); 

		// fetching all institusi
		$result = $db->getAllChallengeType(); 
		

		$response["error"] = false;
		$response["allChallengeType"] = array();
		
		for ($i=0; $i<count($result); $i++) {
		    $tmp = array();
			$tmp["challenge_type_id"] =	$result[$i]["challenge_type_id"];
			$tmp["type_name"] =	$result[$i]["type_name"];
			$tmp["description"] =	$result[$i]["description"];


		    array_push($response["allChallengeType"], $tmp);
	    }
		echoRespnse(200, $response);
});

$app->post('/dataQuestion', function() use ($app) {
		$response = array(); 

		$db = new DbHandler(); 

		// fetching all institusi
		$result = $db->getAllQuestion(); 
		

		$response["error"] = false;
		$response["allQuestion"] = array();
		
		while ($strData = $result->fetch_assoc()) {
	    $tmp = array();
	    $tmp["checkpoint_id"] = utf8_encode($strData["checkpoint_id"]);
	    $tmp["challenge_type_id"] = utf8_encode($strData["challenge_type_id"]);
	    $tmp["question_id"] = utf8_encode($strData["question_id"]);
	    $tmp["no_soal"] = utf8_encode($strData["no_soal"]);
	    $tmp["question"] = utf8_encode($strData["question"]);
	    $tmp["option1"] = utf8_encode($strData["option1"]);
	    $tmp["option2"] = utf8_encode($strData["option2"]);
	    $tmp["option3"] = utf8_encode($strData["option3"]);
	    $tmp["option4"] = utf8_encode($strData["option4"]);
	    $tmp["answer"] = utf8_encode($strData["answer"]);
	    
	    array_push($response["allQuestion"], $tmp);
	}
		echoRespnse(200, $response);
});

$app->post('/dataAbout', function() use ($app) {
		$response = array(); 

		$db = new DbHandler(); 

		// fetching all institusi
		$result = $db->getAllAbout(); 
		

		$response["error"] = false;
		$response["allAbout"] = array();
		
		while ($strData = $result->fetch_assoc()) {
	    $tmp = array();
	    $tmp["about_id"] = utf8_encode($strData["about_id"]);
	    $tmp["name"] = utf8_encode($strData["name"]);
	    $tmp["nim"] = utf8_encode($strData["nim"]);
	    $tmp["prodi"] = utf8_encode($strData["prodi"]);
	    $tmp["path_gambar"] = utf8_encode($strData["path_gambar"]);
	 
	    array_push($response["allAbout"], $tmp);
	}
		echoRespnse(200, $response);
});


$app->post('/dataAchievementUser', function() use ($app) {
		$response = array(); 

		$db = new DbHandler(); 

		// fetching all institusi
		$result = $db->getAllAchievementUser(); 
		

		$response["error"] = false;
		$response["allAchievementUser"] = array();
		
		while ($strData = $result->fetch_assoc()) {
	    $tmp = array();
	    $tmp["achievement_user_id"] = utf8_encode($strData["achievement_user_id"]);
	    $tmp["user"] = utf8_encode($strData["user"]);
	    $tmp["checkpoint_name"] = utf8_encode($strData["checkpoint_name"]);
	    $tmp["challenge_type_name"] = utf8_encode($strData["challenge_type_name"]); 
	    $tmp["hadiah"] = utf8_encode($strData["hadiah"]);

	    array_push($response["allAchievementUser"], $tmp);
	}
		echoRespnse(200, $response);
});


$app->post('/dataAchievement', function() use ($app) {
		$response = array(); 

		$db = new DbHandler(); 

		// fetching all institusi
		$result = $db->getAllAchievement(); 
		

		$response["error"] = false;
		$response["allAchievement"] = array();
		
		while ($strData = $result->fetch_assoc()) {
	    $tmp = array();
	    $tmp["achievement_id"] = utf8_encode($strData["achievement_id"]);
	    $tmp["checkpoint_name"] = utf8_encode($strData["checkpoint_name"]);
	    $tmp["type_name"] = utf8_encode($strData["type_name"]);
	    $tmp["hadiah"] = utf8_encode($strData["hadiah"]);
	    $tmp["path_gambar"] = utf8_encode($strData["path_gambar"]);
	 
	    array_push($response["allAchievement"], $tmp);
	}
		echoRespnse(200, $response);
});


$app->post('/dataLeaderboard', function() use ($app) {
		$response = array(); 

		$db = new DbHandler(); 

		// fetching all institusi
		$result = $db->getAllLeaderboard(); 
		

		$response["error"] = false;
		$response["allLeaderboard"] = array();
		
		while ($strData = $result->fetch_assoc()) {
	    $tmp = array();
	    $tmp["leaderboard_id"] = utf8_encode($strData["leaderboard_id"]);
	    $tmp["challenge_type_name"] = utf8_encode($strData["challenge_type_name"]);
	    $tmp["checkpoint_name"] = utf8_encode($strData["checkpoint_name"]);
	    $tmp["user"] = utf8_encode($strData["user"]);
	    $tmp["score"] = utf8_encode($strData["score"]);
	    $tmp["date_time"] = utf8_encode($strData["date_time"]);
	 
	    array_push($response["allLeaderboard"], $tmp);
	}
		echoRespnse(200, $response);
});


$app->get('/dataCheckpoint', function() use ($app) {
		$response = array(); 

		$db = new DbHandler(); 

		// fetching all institusi
		$result = $db->getAllDataCheckpoint(); 
		

		$response["error"] = false;
		$response["allCheckpoint"] = array();
		
		for ($i=0; $i<count($result); $i++) {
		    $tmp = array();
			$tmp["checkpoint_id"] =	$result[$i]["checkpoint_id"];
			$tmp["location_id"] =	$result[$i]["location_id"];
			$tmp["checkpoint_name"] =	$result[$i]["checkpoint_name"];
			$tmp["latitude"] =	$result[$i]["latitude"];
			$tmp["longitude"] =	$result[$i]["longitude"];
			$tmp["path_gambarpoint"] =	$result[$i]["path_gambarpoint"];
			$tmp["description"] = $result[$i]["description"];

		    array_push($response["allCheckpoint"], $tmp);
	    }
		echoRespnse(200, $response);
});

$app->get('/dataCheckpointMarker', function() use ($app) {
		$response = array(); 

		$db = new DbHandler(); 

		// fetching all institusi
		$result = $db->getAllDataCheckpointMarker(); 
		

		$response["error"] = false;
		$response["allCheckpoint"] = array();
		
		for ($i=0; $i<count($result); $i++) {
		    $tmp = array();
			$tmp["checkpoint_id"] =	$result[$i]["checkpoint_id"];
			$tmp["location_id"] =	$result[$i]["location_id"];
			$tmp["checkpoint_name"] =	$result[$i]["checkpoint_name"];
			$tmp["latitude"] =	$result[$i]["latitude"];
			$tmp["longitude"] =	$result[$i]["longitude"];

			$tmp["path_gambarpoint"] =	$result[$i]["path_gambarpoint"];

		    array_push($response["allCheckpoint"], $tmp);
	    }
		echoRespnse(200, $response);
});

/**
 * Echoing json response to client
 * @param String $status_code Http response code
 * @param Int $response Json response
 * Daftar response
 * 200	OK
 * 201	Created
 * 304	Not Modified
 * 400	Bad Request
 * 401	Unauthorized
 * 403	Forbidden
 * 404	Not Found
 * 422	Unprocessable Entity
 * 500	Internal Server Error
 */
function echoRespnse($status_code, $response) {
    $app = \Slim\Slim::getInstance();
    // Http response code
    $app->status($status_code);

    // setting response content type to json
    $app->contentType('application/json');

	//print_r($response);
    echo json_encode($response);
}

$app->run();


?>
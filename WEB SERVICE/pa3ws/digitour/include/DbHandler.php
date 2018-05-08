<?php

class DbHandler {
 
    private $conn;
 
    function __construct() {
        require_once dirname(__FILE__) . './DbConnect.php';
        // opening db connection
        $db = new DbConnect();
        $this->conn = $db->connect();
    }
 
	/**
	* Fetching hasil liga mingguan
	*/

	public function tambahLeaderboard($challenge_type_name, $checkpoint_name, $user, $score, $date_time){
		$stmt = $this->conn->prepare("INSERT INTO t_leaderboard (challenge_type_name,checkpoint_name, user, score, date_time) VALUES (?, ?, ?, ?, ?)");
		
		$stmt->bind_param('sssis',$challenge_type_name, $checkpoint_name, $user, $score, $date_time);
		$stmt->execute();

		//date('Y-m-d')
	}

	public function tambahAchievementUser($user, $checkpoint_name, $challenge_type_name, $hadiah){
		$stmt = $this->conn->prepare("INSERT INTO t_achievement_user (user,checkpoint_name,challenge_type_name,hadiah) VALUES (?, ?, ?, ?)");
		
		$stmt->bind_param('ssss',$user, $checkpoint_name, $challenge_type_name, $hadiah);
		$stmt->execute();

		//date('Y-m-d')
	}

		public function getAllDataLocation() {
        $stmt = $this->conn->prepare("SELECT *  from t_location ORDER BY location_id ASC");
 
		$stmt->execute();		
		/* bind result variables */

		$stmt->bind_result($Id,$NamaLokasi, $NamaKota,$pathGambar);
		
		$i = 0;
		while ($stmt->fetch()) { 
			$strData[$i]["location_id"] = $Id;
			$strData[$i]["location_name"] = $NamaLokasi;
			$strData[$i]["city_name"] = $NamaKota;
			$strData[$i]["path_gambar"] = $pathGambar;
			
			$i++;
		}
		return $strData;		
    }

    

    public function getAllQuestion(){
		$stmt = $this->conn->prepare("SELECT *  from t_question ");
		
		//$stmt->bind_param("si",$checkpoint_id, $challange_type_id);
		$stmt->execute();
		$tasks = $stmt->get_result();
        $stmt->close();
		
        return $tasks;		

	}

public function getAllAchievement(){
		$stmt = $this->conn->prepare("SELECT t_achievement.achievement_id, t_checkpoint.checkpoint_name, t_achievement.hadiah, t_challenge_type.type_name, t_achievement.path_gambar
FROM t_achievement INNER JOIN t_checkpoint ON t_achievement.checkpoint_id=t_checkpoint.checkpoint_id INNER JOIN t_challenge_type ON t_achievement.challenge_type_id=t_challenge_type.challenge_type_id");
		
		//$stmt->bind_param("si",$checkpoint_id, $challange_type_id);
		$stmt->execute();
		$tasks = $stmt->get_result();
        $stmt->close();
		
        return $tasks;		

	}

	public function getAllLeaderboard(){
		$stmt = $this->conn->prepare("SELECT *  FROM t_leaderboard ORDER BY score DESC");
		
		$stmt->execute();
		$tasks = $stmt->get_result();
        $stmt->close();
		
        return $tasks;		

	}

	public function getAllAbout(){
		$stmt = $this->conn->prepare("SELECT *  from t_about ");
		
		//$stmt->bind_param("si",$checkpoint_id, $challange_type_id);
		$stmt->execute();
		$tasks = $stmt->get_result();
        $stmt->close();
		
        return $tasks;		

	}

public function getAllAchievementUser(){
		$stmt = $this->conn->prepare("SELECT *  from t_achievement_user");
		
		//$stmt->bind_param("si",$checkpoint_id, $challange_type_id);
		$stmt->execute();
		$tasks = $stmt->get_result();
        $stmt->close();
		
        return $tasks;		

	}

    public function getAllChallengeType(){
		$stmt = $this->conn->prepare("SELECT *  from t_challenge_type");
		
	
		$stmt->execute();
		$stmt->bind_result($Id, $typenama,$deskripsi);
		
		$i = 0;
		while ($stmt->fetch()) { 
			$strData[$i]["challenge_type_id"] = $Id;
			$strData[$i]["type_name"] = $typenama;
			$strData[$i]["description"] = $deskripsi;
		
			
			$i++;
		}
		return $strData;		

	}

    public function getAllDataCheckpoint() {
        $stmt = $this->conn->prepare("SELECT * from t_checkpoint ");
 
 		//$stmt->bind_param("i", $location_id);
		$stmt->execute();		
		/* bind result variables */

		$stmt->bind_result($Checkpointid,$Locationid, $Checkpointname,$Latitude, $Longitude, $Pathgambarpoint, $Description);
		
		$i = 0;

		while ($stmt->fetch()) { 
			$strData[$i]["checkpoint_id"] = $Checkpointid;
			$strData[$i]["location_id"] = $Locationid;
			$strData[$i]["checkpoint_name"] = $Checkpointname;
			$strData[$i]["latitude"] = $Latitude;
			$strData[$i]["longitude"] = $Longitude;
			$strData[$i]["path_gambarpoint"] = $Pathgambarpoint;
			$strData[$i]["description"] = $Description;			

			$i++;
		}
		return $strData;		
    }

    public function getAllDataCheckpointMarker() {
        $stmt = $this->conn->prepare("SELECT * from t_checkpoint");
 

		$stmt->execute();		
		/* bind result variables */

		$stmt->bind_result($Checkpointid,$Locationid, $Checkpointname,$Latitude, $Longitude, $Pathgambarpoint);
		
		$i = 0;

		while ($stmt->fetch()) { 
			$strData[$i]["checkpoint_id"] = $Checkpointid;
			$strData[$i]["location_id"] = $Locationid;
			$strData[$i]["checkpoint_name"] = $Checkpointname;
			$strData[$i]["latitude"] = $Latitude;
			$strData[$i]["longitude"] = $Longitude;
			$strData[$i]["path_gambarpoint"] = $Pathgambarpoint;

			$i++;
		}
		return $strData;		
    }
 
}
 
?>
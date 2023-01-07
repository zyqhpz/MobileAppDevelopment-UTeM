<?php

$hostname = "localhost";
$database = "dbstudent";
$username = "root";
$password = "";

$db = new PDO("mysql:host=$hostname;dbname=$database", $username, $password);

if (isset($_REQUEST['selectFn']) && $_REQUEST['selectFn'] == "fnSaveData") {
    $varName = $_REQUEST['studName'];
    $gender = $_REQUEST['studGender'];
    $dob = $_REQUEST['studDob'];
    $studNo = $_REQUEST['studNo'];
    $studState = $_REQUEST['studState'];
    $studEmail = $_REQUEST['studEmail'];

    try {
        $stmt = $db->prepare("INSERT INTO students (studName, studGender, studDob, studNo, studState, studEmail)
                                VALUES (:studName, :studGender, :studDob, :studNo, :studState, :studEmail)");

        $stmt->execute(array(':studName' => $varName, ':studGender' => $gender, ':studDob' => $dob, ':studNo' => $studNo, ':studState' => $studState, ':studEmail' => $studEmail));

        $response['respond'] = "Information saved successfully";
        echo json_encode($response);
    } catch (PDOException $e) {
        $response['respond'] = "Error: " . $e->getMessage();
        echo json_encode($response);
    }
} else if (isset($_REQUEST['selectFn']) && $_REQUEST['selectFn'] == "fnSearchStud") {
    try {

        $studNo = $_REQUEST['studNo'];

        $stmt = $db->prepare("SELECT * FROM students WHERE studNo LIKE :studNo");
        $stmt->execute(array(':studNo' => $studNo));
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        
        $response['respond'] = $result;
        echo json_encode($response);
    } catch (PDOException $e) {
        $response['respond'] = "Error: " . $e->getMessage();
        echo json_encode($response);
    }
}
else {
    $response['respond'] = "Error: No function selected";
    echo json_encode($response);
}
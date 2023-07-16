<?php
$error_message = "";

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  $email = $_POST['email'];
  $password = $_POST['password'];
  if (!empty($email) && !empty($password)) {
    $host = 'localhost';
    $user = 'root';
    $db_password = '';
    $database = 'interiorshop';
    $mysqli = new mysqli($host, $user, $db_password, $database);
    if ($mysqli->connect_error) {
      die('Connect Error (' . $mysqli->connect_errno . ') '
        . $mysqli->connect_error);
    }
    $query = "SELECT * FROM users WHERE email = '$email'";
    $result = $mysqli->query($query);

    if ($result->num_rows == 1) {
      $row = $result->fetch_assoc();
      if (password_verify($password, $row['password'])) {
        session_start();
        $_SESSION['fullname'] = $row['firstname'] . ' ' . $row['lastname'];
        $_SESSION['role'] = $row['role'];
        header("Location: main-view.php");
        exit;
      } else {
        $error_message = "Wrong email or password.";
      }
    } else {
      $error_message = "Wrong email or password.";
    }
    $mysqli->close();
  } else {
    $error_message = "Please enter email and password.";
  }
}

if (isset($_POST['email']) && isset($_POST['password'])) {
  $email = $_POST['email'];
  $password = $_POST['password'];
} else {
  $email = "";
  $password = "";
}

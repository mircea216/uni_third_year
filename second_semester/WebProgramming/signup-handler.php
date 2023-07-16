<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $firstname = $_POST["firstname"];
    $lastname = $_POST["lastname"];
    $email = $_POST["email"];
    $password = $_POST["password-registration"];
    $hashed_password = password_hash($password, PASSWORD_DEFAULT);
    $role = $_POST["role-dropdown"];
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "interiorshop";

    $conn = new mysqli($servername, $username, $password, $dbname);
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    $sql = "INSERT INTO users (firstname, lastname, email, password, role)
  VALUES ('$firstname', '$lastname', '$email', '$hashed_password', '$role')";

    if ($conn->query($sql) === TRUE) {
        header("Location: login-page.php");
        exit;
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }

    $conn->close();
}

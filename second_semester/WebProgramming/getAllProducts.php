<?php
$host = 'localhost';
$user = 'root';
$password = '';
$database = 'interiorshop';
$mysqli = new mysqli($host, $user, $password, $database);

if ($mysqli->connect_error) {
    die('Connect Error (' . $mysqli->connect_errno . ') '
        . $mysqli->connect_error);
}

$query = 'SELECT * FROM products';
$result = $mysqli->query($query);

if (!$result) {
    die('Query Error: ' . $mysqli->error);
}

while ($row = $result->fetch_assoc()) {
    $id = $row['id'];
    include 'element-card.html';
}

$result->free();
$mysqli->close();
?>
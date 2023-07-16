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

$id = $_GET['id'];
$query = 'SELECT * FROM products WHERE id = ' . $id;
$result = $mysqli->query($query);

if (!$result) {
    die('Query Error: ' . $mysqli->error);
}

$row = $result->fetch_assoc();

$result->free();
$mysqli->close();

$productName = $row['name'];
$productCategory = $row['category'];
$productPrice = $row['price'];
$productImage = $row['imagePath'];
$productMaterial = $row['material'];
$productLength= $row['length'];
$productWidth = $row['width'];
$productHeight= $row['height'];
$productWeight = $row['weight'];
$productColor = $row['color'];

?>
<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "interiorshop";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if (isset($_GET['category'])) {
    $category = $_GET['category'];

    $sql = "SELECT * FROM products WHERE category = '$category'";
    $result = $conn->query($sql);
}

while ($row = $result->fetch_assoc()) {
    $id = $row['id'];
    include 'element-card.html';
}

$conn->close();

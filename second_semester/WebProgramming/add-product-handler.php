<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "interiorshop";

$conn = new mysqli($servername, $username, $password, $dbname);

if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $name = $_POST['name'];
    $price = $_POST['price'];
    $category = $_POST['category-add-dropdown'];
    $material = $_POST['material-dropdown'];
    $color = $_POST['color-dropdown'];
    $height = $_POST['height'];
    $width = $_POST['width'];
    $length = $_POST['length'];
    $weight = $_POST['weight'];
    echo ($category);

    $target_dir = "assets/";
    $target_file = $target_dir . basename($_FILES["imagepath"]["name"]);
    move_uploaded_file($_FILES["imagepath"]["tmp_name"], $target_file);
    $image_path = "assets/" . $_FILES["imagepath"]["name"];

    $sql = "INSERT INTO products (name, price, category, material, color, height, width, length, weight, imagePath)
VALUES ('$name', '$price', '$category', '$material', '$color', '$height', '$width', '$length', '$weight', '$image_path')";

    if (mysqli_query($conn, $sql)) {
        header("Location: main-view.php");
        exit;
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($conn);
    }

    mysqli_close($conn);
}

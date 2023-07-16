<html>

<head>
    <link rel="stylesheet" type="text/css" href="style-page.css">
</head>
<?php
session_start();
$fullname = $_SESSION['fullname'];
$role = $_SESSION['role'];
?>

<body>
    <header id="header-element">
        <i id="user-profile" class="link-header"><?php echo $fullname; ?></i>
        <a href="products-by-category.php?category=furniture" id=" furniture-link" class="link-header">Furniture</a>
        <a href="products-by-category.php?category=accessories" id="accessories-link" class="link-header">Accessories</a>
        <a href="products-by-category.php?category=inspiration" id="inspiration-link" class="link-header">Inspiration</a>
        <button id="logout-button" class="link-header">Logout</button>
    </header>

    <button id="go-back" onclick="goBack()"></button>

    <div id="card-container">
        <?php include 'getProductsByCategory.php'; ?>
    </div>
    <footer id="footer-element">
        <div id="footer-container">
            <p id="logo">&copy; Your minimal design</p>
        </div>
    </footer>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</body>

</html>
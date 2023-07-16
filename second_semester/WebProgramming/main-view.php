<html>

<head>
  <link rel="stylesheet" type="text/css" href="style-page.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    $(document).ready(function() {
      $("#search-bar").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#card-container #product-card").filter(function() {
          $(this).toggle($(this).find("#product-name").text().toLowerCase().indexOf(value) > -1)
        });
      });
    });
    $(document).ready(function() {
      $("#sort-button").on("click", function() {
        var cards = $("#card-container #product-card");
        cards.sort(function(a, b) {
          var nameA = $(a).find("#product-name").text().toUpperCase();
          var nameB = $(b).find("#product-name").text().toUpperCase();
          if (nameA < nameB) {
            return -1;
          }
          if (nameA > nameB) {
            return 1;
          }
          return 0;
        });
        $("#card-container").append(cards);
      });
    });
    $(document).ready(function() {
      $("#category-dropdown").on("change", function() {
        var selectedCategory = $(this).val().toLowerCase();
        $("#card-container #product-card").filter(function() {
          var productCategory = $(this).find("#product-category").text().toLowerCase();
          if (selectedCategory === productCategory) {
            $(this).show();
          } else {
            $(this).hide();
          }
        });
      });
    });
  </script>
</head>

<body>
  <?php
  session_start();
  $fullname = $_SESSION['fullname'];
  $role = $_SESSION['role'];
  ?>
  <header id="header-element">
    <i id="user-profile" class="link-header"><?php echo $fullname; ?></i>
    <a href="products-by-category.php?category=furniture" id=" furniture-link" class="link-header">Furniture</a>
    <a href="products-by-category.php?category=accessories" id="accessories-link" class="link-header">Accessories</a>
    <a href="products-by-category.php?category=inspiration" id="inspiration-link" class="link-header">Inspiration</a>
    <button id="logout-button" class="link-header">Logout</button>
  </header>

  <div id="search-sort-holder">
    <input type="text" id="search-bar" placeholder="Search products">
    <button id="sort-button">Sort by name</button>
    <select id="category-dropdown">
      <option id="dropdown-placeholder" value="" disabled selected>Search by category</option>
      <option value="accessories">Accessories</option>
      <option value="inspiration">Inspiration</option>
      <option value="furniture">Furniture</option>
    </select>
    <?php
    if ($role === "admin") {
      echo '<button id="add-product">Add product</button>';
    }
    ?>
  </div>

  <div id="card-container">
    <?php include 'getAllProducts.php'; ?>
  </div>
  <footer id="footer-element">
    <div id="footer-container">
      <p id="logo">&copy; Your minimal design</p>
    </div>
  </footer>
</body>

<script>
  const logoutButton = document.getElementById('logout-button');
  logoutButton.addEventListener('click', function(event) {
    event.preventDefault();
    window.location.href = 'login-page.php';
  });
  const addProduct = document.getElementById('add-product');
  addProduct.addEventListener('click', function(event) {
    event.preventDefault();
    window.location.href = 'add-product.php';
  });
</script>

</html>
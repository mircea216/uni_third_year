<!DOCTYPE html>
<html>

<head>
  <link rel="stylesheet" type="text/css" href="style-page.css" />
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    $(document).ready(function() {
      $("#actual-image").click(function() {
        var imageSource = $(this).attr("src");
        var imageHTML = "<div class='popup-image'><img src='" + imageSource + "' /></div>" +
          "<p class='popup-text'>Image of the product</p>";
        $("body").append("<div class='popup-container'>" + imageHTML + "</div>");
        $(".popup-container").fadeIn();
      });

      $(document).on("click", ".popup-container", function() {
        $(this).fadeOut(function() {
          $(this).remove();
        });
      });
    });
  </script>
</head>

<body>
  <?php include 'getProductById.php'; ?>

  <div id="list-table-holder">
    <button id="go-back" onclick="goBack()"></button>
    <!-- Nested tables with colspan and rowspan-->
    <table>
      <tr>
        <th>Product Details:</th>
        <td>
          <table>
            <tr>
              <td id=" produs-image" rowspan="3">Image</td>
              <td>
                <img id="actual-image" src="<?php echo $productImage; ?>" alt="Product image" />
              </td>
            </tr>
            <tr>
              <td>Product Name</td>
              <td><?php echo $productName; ?></td>
            </tr>
            <tr>
              <td>Product Category</td>
              <td><?php echo $productCategory; ?></td>
            </tr>
            <tr>
              <td colspan="2" id="product-price">Price</td>
              <td><?php echo $productPrice, " $"; ?></td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <!-- Nested lists -->

    <ul>
      <li>Features</li>
      <li>
        <ul>
          <li>Dimensions:</li>
          <li>
            <ul>
              <li><?php echo "Length: ", $productLength, " cm"; ?></li>
              <li><?php echo "Width: ", $productWidth, " cm"; ?></li>
              <li id="view-more-btn"><button id="view-more-handler" onclick="showMore()">View more</button></li>
              <li id="show-less-btn" style="display:none"><button id="show-less-handler" onclick="showLess()">Show less</button></li>
              <li id="height" style="display:none"><?php echo "Height: ", $productHeight, " cm"; ?></li>
              <li id="weight" style="display:none"><?php echo "Weight: ", $productWeight, " kg"; ?></li>
            </ul>
          </li>
          <li><?php echo "Color: ", $productColor ?></li>
          <li><?php echo "Material: ", $productMaterial; ?></li>
        </ul>
      </li>
    </ul>
  </div>
  <footer id="footer-element">
    <div id="footer-container">
      <p id="logo">&copy; Your minimal design</p>
    </div>
  </footer>
</body>

<script>
  function showMore() {
    document.getElementById("height").style.display = "block";
    document.getElementById("weight").style.display = "block";
    document.getElementById("view-more-btn").style.display = "none";
    document.getElementById("show-less-btn").style.display = "block";
  }

  function showLess() {
    document.getElementById("height").style.display = "none";
    document.getElementById("weight").style.display = "none";
    document.getElementById("view-more-btn").style.display = "block";
    document.getElementById("show-less-btn").style.display = "none";
  }

  function goBack() {
    window.history.back();
  }
</script>


</html>
<!DOCTYPE html>
<html>

<head>
  <link rel="stylesheet" type="text/css" href="style-page.css" />
  <?php
  $error_message = "";

  if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $email = $_POST['email'];
    $password = $_POST['password'];
    if (!empty($email) && !empty($password)) {
      include 'login-handler.php';
    } else {
      $error_message = "Please enter email and password.";
    }
  }
  ?>
  <script>
    function hideErrorMessage() {
      document.getElementById('error_message').style.display = 'none';
    }
  </script>
</head>

<body>
  <form id="login-form" method="post">
    <div id="login-holder">
      <div id="wrapper">
        <div id="login-container">
          <label id="email-label" for="email">Email</label>
          <input type="text" id="email" name="email" placeholder="Enter the email " required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" value="<?php echo isset($_POST['email']) ? $_POST['email'] : ''; ?>" oninput="hideErrorMessage()" />
          <div id="email-validation"></div>
          <label id="password-label" for="password">Password</label>
          <input type="password" id="password" name="password" placeholder="Enter the password " required minlength="8" value="<?php echo isset($_POST['password']) ? $_POST['password'] : ''; ?>" oninput="hideErrorMessage()" />
          <div id="error_message"><?php echo $error_message; ?></div>
          <div id="password-validation"></div>
          <button id="login-button">Log In</button>
          <p id="signup-section">
            Not a member? <a href="signup-page.php">Sign Up</a>
          </p>
        </div>
      </div>
    </div>
  </form>
  <footer id="footer-element">
    <div id="footer-container">
      <p id="logo">&copy; Your minimal design</p>
    </div>
  </footer>
  <script>
    const emailInput = document.getElementById("email");
    const emailValidation = document.getElementById("email-validation");
    const passwordInput = document.getElementById("password");
    const passwordValidation = document.getElementById("password-validation");

    emailInput.addEventListener("input", () => {
      if (emailInput.validity.valid) {
        emailValidation.textContent = "Email is valid";
        emailValidation.classList.remove("invalid-field");
        emailValidation.classList.add("valid-field");
        setTimeout(() => {
          emailValidation.classList.remove("valid-field");
          emailValidation.textContent = "";
        }, 3000);
      } else {
        emailValidation.textContent = "Email is invalid";
        emailValidation.classList.remove("valid-field");
        emailValidation.classList.add("invalid-field");
      }
    });

    passwordInput.addEventListener("input", () => {
      if (passwordInput.validity.valid) {
        passwordValidation.textContent = "Password is valid";
        passwordValidation.classList.remove("invalid-field");
        passwordValidation.classList.add("valid-field");
        setTimeout(() => {
          passwordValidation.classList.remove("valid-field");
          passwordValidation.textContent = "";
        }, 3000);
      } else {
        passwordValidation.textContent =
          "Password should be at least 8 characters long";
        passwordValidation.classList.remove("valid-field");
        passwordValidation.classList.add("invalid-field");
      }
    });
  </script>
</body>

</html>
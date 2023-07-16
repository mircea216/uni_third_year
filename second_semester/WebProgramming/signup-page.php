<!DOCTYPE html>
<html>

<head>
  <link rel="stylesheet" type="text/css" href="style-page.css" />
</head>

<body>
  <form method="post" action="signup-handler.php">
    <div id="signup-holder-register">
      <div id="wrapper-register">
        <div id="signup-container">
          <label id="firstname-label" for="firstname">First Name</label>
          <input type="text" id="firstname" name="firstname" placeholder="Enter the first name " />
          <div id="firstname-validation"></div>
          <label id="lastname-label" for="password">Last Name</label>
          <input type="text" id="lastname" name="lastname" placeholder="Enter your last name " />
          <div id="lastname-validation"></div>
          <label id="email-label" for="email">Email</label>
          <input type="text" id="email-registration" name="email" placeholder="Enter the email " required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" />
          <div id="email-validation-registration"></div>
          <label id="password-label" for="password-registration">Password</label>
          <input type="password" id="password-registration" name="password-registration" placeholder="Enter the password " required minlength="8" />
          <div id="password-validation-registration"></div>
          <label id="role-label" for="role">Role</label>
          <select id="role-dropdown" name="role-dropdown" required>
            <option id="dropdown-placeholder" value="" disabled selected>Search role</option>
            <option value="admin">Admin</option>
            <option value="user">User</option>
          </select>
          <button id="signup-button">Sign Up</button>
          <p id="register-section">
            Already a member? <a href="login-page.php">Log in</a>
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
</body>
<script>
  const firstNameInput = document.getElementById("firstname");
  const firstNameValidation = document.getElementById("firstname-validation");
  const lastNameInput = document.getElementById("lastname");
  const lastNameValidation = document.getElementById("lastname-validation");

  firstNameInput.addEventListener("input", () => {
    if (firstNameInput.value.trim() !== "") {
      firstNameValidation.textContent = "First name is valid";
      firstNameValidation.classList.remove("invalid-field");
      firstNameValidation.classList.add("valid-field");
      setTimeout(() => {
        firstNameValidation.classList.remove("valid-field");
        firstNameValidation.textContent = "";
      }, 3000);
    } else {
      firstNameValidation.textContent = "First name cannot be empty";
      firstNameValidation.classList.remove("valid-field");
      firstNameValidation.classList.add("invalid-field");
    }
  });

  lastNameInput.addEventListener("input", () => {
    if (lastNameInput.value.trim() !== "") {
      lastNameValidation.textContent = "Last name is valid";
      lastNameValidation.classList.remove("invalid-field");
      lastNameValidation.classList.add("valid-field");
      setTimeout(() => {
        lastNameValidation.classList.remove("valid-field");
        lastNameValidation.textContent = "";
      }, 3000);
    } else {
      lastNameValidation.textContent = "Last name cannot be empty";
      lastNameValidation.classList.remove("valid-field");
      lastNameValidation.classList.add("invalid-field");
    }
  });
  const emailInput = document.getElementById("email-registration");
  const emailValidation = document.getElementById("email-validation-registration");
  const passwordInput = document.getElementById("password-registration");
  const passwordValidation = document.getElementById("password-validation-registration");

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
      passwordValidation.textContent = "Password should be at least 8 characters long";
      passwordValidation.classList.remove("valid-field");
      passwordValidation.classList.add("invalid-field");
    }
  });
</script>

</html>
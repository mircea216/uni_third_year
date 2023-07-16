<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="style-page.css">
</head>

<body>
    <form id="form" action="add-product-handler.php" method="post" enctype="multipart/form-data">
        <div id="add-product-holder">
            <button id="go-back-add" onclick="goBack()"></button>
            <div id="add-product-wrapper">
                <div id="add-product-container">
                    <label id="name-label" for="name">Name</label>
                    <input type="text" id="name-add" name="name" placeholder="Enter the name " />
                    <label for="price-label">Price</label>
                    <input type="number" id="price-add" name="price" required placeholder="Enter the price ">
                    <label id="category-label" for="category">Category</label>
                    <select id="category-add-dropdown" name="category-add-dropdown">
                        <option id="dropdown-add-placeholder" value="" disabled selected>Select category</option>
                        <option value="Accessories">Accessories</option>
                        <option value="Inspiration">Inspiration</option>
                        <option value="Furniture">Furniture</option>
                    </select>
                    <label id="material-label" for="role">Material</label>
                    <select id="material-dropdown" name="material-dropdown" required>
                        <option id="material-placeholder" value="" disabled selected>Select material</option>
                        <option value="Wooden">Wooden</option>
                        <option value="Ceramics">Ceramics</option>
                        <option value="Wax">Wax</option>
                        <option value="Natural">Natural</option>
                    </select>
                    <label id="color-label" for="role">Color</label>
                    <select id="color-dropdown" name="color-dropdown" required>
                        <option id="color-placeholder" value="" disabled selected>Select color</option>
                        <option value="White">White</option>
                        <option value="Cream">Cream</option>
                        <option value="Blackish">Blackish</option>
                        <option value="Green">Green</option>
                        <option value="Cream-wheatty">Cream-wheatty</option>
                        <option value="Light-Brown">Light-Brown</option>
                        <option value="Neutral">Neutral</option>
                    </select>
                    <label for="height-label">Height</label>
                    <input type="number" id="height-add" name="height" required placeholder="Enter the height ">
                    <label for="width-label">Width</label>
                    <input type="number" id="width-add" name="width" required placeholder="Enter the width ">
                    <label for="length-label">Length</label>
                    <input type="number" id="length-add" name="length" required placeholder="Enter the length ">
                    <label for="weight-label">Weight</label>
                    <input type="number" id="weight-add" name="weight" required placeholder="Enter the weight ">
                    <label for="imagepath">Image</label>
                    <div id="img-holder"><input type="file" id="image-path" name="imagepath" accept="image/*" required></div>
                    <button id="add-product-button">Add product</button>
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
        const materialDropdown = document.getElementById('material-dropdown');
        const colorDropdown = document.getElementById('color-dropdown');
        const materialOptions = {
            Wooden: ['Light-Brown', 'Black'],
            Ceramics: ['White', 'Cream', 'Cream-wheatty', 'Neutral'],
            Wax: ['Cream', 'Green'],
            Natural: ['Green', 'Light-Brown']
        };

        function updateColorDropdown() {
            const selectedMaterial = materialDropdown.value;

            colorDropdown.innerHTML = '<option id="color-placeholder" value="" disabled selected>Select color</option>';

            materialOptions[selectedMaterial].forEach(color => {
                const option = document.createElement('option');
                option.value = color;
                option.text = color;
                colorDropdown.add(option);
            });
        }

        materialDropdown.addEventListener('change', updateColorDropdown);

        function goBack() {
            window.history.back();
        }
    </script>
</body>

</html>
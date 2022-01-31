    <script>
        var menuIcon = document.getElementById("menu");
        var sidbar = document.getElementById("links");

        menuIcon.onclick = function () {
            if (sidbar.style.right === "-118%") {
                sidbar.style.right = "-6%";
                sidbar.style.display = "block";
            }

            else {
                sidbar.style.right = "-118%";
                sidbar.style.display = "flex";

            }
        };

    </script>
</body>
</html>

// ==========================================
// LOGIN
// ==========================================

const loginForm = document.getElementById("loginForm");


if (loginForm) {

    loginForm.addEventListener("submit", async function (event) {

        event.preventDefault();


        const email =
            document.getElementById("email").value.trim();

        const password =
            document.getElementById("password").value;


        const message =
            document.getElementById("loginMessage");


        message.textContent = "Logging in...";


        try {

            const response = await fetch("/login", {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify({
                    email: email,
                    password: password
                })

            });


            const text = await response.text();


            let data;

            try {

                data = JSON.parse(text);

            } catch {

                data = text;

            }


            if (!response.ok) {

                message.textContent =
                    typeof data === "string"
                        ? data
                        : "Login failed.";

                return;
            }


            // Save login information

            localStorage.setItem(
                "token",
                data.token
            );

            localStorage.setItem(
                "userId",
                data.id
            );

            localStorage.setItem(
                "fullName",
                data.fullName
            );

            localStorage.setItem(
                "email",
                data.email
            );

            localStorage.setItem(
                "role",
                data.role
            );


            message.textContent =
                "Login successful!";


            // Redirect according to role

            setTimeout(function () {

                if (data.role === "ADMIN") {

                    window.location.href =
                        "admin-dashboard.html";

                }

                else if (data.role === "HELPER") {

                    window.location.href =
                        "helper-dashboard.html";

                }

                else if (data.role === "BENEFICIARY") {

                    window.location.href =
                        "beneficiary-dashboard.html";

                }

                else {

                    window.location.href =
                        "index.html";

                }

            }, 500);


        } catch (error) {

            console.error("Login Error:", error);

            message.textContent =
                "Unable to connect to server.";

        }

    });

}



// ==========================================
// REGISTER
// ==========================================

const registerForm =
    document.getElementById("registerForm");


if (registerForm) {

    registerForm.addEventListener(
        "submit",
        async function (event) {

            event.preventDefault();


            const fullName =
                document.getElementById("fullName")
                    .value.trim();

            const email =
                document.getElementById("email")
                    .value.trim();

            const phone =
                document.getElementById("phone")
                    .value.trim();

            const city =
                document.getElementById("city")
                    .value.trim();

            const password =
                document.getElementById("password")
                    .value;


            const message =
                document.getElementById(
                    "registerMessage"
                );


            // Gmail validation

            if (!email.endsWith("@gmail.com")) {

                message.textContent =
                    "Please use a Gmail address.";

                return;
            }


            message.textContent =
                "Creating account...";


            try {

                const response = await fetch(
                    "/register",
                    {

                        method: "POST",

                        headers: {
                            "Content-Type":
                                "application/json"
                        },

                        body: JSON.stringify({

                            fullName: fullName,

                            email: email,

                            phone: phone,

                            city: city,

                            password: password

                        })

                    }
                );


                const text =
                    await response.text();


                let data;

                try {

                    data = JSON.parse(text);

                } catch {

                    data = text;

                }


                if (!response.ok) {

                    message.textContent =
                        typeof data === "string"
                            ? data
                            : "Registration failed.";

                    return;
                }


                message.textContent =
                    "Account created successfully!";


                // Redirect to login

                setTimeout(function () {

                    window.location.href =
                        "login.html";

                }, 1000);


            } catch (error) {

                console.error(
                    "Registration Error:",
                    error
                );

                message.textContent =
                    "Unable to connect to server.";

            }

        }
    );

}
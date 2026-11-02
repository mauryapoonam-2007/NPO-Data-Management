const token = localStorage.getItem("token");
const role = localStorage.getItem("role");


if (!token || role !== "ADMIN") {

    window.location.href = "login.html";

}


const staffForm =
    document.getElementById("staffForm");

const staffMessage =
    document.getElementById("staffMessage");


staffForm.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();


        const staffData = {

            fullName:
                document
                    .getElementById("fullName")
                    .value
                    .trim(),

            email:
                document
                    .getElementById("email")
                    .value
                    .trim(),

            phone:
                document
                    .getElementById("phone")
                    .value
                    .trim(),

            designation:
                document
                    .getElementById("designation")
                    .value
                    .trim(),

            department:
                document
                    .getElementById("department")
                    .value,

            joiningDate:
                document
                    .getElementById("joiningDate")
                    .value,

            city:
                document
                    .getElementById("city")
                    .value
                    .trim()

        };


        staffMessage.textContent =
            "Adding staff...";


        try {

            const response = await fetch(
                "/admin/staff",
                {

                    method: "POST",

                    headers: {

                        "Content-Type":
                            "application/json",

                        "Authorization":
                            "Bearer " + token

                    },

                    body:
                        JSON.stringify(staffData)

                }
            );


            if (!response.ok) {

                const errorText =
                    await response.text();

                console.error(errorText);

                staffMessage.textContent =
                    "Unable to add staff.";

                return;

            }


            staffMessage.textContent =
                "Staff added successfully!";


            staffForm.reset();


        } catch (error) {

            console.error(
                "Staff Error:",
                error
            );

            staffMessage.textContent =
                "Unable to connect to server.";

        }

    }
);


document
    .getElementById("logoutBtn")
    .addEventListener(
        "click",
        function () {

            localStorage.clear();

            window.location.href =
                "login.html";

        }
    );
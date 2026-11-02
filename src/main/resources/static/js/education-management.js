const token = localStorage.getItem("token");
const role = localStorage.getItem("role");


if (!token || role !== "ADMIN") {

    window.location.href = "login.html";

}


const educationForm =
    document.getElementById("educationForm");

const educationMessage =
    document.getElementById("educationMessage");


educationForm.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();


        const educationData = {

            childId:
                document
                    .getElementById("childId")
                    .value
                    .trim(),

            childName:
                document
                    .getElementById("childName")
                    .value
                    .trim(),

            schoolName:
                document
                    .getElementById("schoolName")
                    .value
                    .trim(),

            className:
                document
                    .getElementById("className")
                    .value
                    .trim(),

            academicYear:
                document
                    .getElementById("academicYear")
                    .value
                    .trim(),

            admissionDate:
                document
                    .getElementById("admissionDate")
                    .value || null,

            attendancePercentage:
                document
                    .getElementById("attendancePercentage")
                    .value
                    ? Number(
                        document
                            .getElementById(
                                "attendancePercentage"
                            )
                            .value
                    )
                    : null,

            performance:
                document
                    .getElementById("performance")
                    .value,

            remarks:
                document
                    .getElementById("remarks")
                    .value
                    .trim()

        };


        educationMessage.textContent =
            "Adding education record...";


        try {

            const response = await fetch(
                "/admin/education",
                {

                    method: "POST",

                    headers: {

                        "Content-Type":
                            "application/json",

                        "Authorization":
                            "Bearer " + token

                    },

                    body:
                        JSON.stringify(
                            educationData
                        )

                }
            );


            if (!response.ok) {

                const error =
                    await response.text();

                console.error(error);

                educationMessage.textContent =
                    "Unable to add education record.";

                return;

            }


            educationMessage.textContent =
                "Education record added successfully!";


            educationForm.reset();


        } catch (error) {

            console.error(
                "Education Error:",
                error
            );

            educationMessage.textContent =
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
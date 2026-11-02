const token = localStorage.getItem("token");
const role = localStorage.getItem("role");


// LOGIN CHECK

if (!token || role !== "BENEFICIARY") {

    window.location.href = "login.html";

}


// DISPLAY NAME

const beneficiaryName =
    document.getElementById("beneficiaryName");

if (beneficiaryName) {

    beneficiaryName.textContent =
        localStorage.getItem("fullName") || "Beneficiary";

}


// CREATE HELP REQUEST

const helpRequestForm =
    document.getElementById("helpRequestForm");


if (helpRequestForm) {

    helpRequestForm.addEventListener(
        "submit",
        async function(event) {

            event.preventDefault();


            const message =
                document.getElementById(
                    "helpRequestMessage"
                );


            const requestData = {

                beneficiaryId:
                    localStorage.getItem("userId"),

                title:
                    document.getElementById("title").value.trim(),

                description:
                    document.getElementById("description").value.trim(),

                category:
                    document.getElementById("category").value,

                city:
                    document.getElementById("city").value.trim(),

                priority:
                    document.getElementById("priority").value

            };


            message.textContent =
                "Submitting request...";


            try {

                const response = await fetch(
                    "/help-requests",
                    {
                        method: "POST",

                        headers: {
                            "Content-Type": "application/json",
                            "Authorization":
                                "Bearer " + token
                        },

                        body:
                            JSON.stringify(requestData)
                    }
                );


                const text =
                    await response.text();


                if (!response.ok) {

                    console.error(text);

                    message.textContent =
                        "Unable to submit help request.";

                    return;

                }


                message.textContent =
                    "Help request submitted successfully!";

                helpRequestForm.reset();

            }

            catch (error) {

                console.error(
                    "Help Request Error:",
                    error
                );

                message.textContent =
                    "Unable to connect to server.";

            }

        }
    );

}


// LOGOUT

function logout() {

    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    localStorage.removeItem("fullName");
    localStorage.removeItem("email");
    localStorage.removeItem("role");

}
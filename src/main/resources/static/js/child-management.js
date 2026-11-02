const token = localStorage.getItem("token");
const role = localStorage.getItem("role");

if (!token || (role !== "ADMIN" && role !== "STAFF")) {
    window.location.href = "login.html";
}


const childForm = document.getElementById("childForm");
const childMessage = document.getElementById("childMessage");
const childrenContainer = document.getElementById("childrenContainer");


childForm.addEventListener("submit", async function (event) {

    event.preventDefault();

    const childData = {

        childName: document.getElementById("childName").value.trim(),

        dateOfBirth:
            document.getElementById("dateOfBirth").value,

        gender:
            document.getElementById("gender").value,

        admissionDate:
            document.getElementById("admissionDate").value,

        educationStatus:
            document.getElementById("educationStatus").value,

        healthStatus:
            document.getElementById("healthStatus").value,

        city:
            document.getElementById("city").value.trim()
    };


    try {

        const response = await fetch("/admin/children", {

            method: "POST",

            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
            },

            body: JSON.stringify(childData)
        });


        const data = await response.json();


        if (!response.ok) {

            console.error(data);

            childMessage.textContent =
                "Unable to add child.";

            return;
        }


        childMessage.textContent =
            "Child added successfully!";


        childForm.reset();

        loadChildren();

    } catch (error) {

        console.error("Child Error:", error);

        childMessage.textContent =
            "Unable to connect to server.";
    }

});


async function loadChildren() {

    try {

        const response = await fetch("/admin/children", {

            method: "GET",

            headers: {
                "Authorization": "Bearer " + token
            }

        });


        if (!response.ok) {

            throw new Error("Unable to load children");

        }


        const children = await response.json();

        childrenContainer.innerHTML = "";


        if (children.length === 0) {

            childrenContainer.innerHTML =
                "<p>No children records found.</p>";

            return;
        }


        children.forEach(child => {

            const card = document.createElement("div");

            card.className = "dashboard-card";

            card.innerHTML = `

                <h3>${child.childName}</h3>

                <p>
                    <strong>Gender:</strong>
                    ${child.gender}
                </p>

                <p>
                    <strong>Date of Birth:</strong>
                    ${child.dateOfBirth}
                </p>

                <p>
                    <strong>Admission:</strong>
                    ${child.admissionDate}
                </p>

                <p>
                    <strong>Education:</strong>
                    ${child.educationStatus}
                </p>

                <p>
                    <strong>Health:</strong>
                    ${child.healthStatus}
                </p>

                <p>
                    <strong>City:</strong>
                    ${child.city}
                </p>

                <p>
                    <strong>Status:</strong>
                    ${child.status}
                </p>

            `;

            childrenContainer.appendChild(card);

        });


    } catch (error) {

        console.error("Load Children Error:", error);

        childrenContainer.innerHTML =
            "<p>Unable to load children.</p>";
    }
}


document.getElementById("logoutBtn").addEventListener(
    "click",
    function () {

        localStorage.removeItem("token");
        localStorage.removeItem("userId");
        localStorage.removeItem("fullName");
        localStorage.removeItem("email");
        localStorage.removeItem("role");

        window.location.href = "login.html";
    }
);



// ==============================
// HELPER DASHBOARD
// ==============================

const token = localStorage.getItem("token");
const helperId = localStorage.getItem("userId");
const role = localStorage.getItem("role");


// Login check
if (!token || !helperId || role !== "HELPER") {

    window.location.href = "login.html";

}


// Helper information
const helperName =
    localStorage.getItem("fullName");

if (helperName) {

    document.getElementById("helperName").textContent =
        helperName;

}


// ==============================
// LOAD HELPER IMPACT
// ==============================

async function loadHelperImpact() {

    try {

        const response = await fetch(
            `/helper/impact/${helperId}`,
            {
                method: "GET",

                headers: {
                    "Authorization": `Bearer ${token}`
                }
            }
        );


        if (!response.ok) {

            throw new Error(
                "Unable to load helper impact"
            );

        }


        const data = await response.json();


        // Display data
        document.getElementById("points").textContent =
            data.points;

        document.getElementById("completedHelps").textContent =
            data.completedHelps;

        document.getElementById("level").textContent =
            data.level;

        document.getElementById("badge").textContent =
            data.badge;


    } catch (error) {

        console.error(error);

    }

}


// Load dashboard data
loadHelperImpact();


// ==============================
// LOGOUT
// ==============================

const logoutBtn =
    document.getElementById("logoutBtn");

if (logoutBtn) {

    logoutBtn.addEventListener("click", function (event) {

        event.preventDefault();

        localStorage.removeItem("token");
        localStorage.removeItem("userId");
        localStorage.removeItem("fullName");
        localStorage.removeItem("email");
        localStorage.removeItem("role");

        window.location.href = "index.html";

    });

}
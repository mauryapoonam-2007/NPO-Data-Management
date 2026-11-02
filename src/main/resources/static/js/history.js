// ==============================
// HELPER HISTORY
// ==============================

const token = localStorage.getItem("token");
const helperId = localStorage.getItem("userId");
const role = localStorage.getItem("role");


// Login check
if (!token || !helperId || role !== "HELPER") {
    window.location.href = "login.html";
}


// ==============================
// LOAD HELP HISTORY
// ==============================

async function loadHistory() {

    const container =
        document.getElementById("historyContainer");

    try {

        const response = await fetch(
            `/helper/history/${helperId}`,
            {
                method: "GET",

                headers: {
                    "Authorization": `Bearer ${token}`
                }
            }
        );


        if (!response.ok) {
            throw new Error("Unable to load history");
        }


        const history = await response.json();

        container.innerHTML = "";


        if (history.length === 0) {

            container.innerHTML =
                "<p>No completed help activities found.</p>";

            return;
        }


        history.forEach(item => {

            const card =
                document.createElement("div");

            card.className = "help-card";

            const completedDate =
                item.completedAt
                    ? new Date(item.completedAt)
                        .toLocaleString()
                    : "Not available";


            card.innerHTML = `
                <h3>${item.title}</h3>

                <p>
                    <strong>Category:</strong>
                    ${item.category}
                </p>

                <p>
                    <strong>City:</strong>
                    ${item.city}
                </p>

                <p>
                    <strong>Priority:</strong>
                    ${item.priority}
                </p>

                <p>
                    <strong>Completed On:</strong>
                    ${completedDate}
                </p>

                <p>
                    <strong>Status:</strong>
                    COMPLETED
                </p>
            `;


            container.appendChild(card);

        });


    } catch (error) {

        console.error(error);

        container.innerHTML =
            "<p>Unable to load help history.</p>";

    }

}


// ==============================
// LOGOUT
// ==============================

const logoutBtn =
    document.getElementById("logoutBtn");

if (logoutBtn) {

    logoutBtn.addEventListener("click", function(event) {

        event.preventDefault();

        localStorage.clear();

        window.location.href = "index.html";

    });

}


// Load history
loadHistory();
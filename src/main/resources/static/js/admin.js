const token = localStorage.getItem("token");
const role = localStorage.getItem("role");
const adminName = localStorage.getItem("fullName");

// Admin login check
if (!token || role !== "ADMIN") {
    window.location.href = "login.html";
}


// Admin name
document.getElementById("adminName").textContent =
    adminName || "Admin";


// Load dashboard data
async function loadDashboard() {

    try {

        const response = await fetch("/admin/dashboard", {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (!response.ok) {

            if (response.status === 401 || response.status === 403) {
                alert("Access denied. Please login as Admin.");
                localStorage.clear();
                window.location.href = "login.html";
                return;
            }

            throw new Error("Unable to load dashboard");
        }

        const data = await response.json();

        // Users
        document.getElementById("totalUsers").textContent =
            data.totalUsers || 0;

        document.getElementById("totalHelpers").textContent =
            data.totalHelpers || 0;

        document.getElementById("totalBeneficiaries").textContent =
            data.totalBeneficiaries || 0;


        // Help Requests
        document.getElementById("totalRequests").textContent =
            data.totalRequests || 0;

        document.getElementById("pendingRequests").textContent =
            data.pendingRequests || 0;

        document.getElementById("completedHelps").textContent =
            data.totalCompletedHelps || 0;


        // Inventory
        document.getElementById("inventoryItems").textContent =
            data.totalInventoryItems || 0;


        // Donations
        document.getElementById("totalDonations").textContent =
            data.totalDonations || 0;

    } 
    catch (error) {

        console.error("Dashboard Error:", error);

        alert("Unable to load dashboard data.");
    }
}


// Logout
function logout() {

    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    localStorage.removeItem("fullName");
    localStorage.removeItem("email");
    localStorage.removeItem("role");

    window.location.href = "login.html";
}


// Load dashboard when page opens
loadDashboard();
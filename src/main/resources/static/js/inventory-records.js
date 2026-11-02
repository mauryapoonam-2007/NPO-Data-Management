
const INVENTORY_RECORDS_API = "/admin/inventory";

let allInventoryRecords = [];

document.addEventListener("DOMContentLoaded", function () {
    const searchInput = document.getElementById("searchInventory");

    searchInput.addEventListener("input", function () {
        filterInventoryRecords(searchInput.value);
    });

    loadInventoryRecords();
});

async function loadInventoryRecords() {
    const tableBody = document.getElementById("inventoryTableBody");
    const message = document.getElementById("recordsMessage");
    const token = localStorage.getItem("token");

    if (!token) {
        showRecordsError("Please login as admin to view inventory.");
        return;
    }

    try {
        const response = await fetch(INVENTORY_RECORDS_API, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (response.status === 401 || response.status === 403) {
            throw new Error("Login expired or admin access is required. Please login again.");
        }

        if (!response.ok) {
            throw new Error("Unable to load inventory records.");
        }

        allInventoryRecords = await response.json();

        if (!Array.isArray(allInventoryRecords)) {
            throw new Error("Unexpected response received from server.");
        }

        message.textContent = "";
        filterInventoryRecords("");

    } catch (error) {
        console.error("Load inventory error:", error);
        showRecordsError(error.message);
    }
}

function filterInventoryRecords(searchText) {
    const search = searchText.trim().toLowerCase();

    const filteredRecords = allInventoryRecords.filter(function (item) {
        return [
            item.itemName,
            item.category,
            item.city
        ].some(function (value) {
            return String(value ?? "").toLowerCase().includes(search);
        });
    });

    displayInventoryRecords(filteredRecords);
}

function displayInventoryRecords(records) {
    const tableBody = document.getElementById("inventoryTableBody");
    tableBody.innerHTML = "";

    if (records.length === 0) {
        const row = document.createElement("tr");
        const cell = document.createElement("td");

        cell.colSpan = 6;
        cell.textContent = allInventoryRecords.length === 0
            ? "No inventory records found."
            : "No matching records found.";

        row.appendChild(cell);
        tableBody.appendChild(row);
        return;
    }

    records.forEach(function (item) {
        const row = document.createElement("tr");

        const values = [
            item.itemName,
            item.category,
            item.quantity,
            item.unit,
            item.city,
            item.description
        ];

        values.forEach(function (value) {
            const cell = document.createElement("td");
            cell.textContent = value ?? "-";
            row.appendChild(cell);
        });

        tableBody.appendChild(row);
    });
}

function showRecordsError(errorMessage) {
    const tableBody = document.getElementById("inventoryTableBody");
    const message = document.getElementById("recordsMessage");

    tableBody.innerHTML = "";
    message.style.color = "red";
    message.textContent = errorMessage;
}

function logout() {
    localStorage.removeItem("token");
    window.location.href = "login.html";
}

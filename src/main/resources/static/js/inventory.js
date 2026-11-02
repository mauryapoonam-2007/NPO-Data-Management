
const INVENTORY_API = "/admin/inventory";

document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("inventoryForm");

    form.addEventListener("submit", function (event) {
        event.preventDefault();
        addInventory();
    });
});

async function addInventory() {
    const form = document.getElementById("inventoryForm");
    const button = form.querySelector('button[type="submit"]');
    const message = document.getElementById("message");
    const token = localStorage.getItem("token");

    if (!token) {
        message.style.color = "red";
        message.textContent = "Please login as admin first.";
        return;
    }

    const item = {
        itemName: document.getElementById("itemName").value.trim(),
        category: document.getElementById("category").value,
        quantity: Number(document.getElementById("quantity").value),
        unit: document.getElementById("unit").value,
        city: document.getElementById("city").value.trim(),
        description: document.getElementById("description").value.trim()
    };

    button.disabled = true;
    button.textContent = "Saving...";

    try {
        const response = await fetch(INVENTORY_API, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
            },
            body: JSON.stringify(item)
        });

        if (response.status === 401 || response.status === 403) {
            throw new Error("Login expired or admin access is required. Please login again.");
        }

        if (!response.ok) {
            throw new Error("Could not save inventory. Check the backend logs.");
        }

        message.style.color = "green";
        message.textContent = "Inventory item added successfully!";
        form.reset();

        setTimeout(function () {
            message.textContent = "";
        }, 3000);

    } catch (error) {
        console.error("Add inventory error:", error);
        message.style.color = "red";
        message.textContent = error.message || "Unable to add inventory item.";
    } finally {
        button.disabled = false;
        button.textContent = "Add Inventory";
    }
}

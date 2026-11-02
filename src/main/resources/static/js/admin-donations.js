const token = localStorage.getItem("token");
const role = localStorage.getItem("role");


// ADMIN CHECK

if (!token || role !== "ADMIN") {

    window.location.href = "login.html";

}


// LOAD DONATIONS
async function loadDonations() {

    const status =
        document.getElementById("statusFilter").value.trim();

    let url = "/admin/donations";

    if (status !== "") {
        url = "/admin/donations/search?status="
            + encodeURIComponent(status);
    }

    console.log("Request URL:", url);

    try {

        const response = await fetch(url, {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token,
                "Content-Type": "application/json"
            }
        });

        console.log("Response Status:", response.status);

        if (!response.ok) {

            const errorText = await response.text();

            console.error("Server Error:", errorText);

            throw new Error(
                "Unable to load donations. Status: "
                + response.status
            );
        }

        const donations = await response.json();

        console.log("Donations:", donations);

        displayDonations(donations);

    } catch (error) {

        console.error("Donation Search Error:", error);

        document.getElementById("donationMessage").textContent =
            "Unable to load donations.";

    }
}


// DISPLAY DONATIONS

function displayDonations(donations) {

    const container =
        document.getElementById("donationList");


    container.innerHTML = "";


    if (donations.length === 0) {

        container.innerHTML =
            "<p>No donations found.</p>";

        return;

    }


    donations.forEach(function(donation) {

        const card =
            document.createElement("div");

        card.className = "donation-admin-card";


        let donationDetails = "";


        if (donation.type === "MONEY") {

            donationDetails =
                `<p><strong>Amount:</strong> ₹${donation.amount}</p>`;

        }

        else {

            donationDetails = `

                <p>
                    <strong>Item:</strong>
                    ${donation.itemName || "-"}
                </p>

                <p>
                    <strong>Quantity:</strong>
                    ${donation.quantity || "-"}
                    ${donation.unit || ""}
                </p>

            `;

        }


        card.innerHTML = `

            <div class="donation-card-header">

                <h3>
                    ${donation.donorName}
                </h3>

                <span class="donation-status">
                    ${donation.status}
                </span>

            </div>


            <p>
                <strong>Email:</strong>
                ${donation.donorEmail}
            </p>


            <p>
                <strong>Phone:</strong>
                ${donation.donorPhone}
            </p>


            <p>
                <strong>Type:</strong>
                ${donation.type}
            </p>


            ${donationDetails}


            <p>
                <strong>Category:</strong>
                ${donation.category}
            </p>


            <p>
                <strong>City:</strong>
                ${donation.city}
            </p>


            <p>
                <strong>Description:</strong>
                ${donation.description || "-"}
            </p>


            <div class="donation-actions">

                ${
                    donation.status === "PENDING"

                    ?

                    `

                    <button
                        onclick="verifyDonation('${donation.id}')">
                        Verify
                    </button>


                    <button
                        onclick="rejectDonation('${donation.id}')">
                        Reject
                    </button>

                    `

                    :

                    ""

                }

            </div>

        `;


        container.appendChild(card);

    });

}


// VERIFY DONATION

async function verifyDonation(id) {

    try {

        const response = await fetch(
            `/admin/donations/${id}/verify`,
            {

                method: "PUT",

                headers: {

                    "Authorization": "Bearer " + token

                }

            }
        );


        if (!response.ok) {

            throw new Error("Verification failed");

        }


        alert("Donation verified successfully.");

        loadDonations();

    }

    catch (error) {

        console.error(error);

        alert("Unable to verify donation.");

    }

}


// REJECT DONATION

async function rejectDonation(id) {

    try {

        const response = await fetch(
            `/admin/donations/${id}/reject`,
            {

                method: "PUT",

                headers: {

                    "Authorization": "Bearer " + token

                }

            }
        );


        if (!response.ok) {

            throw new Error("Rejection failed");

        }


        alert("Donation rejected.");

        loadDonations();

    }

    catch (error) {

        console.error(error);

        alert("Unable to reject donation.");

    }

}


// LOGOUT

function logout() {

    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    localStorage.removeItem("fullName");
    localStorage.removeItem("email");
    localStorage.removeItem("role");

}

document.getElementById("statusFilter")
    .addEventListener("change", loadDonations);
// INITIAL LOAD

loadDonations();
// ==========================================
// DONATION FORM
// ==========================================

const donationForm =
    document.getElementById("donationForm");


const typeSelect =
    document.getElementById("type");


const amountGroup =
    document.getElementById("amountGroup");


const materialFields =
    document.getElementById("materialFields");



// ==========================================
// MONEY / MATERIAL TOGGLE
// ==========================================

function updateDonationFields() {

    if (typeSelect.value === "MATERIAL") {

        // Hide money field
        amountGroup.style.display = "none";

        // Show material fields
        materialFields.style.display = "block";

    } else {

        // Show money field
        amountGroup.style.display = "block";

        // Hide material fields
        materialFields.style.display = "none";

    }

}


// Run when donation type changes

typeSelect.addEventListener(
    "change",
    updateDonationFields
);


// Run once when page loads

updateDonationFields();



// ==========================================
// SUBMIT DONATION
// ==========================================

donationForm.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();


        const message =
            document.getElementById(
                "donationMessage"
            );


        const type =
            document.getElementById(
                "type"
            ).value;


        const donorName =
            document.getElementById(
                "donorName"
            ).value.trim();


        const donorEmail =
            document.getElementById(
                "donorEmail"
            ).value.trim();


        const donorPhone =
            document.getElementById(
                "donorPhone"
            ).value.trim();


        const category =
            document.getElementById(
                "category"
            ).value.trim();


        const city =
            document.getElementById(
                "city"
            ).value.trim();


        const description =
            document.getElementById(
                "description"
            ).value.trim();


        let amount = null;
        let itemName = null;
        let quantity = null;
        let unit = null;



        // MONEY

        if (type === "MONEY") {

            amount = Number(
                document.getElementById(
                    "amount"
                ).value
            );

        }



        // MATERIAL

        if (type === "MATERIAL") {

            itemName =
                document.getElementById(
                    "itemName"
                ).value.trim();


            quantity = Number(
                document.getElementById(
                    "quantity"
                ).value
            );


            unit =
                document.getElementById(
                    "unit"
                ).value.trim();


            if (!itemName) {

                message.textContent =
                    "Please enter item name.";

                return;
            }


            if (!quantity || quantity <= 0) {

                message.textContent =
                    "Please enter a valid quantity.";

                return;
            }

        }



        const donation = {

            donorName: donorName,

            donorEmail: donorEmail,

            donorPhone: donorPhone,

            type: type,

            amount: amount,

            itemName: itemName,

            quantity: quantity,

            unit: unit,

            category: category,

            city: city,

            description: description

        };



        message.textContent =
            "Submitting donation...";



        try {

            const response = await fetch(
                "/donations",
                {

                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body: JSON.stringify(
                        donation
                    )

                }
            );


            const text =
                await response.text();


            let data;

            try {

                data = JSON.parse(text);

            } catch {

                data = text;

            }



            if (!response.ok) {

                message.textContent =
                    typeof data === "string"
                        ? data
                        : "Donation failed.";

                return;
            }



            message.textContent =
                "Donation submitted successfully! It is pending verification.";


            donationForm.reset();


            // Reset fields after form reset

            updateDonationFields();


        } catch (error) {

            console.error(
                "Donation Error:",
                error
            );

            message.textContent =
                "Unable to connect to server.";

        }

    }
);
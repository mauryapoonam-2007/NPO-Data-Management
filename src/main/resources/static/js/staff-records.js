const token = localStorage.getItem("token");
const role = localStorage.getItem("role");


if (!token || role !== "ADMIN") {

    window.location.href = "login.html";

}


const staffContainer =
    document.getElementById("staffContainer");

const searchStaff =
    document.getElementById("searchStaff");


let allStaff = [];


async function loadStaff() {

    try {

        const response = await fetch(
            "/admin/staff",
            {

                method: "GET",

                headers: {

                    "Authorization":
                        "Bearer " + token

                }

            }
        );


        if (!response.ok) {

            throw new Error(
                "Unable to load staff"
            );

        }


        allStaff =
            await response.json();


        displayStaff(allStaff);


    } catch (error) {

        console.error(
            "Staff Records Error:",
            error
        );

        staffContainer.innerHTML =
            "<p>Unable to load staff records.</p>";

    }

}


function displayStaff(staffList) {

    staffContainer.innerHTML = "";


    if (staffList.length === 0) {

        staffContainer.innerHTML =
            "<p>No staff records found.</p>";

        return;

    }


    const table =
        document.createElement("table");

    table.className =
        "records-table";


    table.innerHTML = `

        <thead>

            <tr>

                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Designation</th>
                <th>Department</th>
                <th>Joining Date</th>
                <th>City</th>
                <th>Status</th>

            </tr>

        </thead>

        <tbody></tbody>

    `;


    const tbody =
        table.querySelector("tbody");


    staffList.forEach(staff => {

        const row =
            document.createElement("tr");


        row.innerHTML = `

            <td>
                ${staff.fullName}
            </td>

            <td>
                ${staff.email}
            </td>

            <td>
                ${staff.phone}
            </td>

            <td>
                ${staff.designation}
            </td>

            <td>
                ${staff.department}
            </td>

            <td>
                ${staff.joiningDate}
            </td>

            <td>
                ${staff.city}
            </td>

            <td>
                ${staff.status}
            </td>

        `;


        tbody.appendChild(row);

    });


    staffContainer.appendChild(table);

}


searchStaff.addEventListener(
    "input",
    function () {

        const text =
            searchStaff.value
                .toLowerCase()
                .trim();


        const filtered =
            allStaff.filter(staff =>
                staff.fullName
                    .toLowerCase()
                    .includes(text)
            );


        displayStaff(filtered);

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


loadStaff();
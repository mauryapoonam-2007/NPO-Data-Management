const token = localStorage.getItem("token");
const role = localStorage.getItem("role");


if (!token || role !== "ADMIN") {

    window.location.href = "login.html";

}


const educationContainer =
    document.getElementById(
        "educationContainer"
    );

const searchEducation =
    document.getElementById(
        "searchEducation"
    );


let allEducation = [];


async function loadEducation() {

    try {

        const response = await fetch(
            "/admin/education",
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
                "Unable to load education records"
            );

        }


        allEducation =
            await response.json();


        displayEducation(
            allEducation
        );


    } catch (error) {

        console.error(
            "Education Records Error:",
            error
        );

        educationContainer.innerHTML =
            "<p>Unable to load education records.</p>";

    }

}


function displayEducation(records) {

    educationContainer.innerHTML = "";


    if (records.length === 0) {

        educationContainer.innerHTML =
            "<p>No education records found.</p>";

        return;

    }


    const table =
        document.createElement("table");

    table.className =
        "records-table";


    table.innerHTML = `

        <thead>

            <tr>

                <th>Child Name</th>
                <th>School</th>
                <th>Class</th>
                <th>Academic Year</th>
                <th>Admission Date</th>
                <th>Attendance</th>
                <th>Performance</th>
                <th>Remarks</th>
                <th>Status</th>

            </tr>

        </thead>

        <tbody></tbody>

    `;


    const tbody =
        table.querySelector("tbody");


    records.forEach(record => {

        const row =
            document.createElement("tr");


        row.innerHTML = `

            <td>
                ${record.childName}
            </td>

            <td>
                ${record.schoolName}
            </td>

            <td>
                ${record.className}
            </td>

            <td>
                ${record.academicYear}
            </td>

            <td>
                ${record.admissionDate || "-"}
            </td>

            <td>
                ${record.attendancePercentage != null
                    ? record.attendancePercentage + "%"
                    : "-"
                }
            </td>

            <td>
                ${record.performance || "-"}
            </td>

            <td>
                ${record.remarks || "-"}
            </td>

            <td>
                ${record.status}
            </td>

        `;


        tbody.appendChild(row);

    });


    educationContainer.appendChild(table);

}


searchEducation.addEventListener(
    "input",
    function () {

        const searchText =
            searchEducation.value
                .toLowerCase()
                .trim();


        const filtered =
            allEducation.filter(record =>
                record.childName
                    .toLowerCase()
                    .includes(searchText)
            );


        displayEducation(filtered);

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


loadEducation();
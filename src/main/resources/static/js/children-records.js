const token = localStorage.getItem("token");
const role = localStorage.getItem("role");


// Only Admin can access
if (!token || role !== "ADMIN") {
    window.location.href = "login.html";
}


const childrenContainer =
    document.getElementById("childrenContainer");

const searchChild =
    document.getElementById("searchChild");

let allChildren = [];


// Load all children
async function loadChildren() {

    try {

        const response = await fetch(
            "/admin/children",
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
                "Unable to load children"
            );
        }


        allChildren = await response.json();

        displayChildren(allChildren);


    } catch (error) {

        console.error(
            "Children Records Error:",
            error
        );

        childrenContainer.innerHTML =
            "<p>Unable to load children records.</p>";
    }
}


// Display children
function displayChildren(children) {

    childrenContainer.innerHTML = "";


    if (children.length === 0) {

        childrenContainer.innerHTML =
            "<p>No children records found.</p>";

        return;
    }


    const table = document.createElement("table");

    table.className = "records-table";


    table.innerHTML = `
        <thead>

            <tr>

                <th>Name</th>
                <th>Date of Birth</th>
                <th>Gender</th>
                <th>Admission Date</th>
                <th>Education</th>
                <th>Health</th>
                <th>City</th>
                <th>Status</th>

            </tr>

        </thead>

        <tbody></tbody>
    `;


    const tbody =
        table.querySelector("tbody");


    children.forEach(child => {

        const row =
            document.createElement("tr");


        row.innerHTML = `

            <td>${child.childName}</td>

            <td>${child.dateOfBirth}</td>

            <td>${child.gender}</td>

            <td>${child.admissionDate}</td>

            <td>${child.educationStatus}</td>

            <td>${child.healthStatus}</td>

            <td>${child.city}</td>

            <td>${child.status}</td>

        `;


        tbody.appendChild(row);

    });


    childrenContainer.appendChild(table);
}


// Search
searchChild.addEventListener(
    "input",
    function () {

        const searchText =
            searchChild.value
                .toLowerCase()
                .trim();


        const filteredChildren =
            allChildren.filter(child =>
                child.childName
                    .toLowerCase()
                    .includes(searchText)
            );


        displayChildren(filteredChildren);
    }
);


// Logout
document
    .getElementById("logoutBtn")
    .addEventListener(
        "click",
        function () {

            localStorage.removeItem("token");
            localStorage.removeItem("userId");
            localStorage.removeItem("fullName");
            localStorage.removeItem("email");
            localStorage.removeItem("role");

            window.location.href =
                "login.html";
        }
    );


loadChildren();
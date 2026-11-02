// ==============================
// HELPER REQUESTS
// ==============================

const token = localStorage.getItem("token");
const helperId = localStorage.getItem("userId");
const role = localStorage.getItem("role");


// Login check
if (!token || !["ADMIN", "HELPER"].includes(role)) {
    window.location.href = "login.html";
}


// ==============================
// LOAD MY TASKS
// ==============================

async function loadTasks() {

    const container =
        document.getElementById("tasksContainer");

    try {

        const response = await fetch(
            `/helper/tasks/${helperId}`,
            {
                method: "GET",

                headers: {
                    "Authorization": `Bearer ${token}`
                }
            }
        );


        if (!response.ok) {
    const errorText = await response.text();
    console.error("Task API error:", response.status, errorText);
    container.innerHTML = `<p>Unable to load tasks. Error: ${response.status}</p>`;
    return;
}

        

        const tasks = await response.json();


        container.innerHTML = "";


        if (tasks.length === 0) {

            container.innerHTML =
                "<p>No tasks assigned to you.</p>";

            return;
        }


        tasks.forEach(task => {

            const card =
                document.createElement("div");

            card.className = "help-card";


            card.innerHTML = `
                <h3>${task.title}</h3>

                <p>
                    ${task.description}
                </p>

                <p>
                    <strong>Category:</strong>
                    ${task.category}
                </p>

                <p>
                    <strong>City:</strong>
                    ${task.city}
                </p>

                <p>
                    <strong>Priority:</strong>
                    ${task.priority}
                </p>

                <p>
                    <strong>Status:</strong>
                    ${task.status}
                </p>

                <div class="task-buttons">

                    ${
                        task.status === "ASSIGNED"
                        ?
                        `<button
                            class="btn primary-btn"
                            onclick="acceptTask('${task.id}')">
                            Accept Task
                        </button>`
                        :
                        ""
                    }

                    ${
                        task.status === "IN_PROGRESS"
                        ?
                        `<button
                            class="btn primary-btn"
                            onclick="completeTask('${task.id}')">
                            Complete Task
                        </button>`
                        :
                        ""
                    }

                </div>
            `;


            container.appendChild(card);

        });


    } catch (error) {

        console.error(error);

        container.innerHTML =
            "<p>Unable to load tasks.</p>";

    }

}


// ==============================
// ACCEPT TASK
// ==============================

async function acceptTask(requestId) {

    try {

        const response = await fetch(
            `/helper/tasks/${requestId}/accept?helperId=${helperId}`,
            {
                method: "PUT",

                headers: {
                    "Authorization": `Bearer ${token}`
                }
            }
        );


        if (!response.ok) {

            const error =
                await response.text();

            alert(error);

            return;
        }


        alert("Task accepted successfully!");

        loadTasks();


    } catch (error) {

        console.error(error);

        alert("Server error.");

    }

}


// ==============================
// COMPLETE TASK
// ==============================

async function completeTask(requestId) {

    const confirmComplete =
        confirm(
            "Are you sure you completed this task?"
        );


    if (!confirmComplete) {
        return;
    }


    try {

        const response = await fetch(
            `/helper/tasks/${requestId}/complete?helperId=${helperId}`,
            {
                method: "PUT",

                headers: {
                    "Authorization": `Bearer ${token}`
                }
            }
        );


        if (!response.ok) {

            const error =
                await response.text();

            alert(error);

            return;
        }


        alert(
            "Task completed successfully! You earned 50 points."
        );

        loadTasks();


    } catch (error) {

        console.error(error);

        alert("Server error.");

    }

}


// ==============================
// LOGOUT
// ==============================

const logoutBtn =
    document.getElementById("logoutBtn");

if (logoutBtn) {

    logoutBtn.addEventListener("click", function (event) {

        event.preventDefault();

        localStorage.clear();

        window.location.href = "index.html";

    });

}


// Load tasks
loadTasks();
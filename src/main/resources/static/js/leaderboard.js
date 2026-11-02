const token = localStorage.getItem("token");

async function loadLeaderboard() {

    try {

        const response = await fetch("/leaderboard", {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (!response.ok) {
            throw new Error("Unable to load leaderboard");
        }

        const data = await response.json();

        // Highest points first
        data.sort((a, b) => b.points - a.points);

        const topThree = data.slice(0, 3);
        const otherHelpers = data.slice(3);

        const topContainer =
            document.getElementById("topThreeContainer");

        const otherContainer =
            document.getElementById("otherHelpersContainer");

        topContainer.innerHTML = "";
        otherContainer.innerHTML = "";

        // ---------- TOP 3 ----------
        topThree.forEach((helper, index) => {

            const rank = index + 1;

            let medal = "";
            let position = "";

            if (rank === 1) {
                medal = "&#129351;";
                position = "1st";
            } else if (rank === 2) {
                medal = "&#129352;";
                position = "2nd";
            } else {
                medal = "&#129353;";
                position = "3rd";
            }

            const card = document.createElement("div");

            card.className = "top-helper-card";

            card.innerHTML = `
                <div class="rank">
                    ${medal} ${position}
                </div>

                <h3>${helper.helperName}</h3>

                <p class="points">
                    ${helper.points} Points
                </p>

                <p class="badge">
                    ${helper.badge}
                </p>
            `;

            topContainer.appendChild(card);
        });

        // ---------- OTHER HELPERS ----------
        otherHelpers.forEach((helper, index) => {

            const rank = index + 4;

            const row = document.createElement("div");

            row.className = "helper-ranking-row";

            row.innerHTML = `
                <span class="helper-rank">
                    ${rank}
                </span>

                <span class="helper-name">
                    ${helper.helperName}
                </span>

                <span class="helper-points">
                    ${helper.points} Points
                </span>

                <span class="helper-badge">
                    ${helper.badge}
                </span>
            `;

            otherContainer.appendChild(row);
        });

        // No other helpers
        if (otherHelpers.length === 0) {
            otherContainer.innerHTML =
                "<p>No other helpers in the ranking.</p>";
        }

    } catch (error) {

        console.error("Leaderboard Error:", error);

        document.getElementById("topThreeContainer").innerHTML =
            "<p>Unable to load leaderboard.</p>";

    }
}

loadLeaderboard();
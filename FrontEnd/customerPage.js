document.addEventListener('DOMContentLoaded', () => {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const userId = urlParams.get('id'); 
    console.log(userId);
    const showOrdersButton = document.getElementById('showOrdersButton');
    showOrdersButton.addEventListener('click', () => {
        alert(userId)
        window.location.href = `OrderPage.html?id=${userId}`;
    });

    checkUserActiveStatus(userId);

    function checkUserActiveStatus(userId) {
        fetch(`http://localhost:8181/users/${userId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch user data');
                }
                return response.json();
            })
            .then(user => {
                if (user.active) {
                    updateGreeting(user.userName);
                    fetchAndDisplayVehicleDetails();
                } else {
                    alert('You are not in an active state.Please Contact the Admin');
                    window.location.href = `index.html`;
                }
            })
            .catch(error => {
                console.error('Error fetching user data:', error);
            });
    }

    function updateGreeting(name) {
        const greetingDiv = document.getElementById('greeting');
        greetingDiv.textContent = `Hello ${name},`;
    }

    function fetchAndDisplayVehicleDetails() {
        fetch('http://localhost:8181/vehicles')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch vehicle data');
                }
                return response.json();
            })
            .then(data => {
                populateTable(data);
                attachClickEventListeners();
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }

    function populateTable(data) {
        const tableBody = document.getElementById('vehicleTableBody');
        tableBody.innerHTML = '';

        data.forEach(vehicle => {
            const row = tableBody.insertRow();
            row.innerHTML = `
                <td class="vehicle_name" data-vehicle-id="${vehicle.id}">${vehicle.name}</td>
                <td>${vehicle.type}</td>
                <td>${vehicle.registrationNumber}</td>
                <td>${vehicle.fuelType}</td>
                <td>${vehicle.rate}</td>
                <td><button class="requestRideButton" data-vehicle-id="${vehicle.id}">Request Ride</button></td>
            `;
        });
    }

    function showVehicleDetails(vehicleId, userId) {
        fetch(`http://localhost:8181/vehicles/${vehicleId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch vehicle details');
                }
                return response.json();
            })
            .then(vehicleDetails => {
                const queryString = `?userId=${userId}` + `&vehicleId=${vehicleId}` +
                    `&name=${encodeURIComponent(vehicleDetails.name)}` +
                    `&type=${encodeURIComponent(vehicleDetails.type)}` +
                    `&registrationNumber=${encodeURIComponent(vehicleDetails.registrationNumber)}` +
                    `&fuelType=${encodeURIComponent(vehicleDetails.fuelType)}` +
                    `&rate=${vehicleDetails.rate}`;
                alert(queryString);
                window.location.href = `rideRequestForm.html${queryString}`;
            })
            .catch(error => {
                console.error('Error fetching vehicle details:', error);
            });
    }

    function attachClickEventListeners() {
        document.querySelectorAll('.requestRideButton').forEach(button => {
            button.addEventListener('click', () => {
                const queryString = window.location.search;
                const urlParams = new URLSearchParams(queryString);
                const userId = urlParams.get('id');
                const vehicleId = button.getAttribute('data-vehicle-id');
                showVehicleDetails(vehicleId, userId);
            });
        });
    }

    // Logout button event listener
    const logoutButton = document.getElementById('logoutButton');
    logoutButton.addEventListener('click', () => {
        window.location.href = 'index.html';
    });
});

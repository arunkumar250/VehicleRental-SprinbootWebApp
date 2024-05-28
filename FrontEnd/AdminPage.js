document.addEventListener('DOMContentLoaded', () => {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    console.log(queryString)
    const username = urlParams.get('username'); 
    console.log(username)
    updateGreeting(username);

    // Function to fetch and display vehicle details
    function fetchAndDisplayVehicleDetails() {
        fetch('http://localhost:8181/vehicles')
            .then(response => response.json())
            .then(data => {
                populateTable(data);
                attachClickEventListeners();
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }

    // Function to fetch and display orders
    function fetchAndDisplayOrders() {
        window.location.href = `OrderPage.html?role=admin`;
    }

    // Event listener for adding an offer
    document.getElementById('addOfferButton').addEventListener('click', () => {
        window.location.href = 'addOffer.html'; // Change the URL to your offer adding page
    });

    function updateGreeting(username) {
        const greetingDiv = document.getElementById('greeting');
        greetingDiv.textContent = `Hello ${username},`;
    }

    // Function to populate the vehicle table
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
                <td>${vehicle.vehicleColor}</td>
            `;
        });
    }

    // Function to show vehicle details
    function showVehicleDetails(vehicleId) {
        fetch(`http://localhost:8181/vehicles/${vehicleId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch vehicle details');
                }
                return response.json();
            })
            .then(vehicleDetails => {
                console.log(vehicleId);
                const queryString = `?id=${vehicleId}&type=${vehicleDetails.type}&name=${vehicleDetails.name}&registrationNumber=${vehicleDetails.registrationNumber}&fuelType=${vehicleDetails.fuelType}&rate=${vehicleDetails.rate}`;
                window.location.href = `requestForm.html${queryString}`;
            })
            .catch(error => {
                console.error('Error fetching vehicle details:', error);
            });
    }

    // Function to attach click event listeners to vehicle names
    function attachClickEventListeners() {
        document.querySelectorAll('.vehicle_name').forEach(cell => {
            cell.addEventListener('click', () => {
                const vehicleId = cell.getAttribute('data-vehicle-id');
                showVehicleDetails(vehicleId);
            });
        });
    }

    // Event listener for showing orders
    document.getElementById('showOrdersButton').addEventListener('click', () => {
        fetchAndDisplayOrders();
    });

    fetchAndDisplayVehicleDetails();

    // Logout button event listener
    const logoutButton = document.getElementById('logoutButton');
    logoutButton.addEventListener('click', () => {
        window.location.href = 'index.html';
    });
});

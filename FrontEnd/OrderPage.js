// Function to format date and time
function formatDateTime(dateTimeStr) {
    const dateTime = new Date(dateTimeStr);
    return dateTime.toLocaleString(); 
}

// Function to fetch and display orders
function fetchAndDisplayOrder() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const userId = urlParams.get('id');     
    fetch(`http://localhost:8181/vehiclerides/customer/${userId}`)
        .then(response => response.json())
        .then(data => {
            populateOrderTable(data);
        })
        .catch(error => {
            console.error('Error fetching order:', error);
        });
}

// Function to cancel a ride
function cancelRide(rideId) {
    fetch(`http://localhost:8181/vehiclerides/update/${rideId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ rideStatus: 'Cancelled' })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to cancel ride');
        }
        console.log('Ride cancelled successfully');
        fetchAndDisplayOrder();
    })
    .catch(error => {
        console.error('Error cancelling ride:', error);
    });
}

// Function to create a cancel button for a ride
function createCancelButton(rideId) {
    const cancelButton = document.createElement('button');
    cancelButton.textContent = 'Cancel';
    cancelButton.addEventListener('click', () => {
        cancelRide(rideId);
    });
    return cancelButton;
}

// Function to populate the order table with ride details
function populateOrderTable(rides) {
    const tableBody = document.getElementById('ridesTableBody');
    tableBody.innerHTML = ''; 

    rides.forEach(ride => {
        const row = tableBody.insertRow(); 
        const customerId = ride.customer ? ride.customer.id : 'N/A'; 
        row.innerHTML = `
            <td>${customerId}</td>
            <td>${ride.vehicle.name}</td>
            <td>${formatDateTime(ride.startTime)}</td>
            <td>${formatDateTime(ride.endTime)}</td>
            <td>${formatDateTime(ride.createDateTime)}</td>
            <td>${ride.totalAmount}</td>
            <td>${ride.rideStatus}</td>
            <td></td> <!-- Placeholder for cancel button -->
        `;

        const cancelButton = createCancelButton(ride.rideId);
        row.cells[row.cells.length - 1].appendChild(cancelButton);
    });
}

document.addEventListener('DOMContentLoaded', () => {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const role = urlParams.get('role');
    if (role === 'admin') {
        fetch('http://localhost:8181/vehiclerides/all')
            .then(response => response.json())
            .then(data => {
                populateOrderTable(data);
            })
            .catch(error => {
                console.error('Error fetching vehicle rides:', error);
            });
    } else {
        fetchAndDisplayOrder();
    }
});

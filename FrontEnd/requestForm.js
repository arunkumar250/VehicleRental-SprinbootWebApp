// This function is triggered when the DOM content is fully loaded
document.addEventListener('DOMContentLoaded', () => {
    // Extracting URL parameters
    const urlParams = new URLSearchParams(window.location.search);
    const vehicleId = urlParams.get('id');
    const type = urlParams.get('type');
    const name = urlParams.get('name');
    const registrationNumber = urlParams.get('registrationNumber');
    const fuelType = urlParams.get('fuelType');
    const rate = urlParams.get('rate');
    const vehicleColor = urlParams.get('color');

    // Setting input field values with the extracted data
    document.getElementById('type').value = type;
    document.getElementById('name').value = name;
    document.getElementById('registrationNumber').value = registrationNumber;
    document.getElementById('fuelType').value = fuelType;
    document.getElementById('rate').value = rate;
    document.getElementById('color').value = vehicleColor;
    // Adding event listener for the submit button
    document.getElementById('submitBtn').addEventListener('click', function() {
        // Creating an object with updated vehicle details
        const updatedVehicle = {
            id: vehicleId,
            type: document.getElementById('type').value,
            name: document.getElementById('name').value,
            registrationNumber: document.getElementById('registrationNumber').value,
            fuelType: document.getElementById('fuelType').value,
            rate: parseInt(document.getElementById('rate').value),
            vehicleColor: document.getElementById('color').value
        };
        alert(JSON.stringify(updatedVehicle));

        // If no vehicle ID is present, it means a new vehicle is being added
        if (!vehicleId) {
            createVehicle(updatedVehicle); // Call function to add vehicle
        } else {
            updateVehicle(vehicleId, updatedVehicle); // Call function to update vehicle
        }
    });

    // Adding event listener for the delete button
    document.getElementById('deleteBtn').addEventListener('click', function() {
        // Confirmation dialog before deleting
        if (confirm('confirm to delete?')) {
            const urlParams = new URLSearchParams(window.location.search);
            const vehicleId = urlParams.get('id');
            deleteVehicle(vehicleId); // Call function to delete vehicle
        }
    });

    // Adding event listener for the close button
    document.getElementById('closeButton').addEventListener('click', function() {
        window.location.href = 'AdminPage.html'; // Redirecting to AdminPage.html
    });
});

// Function to add a new vehicle
function createVehicle(newVehicle) {
    alert(newVehicle);
    fetch('http://localhost:8181/vehicles/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newVehicle)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to add vehicle');
        }
        return response.json();
    })
    .then(data => {
        console.log('Success:', data);
        alert('Vehicle added successfully!');
        window.location.href = 'index1.html'; // Redirecting to index1.html after successful addition
    })
    .catch(error => {
        console.error('Error adding vehicle:', error);
    });
}

// Function to update an existing vehicle
function updateVehicle(vehicleId, updatedVehicle) {
    const updateData = {};

    // Creating an object with updated fields
    if (updatedVehicle.type) {
        updateData.type = updatedVehicle.type;
    }
    if (updatedVehicle.name) {
        updateData.name = updatedVehicle.name;
    }
    if (updatedVehicle.registrationNumber) {
        updateData.registrationNumber = updatedVehicle.registrationNumber;
    }
    if (updatedVehicle.fuelType) {
        updateData.fuelType = updatedVehicle.fuelType;
    }
    if (updatedVehicle.rate !== undefined && !isNaN(updatedVehicle.rate)) {
        updateData.rate = parseInt(updatedVehicle.rate);
    }
    if (updatedVehicle.vehicleColor !== undefined && !isNaN(updatedVehicle.vehicleColor)) {
        updateData.vehicleColor = parseInt(updatedVehicle.vehicleColor);
    }

    // Sending a PUT request to update the vehicle
    fetch(`http://localhost:8181/vehicles/update/${vehicleId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updateData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to update vehicle');
            console.log("eror")
        }
        return response.json();
    })
    .then(data => {
        console.log('Success:', data);
        alert('Vehicle details updated successfully!');
        window.location.href = 'Adminpage.html'; // Redirecting to Adminpage.html after successful update
    })
    .catch(error => {
        console.error('Error updating vehicle details:', error);
        alert('Failed to update vehicle details');
    });
}

// Function to delete a vehicle
function deleteVehicle(vehicleId) {
    // Sending a DELETE request to delete the vehicle
    fetch(`http://localhost:8181/vehicles/delete/${vehicleId}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to delete vehicle');
        }
        alert('Vehicle deleted successfully!');
        window.location.href = 'AdminPage.html'; // Redirecting to AdminPage.html after successful deletion
    })
    .catch(error => {
        console.error('Error deleting vehicle:', error);
        alert('The Vehicle Have the Future Order Cannot be deleted');
    });
}

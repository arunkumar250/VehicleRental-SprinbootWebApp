// Function to handle the click event of the "Fetch Offers" button
document.getElementById('fetchOffersButton').addEventListener('click', function(event) {
    event.preventDefault(); // Prevent default form submission behavior

    // Retrieve rate from URL parameters
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const rate = parseInt(urlParams.get('rate'), 10);

    // Retrieve start and end time from input fields
    const startTime = new Date(document.getElementById('startTime').value);
    const endTime = new Date(document.getElementById('endTime').value);

    // Validate the selected date range
    if (!validateDate(startTime, endTime)) {
        alert("Please re-enter the date. The start date must be before the end date!");
        return;
    }

    // Calculate the number of days between start and end time
    const daysDifference = calculateDaysDifference(startTime, endTime);

    // Fetch eligible offers based on the number of days
    fetch(`http://localhost:8181/offers/eligible?days=${daysDifference}`)
        .then(response => response.json())
        .then(offers => {
            if (offers.length === 0) {
                alert('No eligible offers available for the selected date range.');
            } else {
                // Display the eligible offers in the dropdown
                displayEligibleOffers(offers);

                // Calculate and display the total amount
                const totalAmount = calculateAmount(daysDifference, rate);
                document.getElementById('totalAmount').value = totalAmount;

                // Show the confirm button
                document.getElementById('confirmButton').style.display = 'block';
            }
        })
        .catch(error => {
            console.error('Error fetching eligible offers:', error);
        });
});

// Function to calculate the number of days between two dates
function calculateDaysDifference(startDate, endDate) {
    const oneDay = 24 * 60 * 60 * 1000; // Number of milliseconds in a day
    const diffInMilliseconds = Math.abs(endDate - startDate);
    const diffInDays = Math.round(diffInMilliseconds / oneDay);
    return diffInDays;
}

// Function to validate the selected date range
function validateDate(startTime, endTime) {
    return startTime < endTime;
}

// Function to display eligible offers in the dropdown
function displayEligibleOffers(offers) {
    const offersSelect = document.getElementById('eligibleOffers');
    offersSelect.innerHTML = ''; // Clear previous options

    // Create and append options for each eligible offer
    offers.forEach(offer => {
        const option = document.createElement('option');
        option.value = offer.id;
        option.text = `${offer.name} - ${offer.discountValue}% off`;
        offersSelect.add(option);
    });
}

// Event listener for the "Confirm Ride" button
document.getElementById('confirmButton').addEventListener('click', function(event) {
    event.preventDefault(); // Prevent default form submission behavior

    // Retrieve relevant data from URL parameters and form inputs
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const userId = urlParams.get('userId');
    const vehicleId = parseInt(urlParams.get('vehicleId'), 10);
    const rate = parseInt(urlParams.get('rate'), 10);
    const customerId = userId;
    const startTime = new Date(document.getElementById('startTime').value);
    const endTime = new Date(document.getElementById('endTime').value);
    const createdDateTime = new Date();
    const daysDifference = calculateDaysDifference(startTime, endTime);
    const totalAmount = calculateAmount(daysDifference, rate);
    const selectedOfferId = document.getElementById('eligibleOffers').value;

    // Prepare data for ride request
    const rideData = {
        vehicleId: vehicleId,
        customerId: customerId,
        startTime: startTime.toISOString(),
        endTime: endTime.toISOString(),
        createdDateTime: createdDateTime.toISOString(),
        rideStatus: 'confirmed',
        totalAmount: totalAmount,
        selectedOfferId: selectedOfferId
    };

    // Send a POST request to add the ride
    fetch('http://localhost:8181/vehiclerides/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(rideData)
    })
    .then(response => response.json())
    .then(data => {
        // Handle the response and provide feedback to the user
        if (data.message) {
            document.getElementById('totalAmount').value = data.totalAmount;
            alert(`Ride confirmed successfully. Total amount: ${data.totalAmount}. You saved: ${data.savedAmount} (Actual amount was ${data.actualAmount})`);
        } else {
            throw new Error('Unexpected response format');
        }
    })
    .catch(error => {
        // Handle errors that occur during the request
        alert('Error confirming ride: ' + error.message);
    });
});

// Function to calculate the total amount based on the number of days and rate
function calculateAmount(days, rate) {
    return days * rate;
}

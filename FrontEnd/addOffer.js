document.getElementById('submitOffer').addEventListener('click', function() {
    // Gather offer data from the form
    const offerData = {
        name: document.getElementById('name').value,
        description: document.getElementById('description').value,
        discountValue: parseFloat(document.getElementById('discount_value').value),
        startDate: document.getElementById('start_date').value,
        endDate: document.getElementById('end_date').value,
        minimumDays: parseInt(document.getElementById('minimum_days').value),
        status: document.getElementById('status').value === 'true'
    };

    // Log offer data for debugging
    console.log(JSON.stringify(offerData));  // For debugging

    // Send a POST request to add the offer
    fetch('http://localhost:8181/offers/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(offerData)
    })
    .then(response => {
        if (response.ok) {
            // If successful, display success message
            alert('Offer added successfully');
        } else {
            // If there's an error, display the error message
            response.json().then(data => {
                alert('Error adding offer: ' + data.message);
            });
        }
    })
    .catch(error => {
        // Handle any network or other errors
        console.error('Error:', error);
        alert('Error adding offer');
    });
});

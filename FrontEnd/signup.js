document.addEventListener('DOMContentLoaded', () => {
    const signupForm = document.getElementById('signupForm');

    // Event listener for form submission
    signupForm.addEventListener('submit', async (event) => {
        event.preventDefault(); // Prevent default form submission behavior

        // Get form input values
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        const email = document.getElementById('email').value;
        const phoneNumber = document.getElementById('phone').value;

        // Create user object
        const user = {
            userName: username,
            password: password,
            email: email,
            phoneNumber: phoneNumber,
            role: 'customer'
        };

        // Log user object for debugging
        console.log(JSON.stringify(user));

        try {
            // Send POST request to register user
            const response = await fetch('http://localhost:8181/users/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            });

            // Check if registration was successful
            if (response.ok) {
                // Show success message and redirect to login page
                alert('Registration successful! Please login to continue.');
                window.location.href = 'index.html';
            } else {
                // Throw error if registration failed
                throw new Error('Registration failed');
            }
        } catch (error) {
            // Log and display error message if registration fails
            console.error('Registration error:', error);
            alert('Registration failed. Please try again.');
        }
    });
});

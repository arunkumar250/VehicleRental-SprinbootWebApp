document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('loginForm');

    loginForm.addEventListener('submit', async (event) => {
        event.preventDefault(); // Prevent default form submission behavior

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        const userType = new URLSearchParams(window.location.search).get('type');
       alert(JSON.stringify({ userName: username, password: password, role: userType }))
        try {
            const response = await fetch("http://localhost:8181/users/login", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ userName: username, password: password, role: userType })
            });

            if (!response.ok) {
                throw new Error('Login failed'); // Throw error for unsuccessful login
            }
            
            const user = await response.json(); // Parse response JSON
            const userId = user.id; // Extract user ID from the response
            alert(`Login successful as ${userType}`);

            // Redirect based on user type
            if (userType === 'admin') {
                window.location.href = `AdminPage.html?role=admin&username=${username}`;

            } else {
                window.location.href = `customerPage.html?id=${userId}`;
            }
        } catch (error) {
            console.error('Login error:', error);
            alert('Login failed. Please try again.');
        }
    });

    // Event listener for signup button
    document.getElementById('signupButton').addEventListener('click', () => {
        window.location.href = 'signup.html';
    });
});

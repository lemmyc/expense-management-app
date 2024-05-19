//document.getElementById('registerForm').addEventListener('submit', function(event) {
//        event.preventDefault();
//        const formData = {
//            username: document.getElementById('username').value,
//            password: document.getElementById('password').value,
//            email: document.getElementById('email').value
//        };
//
//        fetch('/api/auth/register', {
//            method: 'POST',
//            headers: {
//                'Content-Type': 'application/json'
//            },
//            body: JSON.stringify(formData)
//        })
//        .then(response => response.json())
//        .then(data => {
//            console.log('Success:', data);
//            alert('User registered successfully!');
//            window.location.href = '/login';
//        })
//        .catch((error) => {
//            alert('Registration failed!\n' + error);
//        });
//    });
document.getElementById('loginForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const formData = {
            username: document.getElementById('username').value,
            password: document.getElementById('password').value,
        };

        fetch('/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then(response => response.json())
        .then(data => {
            localStorage.setItem('token', data.token);
            alert('User logged in successfully!');
            window.location.href = '/';
        })
        .catch((error) => {
            alert('Login failed!\n' + error);
        });
    });



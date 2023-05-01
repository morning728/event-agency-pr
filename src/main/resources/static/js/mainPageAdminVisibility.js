var editBtns = document.getElementsByClassName('editBtn');
const addForm = document.getElementById('addingEventForm');

var token = sessionStorage.getItem('token');
var header = 'Bearer_' + token;

if(token){
    fetch('/api/v1/auth/checkAdmin', {
            method: 'GET',
            headers: {
                      'Authorization': header
                    }
    }).then(response => {
        if (response.ok) { // Если запрос не успешен
            Array.from(editBtns).forEach((element) => {
                // Do stuff here
                element.style.display = 'block';
            });
            addForm.style.display = 'block';
        }
    })

} else {
    location.href = '/api/v1/auth/login';
}
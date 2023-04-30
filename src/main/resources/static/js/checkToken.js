var token = sessionStorage.getItem('token');
var header = 'Bearer_' + token;
if(token){
    fetch('/api/v1/auth/check', {
            method: 'GET',
            headers: {
                      'Authorization': header
                    }
    }).then(response => {
        if (!response.ok) { // Если запрос не успешен
            location.href = '/api/v1/auth/login';
        }
    })

} else {
    location.href = '/api/v1/auth/login';
}
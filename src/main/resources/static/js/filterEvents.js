const form = document.getElementById('filter');

if (form != null) {
    form.addEventListener('submit', (event) => {
        event.preventDefault(); // Отменяем отправку формы по умолчанию

        const username = form.querySelector('#username').value;
        const password = form.querySelector('#password').value;

        // Отправляем асинхронный POST-запрос на сервер для авторизации
        fetch('/api/v1/auth/login', {
            method: 'POST',
            body: JSON.stringify({username, password}),
            headers: {'Content-Type': 'application/json'}
        }).then(response => {

            if (response.ok) { // Если запрос успешен

                return response.json(); // Парсим ответ в формате JSON
            } else {
                alert("asd");
            }
        }).then(data => {
            // Сохраняем полученный токен в localStorage или sessionStorage
            //document.cookie = `token=${data.token}`
            if(data.token){
                token = data.token;
                sessionStorage.setItem('token', token);
                alert(token);
            }
            else{
                document.getElementById("PreMessage").textContent = data.errorMsg;
            }


        }).catch(error => {
            console.error(error);
        });
    });
}
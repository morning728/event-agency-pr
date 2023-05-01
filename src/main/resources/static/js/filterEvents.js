const form = document.getElementById('sortByForm');
var eventHolder = document.getElementById('eventHolder');
var token = sessionStorage.getItem('token');
var header = 'Bearer_' + token;

if (form != null) {
    form.addEventListener('submit', (event) => {
        event.preventDefault(); // Отменяем отправку формы по умолчанию

        const type = form.querySelector('#type').value;
        const minPriceForPerson = Number(form.querySelector('#minPriceForPerson').value);
        const maxPriceForPerson = Number(form.querySelector('#maxPriceForPerson').value);
        const minMinPrice = Number(form.querySelector('#minMinPrice').value);
        const maxMinPrice = Number(form.querySelector('#maxMinPrice').value);

        // Отправляем асинхронный POST-запрос на сервер для авторизации
        fetch('/catalog/sort', {
            method: 'POST',
            body: JSON.stringify({
                type,
                minPriceForPerson,
                maxPriceForPerson,
                minMinPrice,
                maxMinPrice
            }),
            headers: {'Content-Type': 'application/json',
                    'Authorization': header
            }
        }).then(response => {

            if (response.ok) { // Если запрос успешен

                return response.json(); // Парсим ответ в формате JSON
            } else {
                alert("Invalid Data ^-^");
            }
        }).then(data => {
            // Сохраняем полученный токен в localStorage или sessionStorage
            //document.cookie = `token=${data.token}`
            changeData(data);


        }).catch(error => {
            console.error(error);
        });
    });
}

function changeData(data){
    eventHolder.innerHTML='';
    console.log(data);

    data.forEach(element => {
        const newDiv = document.createElement('div');
        const new1 = document.createElement('h4');
        new1.textContent=element.name;
        const new2 = document.createElement('p');
        new2.textContent=element.description;
        const new3 = document.createElement('p');
        new3.textContent=element.minimumPrice;
        const new4 = document.createElement('br');

        newDiv.appendChild(new1);
        newDiv.appendChild(new2);
        newDiv.appendChild(new3);
        newDiv.appendChild(new4);
        //alert(element);
        eventHolder.appendChild(newDiv);
    });
}
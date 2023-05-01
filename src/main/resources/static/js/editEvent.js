var token = sessionStorage.getItem('token');
var header = 'Bearer_' + token;

const form = document.getElementById('editForm');
const successMsg = document.getElementById('success');
const failMsg = document.getElementById('fail');

if(form != null){
    form.addEventListener('submit', (event) => {
            event.preventDefault(); // Отменяем отправку формы по умолчанию

            const id =  Number(document.getElementById('id').value);
            const name = document.getElementById('name').value;
            const description = document.getElementById('desc').value;
            const minimumPrice = Number(document.getElementById('minPr').value);
            const type = document.getElementById('type').value;
            const priceForPerson = Number(document.getElementById('PFP').value);


            const address = '/catalog/' + id + '/edit'

            // Отправляем асинхронный POST-запрос на сервер для авторизации
            fetch(address, {
                method: 'PUT',
                body: JSON.stringify({
                    id,
                    name,
                    description,
                    minimumPrice,
                    priceForPerson,
                    type
                }),
                headers: {'Content-Type': 'application/json',
                        'Authorization': header
                }
            }).then(response => {

                if (response.ok) { // Если запрос успешен
                    failMsg.style.display = 'none';
                    successMsg.style.display = 'block';
                    return response.json(); // Парсим ответ в формате JSON

                } else {
                    failMsg.style.display = 'block';
                    successMsg.style.display = 'none';
                }
            }).catch(error => {
                console.error(error);
            });
        });
    }
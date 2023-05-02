const selectFiledForm = document.getElementById('sortByChoice');



function changeSortField(){
    if(selectFiledForm.value == "typeFiled"){
        document.getElementById('type').style.display='block';
        document.getElementById('type_label').style.display='block';

        document.getElementById('minPriceForPerson_label').style.display='none';
        document.getElementById('minPriceForPerson').style.display='none';
        document.getElementById('minPriceForPerson').value=0;
        document.getElementById('maxPriceForPerson').style.display='none';
        document.getElementById('maxPriceForPerson').value=0;
        document.getElementById('maxPriceForPerson_label').style.display='none';

        document.getElementById('minMinPrice').style.display='none';
        document.getElementById('minMinPrice').value=0;
        document.getElementById('minMinPrice_label').style.display='none';
        document.getElementById('maxMinPrice').style.display='none';
        document.getElementById('maxMinPrice_label').style.display='none';
        document.getElementById('maxMinPrice').value=0;

        document.getElementById('sortBtn').style.display='block';
    } else if(selectFiledForm.value == "minPrice") {
        document.getElementById('type').style.display='none';
        document.getElementById('type_label').style.display='none';
        document.getElementById('type').value="TYPE";

        document.getElementById('minPriceForPerson_label').style.display='none';
        document.getElementById('minPriceForPerson').style.display='none';
        document.getElementById('minPriceForPerson').value=0;
        document.getElementById('maxPriceForPerson').style.display='none';
        document.getElementById('maxPriceForPerson').value=0;
        document.getElementById('maxPriceForPerson_label').style.display='none';

        document.getElementById('minMinPrice').style.display='block';
        document.getElementById('maxMinPrice').style.display='block';
        document.getElementById('minMinPrice_label').style.display='block';
        document.getElementById('maxMinPrice_label').style.display='block';

        document.getElementById('sortBtn').style.display='block';
    } else if (selectFiledForm.value == "priceForPerson"){
        document.getElementById('type').style.display='none';
        document.getElementById('type_label').style.display='none';
        document.getElementById('type').value="TYPE";


        document.getElementById('minPriceForPerson').style.display='block';
        document.getElementById('minPriceForPerson_label').style.display='block';
        document.getElementById('maxPriceForPerson').style.display='block';
        document.getElementById('maxPriceForPerson_label').style.display='block';

        document.getElementById('minMinPrice').style.display='none';
        document.getElementById('minMinPrice').value=0;
        document.getElementById('minMinPrice_label').style.display='none';
        document.getElementById('maxMinPrice').style.display='none';
        document.getElementById('maxMinPrice_label').style.display='none';
        document.getElementById('maxMinPrice').value=0;

        document.getElementById('sortBtn').style.display='block';
    }

}



const formOrd = document.getElementById('addingOrderForm');
var token = sessionStorage.getItem('token');
var header = 'Bearer_' + token;
var authorId;

if(token){
    fetch('/api/v1/users/getUsername?token=' + token, {
            method: 'GET',
            headers: {
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
          authorId = data.id;


    }).catch(error => {
        console.error(error);
    });

} else {
    location.href = '/api/v1/auth/login';
}

if (formOrd != null) {
    formOrd.addEventListener('submit', (event) => {
        event.preventDefault(); // Отменяем отправку формы по умолчанию

        const name = formOrd.querySelector('#ordName').value;
        const details = formOrd.querySelector('#ordDetails').value;
        const wantedDate = formOrd.querySelector('#ordDate').value;

        // Отправляем асинхронный POST-запрос на сервер для авторизации
        fetch('/orders/', {
            method: 'POST',
            body: JSON.stringify({
                name,
                details,
                ordDate,
                authorId
            }),
            headers: {
                    'Content-Type': 'application/json',
                    'Authorization': header
            }
        }).then(response => {

            if (response.ok) { // Если запрос успешен

                alert("Your order was saved!");
                location.href="/catalog/";
            } else {
                alert("Invalid Data ^-^");
            }
        }).catch(error => {
            console.error(error);
        });
    });
}
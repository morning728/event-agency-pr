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
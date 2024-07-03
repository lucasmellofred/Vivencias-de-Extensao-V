function showAdditionalFields(selectedValue) {
    var additionalFieldsDiv = document.getElementById('additionalFields');
    var infoAdicionalLabel = document.getElementById('labelAdicional');
    var infoAdicionalInput = document.getElementById('infoAdicional');

    if (selectedValue === 'Alimento') {
        infoAdicionalLabel.innerText = 'Quantidade em quilos (ex: 1,5kg):';
        infoAdicionalInput.setAttribute('placeholder', '1,5kg');
        additionalFieldsDiv.style.display = 'block';
    } else if (selectedValue === 'Vestimentas') {
        infoAdicionalLabel.innerText = 'Tipo de Vestimenta:';
        infoAdicionalInput.setAttribute('placeholder', 'Ex: Roupas, Cobertores');
        additionalFieldsDiv.style.display = 'block';
    } else if (selectedValue === 'Produtos') {
        infoAdicionalLabel.innerText = 'Tipo de Produto:';
        infoAdicionalInput.setAttribute('placeholder', 'Ex: Higiene, Produtos de limpeza');
        additionalFieldsDiv.style.display = 'block';
    } else {
        additionalFieldsDiv.style.display = 'none';
    }
}

function openTab(event, tabId) {
    var i, tabcontent, tablinks;

    tabcontent = document.getElementsByClassName("tab-content");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    tablinks = document.getElementsByClassName("tab-link");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    document.getElementById(tabId).style.display = "block";
    event.currentTarget.className += " active";
}

function clearForm() {
    document.querySelector('form').reset();
}

function carregarlistaagencia() {
    var usuariologado = localStorage.getItem("logado");

    fetch("http://localhost:8080/agencia/listar")
        .then(res => res.json())
        .then(res => preenchercombo(res));
}

function preenchercombo(lista){
    var combo = "<option selected>Selecione a Agencia</option>";

    for (contador=0; contador<lista.length; contador++)
    {
        combo += 
        "<option value='" + lista[contador].id + "'>" + lista[contador].numero + " - " + lista[contador].nome + "</option>";
    }

    document.getElementById("cmbagencia").innerHTML = combo;
}
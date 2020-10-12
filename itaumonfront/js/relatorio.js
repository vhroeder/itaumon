function carregarlistaagencia() {
    var usuariologado = localStorage.getItem("logado");

    fetch("http://localhost:8080/agencia/listar")
        .then(res => res.json())
        .then(res => preenchercombo(res));
}

function preenchercombo(lista) {
    var combo = "<option selected>Selecione o Filtro</option>";
    combo += "<option value='0'>TODOS OS FERIADOS</option>";

    for (contador = 0; contador < lista.length; contador++) {
        combo +=
            "<option value='" + lista[contador].id + "'>" + lista[contador].numero + " - " + lista[contador].nome + "</option>";
    }

    document.getElementById("cmbagencia").innerHTML = combo;
}

function filtrar() {
    var filtro = document.getElementById("cmbagencia").value;

    if (isNaN(filtro)) {
        document.getElementById("cmbagencia").focus();
        window.alert("Selecione uma opção de Filtro !!");
        return;
    }

    if (filtro != 0) {
        fetch("http://localhost:8080/agencia/consultar/" + filtro)
            .then(res => res.json())
            .then(res => preenchertabela1(res))
            .catch(err => {
                window.alert("Erro ao recuperar os Feriados !!");
            });    
    }
    else {
        fetch("http://localhost:8080/feriado/listar")
            .then(res => res.json())
            .then(res => preenchertabela2(res))
            .catch(err => {
                window.alert("Erro ao recuperar os Feriados !!");
            });    
    }

}
function preenchertabela1(lista) {

    if (lista.feriados.length == 0)
    {
        window.alert("Nao existem Feriados para o filtro selecionado !!");
    }

    var tabela = "";

    for (contador = 0; contador < lista.feriados.length; contador++) {
        tabela +=
            "<tr>" +
                  "<td>" + lista.feriados[contador].dataInicio + " - " + lista.feriados[contador].dataFim + 
            "</td> <td>" + lista.feriados[contador].nome + 
            "</td> <td>" + lista.nome + 
            "</td> </tr>";
    }

    document.getElementById("tabferiados").innerHTML = tabela;
}
function preenchertabela2(lista) {

    if (lista.length == 0)
    {
        window.alert("Nao existem Feriados para o filtro selecionado !!");
    }

    var tabela = "";

    for (contador = 0; contador < lista.length; contador++) {
        tabela +=
            "<tr>" +
                  "<td>" + lista[contador].dataInicio + " - " + lista[contador].dataFim + 
            "</td> <td>" + lista[contador].nome + 
            "</td> <td>" + lista[contador].agencia.nome + 
            "</td> </tr>";
    }

    document.getElementById("tabferiados").innerHTML = tabela;
}
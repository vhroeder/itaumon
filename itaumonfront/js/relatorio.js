function carregarlistaagencia() {
    var usuariologado = localStorage.getItem("logado");

    fetch("http://localhost:8080/agencia/listar")
        .then(res => res.json())
        .then(res => preenchercombo(res));
}

function preenchercombo(lista) {
    var combo = "<option selected>Selecione a Agência</option>";
    
    for (contador = 0; contador < lista.length; contador++) {
        combo +=
            "<option value='" + lista[contador].id + "'>" + lista[contador].id + " - " + lista[contador].nome + "</option>";
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

    fetch("http://localhost:8080/agencia/consultar/" + filtro)
            .then(res => res.json())
            .then(res => preenchertabelalocais(res))
            .catch(err => {
                window.alert("Erro ao recuperar os Feriados !!");
            }); 
}
function preenchertabelalocais(lista) {

    if (lista.feriados.length == 0)
    {
        window.alert("Essa agência não tem feriados locais !!");
    }

    var tabela = "";
    for (contador = 0; contador < lista.feriados.length; contador++) {
        tabela +=
            "<tr>" +
                  "<td>" + lista.feriados[contador].dataInicio + " - " + lista.feriados[contador].dataFim + 
            "</td> <td>" + lista.feriados[contador].nome + 
            "</td> <td>" + lista.id + " - " + lista.nome + 
            "</td> </tr>";
    }


    fetch("http://localhost:8080/feriado/listarnacionais")
            .then(res => res.json())
            .then(res => preenchertabelanacionais(res, tabela))
            .catch(err => {
                window.alert("Erro ao recuperar os Feriados !!");
            });
}
function preenchertabelanacionais(lista, tabelalocal) {

    if (lista.length == 0)
    {
        window.alert("Essa agência não tem feriados nacionais !!");
    }

    for (contador = 0; contador < lista.length; contador++) {
        tabelalocal +=
            "<tr>" +
                  "<td>" + lista[contador].dataInicio + " - " + lista[contador].dataFim + 
            "</td> <td>" + lista[contador].nome + 
            "</td> <td> Todas as Agências </td> </tr>";
    }

    document.getElementById("tabferiados").innerHTML = tabelalocal;
}
function carregarlistaagencia() {
    var usuariologado = localStorage.getItem("logado");

    fetch("http://localhost:8080/agencia/listar")
        .then(res => res.json())
        .then(res => preenchercombo(res));
}

function preenchercombo(lista) {
    var combo = "";

    for (contador = 0; contador < lista.length; contador++) {
        combo +=
            "<option value='" + lista[contador].id + "'>" + lista[contador].id + " - " + lista[contador].nome + "</option>";
    }

    document.getElementById("cmbagencia").innerHTML = combo;
}
function cadastrarferiado() {
    var feriado = document.getElementById("txtnomeferiado").value.trim().toUpperCase();
    var datainicio = document.getElementById("txtdatainicioferiado").value;
    var datafim = document.getElementById("txtdatafimferiado").value;
    var idagencia = document.getElementById("cmbagencia").value;

    if (idagencia == "" && document.getElementById("todasasagencias").checked == false) {
        document.getElementById("cmbagencia").focus();
        window.alert("Selecionar TODAS ou uma Agencia especifica !!");
        return;
    }

    document.getElementById("txtnomeferiado").value = feriado;

    if (is_empty(feriado)) {
        document.getElementById("txtnomeferiado").focus();
        window.alert("Informar o nome do Feriado !!");
        return;
    }

    if (!validadata(datainicio))
    {
        document.getElementById("txtdatainicioferiado").focus();
        window.alert("Informe uma data no padrão DD/MM/YYYY");
        return;
    }
    if (!validadata(datafim))
    {
        document.getElementById("txtdatafimferiado").focus();
        window.alert("Informe uma data no padrão DD/MM/YYYY");
        return;
    }

    datainicio = datainicio.replace(".", "/");
    datainicio = datainicio.replace(".", "/");
    datafim = datafim.replace(".", "/");
    datafim = datafim.replace(".", "/");

    var dateinicio = new Date(datainicio.split('/').reverse().join('/'));
    var datefim = new Date(datafim.split('/').reverse().join('/'));

    if (dateinicio > datefim){
        document.getElementById("txtdatainicioferiado").focus();
        window.alert("Data Inicio nao pode ser maior que Data Fim !!");
        return;
    }

    var carta = {};
    var envelope = {};

    if (document.getElementById("todasasagencias").checked == true){ //se está selecionado todas as agencias, considera-se feriado nacional
        carta = {
            "nome": feriado,
            "dataInicio": datainicio,
            "dataFim": datafim,
            "nacional": true
        };
        envelope = {
            method: "POST",
            body: JSON.stringify(carta),
            headers: {
                "Content-type": "application/json"
            }
        };
        fetch("http://localhost:8080/feriado/incluir", envelope)
        .then(res => res.json())
        .then(res => {
            window.location = "cadastro.html";
            window.alert("Feriado gravado com sucesso !!");
        })
        .catch(err => {
            window.alert("Erro ao gravar o feriado !");
        });
        alert("Cadastrado feriado nacional");
    }else{ //se tem agência(s) selecionada(s), cadastra apenas para aquela agência
        var listadeagencias = $('#cmbagencia').val();
        for (contador = 0; contador < listadeagencias.length; contador++){
            carta = {
                "nome": feriado,
                "dataInicio": datainicio,
                "dataFim": datafim,
                "nacional": false,
                "agencia": {"id": listadeagencias[contador]}
            };
            envelope = {
                method: "POST",
                body: JSON.stringify(carta),
                headers: {
                    "Content-type": "application/json"
                }
            };
            fetch("http://localhost:8080/feriado/incluir", envelope)
            .then(res => res.json())
            .then(res => {
                window.location = "cadastro.html";
                window.alert("Feriado gravado com sucesso !!");
            })
            .catch(err => {
                window.alert("Erro ao gravar o feriado !");
        });
            alert("Cadastrado feriado regional n°" + contador+1);
        }
    }
}
function is_empty(e) {
    switch (e) {
        case "":
        case null:
        case typeof (e) == "undefined":
            return true;
        default:
            return false;
    }
}
function validadata(data) {
    reg = /[^\d\/\.]/gi;                  // Mascara = dd/mm/aaaa | dd.mm.aaaa
    var valida = data.replace(reg, '');    // aplica mascara e valida só numeros
    if (valida && valida.length == 10) {  // é válida, então ;)
        var ano = data.substr(6),
            mes = data.substr(3, 2),
            dia = data.substr(0, 2),
            M30 = ['04', '06', '09', '11'],
            v_mes = /(0[1-9])|(1[0-2])/.test(mes),
            v_ano = /(19[1-9]\d)|(20\d\d)|2100/.test(ano),
            rexpr = new RegExp(mes),
            fev29 = ano % 4 ? 28 : 29;

        if (v_mes && v_ano) {
            if (mes == '02') return (dia >= 1 && dia <= fev29);
            else if (rexpr.test(M30)) return /((0[1-9])|([1-2]\d)|30)/.test(dia);
            else return /((0[1-9])|([1-2]\d)|3[0-1])/.test(dia);
        }
    }
    return false                           // se inválida :(
}
//funcções para cadastro de agências
function bloquearselecao(){
    var checkbox = document.getElementById("todasasagencias");
    if (checkbox.checked == true){
        document.getElementById("cmbagencia").selectedIndex = -1;
        document.getElementById("cmbagencia").disabled = true;
    }else{
        document.getElementById("cmbagencia").disabled = false;
    }
}

/*function testevalorlista(){
    var listadeagencias = $('#cmbagencia').val()
    alert(listadeagencias);
    for (contador = 0; contador < listadeagencias.length; contador++){
        alert(listadeagencias[contador]);
    }
}*/
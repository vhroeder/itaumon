function carregarindex(){
    localStorage.clear();
}

function logar() {
    var usuario = document.getElementById("txtusuario").value.trim().toUpperCase();
    var chave = "";

    document.getElementById("txtusuario").value = usuario;
    document.getElementById("txtusuario").focus();

    if (is_empty(usuario)) {
        window.alert("Informe sua RACF ou Funcional !!");
        return;
    }

    if (isNaN(usuario)) {
        if (usuario.length != 7) {
            window.alert("Informe 7 caracteres para RACF");
            return;
        }
        else {
            chave = "racf";
        }
    }
    else {
        if (usuario.length != 9) {
            window.alert("Informe 9 caracteres para Funcional");
            return;
        }
        else {
            chave = "funcional";
        }
    }

    var senha = document.getElementById("txtsenha").value;
    document.getElementById("txtsenha").focus();

    if (is_empty(senha) || senha.length < 8) {
        window.alert("Informe uma senha com pelo menos 8 caracteres !!");
        return;
    }

    var carta = null;

    if (chave == "racf") {
        var carta = {
            racf: usuario,
            senha: senha
        }
    }
    else{
        var carta = {
            funcional: usuario,
            senha: senha
        }        
    }

    var envelope = {
        method: "POST",
        body: JSON.stringify(carta),
        headers: {
            "Content-type": "application/json" 
        }
    }

    fetch("http://localhost:8080/usuario/login", envelope)
    .then(res => res.json())
    .then(res => {
        localStorage.setItem("logado", JSON.stringify(res));
        window.location = "selecao.html"
    })
    .catch(err => {
        window.alert("Erro no procedimento de Login !");
    })
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
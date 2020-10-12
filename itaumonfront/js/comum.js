function carregarusuario() {

    var usuariologado = localStorage.getItem("logado");

    if (usuariologado == null) {
        window.location = "index.html";
    }

    var usuariojson = JSON.parse(usuariologado);

    document.getElementById("fotousuario").innerHTML = "<img width='80%' src='images/" + usuariojson.foto + "'>";
    document.getElementById("txtnomeusuario").innerHTML = "<h4>" + usuariojson.nome + "</h4>";
    document.getElementById("txtracfusuario").innerHTML = "<h4>" + usuariojson.racf + "</h4>";
    document.getElementById("txtfuncionalusuario").innerHTML = "<h4>" + usuariojson.funcional + "</h4>";
    document.getElementById("txtemailusuario").innerHTML = "<h4>" + usuariojson.email + "</h4>";


}
function logout() {
    localStorage.clear();
    window.location = "index.html";
}


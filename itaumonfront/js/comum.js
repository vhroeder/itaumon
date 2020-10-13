function carregarusuario() {

    var usuariologado = localStorage.getItem("logado");

    if (usuariologado == null) {
        window.location = "index.html";
    }

    var usuariojson = JSON.parse(usuariologado);

    document.getElementById("fotousuario").innerHTML = "<img width='75%' src='images/" + usuariojson.foto + "'>";
    document.getElementById("txtnomeusuario").innerHTML = "" + usuariojson.nome + "";
    document.getElementById("txtracfusuario").innerHTML = "" + usuariojson.racf + "";
    document.getElementById("txtfuncionalusuario").innerHTML = "" + usuariojson.funcional + "";
    document.getElementById("txtemailusuario").innerHTML = "" + usuariojson.email + "";


}
function logout() {
    localStorage.clear();
    window.location = "index.html";
}


$(document).ready(function(){
    //On ready
});

async function iniciarSesion(){

    //Capturar Data de los inputs
    let data = {};
    data.email = document.getElementById("txtEmail").value;
    data.password = document.getElementById("txtPassword").value;

    //Peticion a Server
        const request = await fetch('api/login', {
            method:'POST',
            headers:{
            'Accept':'application/json',
            'Content-Type':'application/json'
            },
            body: JSON.stringify(data)
        });
        const res = await request.text();

        if(res != 'FAIL'){
            alert("Credenciales correctas");
            localStorage.token = res;
            localStorage.email = data.email;
            window.location.href = "usuarios.html";
        }else{
            alert("Las credenciales son incorrectas, intentalo de nuevo.");
        }
        //console.log(res);
}
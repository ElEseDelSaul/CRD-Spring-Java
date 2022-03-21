$(document).ready(function () {
    //on Ready
});

async function registarUsuario(){
    let data = {};
    data.nombre = document.getElementById("txtNombre").value;
    data.apellidos = document.getElementById("txtApellidos").value;
    data.telefono = document.getElementById("txtTelefono").value;
    data.email = document.getElementById("txtEmail").value;
    data.password = document.getElementById("txtPassword").value;

    let repeatPassword = document.getElementById("txtRepeatPassword").value;

    //Evaluar coincidencia de password
    if( data.password != repeatPassword ){
        alert("Las cotrase;as no coinciden , intentalo de nuevo.");
        data.password = '';
        repeatPassword = '';
        return;
    }

    //Mostrar objeto por consola
    //console.log(data);
    if(data != null){
        try{
            const request = await fetch('api/usuarios', {
                method: 'POST',
                headers: {
                    'Accept':'application/json',
                    'Content-Type':'application/json'
                },
                body: JSON.stringify(data)
            });
            //const res = await request.json();
            //console.log(res);
            alert("Cuenta creada con exito!");
            window.location.href = "login.html";
        }catch(err){
            console.error("Error: "+err)
        }
    }
}
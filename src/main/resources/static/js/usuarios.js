// Call the dataTables jQuery plugin
$(document).ready(function() {
    document.getElementById('txtEmailUser').outerHTML = localStorage.email;
    cargarUsuarios();
  $('#usuarios').DataTable();
});

function getHeaders(){
    return {
                 'Accept': 'application/json',
                 'Content-Type': 'application/json',
                 'Authorization': localStorage.token
    };
}

//Realizar peticion Get a la ruta usuarios
async function cargarUsuarios(){
  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers: getHeaders()
  });
  const usuarios = await request.json();    //Dar formato Json a la data

  console.table(usuarios);

    let listadoUsuariosHtml = '';
    for (usuario of usuarios){  //Generar estructura para cada unico usuario
    let tel = usuario.telefono == null ? ' - ' : usuario.telefono;

    let btnDelete = '<a onclick="deleteUser(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
    let usuarioHtml =
    '<tr><td>'+ usuario.id +'</td><td>'+ usuario.nombre +' '
    + usuario.apellidos +'</td><td>'+ usuario.email +'</td><td>'
    + tel +'</td><td>' + btnDelete + '</td></tr>';

    listadoUsuariosHtml += usuarioHtml; //Agregar todos los registros a esta variable
    }

  document.querySelector('#usuarios tbody').outerHTML = listadoUsuariosHtml;    //Mostrar todo el listado de usuarios al tbody
}

async function deleteUser(id){
    try{
    if( !confirm("Are you sure to delete this User?") ){
        return;
    }

    const res = await fetch('api/usuarios/'+id, {
            method: 'DELETE',
            headers: getHeaders()
          });
          console.log("Usuario No . "+id+" eliminado.");

          //Recargar pagina
          location.reload();

    }catch(err){
        console.log("Error: "+err);
    }
}
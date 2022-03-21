package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UsuarioController{

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value="saludo")
    public String Saludo(){ return "Hola soy Saludo"; }

    //Validacion de Token
    public boolean validarToken(String token){
        String userId = jwtUtil.getKey(token);
        return userId != null;  //Retorna Token si no es nullo
    }

    //Agregar Usuario
    @RequestMapping(value="api/usuarios", method= RequestMethod.POST)
    public void addUsuario(@RequestBody Usuario user){ //Capturar Usuario del Body
        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id); //Inicializando encriptador
        String hash = argon.hash(1,1024,1,user.getPassword());  //Proceso de encriptacion
        user.setPassword(hash); //Establecer password encriptada
        usuarioDao.addUsuario(user);
    }

    //Obtener Lista de Usuarios
    @RequestMapping(value="api/usuarios")   //Header                        //Value
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token){
        /*
        List<Usuario> usuarios = new ArrayList<>();

        Usuario user = new Usuario();
        user.setId(1);
        user.setNombre("Rodrigo");
        user.setApellidos("Maldonado Perez");
        user.setEmail("maldonado@gmail.com");
        user.setTelefono(654987123L);

        Usuario user2 = new Usuario();
        user2.setId(2);
        user2.setNombre("Maria");
        user2.setApellidos("Garcia Ocampo");
        user2.setEmail("Ocampo@gmail.com");
        user2.setTelefono(654789125L);

        Usuario user3 = new Usuario();
        user3.setId(3);
        user3.setNombre("Pedro");
        user3.setApellidos("Mendoza Carbajal");
        user3.setEmail("Pepito@gmail.com");
        user3.setTelefono(9874561235L);

        usuarios.add(user);
        usuarios.add(user2);
        usuarios.add(user3);

        return usuarios;
        */
        if( !validarToken(token) ){ return null; }

        return usuarioDao.getUsuarios();
    }

    //Obtener usuario
    @RequestMapping(value="api/usuarios/{id}", method= RequestMethod.GET)   //Obtener un Usuario
    public Usuario getUsuario(@RequestHeader(value="Authorization") String token, @PathVariable int id){

        if( !validarToken(token)) { return null; }

        Usuario user = new Usuario();

        user.setId(id);
        user.setNombre("Saul");
        user.setApellidos("Medina Gardu;o");
        user.setEmail("elesedelsaul@gmail.com");
        user.setPassword("password123");
        user.setTelefono(6549871230L);

        return user;
    }

    //Eliminar Usuario
    @RequestMapping(value="api/usuarios/{id}", method = RequestMethod.DELETE)
    public void deleteUsuario(@RequestHeader(value="Authorization") String token, @PathVariable int id) {
        if( !validarToken(token) ){ return; }
        usuarioDao.deleteUsuario(id);
    }

}

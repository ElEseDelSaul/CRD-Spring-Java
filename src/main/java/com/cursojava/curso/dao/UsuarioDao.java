package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;

import java.util.List;

public interface UsuarioDao {//Interface = Especifica qué se debe hacer, pero no cómo hacerlo

    List<Usuario> getUsuarios();

    void deleteUsuario(int id);

    void addUsuario(Usuario user);

    Usuario usuarioVerificado(Usuario usuario);
}

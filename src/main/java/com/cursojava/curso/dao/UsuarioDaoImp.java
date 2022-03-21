package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //Interactuar con el repositorio de la DB
@Transactional  //Trabajar con las consultas a la DB
public class UsuarioDaoImp implements UsuarioDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";//Haciendo referencia a nuestra clase de JAVA no a la DB
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteUsuario(int id) {
        Usuario user = entityManager.find(Usuario.class, id);//Obtener id
        entityManager.remove(user); //Remover
    }

    @Override
    public void addUsuario(Usuario user) {
        entityManager.merge(user); // .merge = require el @GeneratedValue(strategy=GenerationType.IDENTITY)
    }

    @Override
    public Usuario usuarioVerificado(Usuario usuario){
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> list = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if(list.isEmpty()){
            return null;
        }

        String passwordDB = list.get(0).getPassword();  //Obtener la contrase;a de la DB

        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if ( argon.verify(passwordDB, usuario.getPassword()) ){ //Comparar Hash
                return list.get(0);
        }else{
            return null;
        }


        //return !list.isEmpty();
    }
}

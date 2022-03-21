package com.cursojava.curso.models;

//Apoyandonos de lombok para no trabajar con metodos Getters y Setters tradicionales en la clase
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="usuarios") //Hacer referencia al nombre de la Tabla en la DB
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter @Column(name="id")
    private int id;

    @Getter @Setter @Column(name="nombre")
    private String nombre;

    @Getter @Setter @Column(name="apellidos")
    private String apellidos;

    @Getter @Setter @Column(name="telefono")
    private Long telefono;

    @Getter @Setter @Column(name="email")
    private String email;

    @Getter @Setter @Column(name="password")
    private String password;

/*
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellidos(){
        return apellidos;
    }
    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public Long getTelefono(){
        return telefono;
    }
    public void setTelefono(Long telefono){
        this.telefono = telefono;
    }
*/
}

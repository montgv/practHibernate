package Clases;

import jakarta.persistence.*;

//Indicamos a la clase que es una entidad
@Entity
//Asociamos con una tabla de la base de datos
@Table(name = "hospital", schema = "bd_hospital")
public class HospitalEntidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Indica que es una clave primaria y lo indentifica con la columna en la base de datos
    @Id
    @Column(name = "codigo")
    private int codigo;
    //Indica que no es una clave primaria y lo indentifica con la columna en la base de datos
    @Basic
    @Column(name = "nombre")
    private String nombre;
    //Indica que no es una clave primaria y lo indentifica con la columna en la base de datos
    @Basic
    @Column(name = "localidad")
    private String localidad;
    //Indica que no es una clave primaria y lo indentifica con la columna en la base de datos
    @Basic
    @Column(name = "telefono")
    private String telefono;
    //Indica que no es una clave primaria y lo indentifica con la columna en la base de datos
    @Basic
    @Column(name = "numero_camas")
    private Integer numeroCamas;

    //Creamos un constructor vacio
    public HospitalEntidad() {
    }
    //Creamos un constructor con todos los atributos
    public HospitalEntidad(int codigo, String nombre, String localidad, String telefono, Integer numeroCamas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.localidad = localidad;
        this.telefono = telefono;
        this.numeroCamas = numeroCamas;
    }
    //Generamos los getters y los setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getNumeroCamas() {
        return numeroCamas;
    }

    public void setNumeroCamas(Integer numeroCamas) {
        this.numeroCamas = numeroCamas;
    }

    //Generamos el metodo equals y hashCode para la comparacion
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HospitalEntidad that = (HospitalEntidad) o;

        if (codigo != that.codigo) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (localidad != null ? !localidad.equals(that.localidad) : that.localidad != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) return false;
        if (numeroCamas != null ? !numeroCamas.equals(that.numeroCamas) : that.numeroCamas != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (localidad != null ? localidad.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (numeroCamas != null ? numeroCamas.hashCode() : 0);
        return result;
    }
}

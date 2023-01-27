package Clases;

import jakarta.persistence.*;

@Entity
@Table(name = "medico", schema = "bd_hospital")
public class MedicoEntidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Indica que es una clave primaria y lo indentifica con la columna en la base de datos
    @Id
    @Column(name = "medico_cod")
    private int medicoCod;
    //Indica que no es una clave primaria y lo indentifica con la columna en la base de datos
    @Basic
    @Column(name = "nombre")
    private String nombre;
    //Indica que no es una clave primaria y lo indentifica con la columna en la base de datos
    @Basic
    @Column(name = "apellido")
    private String apellido;
    //Indica que no es una clave primaria y lo indentifica con la columna en la base de datos
    @Basic
    @Column(name = "especialidad")
    private String especialidad;
    //Indica que no es una clave primaria y lo indentifica con la columna en la base de datos
    @Basic
    @Column(name = "codigo_hospital", insertable = false, updatable = false)
    private int codigoHospital;
    //Creamos un constructor vacio
    public MedicoEntidad() {
    }
    //Creamos un constructor con todos los atributos
    public MedicoEntidad(int medicoCod, String nombre, String apellido, String especialidad, int codigoHospital, HospitalEntidad hospitalByCodigo) {
        this.medicoCod = medicoCod;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.codigoHospital = codigoHospital;
        this.hospitalByCodigo = hospitalByCodigo;
    }
    //Identifica el tipo de relacion de las tablas, que es una clave foranea
    @ManyToOne
    //Creamos la asociacion entre las tablas
    @JoinColumn(name = "codigo_hospital", referencedColumnName = "codigo", nullable = false)
    private HospitalEntidad hospitalByCodigo;

    //Generamos los getters y los setters
    public int getMedicoCod() {
        return medicoCod;
    }

    public void setMedicoCod(int medicoCod) {
        this.medicoCod = medicoCod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getCodigoHospital() {
        return codigoHospital;
    }

    public void setCodigoHospital(int codigoHospital) {
        this.codigoHospital = codigoHospital;
    }

    //Generamos el metodo equals y hashCode para la comparacion
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicoEntidad that = (MedicoEntidad) o;

        if (medicoCod != that.medicoCod) return false;
        if (codigoHospital != that.codigoHospital) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (apellido != null ? !apellido.equals(that.apellido) : that.apellido != null) return false;
        if (especialidad != null ? !especialidad.equals(that.especialidad) : that.especialidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = medicoCod;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellido != null ? apellido.hashCode() : 0);
        result = 31 * result + (especialidad != null ? especialidad.hashCode() : 0);
        result = 31 * result + codigoHospital;
        return result;
    }
}

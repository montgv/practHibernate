import Clases.HospitalEntidad;
import Clases.MedicoEntidad;
import jakarta.persistence.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    //Creamos EntityManagerFactory que se encarga de abrir la conexion a la base de datos
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistencia");
    //Creamos EntityManager que se encarga de buscar la entidad en su contexto de persistencia y actualiar los valores
    static EntityManager entityManager = entityManagerFactory.createEntityManager();
    //Creamos EntityTransaction que se encarga de gestionar las transacciones
    static EntityTransaction transaction = entityManager.getTransaction();
    //Creamos Scanner que nos permite obtener la entrada de datos primitivos
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;
        //Creamos un bucle para que se repita el menu hasta que seleccionemos la opcion de salir
        while (!salir) {
            System.out.println("");
            System.out.println("==========MENU==========");
            System.out.println("1. Insertar un registro para la tabla hospital.");
            System.out.println("2. Buscar un registro de la tabla hospital.");
            System.out.println("3. Actualizar un registro de la tabla hospital.");
            System.out.println("4. Borrar un registro de la tabla hospital.");
            System.out.println("5. Insertar un registro para la tabla medico.");
            System.out.println("6. Buscar un registro de la tabla medico.");
            System.out.println("7. Actualizar un registro de la tabla medico.");
            System.out.println("8. Borrar un registro de la tabla medico.");
            System.out.println("0. Salir.");
            System.out.print("Introduce la opcion que deseas realizar: ");
            int opcion = sc.nextInt();
            sc.nextLine();
            //Creamos un switch con las diferentes opciones y la funcion de que realiza cada una
            switch (opcion) {
                case 1:
                    insertarHospital();
                    break;
                case 2:
                    buscarHospital();
                    break;
                case 3:
                    listarHospitales();
                    break;
                case 4:
                    modificarHospital();
                    break;
                case 5:
                    borrarHospital();
                    break;
                case 6:
                    insertarMedico();
                    break;
                case 7:
                    buscarMedico();
                    break;
                case 8:
                    listarMedicos();
                    break;
                case 9:
                    modificarMedico();
                    break;
                case 10:
                    borrarMedico();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("No has introducido una opcion correcta.");
            }
        }
        //Cerramos lo que nos daba conexion con la base de datos
        entityManager.close();
        entityManagerFactory.close();
    }
    //Funcion que nos permite insertar un registro a la tabla hospital
    private static void insertarHospital() {
        String nombreHospital, localidad, telefono;
        int codigo, numero_camas;
        //Pedimos los datos por pantalla
        System.out.println("Introduce el codigo del hospital: ");
        codigo = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduce el nombre del hospital: ");
        nombreHospital = sc.nextLine();
        System.out.println("Introduce la localidad del hospital: ");
        localidad = sc.nextLine();
        System.out.println("Introduce el telefono del hospital: ");
        telefono = sc.nextLine();
        System.out.println("Introduce el numero de camas totales del hospital: ");
        numero_camas = sc.nextInt();
        sc.nextLine();
        //Creamos un objeto hospital con los datos
        HospitalEntidad hospital = new HospitalEntidad(codigo, nombreHospital, localidad, telefono, numero_camas);
        //Comienza la transaccion
        transaction.begin();
        //Almacenamos el objeto
        entityManager.merge(hospital);
        //Se vuelca en la base de datos
        transaction.commit();

        System.out.println("Hospital insertado.");
    }
    //Funcion que nos permite buscar un registro de la tabla hospital
    private static void buscarHospital() {
        HospitalEntidad hospital;
        //Pedimos los datos por pantalla
        System.out.println("Introduce el codigo del hospital que deseas buscar: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        //Comienza la transaccion
        transaction.begin();
        //Guardamos el objeto hospital que ha buscado en la base de datos con el codigo introducido
        hospital = entityManager.find(HospitalEntidad.class, codigo);
        //Si no es nulo muestra el hospital y sino un mensaje de no existe
        if (hospital != null) {
            System.out.println(hospital);
        } else {
            System.out.println("No existe ese hospital.");
        }
        //Se vuelca en la base de datos
        transaction.commit();
    }
    //Funcion que nos permite listar todos los registro de la tabla hospital
    private static void listarHospitales() {
        //Comienza la transaccion
        transaction.begin();
        //Buscamos en la base de datos todos los objetos, los guardamos en una lista y lo mostramos por pantalla
        List<HospitalEntidad> listaHospitales;
        Query query = entityManager.createQuery("SELECT codigo FROM HospitalEntidad ");
        listaHospitales = query.getResultList();
        for (HospitalEntidad h : listaHospitales) {
            System.out.println(h.toString());
        }
        //Se vuelca en la base de datos
        transaction.commit();
    }
    //Funcion que nos permite modificar un registro a la tabla hospital
    private static void modificarHospital() {
        String nombreHospital, localidad, telefono;
        int codigo, numero_camas;
        //Pedimos los datos por pantalla
        System.out.println("Introduce el codigo del hospital que deseas modificar: ");
        codigo = sc.nextInt();
        sc.nextLine();
        //Comienza la transaccion
        transaction.begin();
        //Guardamos el objeto hospital que ha buscado en la base de datos con el codigo introducido
        HospitalEntidad hospitalEncontrar = entityManager.find(HospitalEntidad.class, codigo);
        //Si no es nulo modificamos los datos del hospital y sino un mensaje de no existe
        if (hospitalEncontrar != null) {
            System.out.println("Introduce el nombre del hospital: ");
            nombreHospital = sc.nextLine();
            System.out.println("Introduce la localidad del hospital: ");
            localidad = sc.nextLine();
            System.out.println("Introduce el telefono del hospital: ");
            telefono = sc.nextLine();
            System.out.println("Introduce el numero de camas totales del hospital: ");
            numero_camas = sc.nextInt();
            sc.nextLine();
            //Creamos un objeto hospital nuevo con los datos nuevos introducidos
            HospitalEntidad hospital = new HospitalEntidad(codigo, nombreHospital, localidad, telefono, numero_camas);
            //Guardamos el objeto
            entityManager.merge(hospital);
        } else {
            System.out.println("El hospital no existe.");
        }
        //Se vuelca en la base de datos
        transaction.commit();
        System.out.println("Hospital modificado.");
    }
    //Funcion que nos permite borrar un registro a la tabla hospital
    private static void borrarHospital() {
        int codigo;
        //Pedimos los datos por pantalla
        System.out.println("Introduce el codigo del hospital que deseas borrar: ");
        codigo = sc.nextInt();
        sc.nextLine();
        //Comienza la transaccion
        transaction.begin();
        //Guardamos el objeto hospital que ha buscado en la base de datos con el codigo introducido
        HospitalEntidad hospitalEncontrar = entityManager.find(HospitalEntidad.class, codigo);
        //Si no es nulo borra el hospital y sino un mensaje de no existe
        if (hospitalEncontrar != null) {
            entityManager.remove(hospitalEncontrar);

            System.out.println("Hospital eliminado.");
        } else {
            System.out.println("El hospital no existe.");
        }
        //Se vuelca en la base de datos
        transaction.commit();
    }
    //Funcion que nos permite insertar un registro a la tabla medico
    private static void insertarMedico() {
        int medico_cod, codigo_hospital;
        String nombreMedico, apellido, especialidad;
        //Pedimos los datos por pantalla
        System.out.println("Introduce el codigo del medico: ");
        medico_cod = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduce el nombre del medico: ");
        nombreMedico = sc.nextLine();
        System.out.println("Introduce el apellido del medico: ");
        apellido = sc.nextLine();
        System.out.println("Introduce la especialidad del medico: ");
        especialidad = sc.nextLine();
        System.out.println("Introduce el codigo del hospital: ");
        codigo_hospital = sc.nextInt();
        sc.nextLine();
        //Creamos un objeto medico con los datos
        MedicoEntidad medico = new MedicoEntidad(medico_cod, nombreMedico, apellido, especialidad, codigo_hospital, entityManager.find(HospitalEntidad.class, codigo_hospital));
        //Comienza la transaccion
        transaction.begin();
        //Almacenamos el objeto
        entityManager.merge(medico);
        //Se vuelca en la base de datos
        transaction.commit();
        System.out.println("Medico insertado.");
    }
    //Funcion que nos permite buscar un registro de la tabla medico
    private static void buscarMedico() {
        MedicoEntidad medico;
        //Pedimos los datos por pantalla
        System.out.println("Introduce el codigo del medico que deseas buscar: ");
        int medico_cod = sc.nextInt();
        sc.nextLine();
        //Comienza la transaccion
        transaction.begin();
        //Guardamos el objeto medico que ha buscado en la base de datos con el medico_cod introducido
        medico = entityManager.find(MedicoEntidad.class, medico_cod);
        //Si no es nulo muestra el medico y sino un mensaje de no existe
        if (medico != null) {
            System.out.println(medico);
            System.out.println();
        } else {
            System.out.println("No existe ese medico.");
        }
        //Se vuelca en la base de datos
        transaction.commit();
    }
    //Funcion que nos permite listar todos los registro de la tabla medico
    private static void listarMedicos() {
        //Comienza la transaccion
        transaction.begin();
        //Buscamos en la base de datos todos los objetos, los guardamos en una lista y lo mostramos por pantalla
        List<MedicoEntidad> listaMedicos;
        Query query = entityManager.createQuery("SELECT medicoCod FROM MedicoEntidad ");
        listaMedicos = query.getResultList();
        for (MedicoEntidad m : listaMedicos) {
            System.out.println(m.toString());
        }
        //Se vuelca en la base de datos
        transaction.commit();
    }
    //Funcion que nos permite modificar un registro a la tabla medico
    private static void modificarMedico() {
        int medico_cod, codigo_hospital;
        String nombreMedico, apellido, especialidad;
        //Pedimos los datos por pantalla
        System.out.println("Introduce el codigo del medico que deseas modificar: ");
        medico_cod = sc.nextInt();
        sc.nextLine();
        //Comienza la transaccion
        transaction.begin();
        //Guardamos el objeto medico que ha buscado en la base de datos con el medico_cod introducido
        MedicoEntidad medicoEncontrar = entityManager.find(MedicoEntidad.class, medico_cod);
        //Si no es nulo modificamos los datos del medico y sino un mensaje de no existe
        if (medicoEncontrar != null) {
            System.out.println("Introduce el nombre del medico: ");
            nombreMedico = sc.nextLine();
            System.out.println("Introduce el apellido del medico: ");
            apellido = sc.nextLine();
            System.out.println("Introduce la especialidad del medico: ");
            especialidad = sc.nextLine();
            System.out.println("Introduce el codigo del hospital: ");
            codigo_hospital = sc.nextInt();
            sc.nextLine();
            //Creamos un objeto medico nuevo con los datos nuevos introducidos
            MedicoEntidad medico = new MedicoEntidad(medico_cod, nombreMedico, apellido, especialidad, codigo_hospital, entityManager.find(HospitalEntidad.class, codigo_hospital));
            //Guardamos el objeto
            entityManager.merge(medico);
        } else {
            System.out.println("El medico no existe.");
        }
        //Se vuelca en la base de datos
        transaction.commit();
    }
    //Funcion que nos permite borrar un registro a la tabla medico
    private static void borrarMedico() {
        int medico_cod;
        //Pedimos los datos por pantalla
        System.out.println("Introduce el codigo del medico que deseas borrar: ");
        medico_cod = sc.nextInt();
        sc.nextLine();
        //Comienza la transaccion
        transaction.begin();
        //Guardamos el objeto medico que ha buscado en la base de datos con el medico_cod introducido
        MedicoEntidad medicoEncontrar = entityManager.find(MedicoEntidad.class, medico_cod);
        //Si no es nulo borra el medico y sino un mensaje de no existe
        if (medicoEncontrar != null) {
            entityManager.remove(medicoEncontrar);
        } else {
            System.out.println("El medico no existe.");
        }
        //Se vuelca en la base de datos
        transaction.commit();
    }
}


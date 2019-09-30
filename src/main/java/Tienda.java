import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class Tienda {
    static String ruta = "usuarios.csv";
    public static void main(String[]args){
        File file = new File(ruta);

        if (file.exists()) {
            file.delete();
        }
        desplegarMenuPrincipal();
    }


    /**
     * Desplega el menu principal y llama a elegirOpcion()
     */
    public static void desplegarMenuPrincipal(){

        int opcion=1;
        while(opcion!=2) {

            System.out.println("------Menu------");
            System.out.println("[1] Ingresar Usuario");
            System.out.println("[2] Salir");
            opcion = recibirOpcion(2);
            elegirOpcion(opcion);
        }
    }
    /**
     * Intenta recibir una opcion numerica y captura una excpecion
     * @param nroOpciones cantidad de opciones en el menú
     * @return opcion numero de la opcion elegida
     */
    public static int recibirOpcion(int nroOpciones){
        int opcion;

        try{
            opcion= tratarRecibirNro(nroOpciones);
        }
        catch (InputMismatchException ime){
            System.out.println("Debe ingresar un numero!");

            opcion=recibirOpcion(nroOpciones);
        }
        return opcion;
    }
    /**
     *Recibe un numero y lo valida dentro de un rango
     * @param nroOpciones rango de opciones para validar
     * @return opcion elegida
     */
    public static int tratarRecibirNro(int nroOpciones){
        Scanner scan = new Scanner(System.in);

        int opcion;
        do{
            opcion = scan.nextInt();
        }
        while(!validarOpcion(opcion,nroOpciones));
        return opcion;
    }
    /**
     * Valida que un numero este dentro de un rango
     * @param opcion el numero para validar
     * @param nroOpciones tope superior del rango
     * @return True si el numero está dentro del rango
     */
    public static boolean validarOpcion(int opcion,int nroOpciones){
        boolean flag=false;
        if (opcion>0 && opcion<=nroOpciones){
            flag=true;
            //System.out.println("bien validado, opcion:"+opcion);
        }
        if (opcion<=0||opcion>nroOpciones){
            System.out.println("Opcion no valida!");

        }
        return flag;
    }
    /**
     * Llama a la funcion elegida en el menu
     * @param opcion opcion elegida
     */
    private static void elegirOpcion(int opcion) {
        if (opcion==1){
            System.out.println("ingresar Usuario:\n");
            ingresarEmpleado();
        }

        if (opcion==2){
            System.out.println("Salir");
        }
    }








    private static void ingresarEmpleado() {
        String usuario;
        String[] Datos = new String[6];

        //Pedir cada uno de los datos
        System.out.println("Ingrese nombre");
        Datos[0]=recibirNombre();
        System.out.println("Ingrese Rut");
        Datos[1]=recibirRut();
        System.out.println("Ingrese Direccion:\n");
        Datos[2]=recibirDireccion();

        //Llevarlo a formato .csv y añadirlo
        usuario = crearUsuario(Datos);

        agregarUsuario(usuario);

    }

    public static String crearUsuario(String[] datos) {
        String usuario;
        usuario=datos[0]+","+datos[1]+","+datos[2];
        return usuario;
    }

    private static void agregarUsuario(String usuario)  {
        try {
            File file = new File(ruta);

            if (!file.exists()) {
                file.createNewFile();
            }
            escribirLinea(usuario, file);
        }
        catch (IOException ioe){
            System.out.println("ruta invalida");
        }
    }

    private static void escribirLinea(String Linea, File file){
        try {
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\n"+Linea);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String recibirNombre() {
        String nombre;
        Scanner scan = new Scanner(System.in);

        nombre=scan.nextLine();


        if(!validarNombre_deFormato(nombre)){
            nombre= recibirNombre();
        }
        else {
            if (!validarNombre_deNumeros(nombre)){
                nombre=recibirNombre();
            }
        }
        return nombre;
    }

    private static String recibirRut() {
        String rut;
        Scanner scan = new Scanner(System.in);

        rut=scan.next();
        if(!validarRut_deFormato(rut)){
            System.out.println("Rut no valido");
            rut=recibirRut();
        }

        return rut;
    }

    public static String recibirDireccion(){
        String dir[] = new String[4];
        Scanner scan = new Scanner(System.in);
        System.out.println("1.- Ingrese Calle");
        dir[0]=scan.nextLine();
        System.out.println("2.- Ingrese Numero de Casa");
        dir[1]=scan.nextLine();
        System.out.println("3.- Ingrese Ciudad");
        dir[2]=scan.nextLine();
        System.out.println("4.- Ingrese Region");
        dir[3]=scan.nextLine();
        String direccion = dir[0]+","+dir[1]+","+dir[2]+","+dir[3];
        if(!validarDireccion_deFormato(direccion)){
            System.out.println("No ingresó todos los Datos.");
            direccion=recibirDireccion();
        }
        if(!validarDireccion_dePartes(direccion)){

            direccion=recibirDireccion();
        }
        return direccion;
    }

    public static boolean validarNombre_deNumeros(String nombre) {

        String [] parte = nombre.split(" ");
        if(!esPalabra(parte[0]) || !esPalabra(parte[1])){
            System.out.println("Caracteres no validos");
            return false;
        }
        else {
            return true;
        }
    }

    public static boolean validarNombre_deFormato(String nombre) {
        boolean flag=true;

        if(nombre.length()>20){
            System.out.println("El nombre no puede tener mas de 20 caracteres.");
            flag=false;
        }
        if(nombre.length()==0){
            System.out.println("El nombre no puede estar vacio");
            flag=false;
        }
        if(nombre.split(" ").length!=2){
            System.out.println("Debe escribir su nombre y apellido separados");
            flag=false;
        }
        return flag;
    }

    public static boolean validarRut_deFormato(String rut){
        boolean flag=false;
        String [] parte=rut.split("-",2);

        if(contieneGuion(rut) && parte[0].length()==8 && parte[1].length()==1
                && esNumero(parte[0]) && (esNumero(parte[1]) || parte[1].equals("k")))
            flag=true;
        return flag;
    }

    public static boolean validarDireccion_dePartes(String dir) {
        String [] partes = dir.split(",");
        if(esPalabra(partes[0])&& esNumero(partes[1]) && esPalabra(partes[2]) && esPalabra(partes[3])) {
            return true;
        }
        else {
            System.out.println("Formato no valido");
            return false;
        }
    }

    public static boolean validarDireccion_deFormato(String dir) {
        String [] partes= dir.split(",");
        boolean flag=false;
        if(partes.length==4){
            for(int i=0;i<partes.length;i++){
                if(!partes[i].equals(""))
                    flag = true;
                else
                    return false;
            }
        }
        else
            return false;

        return flag;
    }

    private static boolean esPalabra(String nombre) {
        boolean flag=false;
        String [] parte = nombre.split(" ");

        for(int i=0; i<parte.length;i++){
            for(int l=0;l<parte[i].length();l++) {
                if (isLetter(parte[i].charAt(l))) {
                    flag = true;
                } else {
                    return false;
                }
            }
        }
        return flag;
    }

    private static boolean esNumero(String str) {
        boolean flag=false;
        for(int i=0; i<str.length();i++){
            if(isDigit(str.charAt(i))){
                flag=true;
            }
            else{
                return false;
            }
        }
        return flag;
    }

    private static boolean contieneGuion(String str){
        int index=str.indexOf("-");
        if(index!=-1)
            return true;
        else
            return false;
    }




}

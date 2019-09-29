import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Character.isDigit;

public class Tienda {
    static String ruta = "usuarios.csv";
    public static void main(String[]args){
        desplegarMenuPrincipal();
    }


    /**
     * Desplega el menu principal y llama a elegirOpcion()
     */
    public static void desplegarMenuPrincipal(){

        int opcion=1;
        while(opcion!=2) {

            System.out.println("------Menu------");
            System.out.println("[1] Ingresar Empledao");
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
            System.out.println("ingresar empleado");
            ingresarEmpleado();
        }

        if (opcion==2){
            System.out.println("Salir");
        }
    }








    private static void ingresarEmpleado() {
        String empleado;
        String[] Datos = new String[6];

        //Pedir cada uno de los datos
        System.out.println("Ingrese nombre");
        Datos[0]=recibirNombre();
        System.out.println("Ingrese Rut");
        Datos[1]=recibirRut();
        System.out.println("Ingrese Direccion");
        Datos[2]=pedirDireccion();

        //Llevarlo a formato .csv y añadirlo
        empleado = crearEmpleado(Datos);
        System.out.println(empleado);
        agregarEmpleado(empleado,ruta);

    }
    public static String crearEmpleado(String[] datos) {
        String empleado;
        empleado=datos[0]+","+datos[1]+","+datos[2];
        return empleado;
    }
    public static void agregarEmpleado(String empleado,String ruta){
        try {
            PrintWriter writer = new PrintWriter(ruta, "UTF-8");
            writer.println(empleado+"\n");

        }catch (Exception e) {
            e.printStackTrace();
        }
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



    public static String pedirDireccion(){
        String dir="";
        return dir;
    }
    public static boolean validarRut_deFormato(String rut){
        boolean flag=false;
        String [] parte=rut.split("-",2);

        if(contieneGuion(rut) && parte[0].length()==8 && parte[1].length()==1
                && esNumero(parte[0]) && (esNumero(parte[1]) || parte[1].equals("k")))
            flag=true;
        return flag;
    }
    private static boolean contieneGuion(String str){
        int index=str.indexOf("-");
        if(index!=-1)
            return true;
        else
            return false;
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



    private static String recibirNombre() {
        String nombre;
        Scanner scan = new Scanner(System.in);

        nombre=scan.next();
        if(!validarNombre_deNumeros(nombre)){
            System.out.println("Parece que tiene un numero por ahi");
            nombre=recibirNombre();
        }
        if(!validarNombre_deTamaño(nombre)){
           nombre= recibirNombre();
        }
        return nombre;
    }

    public static boolean validarNombre_deNumeros(String nombre) {
        boolean flag=false;
        for(int i=0; i<nombre.length();i++){
            if(!isDigit(nombre.charAt(i))){
                flag=true;
            }
            else{
                return false;
            }
        }
        return flag;
    }

    public static boolean validarNombre_deTamaño(String nombre) {
        boolean flag=true;

        if(nombre.length()>10){
            System.out.println("El nombre no puede tener mas de 10 caracteres.");
            flag=false;
        }
        if(nombre.length()==0){
            System.out.println("El nombre no puede estar vacio");
            flag=false;
        }
        return flag;
    }










}

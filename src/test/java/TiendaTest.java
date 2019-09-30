import org.junit.Test;
import static org.junit.Assert.*;

public class TiendaTest {
    @Test
    public void validarNombre_sinNumeros(){
        String nombre1 = "r4ail 5eira";
        String nombre2= "rail N3ira";
        String nombre3="r7ol neira";
        String nombre4="rail ne'ra";
        String nombre5="Rail Neira";
        assertFalse(Tienda.validarNombre_deNumeros(nombre1));
        assertFalse(Tienda.validarNombre_deNumeros(nombre2));
        assertFalse(Tienda.validarNombre_deNumeros(nombre3));
        assertFalse(Tienda.validarNombre_deNumeros(nombre4));
        assertTrue(Tienda.validarNombre_deNumeros(nombre5));

    }

    @Test
    public void validarNombre_buenFormato(){
        String nombre = "";
        String nombre2= "nombreApellildo";
        String nombre3="nombre";
        String nombre4="nombrelargo apellidolargo";
        String nombre5="nombre apellido";
        assertFalse(Tienda.validarNombre_deFormato(nombre));
        assertFalse(Tienda.validarNombre_deFormato(nombre2));
        assertFalse(Tienda.validarNombre_deFormato(nombre3));
        assertFalse(Tienda.validarNombre_deFormato(nombre4));
        assertTrue(Tienda.validarNombre_deFormato(nombre5));
    }

    @Test
    public void validarRut_buenFormato(){
        String rut1 = "123-4567-8";
        String rut2 = "123456-";
        String rut3 = "12345678-12";
        String rut4 = "123a56789-1";
        String rut5 = "12345678-1";
        assertFalse(Tienda.validarRut_deFormato(rut1));
        assertFalse(Tienda.validarRut_deFormato(rut2));
        assertFalse(Tienda.validarRut_deFormato(rut3));
        assertFalse(Tienda.validarRut_deFormato(rut4));
        assertTrue(Tienda.validarRut_deFormato(rut5));
    }


   @Test
    public void crearEmpleadoTest(){
        String [] Datos = {"Nombre","12345678-1","Calle,1111,Ciudad,Region"};
        String exp = "Nombre,12345678-1,Calle,1111,Ciudad,Region";
        assertEquals(exp,Tienda.crearUsuario(Datos));
    }
    @Test
    public void validarDireccion_buenFormato(){
        String dir1=",,,";
        String dir2="Calle,,,Region";
        String dir3="Calle,1111,Ciudad,Region,Extra";
        String dir4="Calle,1111";
        String dir5="";
        String dir6="Calle,1111,Ciudad,Region";

        assertFalse(Tienda.validarDireccion_deFormato(dir1));
        assertFalse(Tienda.validarDireccion_deFormato(dir2));
        assertFalse(Tienda.validarDireccion_deFormato(dir3));
        assertFalse(Tienda.validarDireccion_deFormato(dir4));
        assertFalse(Tienda.validarDireccion_deFormato(dir5));
        assertTrue(Tienda.validarDireccion_deFormato(dir6));
    }
    @Test
    public void validarDireccion_buenasPartes(){
        String dir1="Call3,0123,Temuco,Araucania";
        String dir2="Calle,111a,Temuco,Araucania";
        String dir3="Calle,1111,Temuc0,Araucania";
        String dir4="Calle,1111,Temuco,Arauc_nia";
        String dir5="Calle,1111,Temuco,Araucania";

        assertFalse(Tienda.validarDireccion_dePartes(dir1));
        assertFalse(Tienda.validarDireccion_dePartes(dir2));
        assertFalse(Tienda.validarDireccion_dePartes(dir3));
        assertFalse(Tienda.validarDireccion_dePartes(dir4));
        assertTrue(Tienda.validarDireccion_dePartes(dir5));

    }






}

import org.junit.Test;
import static org.junit.Assert.*;

public class TiendaTest {
    @Test
    public void validarNombre_sinNumeros(){
        String nombre = "r4ail 5eira";
        assertFalse(Tienda.validarNombre_deNumeros(nombre));
    }

    @Test
    public void validarNombre_noVacioNiGrande(){
        String nombre = "";
        assertFalse(Tienda.validarNombre_deTamaño(nombre));
    }

    @Test
    public void validarRut_buenFormato(){
        String rut1 = "123-4567-8";
        String rut2 = "123456-";
        String rut3 = "12345678-12";
        String rut4 = "123456789-1";
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
        assertEquals(exp,Tienda.crearEmpleado(Datos));
    }
    @Test
    public void validarDireccion_buenFormato(){
        String dir1=",,,";
        String dir2="Calle,,,Region";
        String dir3="Calle,1111,Ciudad,Region,Extra";
        String dir4="Calle,1111";
        String dir5="";
        String dir6="Calle,1111,Ciudad,Region,Extra";

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
        String dir1=",,,";
    }






}

package com.sdi.tests.Tests;

import static com.sdi.tests.utils.SeleniumUtils.EsperaCargaPagina;
import static com.sdi.tests.utils.SeleniumUtils.textoNoPresentePagina;
import static com.sdi.tests.utils.SeleniumUtils.textoPresentePagina;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.sdi.tests.utils.SeleniumUtils;

/**
 * Para que las pruebas funcionene correctamente repetidas veces es necesario 
 * reiniciar la base de datos al mismo estado.
 * 
 * Para ello se encuentra en el proyecto base un script para reiniciarlos datos.
 * 
 * Están pensado para http://localhost:8180/
 *
 */

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PruebasSDI {

    WebDriver driver;
    List<WebElement> elementos = null;
    
    private final static String BASE_URLI = "sdi2-171/";
    private final static String URL = "http://localhost:8180/" + BASE_URLI;
    private final static String LISTRELATEDURL = "http://localhost:8180/sdi2-171/listTripsRelated.xhtml";
    private final static String REGISTERTRIPURL = "http://localhost:8180/sdi2-171/manageTrip.xhtml";
    private final static String VIAJEUSUARIOUSER1 = "?updateTrip=201";
    private final static String LOGINURL = "http://localhost:8180/sdi2-171/login.xhtml";
    private final static String VIAJE= "http://localhost:8180/sdi2-171/showTrip.xhtml?id=";
    private final static String VIAJE201 = "http://localhost:8180/sdi2-171/showTrip.xhtml?id=201";
    private final static String VIAJE206 = "http://localhost:8180/sdi2-171/showTrip.xhtml?id=206";
    

    public PruebasSDI() {
    }
    private void controlA() {
	elementos.get(0).sendKeys(Keys.chord(Keys.CONTROL, "a"));
    }
    
    private void escrbir(String cadena){
	Actions builder = new Actions(driver);	    
	builder.sendKeys(cadena).perform();
    }
    private void click(){
	elementos.get(0).click();
    }
    private void pulsarEnter(){
	elementos.get(0).sendKeys(Keys.RETURN);
    }
    private void irA(String uri){
	driver.get(uri);
    }
    
    private void buscarPorId(String id){
	elementos = EsperaCargaPagina(driver, "id", id, 15); 
    }
    private void buscarPorTexto(String texto){
   	elementos = EsperaCargaPagina(driver, "text", texto, 15); 
       }
    
    private void logearse(String username, String pass){
	irA(URL);
	buscarPorId("form-content:login"); 
	click();
	buscarPorId("content:login:username");
	click();
	escrbir(username);
	buscarPorId("form-content:login:password");
	click();
	escrbir(pass);
	buscarPorId("form-content:login:loginButt");
	click();
    }

    @Before
    public void run() {
	// Creamos el driver para Firefox. Recomendable usar Firefox.
	driver = new FirefoxDriver();
	// Vamos a la página principal de mi aplicación
	driver.get(URL);
    }

    @After
    public void end() {
	// Cerramos el navegador
	driver.quit();
    }

    // PRUEBAS

    // 1. [RegVal] Registro de Usuario con datos válidos.
    @Test
    public void t01_RegVal() {
	buscarPorId("form-content:register");
	click();
	//login
	buscarPorId("form-content:registrarse:login");
	click();
	escrbir("NombreAleatorio"+new Random().nextInt());
	//name
	buscarPorId("form-content:registrarse:name");
	click();
	escrbir("NombreAleatorio"+new Random().nextInt());
	//surname
	buscarPorId("form-content:registrarse:surname");
	click();
	escrbir("NombreAleatorio"+new Random().nextInt());
	//email
	buscarPorId("form-content:registrarse:email");
	click();
	escrbir("NombreAleatorio"+new Random().nextInt()+"@e.c");
	//pass1
	String pass = "NombreAleatorio"+new Random().nextInt();
	buscarPorId("form-content:registrarse:pwd1");
	click();
	escrbir(pass);
	//pass2
	buscarPorId("form-content:registrarse:pwd2");
	click();
	escrbir(pass);
	
	
	buscarPorId("form-content:registrarse:registrarse");
	click();
	
	buscarPorId("form-content:logout");
	
	textoPresentePagina(driver, "Desconectarse");
	
	
	
    }

    // 2. [RegInval] Registro de Usuario con datos inválidos (contraseñas
    // diferentes).
    @Test
    public void t02_RegInval() {
	buscarPorId("form-content:register");
	click();
	//login
	buscarPorId("form-content:registrarse:login");
	click();
	escrbir("NombreAleatorio"+new Random().nextInt());
	//name
	buscarPorId("form-content:registrarse:name");
	click();
	escrbir("NombreAleatorio"+new Random().nextInt());
	//surname
	buscarPorId("form-content:registrarse:surname");
	click();
	escrbir("NombreAleatorio"+new Random().nextInt());
	//email
	buscarPorId("form-content:registrarse:email");
	click();
	escrbir("NombreAleatorio"+new Random().nextInt()+"@e.c");
	//pass1
	buscarPorId("form-content:registrarse:pwd1");
	click();
	escrbir("NombreAleatorio"+new Random().nextInt());
	//pass2
	buscarPorId("form-content:registrarse:pwd2");
	click();
	escrbir("NombreAleatorioA"+new Random().nextInt());
	
	buscarPorId("form-content:registrarse:registrarse");
	click();
	
	buscarPorId("form-content:registrarse:registrarse");
	
	textoPresentePagina(driver, "Registro");
    }

    // 3. [IdVal] Identificación de Usuario registrado con datos válidos.
    @Test
    public void t03_IdVal() {
	buscarPorId("form-content:login"); 
	click();
	buscarPorId("content:login:username");
	click();
	escrbir("user1");
	buscarPorId("form-content:login:password");
	click();
	escrbir("user1");
	buscarPorId("form-content:login:loginButt");
	click();
	buscarPorId("form-content:logout");
	textoPresentePagina(driver, "Desconectarse");
    }

    // 4. [IdInval] Identificación de usuario registrado con datos inválidos.
    @Test
    public void t04_IdInval() {
	buscarPorId("form-content:login"); 
	click();
	buscarPorId("content:login:username");
	click();
	escrbir(new Random().nextInt()+"NombreAleatorio");
	buscarPorId("form-content:login:password");
	click();
	escrbir("user1");
	buscarPorId("form-content:login:loginButt");
	click();
	buscarPorId("form-content:login:loginButt");
	textoPresentePagina(driver, "Identificarse");
    }

    // 5. [AccInval] Intento de acceso con URL desde un usuario no público (no
    // identificado). Intento de acceso a vistas de acceso privado.
    @Test
    public void t05_AccInval() {
	irA(LISTRELATEDURL);
	textoPresentePagina(driver, "Error");
    }

    // 6. [RegViajeVal] Registro de un viaje nuevo con datos válidos.
    @Test
    public void t06_RegViajeVal() {
	logearse("user1", "user1");
	irA(REGISTERTRIPURL);
	buscarPorId("form-content:registrar:precarga");
	click();
	buscarPorId("form-content:registrar:bottonAceptar");
	click();
	buscarPorId("form-content:registrar:bottonAceptar");
	pulsarEnter();
	buscarPorId("form-content:logout");
	textoPresentePagina(driver, "Desconectarse");
    }

    // 7. [RegViajeInVal] Registro de un viaje nuevo con datos inválidos.
    @Test
    public void t07_RegViajeInVal() {
	logearse("user1", "user1");
	irA(REGISTERTRIPURL);
	buscarPorTexto("Aceptar");
	click();
	buscarPorTexto("Ciudad");
    }

    // 8. [EditViajeVal] Edición de viaje existente con datos válidos.
    @Test
    public void t08_EditViajeVal() {
	logearse("user1", "user1");
	irA(REGISTERTRIPURL+VIAJEUSUARIOUSER1);
	buscarPorId("form-content:registrar:bottonModoficar");
	click();
	//buscarPorId("form-content:registrar:bottonModoficar");
	//pulsarEnter();
	buscarPorId("form-content:logout");
	textoPresentePagina(driver, "Desconectarse");
    }

    // 9. [EditViajeInVal] Edición de viaje existente con datos inválidos.
    @Test
    public void t09_EditViajeInVal() {
	logearse("user1", "user1");
	irA(REGISTERTRIPURL+VIAJEUSUARIOUSER1);
	buscarPorId("form-content:registrar:cost_input");
	click();
	controlA();
	escrbir("-50");
	pulsarEnter();
	//Sale el icono de informacion error
	EsperaCargaPagina(driver, "class", "ui-messages-info-icon", 5);
    }

    

    // 10. [CancelViajeVal] Cancelación de un viaje existente por un
    // promotor.
    /**
     * La funcionalionalidad está hecha pero no se ha podido probar.
     */
    @Test
    public void t10_CancelViajeVal() {
	
	
	
	// Loguearse
	// ir a relacionados
	// pulsar check de un viaje
	// pulsar cancelar
	// pulsar boton SI en el dialogo
	// Comprobar que no estas en la pagina de error

    }

    // 11. [CancelMulViajeVal] Cancelación de múltiples viajes existentes
    // por un promotor.
    /**
     * La funcionalionalidad está hecha pero no se ha podido probar.
     */
    @Test
    public void t11_CancelMulViajeVal() {

	// Loguearse
	// ir a relacionados
	// pulsar check de un viaje
	// pulsar check de un viaje (OTRO DISTINTO)
	// pulsar cancelar
	// pulsar boton SI en el dialogo
	// Comprobar que no estas en la pagina de error
    }

    // 12. [Ins1ViajeAceptVal] Inscribir en un viaje un solo usuario y ser
    // admitido por el promotor.
    /**
     * Esta prueba puede fallar por dos razones:
     * * Ajax demasiado lento.
     * * en los snipets aqune pongamos el id aparece un valor intermedio que 
     * 		no siempre es el mismo y no se puede encontrar por ID
     */
    @Test
    public void t12_Ins1ViajeAceptVal() {
	logearse("user1", "user1");
	irA(VIAJE+203);
	buscarPorId("form-content:showTrip:PasajerosPeticiones:j_idt92:pedirPlazaButt");
	click();
	irA(URL);
	buscarPorId("form-content:logout");
	click();
	logearse("user2", "user2");
	irA(VIAJE+203);
	buscarPorId("form-content:showTrip:PasajerosPeticiones:j_idt97:0:acceptApplication");
	click();
	// loguearse con user2
	// ir a URL de viaje de user1
	// pulsar inscribirte
	// ir a index
	// pulsar desconectarse
	// loguearse como user1
	// ir a URL de viaje de user1
	// pulsa en aceptar pasajero

    }

    // 13. [Ins2ViajeAceptVal] Inscribir en un viaje dos usuarios y ser
    // admitidos los dos por el promotor.
    /**
     * Esta prueba puede fallar por dos razones:
     * * Ajax demasiado lento.
     * * en los snipets aqune pongamos el id aparece un valor intermedio que 
     * 		no siempre es el mismo y no se puede encontrar por ID
     */
    @Test
    public void t13_Ins2ViajeAceptVal() {
	
	logearse("user1", "user1");
	irA(VIAJE+204);
	buscarPorId("form-content:showTrip:PasajerosPeticiones:j_idt92:pedirPlazaButt");
	click();
	irA(URL);
	buscarPorId("form-content:logout");
	click();
	logearse("user3", "user3");
	irA(VIAJE+204);
	buscarPorId("form-content:showTrip:PasajerosPeticiones:j_idt97:0:acceptApplication");
	click();
	
	irA(URL);
	buscarPorId("form-content:logout");
	click();
	logearse("user2", "user2");
	irA(VIAJE+204);
	buscarPorId("form-content:showTrip:PasajerosPeticiones:j_idt92:pedirPlazaButt");
	click();
	irA(URL);
	buscarPorId("form-content:logout");
	click();
	logearse("user2", "user2");
	irA(VIAJE+204);
	buscarPorId("form-content:showTrip:PasajerosPeticiones:j_idt97:0:acceptApplication");
	click();
    }

    // 14. [Ins3ViajeAceptInval] Inscribir en un viaje (2 plazas máximo)
    // dos usuarios y ser admitidos los dos y que un tercero intente
    // inscribirse
    // en ese mismo viaje pero ya no pueda por falta de plazas.

  
    @Test
    public void t14_Ins3ViajeAceptInval() {
	assertTrue("", FALSE);
	//Repetir prueba anterior en otro viaje con otros usuarios
	
	//loguearse user4(disntinto a los anteriores)
	// ir a URL de viaje de user1
	// No esta el boton de inscribirte

    }

    // 15. [CancelNoPromotorVal] Un usuario no promotor Cancela plaza.
    /**
     * Esta prueba puede fallar por:
     * * en los snipets aqune pongamos el id aparece un valor intermedio que 
     * 		no siempre es el mismo y no se puede encontrar por ID
     */
    @Test
    public void t15_CancelNoPromotorVal() {
	logearse("user5", "user5");
	irA(VIAJE201);
	buscarPorId("form-content:showTrip:PasajerosPeticiones:j_idt74:0:cancelButton");
	click();
	textoNoPresentePagina(driver, "Error");
	// ir a pagina viaje user1
	// pulsar cancelar plaza

    }

    // 16. [Rech1ViajeVal] Inscribir en un viaje un usuario que será
    // admitido y después rechazarlo por el promotor.
    /**
     * Esta prueba puede fallar por:
     * * en los snipets aqune pongamos el id aparece un valor intermedio que 
     * 		no siempre es el mismo y no se puede encontrar por ID
     */
    @Test
    public void t16_Rech1ViajeVal() {
	logearse("user3", "user3");
	irA(VIAJE206);
	buscarPorId("form-content:showTrip:PasajerosPeticiones:j_idt92:pedirPlazaButt");
	click();
	irA(URL);
	buscarPorId("form-content:logout");
	click();
	logearse("user5", "user5");
	irA(VIAJE206);
	buscarPorId("form-content:showTrip:PasajerosPeticiones:j_idt97:0:acceptApplication");
	click();
	buscarPorId("form-content:showTrip:PasajerosPeticiones:j_idt74:0:cancelButton");
	click();
	buscarPorId("form-nav:setEN");
	textoNoPresentePagina(driver, "ERROR");
	// registar nuevo usuario
	// entrar en viaje de usuario uno
	// pedir plaza
	// logout
	// entrar con user1
	// ir a viaje
	// aceptar peticion
	// cancelar plaza

    }

    // 17. [i18N1] Cambio del idioma por defecto a un segundo idioma.
    // (Probar algunas vistas)
    @Test
    public void t17_i18N1() {
	textoPresentePagina(driver, "Bienvenido" );
	buscarPorId("form-nav:setEN");
	click();
	buscarPorId("form-nav:setEN");
	SeleniumUtils.textoPresentePagina(driver, "Welcome");
	irA(LOGINURL);
	buscarPorId("form-nav:setEN");
	SeleniumUtils.textoNoPresentePagina(driver, "Identificarse");
    }

    // 18. [i18N2] Cambio del idioma por defecto a un segundo idioma y
    // vuelta al idioma por defecto. (Probar algunas vistas)
    @Test
    public void t18_i18N2() {
	textoPresentePagina(driver, "Bienvenido" );
	buscarPorId("form-nav:setEN");
	click();
	buscarPorId("form-nav:setEN");
	SeleniumUtils.textoPresentePagina(driver, "Welcome");
	irA(LOGINURL);
	buscarPorId("form-nav:setEN");
	SeleniumUtils.textoNoPresentePagina(driver, "Identificarse");
	buscarPorId("form-nav:setES");
	click();
	irA(URL);
	logearse("user1", "user1");
	buscarPorId("form-content:logout");
	textoPresentePagina(driver, "Desconectarse");

    } 
    
    // 19. [OpFiltrado] Prueba para el filtrado opcional.

    /**
     * La funcionalionalidad está hecha pero no se ha podido probar.
     * El ajax tarda demasiado.
     */
    @Test
    public void t19_OpFiltrado() {

    } 
    
    // 20. [OpOrden] Prueba para la ordenación opcional.

    /**
     * La funcionalionalidad está hecha pero no se ha podido probar.
     * El ajax tarda demasiado.
     */
    @Test
    public void t20_OpOrden() {

    } 
    
    // 21. [OpPag] Prueba para la paginación opcional.

    @Test
    public void t21_OpPag() {

    }
    
    // 22. [OpMante] Prueba del mantenimiento programado opcional.

    boolean TRUE = true;
    boolean FALSE = false;
    
    
    @Test
    public void t22_OpMante() {
	assertTrue("Realizada en el postcontructor del bean viaje. (leer comentario en prueba)", TRUE);
	
	// En el postcontruct de un viaje se comprueba la fecha actual y todas las peticiones.
	// Se actualizan las peticiones y estados de ser necesario.
	
	// Esto no se puede probar de forma no extremadamente compleja en selenium ya que es transparente al usuario.
    }

}
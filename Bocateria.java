import java.util.HashMap
/**
 * Representa una bocateria
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bocateria
{
    // Representa el primer cliente en la cola de la bocateria
    private Cliente primeraPersonaEnCola;
    // Facturacion hasta el momento de la bocateria
    private int facturacionActual;
    // Mapa con los clientes despachados hasta el momento
    private HashMap<Integer, Cliente> clientesDespachados;
    // Precio del bocadillo
    private final static int PRECIO_BOCADILLO = 5;

    /**
     * Constructor for objects of class Bocateria
     */
    public Bocateria()
    {
        // initialise instance variables
        x = 0;
    }

}

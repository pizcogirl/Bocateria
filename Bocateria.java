import java.util.HashMap;

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
        // Inicializamos las variables, no tenemos primer cliente aun, la facturacion sera 0
        primeraPersonaEnCola = null;
        facturacionActual = 0;
        // Inicializamos el hashmap
        clientesDespachados = new HashMap<Integer, Cliente>();
    }

    /**
     * Metodo que coloca un nuevo cliente en la cola
     */
    public void llegaNuevoClienteALaCola(int numeroDeBocadillos)
    {
        // Primero comprobamos si existe algun cliente en la cola
        if (primeraPersonaEnCola == null)
        {
            // Si no hay ninguno, lo colocamos el primero
            primeraPersonaEnCola = new Cliente(numeroDeBocadillos);
        }
        else
        {
            // Si ya existen, lo colocamos en la cola, para ello cojemos el siguiente en la cola
            // del primer cliente, y vamos preguntando hasta llegar al ultimo
            Cliente ultimoCola = primeraPersonaEnCola;
            while(ultimoCola.getSiguienteEnLaCola() != null)
            {
                ultimoCola = ultimoCola.getSiguienteEnLaCola();
            }
            // CUando llegamos al que no tiene nadie detras, seteamos su siguiente en la cola con el nuevo cliente
            ultimoCola.setSiguienteEnLaCola(new Cliente(numeroDeBocadillos));
        }
    }

}

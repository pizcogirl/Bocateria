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
    public final static int PRECIO_BOCADILLO = 5;

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
     * @param El numero de bocadillos que desea el cliente
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
            // Cuando llegamos al que no tiene nadie detras, seteamos su siguiente en la cola con el nuevo cliente
            ultimoCola.setSiguienteEnLaCola(new Cliente(numeroDeBocadillos));
        }
    }
    
    /**
     * Metodo que imprime por pantalla los datos de los clientes en cola
     */
    public void visualizaDatosClientesEnCola()
    {
        // Recorremos la cola de clientes e imprimemos su información por pantalla
        Cliente cliente = primeraPersonaEnCola;
        while(cliente != null)
        {
            System.out.println(cliente.toString());
            cliente = cliente.getSiguienteEnLaCola();
        }
    }
    
    /**
     * Despacha al cliente actual en la cola y coloca al siguiente cliente como primero de la cola
     */
    public void despacharClienteActual()
    {
        // Sumamos la facturacion del cliente que esta el primero en la cola
        facturacionActual += primeraPersonaEnCola.getNumeroDeBocadillos()*PRECIO_BOCADILLO;
        // Creamos un integer basado en su posicion en la cola para pasarlo como clave
        // en el HashMap de clientes despachados
        Integer clave = new Integer(primeraPersonaEnCola.getNumeroCliente());
        // Añadimos el cliente a clientes despachados
        clientesDespachados.put(clave, primeraPersonaEnCola);
        // cambiamos el primer cliente en la cola por el siguiente cliente
        primeraPersonaEnCola = primeraPersonaEnCola.getSiguienteEnLaCola();
    }

}

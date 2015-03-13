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
        String info = "";
        while(cliente != null)
        {
            info = cliente.toString() +"(" + (PRECIO_BOCADILLO*cliente.getNumeroDeBocadillos()) + " euros)";
            System.out.println(info);
            cliente = cliente.getSiguienteEnLaCola();
        }
    }

    /**
     * Despacha al cliente actual en la cola y coloca al siguiente cliente como primero de la cola
     */
    public void despacharClienteActual()
    {
        if(primeraPersonaEnCola != null)
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

    /**
     * Visualiza por pantalla los datos actuales de la bocateria: facturacion actual, clientes en cola y clientes despachados
     */
    public void visualizaDatosBocateria()
    {
        // Imprimimos por pantalla los datos
        System.out.println("Facturación actual: " + facturacionActual + " euros");
        System.out.println("Estado de la cola:");
        visualizaDatosClientesEnCola();
        System.out.println("Clientes despachados:");
        visualizaDatosClientesDespachados();
    }

    /**
     * Metodo que devuelve la posicion del cliente en la cola que quiere mas bocadillos.
     * @return La posicion del cliente en cola que quiere mas bocadillos, si no hay nadie
     * en la cola devuelve -1, si estan empatados más de uno, devuelve la del primero
     */
    public int getPosicionPrimerClienteConMasBocadillos()
    {
        // Recorremos la cola y vamos guardando la posicion del cliente
        // que quiera mas bocadillos, para comprobar guardamos tambien el numero de bocadillos
        // que quiere, inicializamos en -1 ambas variables
        int posicionMasBocadillos = -1;
        int numBocadillos = -1;
        Cliente cliente = primeraPersonaEnCola;
        while(cliente != null)
        {
            if(cliente.getNumeroDeBocadillos() > numBocadillos)
            {
                numBocadillos = cliente.getNumeroDeBocadillos();
                posicionMasBocadillos = cliente.getNumeroCliente();
            }
            cliente = cliente.getSiguienteEnLaCola();
        }
        return posicionMasBocadillos;
    }

    /**
     * Metodo que representa que un cliente ha abandonado la cola sin llegar a realizar su pedido
     * @param id El numero de cliente que ha abandonado la cola
     */
    public void clienteAbandonaCola(int id)
    {
        // Recorremos la lista de clientes hasta llegar al anterior al que ha abandonado la cola
        // en este tenemos que cambiar el siguiente en cola por el que esperaba tras el
        // que ha abandonado la cola
        Cliente cliente = primeraPersonaEnCola;
        //El caso especial de que el que abandone la cola sea el primero de la cola lo tratamos mirando sus datos
        // Sino recorremos la cola
        if(cliente.getNumeroCliente() == id)
        {
            primeraPersonaEnCola = cliente.getSiguienteEnLaCola();
        }
        else
        {
            // Creamos un boolean para que una vez encontrado el cliente que abandono
            // la cola, ya no busque mas
            boolean encontrado = false;
            while(cliente != null && (!encontrado))
            {
                // Tomamos siempre el siguiente cliente al que nos encontramos, para ver si es el que
                // deja la cola
                Cliente temporal = cliente.getSiguienteEnLaCola();
                // Comprobamos que exista alguien en la cola, y que sea el que buscamos
                if(temporal != null && temporal.getNumeroCliente() == id)
                {
                    // Si es asi, en el cliente anterior cambiamos el siguiente en cola por el que espera 
                    // tras el cliente que se va a marchar de la cola
                    cliente.setSiguienteEnLaCola(temporal.getSiguienteEnLaCola());
                    // Cambiamos el valor del boolean a encontrado para que no itere mas
                    encontrado = true;
                }
                cliente = cliente.getSiguienteEnLaCola();
            }
        }
    }

    /**
     * Visualiza por pantalla los datos de los clientes ya despachados
     */
    public void visualizaDatosClientesDespachados()
    {
        // Buscamos cuantos clientes existen en el hasmap de clientes despachados,
        // seran tantos como el numero de cliente que estamos despachando ahora
        int keyCliente = Cliente.numeroClienteActual;
        String info = "";
        for(int i = 1; i < keyCliente; i++)
        {
            // Comprobamos si la key existe en el hashMap
            if(clientesDespachados.containsKey(i))
            {
                // Tomamos la informacion de ese cliente y la imprimimos por pantalla
                Cliente cliente = clientesDespachados.get(i);
                if (cliente != null)
                {
                    info = cliente.toString();
                    System.out.println(info);
                }
            }
        }
    }

}

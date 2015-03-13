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
     * Ordena la cola segun el numero de bocadillos que quiere cada cliente
     * Si dos estan empatados no es relevante en que orden estan entre ellos
     */
    public void ordenarColaPorNumeroDeBocadillos()
    {
        //Lo primero, cuento cuantos clientes hay en la cola, esa sera
        //el numero de veces que tenga que recorrerla para ordenarla
        int numClientes = 0;
        Cliente cliente = primeraPersonaEnCola;
        while(cliente != null)
        {
            numClientes++;
            cliente = cliente.getSiguienteEnLaCola();
        }
        // Ahora recorremos la cola de clientes tantas veces como clientes tengamos
        // si el numero de bocadillos del siguiente es mayor, intercambiamos las posiciones entre ambos
        // Por lo que volvemos a darle a cliente el valor del primer cliente en la cola
        cliente = primeraPersonaEnCola;
        Cliente siguienteCliente = null;
        // Para poder cambiar las veces tambien debo guardar el cliente anterior
        Cliente anteriorCliente = null;
        for(int i = 0; i < numClientes; i++)
        {
            // Por cada cliente en la cola recorrro toda la cola intercambiando posiciones si es necesario
            anteriorCliente = null;
            cliente = primeraPersonaEnCola;
            while(cliente != null)
            {
                siguienteCliente = cliente.getSiguienteEnLaCola();
                // Si el siguiente cliente quiere mas bocadillos, intercambiamos posiciones
                if(siguienteCliente != null && (siguienteCliente.getNumeroDeBocadillos() > cliente.getNumeroDeBocadillos()))
                {
                    // Cambiamos el cliente tras elactual por el que seguia
                    // al siguiente, y del cliente que seguia al siguiente por el actual
                    cliente.setSiguienteEnLaCola(siguienteCliente.getSiguienteEnLaCola());
                    siguienteCliente.setSiguienteEnLaCola(cliente);
                    // Si el anterior cliente no es null, cambiamos a quien le da la vez por siguienteCliente
                    if(anteriorCliente != null)
                    {
                        anteriorCliente.setSiguienteEnLaCola(siguienteCliente);
                    }
                    else
                    {
                        primeraPersonaEnCola = siguienteCliente;
                    }
                    // Guardamos como anterior cliente el que acabamos de cambiar de posicion, y dejamos cliente como esta
                    // para que lo compare con el siguiente
                    anteriorCliente = siguienteCliente;
                }
                else
                {
                    // Cambiamos clienteAnterior por el que acabamos de examinar, y tomamos el siguiente en la cola
                    // En caso de no haber hecho cambios en la lista
                    anteriorCliente = cliente;
                    cliente = siguienteCliente;
                }
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


/**
 * Representa a un cliente de la bocateria
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cliente
{
    // El numero de cliente de la bocateria
    private int numeroCliente;
    // Siguiente cliente tras este en la cola
    private Cliente siguienteEnLaCola;
    // Numero de bocadillos que desea el cliente
    private int numeroDeBocadillos;
    // Numero de cliente actual
    private static int numeroClienteActual = 1;

    /**
     * Constructor de clientes
     * @param numeroBocatas El numero de bocadillos que quiere el cliente
     */
    public Cliente(int numeroBocatas)
    {
        // Asignamos el numero de cliente y aumentamos el numero de la vez
        numeroCliente = numeroClienteActual;
        numeroClienteActual++;
        // Le pasamos los bocadillos que quiere
        numeroDeBocadillos = numeroBocatas;
        // Al llegar a la cola no habria nadie detras, asi que inicializamos a null
        siguienteEnLaCola = null;
    }
    
    /**
     * Metodo que devuelve el siguiente cliente en cola
     * @return El siguiente cliente en cola
     */
    public Cliente getSiguienteEnLaCola()
    {
        return siguienteEnLaCola;
    }
    
    /**
     * Metodo que devuelve el numero de bocadillos que quiere el cliente
     * @return Numero de bocadillos que quiere el cliente
     */
    public int getNumeroDeBocadillos()
    {
        return numeroDeBocadillos;
    }
    
    /**
     * Metodo que devuelve el numero del cliente
     * @return El numero del cliente
     */
    public int numeroCliente()
    {
        return numeroCliente;
    }
    
    /**
     * Metodo que devuelve la informacion del cliente
     * @return La informacion del cliente
     */
    public String toString()
    {
        String info = "Cliente: " + numeroCliente + " " + numeroDeBocadillos + " bocadillo/s ("
                        + (Bocateria.PRECIO_BOCADILLO*numeroDeBocadillos) + " euros)";
        return info;
    }
    
    /**
     * Coloca el siguiente cliente en la cola
     * @param cliente El siguiente cliente en la cola
     */
    public void setSiguienteEnLaCola(Cliente cliente)
    {
        siguienteEnLaCola = cliente;
    }
}

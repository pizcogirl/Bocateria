
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
    private static numeroClienteActual = 1;

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

}

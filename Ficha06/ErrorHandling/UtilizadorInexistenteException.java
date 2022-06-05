package Ficha06.ErrorHandling;

public class UtilizadorInexistenteException extends Exception {
    public UtilizadorInexistenteException(String s){
        super("UtilizadorInexistenteException: " + s);
    }
}
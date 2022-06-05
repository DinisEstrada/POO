package Ficha06.ErrorHandling;

public class UtilizadorRepetidoException extends Exception {
    public UtilizadorRepetidoException(String s){
        super("UtilizadorRepetidoException: " + s);
    }
}

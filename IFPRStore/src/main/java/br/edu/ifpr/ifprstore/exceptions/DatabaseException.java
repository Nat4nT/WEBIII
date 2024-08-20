package br.edu.ifpr.ifprstore.exceptions;
//não checada
public class DatabaseException extends RuntimeException {
    public DatabaseException(String msg){
        super(msg);
    }
}

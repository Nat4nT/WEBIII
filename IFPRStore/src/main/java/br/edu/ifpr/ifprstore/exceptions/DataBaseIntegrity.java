package br.edu.ifpr.ifprstore.exceptions;
//não checada
public class DataBaseIntegrity extends RuntimeException {
    public DataBaseIntegrity(String msg){
        super(msg);
    }
}

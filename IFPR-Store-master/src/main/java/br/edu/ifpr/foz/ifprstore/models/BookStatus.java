package br.edu.ifpr.foz.ifprstore.models;

public enum BookStatus {
    DISPONIVEL("disponivel"),
    INDISPONIVEL("indisponivel"),
    RESERVADO("reservado");

    private String status;

    private BookStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


}

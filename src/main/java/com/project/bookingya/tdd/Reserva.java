package com.project.bookingya.tdd;

public class Reserva {

    private static int contador = 1;

    private int id;
    private String usuario;
    private String fecha;

    public Reserva(String usuario, String fecha) {
        this.id = contador++;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha){
        this.fecha = fecha;
    }
}

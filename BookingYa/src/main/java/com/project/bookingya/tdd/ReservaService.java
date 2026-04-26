package com.project.bookingya.tdd;

import java.util.ArrayList;
import java.util.List;

public class ReservaService {

    private List<Reserva> reservas = new ArrayList<>();

    public Reserva crearReserva(Reserva reserva) {
        reservas.add(reserva);
        return reserva;
    }

    public Reserva obtenerPorUsuario(String usuario) {
        return reservas.stream()
                .filter(r -> r != null)
                .filter(r -> r.getUsuario().equals(usuario))
                .findFirst()
                .orElse(null);
    }

    public Reserva obtenerPorId(int id) {
        return reservas.stream()
                .filter(r -> r != null)
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Reserva actualizar(Reserva reserva, int id) {
        Reserva existente = obtenerPorId(id);
        if (existente != null) {
            existente.setUsuario(reserva.getUsuario());
        }
        return existente;
    }

    public void eliminar(int id) {
        reservas.removeIf(r -> r.getId() == id);
    }

    public List<Reserva> obtenerTodas() {
        return reservas;
    }
}
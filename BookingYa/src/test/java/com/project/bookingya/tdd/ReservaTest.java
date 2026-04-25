package com.project.bookingya.tdd;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservaTest {

    private static ReservaService service = new ReservaService();
    private static Reserva reserva;

    //Creación de una reserva
    @Test
    @Order(1)
    void testCreateReserva() {
        reserva = new Reserva("Yeimy Marin", "2026-05-01");
        Reserva resultado = service.crearReserva(reserva);

        assertNotNull(resultado);
        assertEquals(reserva.getUsuario(), resultado.getUsuario());

        System.out.println("Usuario Creado: " + resultado.getUsuario());
    }

    //Consulta de una reservapor por usuario
    @Test
    @Order(2)
    void testGetReservaPorUsuario() {
        Reserva resultado = service.obtenerPorUsuario(reserva.getUsuario());

        assertNotNull(resultado);
        assertEquals(reserva.getUsuario(), resultado.getUsuario());

        System.out.println("Consulta Usuario: " + resultado.getUsuario());
    }

    //Obtención de una reserva por ID
    @Test
    @Order(3)
    void testGetReservaPorId() {
        Reserva resultado = service.obtenerPorId(reserva.getId());

        assertNotNull(resultado);
        assertEquals(reserva.getId(), resultado.getId());
        assertEquals(reserva.getUsuario(), resultado.getUsuario());

        System.out.println("Consulta por ID: " + resultado.getId());
    }

    //Actualización de una reserva existente
    @Test
    @Order(4)
    void testUpdateReserva() {
        Reserva existente = service.obtenerPorId(reserva.getId());
        String nuevoUsuario = "Adri Sosa";
        existente.setUsuario(nuevoUsuario);

        Reserva actualizada = service.actualizar(existente, existente.getId());

        assertNotNull(actualizada);
        assertEquals(reserva.getId(), actualizada.getId());
        assertEquals(nuevoUsuario, actualizada.getUsuario());

        System.out.println("Nombre Actualizado: " + actualizada.getUsuario());
    }

    //Eliminación de una reserva
    @Test
    @Order(5)
    void testDeleteReserva() {
        service.eliminar(reserva.getId());
        Reserva resultado = service.obtenerPorId(reserva.getId());

        assertNull(resultado);

        System.out.println("Reserva eliminada con ID: " + reserva.getId());
    }
}
package com.project.bookingya;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


public class ReservaTest {

    private static ReservaService service = new ReservaService();
    private static Reserva reserva;
    ;


    //Creación de una reserva
    @Test
    @Order(1)
    void testCreateReserva() {
        reserva = new Reserva("Yeimy Marin", "2026-05-01");
        Reserva resultado = service.crearReserva(reserva);

        System.out.println("Reserva creada con ID: " + resultado.getId() + " | Nombre Usuario: " + resultado.getUsuario());

        assertNotNull(resultado);
    }

    @Test
    @Order(2)
    void testGetReservaPorUsuario() {
        Reserva resultado = service.obtenerPorUsuario("Yeimy Marin");

        System.out.println("Nombre Usuario: " + resultado.getUsuario());

        assertNotNull(resultado);
    }

    @Test
    @Order(3)
    void testGetReservaPorId() {
        Reserva resultado = service.obtenerPorId(reserva.getId());

        System.out.println("ID Usuario: " + resultado.getId());

        assertNotNull(resultado);
    }

    @Test
    @Order(4)
    void testUpdateReserva() {
        Reserva existente = service.obtenerPorId(reserva.getId());
        existente.setUsuario("Adri Sosa");
        Reserva actualizada = service.actualizar(existente, existente.getId());

        System.out.println("Nombre Actualizado: " + actualizada.getUsuario() + " | ID: " + actualizada.getId());

        assertNotNull(actualizada);
        assertEquals("Adri Sosa", actualizada.getUsuario());
    }

    @Test
    @Order(5)
    void testDeleteReserva() {
        service.eliminar(reserva.getId());

        System.out.println("Reserva eliminada con ID: " + reserva.getId());

        Reserva resultado = service.obtenerPorId(reserva.getId());
        assertNull(resultado);
    }
}
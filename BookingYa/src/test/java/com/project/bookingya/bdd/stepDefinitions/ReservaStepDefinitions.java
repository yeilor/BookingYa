package com.project.bookingya.bdd.stepDefinitions;

import com.project.bookingya.tdd.Reserva;
import com.project.bookingya.tdd.ReservaService;
import io.cucumber.java.en.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ReservaStepDefinitions {

    private ReservaService service = new ReservaService();
    private Reserva reservaRequest;
    private Reserva reservaResponse;
    private Integer responseStatus;

    @Given("que el usuario tiene los datos de una reserva")
    public void queElUsuarioTieneLosDatosDeUnaReserva() {
        // Simulamos los datos de entrada (como vendrían del feature o Excel en futuro)
        reservaRequest = new Reserva("Yeimy Marin", "2026-05-01");
    }

    @When("envía la solicitud para crear la reserva")
    public void envíaLaSolicitudParaCrearLaReserva() {
        reservaResponse = service.crearReserva(reservaRequest);

        // simulación de HTTP status
        responseStatus = (reservaResponse != null) ? 201 : 400;
    }

    @Then("el sistema responde con estado {int}")
    public void elSistemaRespondeConEstado(Integer statusEsperado) {
        assertEquals(statusEsperado, responseStatus);
    }

    @Then("la reserva queda creada correctamente")
    public void laReservaQuedaCreadaCorrectamente() {
        assertNotNull(reservaResponse);
        assertEquals(reservaRequest.getUsuario(), reservaResponse.getUsuario());
        assertEquals(reservaRequest.getFecha(), reservaResponse.getFecha());
    }
}
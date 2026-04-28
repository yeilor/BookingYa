package com.project.bookingya.bdd.stepDefinitions;

import com.project.bookingya.tdd.Reserva;
import com.project.bookingya.tdd.ReservaService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ReservaStepDefinitions {

    private ReservaService service = new ReservaService();
    private Reserva reservaRequest;
    private Reserva reservaResponse;
    private Integer responseStatus;

    @Given("que el usuario tiene los datos de una reserva")
    public void elUsuarioTieneLosDatos(io.cucumber.datatable.DataTable dataTable) {

        var rows = dataTable.asMaps();
        var row = rows.get(0);

        String usuario = row.get("usuario");
        String fecha = row.get("fecha");

        reservaRequest = new Reserva(usuario, fecha);
    }

    @When("envía la solicitud para crear la reserva")
    public void envíaLaSolicitudParaCrearLaReserva() {
        reservaResponse = service.crearReserva(reservaRequest);

        // Simulación de HTTP status
        responseStatus = (reservaResponse != null) ? 201 : 400;
    }

    @Then("el sistema responde con estado {int}")
    public void elSistemaRespondeConEstado(Integer statusEsperado) {
        assertEquals(statusEsperado, responseStatus);
    }

    @And("la reserva queda creada correctamente")
    public void laReservaQuedaCreadaCorrectamente() {
        assertNotNull(reservaResponse);
        assertEquals(reservaRequest.getUsuario(), reservaResponse.getUsuario());
        assertEquals(reservaRequest.getFecha(), reservaResponse.getFecha());

        System.out.println("Reserva: Usuario=" + reservaResponse.getUsuario() +
                ", Fecha=" + reservaResponse.getFecha() +
                ", Status=" + responseStatus);
    }

    @Given("que existe una reserva creada")
    public void queExisteUnaReservaCreada(io.cucumber.datatable.DataTable dataTable) {

        var row = dataTable.asMaps().get(0);

        String usuario = row.get("usuario");
        String fecha = row.get("fecha");

        reservaRequest = new Reserva(usuario, fecha);
        reservaResponse = service.crearReserva(reservaRequest);
    }

    @When("el usuario consulta la reserva por ID")
    public void elUsuarioConsultaLaReservaPorID() {
        reservaResponse = service.obtenerPorId(reservaResponse.getId());
        responseStatus = (reservaResponse != null) ? 200 : 404;
    }

    @Given("hay una reserva creada")
    public void hayUnaReservaCreada(DataTable dataTable) {

        List<Map<String, String>> rows = dataTable.asMaps();
        Map<String, String> row = rows.get(0);

        String usuario = row.get("usuario");
        String fecha = row.get("fecha");

        reservaRequest = new Reserva(usuario, fecha);
        reservaResponse = service.crearReserva(reservaRequest);
    }

    @When("el usuario actualiza la información de la reserva")
    public void elUsuarioActualizaLaInformaciónDeLaReserva(io.cucumber.datatable.DataTable dataTable) {

        var row = dataTable.asMaps().get(0);

        reservaResponse.setUsuario(row.get("usuario"));
        reservaResponse.setFecha(row.get("fecha"));

        reservaResponse = service.actualizar(reservaResponse, reservaResponse.getId());
        responseStatus = 200;
    }

    @Given("existe una reserva creada")
    public void existeUnaReservaCreada(io.cucumber.datatable.DataTable dataTable) {

        var rows = dataTable.asMaps(String.class, String.class);
        var row = rows.get(0);

        reservaRequest = new Reserva(
                row.get("usuario"),
                row.get("fecha")
        );

        reservaResponse = service.crearReserva(reservaRequest);
    }

    @When("el usuario elimina la reserva")
    public void elUsuarioEliminaLaReserva() {
        service.eliminar(reservaResponse.getId());
        reservaResponse = service.obtenerPorId(reservaResponse.getId());
        responseStatus = (reservaResponse == null) ? 200 : 400;
    }

    @Given("que existen reservas en el sistema")
    public void queExistenReservasEnElSistema(io.cucumber.datatable.DataTable dataTable) {

        var rows = dataTable.asMaps();

        for (var row : rows) {
            service.crearReserva(
                    new Reserva(row.get("usuario"), row.get("fecha"))
            );
        }
    }

    @When("el usuario consulta todas las reservas")
    public void elUsuarioConsultaTodasLasReservas() {

        var lista = service.obtenerTodas();
        responseStatus = (lista != null && !lista.isEmpty()) ? 200 : 404;

    }
}
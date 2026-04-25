Feature: Gestión de reservas en la plataforma

  Scenario: Crear una reserva
    Given que el usuario tiene los datos de una reserva
    When envía la solicitud para crear la reserva
    Then el sistema responde con estado 201
    And la reserva queda creada correctamente

  Scenario: Consultar una reserva por ID
    Given que existe una reserva creada
    When el usuario consulta la reserva por ID
    Then el sistema responde con estado 200

  Scenario: Actualizar una reserva
    Given que existe una reserva creada
    When el usuario actualiza la información de la reserva
    Then el sistema responde con estado 200

  Scenario: Eliminar una reserva
    Given que existe una reserva creada
    When el usuario elimina la reserva
    Then el sistema responde con estado 200

  Scenario: Consultar todas las reservas
    Given que existen reservas en el sistema
    When el usuario consulta todas las reservas
    Then el sistema responde con estado 200
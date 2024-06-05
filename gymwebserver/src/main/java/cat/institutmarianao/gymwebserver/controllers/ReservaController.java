package cat.institutmarianao.gymwebserver.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.institutmarianao.gymwebserver.model.Reserva;
import cat.institutmarianao.gymwebserver.model.dto.ReservaDto;
import cat.institutmarianao.gymwebserver.services.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Tag(name = "Reserva", description = "ReservaController API")
@RestController
@RequestMapping("/reservations")
@Validated
public class ReservaController {
	
	@Autowired
    private ReservaService reservaService;

    @Autowired
    private ConversionService conversionService;

    @Operation(summary = "Find all reservas")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ReservaDto.class))) }, description = "Reservations retrieved ok")
    @GetMapping("/find/all")
    public List<ReservaDto> findAll() {
        List<Reserva> reservas = reservaService.findAll();

        List<ReservaDto> reservasDto = new ArrayList<>(reservas.size());
        for (Reserva reserva : reservas) {
            ReservaDto reservaDto = conversionService.convert(reserva, ReservaDto.class);
            reservasDto.add(reservaDto);
        }
        return reservasDto;
    }

    @Operation(summary = "Get reservation by id")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ReservaDto.class)) }, description = "Reservation retrieved ok")
    @ApiResponse(responseCode = "404", content = {
            @Content(mediaType = "application/json") }, description = "Reservation not found")
    @GetMapping("/get/by/id/{id}")
    public ReservaDto findById(@PathVariable("id") @Positive Long id) {
        Reserva Reserva = reservaService.getById(id);
        return conversionService.convert(Reserva, ReservaDto.class);
    }

    @Operation(summary = "Save a reservation")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ReservaDto.class)) }, description = "Reservation saved ok")
    @PostMapping("/save")
    public ReservaDto save(
            @Parameter(schema = @Schema(implementation = ReservaDto.class)) @RequestBody @Valid ReservaDto ReservaDto) {
    	Reserva Reserva = conversionService.convert(ReservaDto, Reserva.class);
    	Reserva savedReserva = reservaService.save(Reserva);
    	return conversionService.convert(savedReserva, ReservaDto.class);
    }

    @Operation(summary = "Delete a reservation")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json") }, description = "Reservation deleted ok")
    @DeleteMapping("/delete/by/id/{id}")
    public void deleteById(@PathVariable("id") @Positive Long id) {
    	reservaService.deleteById(id);
    }

}

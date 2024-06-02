package cat.institutmarianao.gymwebserver.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.institutmarianao.gymwebserver.model.Sala;
import cat.institutmarianao.gymwebserver.services.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;

@Tag(name = "Rooms", description = "RoomController API")

@RestController
@RequestMapping("/rooms")
@Validated
public class SalaController {
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private ConversionService conversionService;
	
	@Operation(summary = "Find all rooms")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Sala.class))) }, description = "Rooms retrieved ok")

	@GetMapping(value = "/find/all")
	public List<Sala> findAll() {
		// find all offices
		List<Sala> sales = salaService.findAll();

		List<Sala> salaList = new ArrayList<>(sales.size());
		for (Sala sala : sales) {
			Sala salaConverted = conversionService.convert(sala, Sala.class);
			salaList.add(salaConverted);
		}
		return salaList;
	}

	@Operation(summary = "Get rooms by id")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Sala.class)) }, description = "Room retrieved ok")
	@ApiResponse(responseCode = "404", content = {
			@Content(mediaType = "application/json") }, description = "Room not found")

	@GetMapping("/get/by/id/{id}")
	public Sala getById(@PathVariable("id") @Positive Long id) {
		// TODO find an office by id
		return salaService.getById(id);
	}

}

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

import cat.institutmarianao.gymwebserver.model.Clase;
import cat.institutmarianao.gymwebserver.model.dto.ClaseDto;
import cat.institutmarianao.gymwebserver.services.ClaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Tag(name = "Clase", description = "ClaseController API")
@RestController
@RequestMapping("/classes")
@Validated
public class ClaseController {

    @Autowired
    private ClaseService claseService;

    @Autowired
    private ConversionService conversionService;

    @Operation(summary = "Find all classes")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClaseDto.class))) }, description = "Classes retrieved ok")
    @GetMapping("/find/all")
    public List<ClaseDto> findAll() {
        List<Clase> clases = claseService.findAll();

        List<ClaseDto> clasesDto = new ArrayList<>(clases.size());
        for (Clase clase : clases) {
            ClaseDto claseDto = conversionService.convert(clase, ClaseDto.class);
            clasesDto.add(claseDto);
        }
        return clasesDto;
    }

    @Operation(summary = "Get class by id")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ClaseDto.class)) }, description = "Class retrieved ok")
    @ApiResponse(responseCode = "404", content = {
            @Content(mediaType = "application/json") }, description = "Class not found")
    @GetMapping("/get/by/id/{id}")
    public ClaseDto findById(@PathVariable("id")@NotNull @Positive Long id) {
        Clase clase = claseService.getById(id);
        return conversionService.convert(clase, ClaseDto.class);
    }

    @Operation(summary = "Save a class")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ClaseDto.class)) }, description = "Class saved ok")
    @PostMapping("/save")
    public ClaseDto save(
            @Parameter(schema = @Schema(implementation = ClaseDto.class)) @RequestBody @Valid ClaseDto claseDto) {
    	Clase clase = conversionService.convert(claseDto, Clase.class);
    	Clase savedClase = claseService.save(clase);
    	return conversionService.convert(savedClase, ClaseDto.class);
    }

    @Operation(summary = "Delete a class")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json") }, description = "Class deleted ok")
    @DeleteMapping("/delete/by/id/{id}")
    public void deleteById(@PathVariable("id")@NotNull @Positive Long id) {
        claseService.deleteById(id);
    }
}

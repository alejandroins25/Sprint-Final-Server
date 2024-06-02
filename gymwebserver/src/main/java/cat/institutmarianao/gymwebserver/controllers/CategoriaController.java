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

import cat.institutmarianao.gymwebserver.model.Categoria;
import cat.institutmarianao.gymwebserver.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;

@Tag(name = "Categories", description = "CategoriaController API")
@RestController
@RequestMapping("/categories")
@Validated
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    @Autowired
    private ConversionService conversionService;
    
    @Operation(summary = "Find all categories")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Categoria.class))) }, description = "Categories retrieved ok")
    @GetMapping(value = "/find/all")
    public List<Categoria> findAll() {
        List<Categoria> categorias = categoriaService.findAll();

        List<Categoria> categoriaList = new ArrayList<>(categorias.size());
        for (Categoria categoria : categorias) {
            Categoria categoriaConverted = conversionService.convert(categoria, Categoria.class);
            categoriaList.add(categoriaConverted);
        }
        return categoriaList;
    }

    @Operation(summary = "Get category by id")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) }, description = "Category retrieved ok")
    @ApiResponse(responseCode = "404", content = {
            @Content(mediaType = "application/json") }, description = "Category not found")
    @GetMapping("/get/by/id/{id}")
    public Categoria getById(@PathVariable("id") @Positive Long id) {
        return categoriaService.getById(id);
    }
}

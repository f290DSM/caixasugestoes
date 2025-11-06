package dev.sdras.caixasugestoes.resources;

import dev.sdras.caixasugestoes.domain.dtos.CategoriaDTO;
import dev.sdras.caixasugestoes.services.CategoriaService;
import jakarta.validation.Valid;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/categorias")
public class CategoriasResource {

    private final CategoriaService categoriaService;

    public CategoriasResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody CategoriaDTO dto) {
        try {
            CategoriaDTO categoria = categoriaService.salvar(dto);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(categoria.getId())
                    .toUri();
            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        categoriaService.excluir(id);
    }
}

package dev.sdras.caixasugestoes.resources;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/caixasugestoes")
public class SugestoesResource {

    private final ModelMapper modelMapper;

    public SugestoesResource(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<?> buscarSugestoes() {
        final List<String> sugestoes = List.of("Festa do Halloween",
                "Noite Mexicana",
                "Festival Food Truck");

        return ResponseEntity.ok(sugestoes);
    }
}

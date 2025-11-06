package dev.sdras.caixasugestoes.services;

import dev.sdras.caixasugestoes.config.exception.RecursoNaoLocalizadoException;
import dev.sdras.caixasugestoes.domain.CategoriaEntity;
import dev.sdras.caixasugestoes.domain.dtos.CategoriaDTO;
import dev.sdras.caixasugestoes.respositories.CategoriaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;
    private final ModelMapper modelMapper;

    public CategoriaService(CategoriaRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public CategoriaDTO salvar(CategoriaDTO dto) {
        CategoriaEntity entity = modelMapper.map(dto, CategoriaEntity.class);
        var entitySaved = repository.save(entity);
        return modelMapper.map(entitySaved, CategoriaDTO.class);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public List<CategoriaDTO> listar() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, CategoriaDTO.class))
                .collect(Collectors.toList());
    }

    public CategoriaDTO buscarPorId(Long id) throws RecursoNaoLocalizadoException {
        Optional<CategoriaEntity> entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new RecursoNaoLocalizadoException("Categoria nao encontrada");
        }
        return modelMapper.map(entity.get(), CategoriaDTO.class);
    }
}

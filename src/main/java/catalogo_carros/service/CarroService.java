package catalogo_carros.service;

import catalogo_carros.model.Carro;
import catalogo_carros.repository.CarroRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    private final CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public List<Carro> listarTodos() {
        return carroRepository.findAll();
    }

    public Carro salvar(Carro carro) {
        return carroRepository.save(carro);
    }

    public Optional<Carro> buscarPorId(Long id) {
        return carroRepository.findById(id);
    }

    public void excluir(Long id) {
        carroRepository.deleteById(id);
    }

    public List<Carro> buscarPorModelo(String modelo) {
        return carroRepository.findByModeloIgnoreCase(modelo);
    }

    public List<Carro> listarOrdenadoPorPreco(String direcao) {
        Sort sort;

        if (direcao.equalsIgnoreCase("desc")) {
            sort = Sort.by("preco").descending();
        } else {
            sort = Sort.by("preco").ascending();
        }
        return carroRepository.findAll(sort);
    }

    public boolean placaPertenceAOutroCarro(String placa, Long id) {
        Optional<Carro> carroComPlaca = carroRepository.findByPlacaIgnoreCase(placa);
        if (carroComPlaca.isEmpty()) {
            return false;
        }
        if (id == null) {
            return true;
        }
        return !carroComPlaca.get().getId().equals(id);
    }
}
package catalogo_carros.service;

import catalogo_carros.model.Carro;
import catalogo_carros.repository.CarroRepository;
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
}
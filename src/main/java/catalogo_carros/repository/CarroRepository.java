package catalogo_carros.repository;

import catalogo_carros.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByModeloIgnoreCase(String modelo);

    Optional<Carro> findByPlacaIgnoreCase(String placa);
}

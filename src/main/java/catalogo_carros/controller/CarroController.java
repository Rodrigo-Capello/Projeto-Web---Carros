package catalogo_carros.controller;

import catalogo_carros.model.Carro;
import catalogo_carros.service.CarroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping("/carros")
    public String listarCarros(Model model) {
        model.addAttribute("carros", carroService.listarTodos());
        return "carros";
    }

    @GetMapping("/carros/novo")
    public String novoCarro(Model model) {
        model.addAttribute("carro", new Carro());
        return "formulario";
    }

    @PostMapping("/carros/salvar")
    public String salvarCarro(@ModelAttribute Carro carro) {
        carroService.salvar(carro);
        return "redirect:/carros";
    }

    @GetMapping("/carros/editar/{id}")
    public String editarCarro(@PathVariable Long id, Model model) {
        Carro carro = carroService.buscarPorId(id).orElseThrow();
        model.addAttribute("carro", carro);
        return "formulario";
    }

    @PostMapping("/carros/excluir/{id}")
    public String excluirCarro(@PathVariable Long id) {
        carroService.excluir(id);
        return "redirect:/carros";
    }
}
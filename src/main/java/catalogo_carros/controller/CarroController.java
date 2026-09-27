package catalogo_carros.controller;

import catalogo_carros.model.Carro;
import catalogo_carros.service.CarroService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String salvarCarro(@Valid @ModelAttribute Carro carro,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "formulario";
        }

        if (carroService.placaPertenceAOutroCarro(carro.getPlaca(), carro.getId())) {
            model.addAttribute("erro", "Já existe um carro registrado com essa placa.");
            return "formulario";
        }

        carroService.salvar(carro);

        redirectAttributes.addFlashAttribute("sucesso", "Carro cadastrado com sucesso.");

        return "redirect:/carros";
    }


    @GetMapping("/carros/editar/{id}")
    public String editarCarro(@PathVariable Long id, Model model) {
        Carro carro = carroService.buscarPorId(id).orElseThrow();
        model.addAttribute("carro", carro);
        return "formulario";
    }


    @PostMapping("/carros/excluir/{id}")
    public String excluirCarro(@PathVariable Long id,
                               RedirectAttributes redirectAttributes) {

        carroService.excluir(id);

        redirectAttributes.addFlashAttribute("sucesso", "Carro excluído com sucesso.");

        return "redirect:/carros";
    }


    @GetMapping("/carros/pesquisar")
    public String pesquisarCarros(@RequestParam String modelo, Model model) {
        model.addAttribute("carros", carroService.buscarPorModelo(modelo));
        return "carros";
    }


    @GetMapping("/carros/ordenar")
    public String ordenarCarros(@RequestParam String direcao, Model model) {
        model.addAttribute("carros", carroService.listarOrdenadoPorPreco(direcao));
        return "carros";
    }

}
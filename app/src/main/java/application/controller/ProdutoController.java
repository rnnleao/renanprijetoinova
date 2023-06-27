package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Produto;
import application.model.ProdutoRepository;

@Controller
@RequestMapping("/Produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepo;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("produtos", produtoRepo.findAll());
        return "/produto/list";
    }

    @RequestMapping("/insert")
    public String insert() {
        return "/produto/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(
            @RequestParam("titulo") String titulo,
            @RequestParam("descricao") String descricao) {
        Produto produto = new Produto();
        produto.setTitulo(titulo);
        produto.setDescricao(descricao);

        produtoRepo.save(produto);
        return "redirect:/produto/list";
    }

}

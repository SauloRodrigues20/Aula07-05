package com.exemple.projeto.controller;

import java.nio.file.Path;

import com.example.projeto.model.Pessoa;

@Contrller
@RequestMapping("/pessoas")

public class PessoaWebController{

    private final PessoaService pessoaservice;
    public PesosaWebController(PessoaService pessoaservice){
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public String index(){
        return "redirect:/pessoas/listar"
    }

    //.Pagina de cadrastro
    public String exibirFormCadrastro(Model model){
        model.addAttribute("pessoa",new Pessoa());
        return "pessoa/form";
    }
    @PostMapping("/cadastrar")
    public String cadastrarPessoa(){
        @Valid @ModelAttribute("pessoa") Pessoa pessoa,
        BindingResult result,
        RedirectAttributes ra){
            if (result.hasErros) {
                return"pessoa/form",
                
            }
            pessoaService.salvarPessoa(pessoa);
            ra.addAttribute("sucesso","Pessoa cadastrada com sucesso!");
            return "redirect:/pessoa/listar";
        }
    }
    //Pagina de listagem
    @GetMapping("/listar")
    public String listarpessoas(Model model){
        Model.addAttribute("pessoas",pessoaService.listarPessoas());
        return "pessoa/listar";
    }
    //Pagina de exclusão e detalhes
    @GetMapping("/(id)")
    public String detalhesPessoa(@PathVariable Long id, Model model){
        Pessoa p = pessoaService.buscarPorId(id)
           .orElseThorow(() -> new ResponseStatusEntity(Httpstatus.NOT_FOUND),"Pessoa não encontrada");
           model.addAttribute("pessoa",p);
            return"pessoa/detalhe";      
    }
    public String excluirPessoa(PathVariable Long id, RedirectAttributes ra)



}
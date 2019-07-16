package guru.springframework.controllers;

import guru.springframework.commands.RepoForm;
import guru.springframework.converters.RepoToRepoForm;
import guru.springframework.domain.Repo;
import guru.springframework.services.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by jt on 1/10/17.
 */
@Controller
public class RepoController {
    private RepoService repoService;

    private RepoToRepoForm repoToRepoForm;

    @Autowired
    public void setRepoToRepoForm(RepoToRepoForm repoToRepoForm) {
        this.repoToRepoForm = repoToRepoForm;
    }

    @Autowired
    public void setRepoService(RepoService repoService) {
        this.repoService = repoService;
    }

    @GetMapping("/")
    public String redirToList(){
        return "redirect:/repo/list";
    }

    @GetMapping({"/repo/list", "/repo"})
    public String listProducts(Model model){
        model.addAttribute("repos", repoService.listAll());
        return "repo/list";
    }

    @GetMapping("/repo/show/{id}")
    public String getProduct(@PathVariable String id, Model model){
        model.addAttribute("repo", repoService.getById(Long.valueOf(id)));
        return "repo/show";
    }

    @GetMapping("repo/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Repo repo = repoService.getById(Long.valueOf(id));
        RepoForm repoForm = repoToRepoForm.convert(repo);

        model.addAttribute("repoForm", repoForm);
        return "repo/repoform";
    }

    @RequestMapping("/repo/new")
    public String newProduct(Model model){
        model.addAttribute("repoForm", new RepoForm());
        return "repo/repoform";
    }

    @RequestMapping(value = "/repo", method = RequestMethod.POST)
    public String saveOrUpdateProduct(
            @Valid RepoForm repoForm,
            BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "repo/repoform";
        }

        Repo savedRepo = repoService.saveOrUpdateProductForm(repoForm);

        return "redirect:/repo/show/" + savedRepo.getId();
    }

    @RequestMapping("/repo/delete/{id}")
    public String delete(@PathVariable String id){
        repoService.delete(Long.valueOf(id));
        return "redirect:/repo/list";
    }
}

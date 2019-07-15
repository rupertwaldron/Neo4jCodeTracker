package guru.springframework.controllers;

import guru.springframework.commands.RepoForm;
import guru.springframework.converters.LibraryToRepoForm;
import guru.springframework.domain.Library;
import guru.springframework.services.LibraryService;
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
public class LibraryController {
    private LibraryService libraryService;

    private LibraryToRepoForm libraryToRepoForm;

    @Autowired
    public void setLibraryToRepoForm(LibraryToRepoForm libraryToRepoForm) {
        this.libraryToRepoForm = libraryToRepoForm;
    }

    @Autowired
    public void setLibraryService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping({"/library/list", "/library"})
    public String listLibraries(Model model){
        model.addAttribute("libraries", libraryService.listAll());
        return "library/list";
    }

    @GetMapping("/library/show/{id}")
    public String getLibrary(@PathVariable String id, Model model){
        model.addAttribute("library", libraryService.getById(Long.valueOf(id)));
        return "library/show";
    }

    @GetMapping("library/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Library library = libraryService.getById(Long.valueOf(id));
        RepoForm repoForm = libraryToRepoForm.convert(library);
        model.addAttribute("repoForm", repoForm);
        return "library/repoform";
    }

    @RequestMapping("/library/new")
    public String newLibrary(Model model){
        model.addAttribute("repoForm", new RepoForm());
        return "library/repoform";
    }

    @RequestMapping(value = "/library", method = RequestMethod.POST)
    public String saveOrUpdateLibrary(
            @Valid RepoForm repoForm,
            BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "library/repoform";
        }

        Library savedLibrary = libraryService.saveOrUpdateProductForm(repoForm);

        return "redirect:/library/show/" + savedLibrary.getId();
    }

    @RequestMapping("/library/delete/{id}")
    public String delete(@PathVariable String id){
        libraryService.delete(Long.valueOf(id));
        return "redirect:/library/list";
    }
}

package guru.springframework.controllers;

import guru.springframework.commands.LinkForm;
import guru.springframework.services.LibraryService;
import guru.springframework.services.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by jt on 1/10/17.
 */
@Controller
public class RelationshipController {
    private RepoService repoService;
    private LibraryService libraryService;

    private LinkForm linkForm;

    @Autowired
    public void setLinkForm(LinkForm linkForm) {
        this.linkForm = linkForm;
    }

    @Autowired
    public void setRepoService(RepoService repoService) {
        this.repoService = repoService;
    }

    @Autowired
    public void setLibraryService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }


    @GetMapping("/relationship/list")
    public String list(Model model){
        model.addAttribute("linkForm", linkForm);
        return "relationship/list";
    }

    @GetMapping("/relationship")
    public String link(Model model){
        model.addAttribute("linkForm", linkForm);
        model.addAttribute("libraries", libraryService.listAll());
        model.addAttribute("repos", repoService.listAll());
        return "relationship/linkForm";
    }

    @RequestMapping(value = "/relationship", method = RequestMethod.POST)
    public String addLink(@Valid LinkForm linkForm){
        repoService.createLibraryLink(linkForm.getRepoName(), linkForm.getLibraryName());

        return "redirect:relationship/linkForm";
    }

}

package guru.springframework.controllers;

import guru.springframework.commands.LinkForm;
import guru.springframework.services.LibraryService;
import guru.springframework.services.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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




    @GetMapping("relationship/linkForm")
    public String link(Model model){
        model.addAttribute("repos", repoService.listAll());
        model.addAttribute("libraries", libraryService.listAll());
        model.addAttribute("linkForm", linkForm);
        return "relationship/list";
    }

}

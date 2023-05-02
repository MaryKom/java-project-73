package hexlet.code.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public final class WelcomeController {
    @GetMapping(path = "/")
    public RedirectView root() {
        return new RedirectView("/login");
    }

}

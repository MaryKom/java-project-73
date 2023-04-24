package hexlet.code.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public final class WelcomeController {
    @GetMapping(path = "/")
    public String root() {
        return new RedirectView("/api/users");
    }

    @GetMapping(path = "/rollbar")
    public String rollbarSend() {
        rollbar.debug("Here is some debug message");
        return "Rollbar message send";
    }
}

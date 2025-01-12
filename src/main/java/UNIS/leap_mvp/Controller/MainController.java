package UNIS.leap_mvp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String aboutService() {
        return "pages/home";
    }

    @GetMapping("/serviceSimulation")
    public String serviceSimulation(){
        return "pages/serviceSimulation";
    }

    @GetMapping("/members")
    public String members(){
        return "pages/members";
    }
}

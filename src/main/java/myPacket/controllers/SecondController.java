package myPacket.controllers;

import lombok.AllArgsConstructor;
import myPacket.dto.RequestDTO;
import myPacket.service.CalculateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class SecondController {

    private RestController1 restController1;
    private CalculateService calcService;

    @PostMapping("/calc")
    public String printResult(@ModelAttribute RequestDTO request, Model model) {
        restController1.calculateAndCreateOrUpdate(request);
        model.addAttribute("result", calcService.getRes());
        model.addAttribute("requestDTO", request);
        return "request-page";
    }

    @GetMapping("/calc")
    public String calcEmptyPage(Model model) {
        if (!model.containsAttribute("requestDTO")) {
            model.addAttribute("requestDTO", new RequestDTO());
        }
        return "request-page";
    }
}

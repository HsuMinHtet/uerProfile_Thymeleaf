package edu.miu.cs489.postgresgradle.userprofilemanagement.controller;

import edu.miu.cs489.postgresgradle.userprofilemanagement.dto.request.ProfileRequestDTO;
import edu.miu.cs489.postgresgradle.userprofilemanagement.dto.request.UserRequestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/home")
public class HomeController {

    @GetMapping
    public String home(Model model){
        model.addAttribute("courseName","CS489");
        model.addAttribute(
                "userRequestDTO",
                new UserRequestDTO(
                        null, null, new ProfileRequestDTO(null, null, null)
                )
        );
        return "home_page";
    }

}

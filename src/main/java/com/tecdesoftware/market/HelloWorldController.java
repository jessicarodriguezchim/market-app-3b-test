package com.tecdesoftware.market;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludar")
public class HelloWorldController {

    @RequestMapping("/hola")
    public String saludar(){
        return"Bye World, Jaime🎉❤️🔥👌😊";
    }
}

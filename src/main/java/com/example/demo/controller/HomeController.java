
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

<<<<<<< HEAD
@RestController
public class HomeController {
    @GetMapping("/")
    public String index() {
            return "HELLO";
=======
@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
            return "hello";
>>>>>>> 90993b3d59b2cd3b4256c21b0ac48ad375b018a0
    }
}

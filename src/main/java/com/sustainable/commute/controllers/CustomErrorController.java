package com.sustainable.commute.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError() {
        // Forward to the custom error page
        return "error";
    }

    @Override
    public String getErrorPath() {
        // Return the path for the error mapping
        return "/error";
    }
}

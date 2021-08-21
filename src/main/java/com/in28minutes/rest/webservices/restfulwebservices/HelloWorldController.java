package com.in28minutes.rest.webservices.restfulwebservices;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloWorldController {

	@Value("${mymessage}")
    private String message; 

    @GetMapping("/getMessage")
    public String getMessage(Model model) {

        model.addAttribute("message", message);

        return "show";
    }

    @GetMapping("/getMessage2")
    public ModelAndView getMessage() {

        ModelAndView mav = new ModelAndView();

        mav.addObject("message", message);
        mav.setViewName("index"); 

        return mav;
    }

    @GetMapping("/getMessageAndTime")
    public String getMessageAndTime(ModelMap map) {

         LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter ofLocalizedDateTime = DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.MEDIUM);

        ofLocalizedDateTime.withLocale(new Locale("sk", "SK"));
        ofLocalizedDateTime.withZone(ZoneId.of("CET"));
        String time = ofLocalizedDateTime.format(now);

        map.addAttribute("message", message).addAttribute("time", time);

        return "show";
    }
}

package myapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @RequestMapping(value ="/test", method = RequestMethod.GET)
    public String test(@RequestParam(value="name", defaultValue="World") String name) {
        return "{\"id\":123}";
    } 
}

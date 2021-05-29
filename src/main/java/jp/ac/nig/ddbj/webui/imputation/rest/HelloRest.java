package jp.ac.nig.ddbj.webui.imputation.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/webui/rest")
@Slf4j
public class HelloRest {


    @CrossOrigin
    @GetMapping("/hello")
    public HelloResult getHello() {
        String message = "GET: Greetings from a RESTful API in the Web UI.";
        HelloResult result = new HelloResult();
        result.setMessage(message);
        log.info(message);

        return result;
    }


    @CrossOrigin
    @PostMapping("/hello")
    public  HelloResult postHello() {
        String message = "Greetings from a RESTful API in the Web UI.";
        HelloResult result = new HelloResult();
        result.setMessage(message);
        log.info(message);

        return result;
    }


}

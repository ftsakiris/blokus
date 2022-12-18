package com.example.playground.rest;

import com.example.playground.service.NumberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.example.playground.PlayGroundApplication.REST_PATH;

@RestController
public class NumberRestController {

    private static final String SVC_PATH = REST_PATH + "/number";

    private final NumberService numberService;

    public NumberRestController(NumberService numberService) {
        this.numberService = numberService;
    }

    @RequestMapping(value = SVC_PATH + "/prime" + "/{number}", method = RequestMethod.GET)
    public ResponseEntity<?> isPrime(@PathVariable Integer number) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Application", "PlayGroundApplication");
        return new ResponseEntity<>(numberService.isPrimeNumber(number), httpHeaders, HttpStatus.OK);
    }
}

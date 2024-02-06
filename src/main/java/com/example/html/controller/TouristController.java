package com.example.html.controller;
import com.example.html.model.TouristAttraction;
import com.example.html.service.TouristService;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "attractions")       //localhost:8080/attractions
public class TouristController {


    private TouristService touristService;


    public TouristController() {
        this.touristService = new TouristService();
    }




    @GetMapping(path = "/list")     //localhost:8080/kea/velkommen
    public ResponseEntity<ArrayList<Message>> getMessages() {
        ArrayList messageList = touristService.getAttractions();
        return new ResponseEntity<ArrayList<Message>>(messageList, HttpStatus.OK);
    }


    @GetMapping(path = "/{name}")    //localhost:8080/kea/velkommen/1
    public ResponseEntity<String> getMessage(@PathVariable String name) {

        touristService.getAttraction(name);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type","text/html");


        return new ResponseEntity<String>(
                "<html><body><h1>" +
                        touristService.getAttraction(name) +
                        "</h1></body></html>"
                ,responseHeaders, HttpStatus.OK);
    }




    @PostMapping(path = "/add")      //localhost:8080/kea/velkommen/opret
    public ResponseEntity<TouristAttraction> postAttraction(@RequestBody String name, String description) {
        TouristAttraction returnTouristAttraction = touristService.postAttractions(name, description);
        return new ResponseEntity<TouristAttraction>(returnTouristAttraction, HttpStatus.OK);
    }


    @PutMapping(path = "/update")     //localhost:8080/kea/velkommen/ret
    public ResponseEntity<TouristAttraction> putAttraction(@RequestBody String name, String description) {
        TouristAttraction returnTouristAttraction = touristService.putAttractions(name, description);
        if (returnTouristAttraction!=null)
            return new ResponseEntity<TouristAttraction>(returnTouristAttraction, HttpStatus.OK);
        else
            return new ResponseEntity<TouristAttraction>(new TouristAttraction("Ikke fundet", "Ikke fundet"), HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/delete/{name}")
    public ResponseEntity<TouristAttraction> deleteMessage(@RequestBody String name) {
        TouristAttraction returnAttraction = touristService.deleteAttractions(name);
        return new ResponseEntity<TouristAttraction>(returnAttraction, HttpStatus.OK);
    }
}




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




    @GetMapping(path = "/list")         //localhost:8080/attractions/list
    public ResponseEntity<ArrayList<TouristAttraction>> getMessages() {
        ArrayList attractionList = touristService.getAttractions();
        return new ResponseEntity<ArrayList<TouristAttraction>>(attractionList, HttpStatus.OK);
    }


    @GetMapping(path = "/{name}")       //localhost:8080/attractions/name
    public ResponseEntity<String> getMessage(@PathVariable String name) {

        touristService.getAttraction(name);
        for (TouristAttraction touristAttraction : touristService.getAttraction(name)) {
            String attractionName = touristAttraction.getName();
            String attractionDecription = touristAttraction.getDescription();
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Content-Type","text/html");
            return new ResponseEntity<String>(
                    "<html><body><h1>" +
                            attractionName +
                            "</h1>" +
                            "<p>" +
                            attractionDecription +
                            "</p>" +
                            "</body>" +
                            "</html>"
                    ,responseHeaders, HttpStatus.OK);
        }
        return null;
    }




    @PostMapping(path = "/add")         //localhost:8080/attractions/add
    public ResponseEntity<TouristAttraction> postAttraction(@RequestBody TouristAttraction touristAttraction) {
        TouristAttraction returnTouristAttraction = touristService.postAttractions(touristAttraction.getName(), touristAttraction.getDescription());
        return new ResponseEntity<TouristAttraction>(returnTouristAttraction, HttpStatus.OK);
    }


    @PutMapping(path = "/update")       //localhost:8080/attractions/update
    public ResponseEntity<TouristAttraction> putAttraction(@RequestBody String name, String description) {
        TouristAttraction returnTouristAttraction = touristService.putAttractions(name, description);
        if (returnTouristAttraction!=null)
            return new ResponseEntity<TouristAttraction>(returnTouristAttraction, HttpStatus.OK);
        else
            return new ResponseEntity<TouristAttraction>(new TouristAttraction("Ikke fundet", "Ikke fundet"), HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/delete/{name}")    //localhost:8080/attraction/delete/name
    public ResponseEntity<TouristAttraction> deleteMessage(@RequestBody String name) {
        TouristAttraction returnAttraction = touristService.deleteAttractions(name);
        return new ResponseEntity<TouristAttraction>(returnAttraction, HttpStatus.OK);
    }
}




package com.example.html.service;

import com.example.html.model.TouristAttraction;
import com.example.html.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TouristService {
    private TouristRepository touristRepository;

    public TouristService() {
        this.touristRepository = new TouristRepository();
    }

    public ArrayList<TouristAttraction> getAttraction(String name) {
        return touristRepository.getTouristAttraction(name);
    }

    public ArrayList<TouristAttraction> getAttractions() {
        return touristRepository.getTouristAttractions();
    }

    public TouristAttraction postAttractions(String name, String description) {
        return touristRepository.postAttraction(name, description);
    }

    public TouristAttraction deleteAttractions(String name) {
        return touristRepository.deleteAttraction(name);
    }

    public TouristAttraction putAttractions(String name, String description) {
        return touristRepository.putAttraction(name, description);
    }
}

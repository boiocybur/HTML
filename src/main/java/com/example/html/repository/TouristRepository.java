package com.example.html.repository;

import com.example.html.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TouristRepository {
    private ArrayList<TouristAttraction> touristAttractions;

    public TouristRepository() {
        this.touristAttractions = new ArrayList<>();
        touristAttractions.add(new TouristAttraction("Amalienborg Slot", "Slot"));
        touristAttractions.add(new TouristAttraction("Tivoli", "Forlystelsespark"));
        touristAttractions.add(new TouristAttraction("Fredensborg Slot", "Slot"));
        touristAttractions.add(new TouristAttraction("København Zoo", "Zoo"));
    }

    public ArrayList<TouristAttraction> getTouristAttractions() {
        return touristAttractions;
    }

        public TouristAttraction postAttraction(String name, String description) {
            TouristAttraction touristAttraction = new TouristAttraction(name, description);
            touristAttractions.add(touristAttraction);
            return touristAttraction;
        }

        public TouristAttraction deleteAttraction(String name) {
            for (TouristAttraction attraction : touristAttractions) {
                if (attraction.getName().equals(name)) {
                    touristAttractions.remove(name);
                    return attraction;
                }
            }
            return null;
        }

        public TouristAttraction putAttraction(String name, String description){
            boolean attractionsFound = false;
            for (int i = 0; i < touristAttractions.size(); i++) {
                TouristAttraction touristAttraction = touristAttractions.get(i);
                if (touristAttraction.getName().toLowerCase().equals(name.toLowerCase())) {
                    touristAttractions.set(i, new TouristAttraction(name, description));
                    attractionsFound = true;
                    return touristAttraction;
                }
            }
            return null;
        }

        public ArrayList<TouristAttraction> getTouristAttraction(String name) {
            ArrayList<TouristAttraction> searchResult = new ArrayList<>();
            for (TouristAttraction touristAttraction : touristAttractions) {
                if (touristAttraction.getName().toLowerCase().contains(name)) {
                    searchResult.add(touristAttraction);
                }
            }
            return searchResult;
        }
    }

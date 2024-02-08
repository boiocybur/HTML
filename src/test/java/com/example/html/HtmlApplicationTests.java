package com.example.html;

import com.example.html.model.TouristAttraction;
import com.example.html.service.TouristService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HtmlApplicationTests {

    @Test
    void contextLoads() {

    }

    private TouristService touristService;

    @BeforeEach
    public void setUp() {
        // Create a new instance of TouristService with an actual TouristRepository
        touristService = new TouristService();
    }

    @Test
    public void testGetAttraction() {
        String name = "Amalienborg";
        ArrayList<TouristAttraction> result = touristService.getAttraction(name);
        assertEquals(1, result.size());
        assertEquals(name, result.get(0).getName());
    }

    @Test
    public void testGetAttractions() {
        ArrayList<TouristAttraction> result = touristService.getAttractions();
        // Assuming the repository is initialized with some tourist attractions
        // Adjust this assertion based on your actual data
        assertEquals(4, result.size());
    }

    @Test
    public void testPostAttractions() {
        String name = "New Attraction";
        String description = "Description";
        TouristAttraction newAttraction = touristService.postAttractions(name, description);
        assertEquals(name, newAttraction.getName());
        assertEquals(description, newAttraction.getDescription());
    }

    @Test
    public void testDeleteAttractions() {
        String name = "Amalienborg Slot";
        TouristAttraction deletedAttraction = touristService.deleteAttractions(name);
        assertEquals(name, deletedAttraction.getName());
    }

    @Test
    public void testPutAttractions() {
        String name = "Amalienborg Slot";
        String newDescription = "New Description";
        TouristAttraction updatedAttraction = touristService.putAttractions(name, newDescription);
        assertEquals(name, updatedAttraction.getName());
        assertEquals(newDescription, updatedAttraction.getDescription());
    }
}



package by.trubetski.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ecosystem {
    private String name;
    private List<Animal> animals;
    private List<Plant> plants;
    private int temperature;
    private int humidity;
    private int quantityWater;
    private int quantityEarth;

    public Ecosystem(String name) {
        this.name = name;
        this.plants = new ArrayList<>();
        this.animals = new ArrayList<>();
    }
}

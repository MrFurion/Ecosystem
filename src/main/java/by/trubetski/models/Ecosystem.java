package by.trubetski.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ecosystem {
    private String name;
    private List<Animal> animals = new ArrayList<>();
    private List<Plant> plants = new ArrayList<>();
    private int temperature;
    private int humidity;
    private int quantityWater;
    private int quantityEarth;

    public Ecosystem(String name, int temperature, int humidity, int quantityWater, int quantityEarth) {
        this.name = name;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.temperature = temperature;
        this.humidity = humidity;
        this.quantityWater = quantityWater;
        this.quantityEarth = quantityEarth;
    }
}

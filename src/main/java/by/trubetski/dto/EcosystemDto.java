package by.trubetski.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EcosystemDto {
    @NotNull(message = "имя недолжно буть пустым")
    private String name;
    private List<AnimalDto> animals = new ArrayList<>();
    private List<PlantDto> plants = new ArrayList<>();
    @Min(10)
    private int temperature;
    private int humidity;
    private int quantityWater;
    private int quantityEarth;
}

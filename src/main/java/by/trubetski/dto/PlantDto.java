package by.trubetski.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlantDto {
    private String name;
    private int age;
    private int weight;
    private int timeLife;
    private int count;
}

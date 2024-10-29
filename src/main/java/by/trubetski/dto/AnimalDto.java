package by.trubetski.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimalDto {
    private String name;
    private String gender;
    private String dietType;
    private int age;
    private int offspringCount;
    private int dailyFoodRequirement;
}

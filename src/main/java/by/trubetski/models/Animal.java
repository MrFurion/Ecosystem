package by.trubetski.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    private String name;
    private String gender;
    private String dietType;
    private int age;
    private int offspringCount;
    private int dailyFoodRequirement;
}
package by.trubetski.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Plant {
    private String name;
    private int age;
    private int weight;
    private int timeLife;
    private int count;
}

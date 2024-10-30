package by.trubetski.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static by.trubetski.constants.AnimalDtoConstants.AGE_MAX;
import static by.trubetski.constants.AnimalDtoConstants.AGE_MIN;
import static by.trubetski.constants.AnimalDtoConstants.AGE_NOT_SHOULD_NULL;
import static by.trubetski.constants.AnimalDtoConstants.CHARACTERS_OF_NAME;
import static by.trubetski.constants.AnimalDtoConstants.DAILY_FOOD_MAX;
import static by.trubetski.constants.AnimalDtoConstants.DAILY_FOOD_MIN;
import static by.trubetski.constants.AnimalDtoConstants.DAILY_FOOD_NOT_SHOULD_NULL;
import static by.trubetski.constants.AnimalDtoConstants.DIET_TYPE_NOT_SHOULD_EMPTY;
import static by.trubetski.constants.AnimalDtoConstants.GENDER_NOT_SHOULD_EMPTY;
import static by.trubetski.constants.AnimalDtoConstants.OFFSPRING_COUNT_MAX;
import static by.trubetski.constants.AnimalDtoConstants.OFFSPRING_COUNT_MIN;
import static by.trubetski.constants.AnimalDtoConstants.OFFSPRING_COUNT_NOT_SHOULD_NULL;
import static by.trubetski.constants.AnimalDtoConstants.VALUER_FOR_DIET_TYPE;
import static by.trubetski.constants.AnimalDtoConstants.VALUE_DIET_TYPE;
import static by.trubetski.constants.AnimalDtoConstants.VALUE_FOR_GENDER;
import static by.trubetski.constants.AnimalDtoConstants.VALUE_GENDER;
import static by.trubetski.constants.ValidatorConstants.LIMITS;
import static by.trubetski.constants.ValidatorConstants.NAME_NOT_EMPTY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimalDto {

    @NotEmpty(message = NAME_NOT_EMPTY)
    @Size(min = 4, max = 26, message = LIMITS + CHARACTERS_OF_NAME)
    private String name;

    @NotEmpty(message = GENDER_NOT_SHOULD_EMPTY)
    private String gender;

    @NotEmpty(message = DIET_TYPE_NOT_SHOULD_EMPTY)
    private String dietType;

    @Min(value = 0, message = LIMITS + AGE_MIN)
    @Max(value = 200, message = LIMITS + AGE_MAX)
    @NotNull(message = AGE_NOT_SHOULD_NULL)
    private int age;

    @Min(value = 0, message = LIMITS + OFFSPRING_COUNT_MIN)
    @Max(value = 2000, message = LIMITS + OFFSPRING_COUNT_MAX)
    @NotNull(message = OFFSPRING_COUNT_NOT_SHOULD_NULL)
    private int offspringCount;

    @Min(value = 1, message = LIMITS + DAILY_FOOD_MIN)
    @Max(value = 100, message = LIMITS + DAILY_FOOD_MAX)
    @NotNull(message = DAILY_FOOD_NOT_SHOULD_NULL)
    private int dailyFoodRequirement;
}

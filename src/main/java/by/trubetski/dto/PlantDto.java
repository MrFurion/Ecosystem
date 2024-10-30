package by.trubetski.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static by.trubetski.constants.PlaintDtoConstants.AGE;
import static by.trubetski.constants.PlaintDtoConstants.AGE_NOT_SHOULD_NULL;
import static by.trubetski.constants.PlaintDtoConstants.CHARACTERS_OF_NAME;
import static by.trubetski.constants.PlaintDtoConstants.COUNT;
import static by.trubetski.constants.PlaintDtoConstants.COUNT_NOT_SHOULD_NULL;
import static by.trubetski.constants.PlaintDtoConstants.LIMITS_MIN_FOR_AGE;
import static by.trubetski.constants.PlaintDtoConstants.LIMIT_MIN_FOR_COUNT;
import static by.trubetski.constants.PlaintDtoConstants.LIMIT_MIN_FOR_TIME_LIFE;
import static by.trubetski.constants.PlaintDtoConstants.LIMIT_MIN_FOR_WEIGHT;
import static by.trubetski.constants.PlaintDtoConstants.TIME_LIFE;
import static by.trubetski.constants.PlaintDtoConstants.TIME_LIFE_NOT_SHOULD_NULL;
import static by.trubetski.constants.PlaintDtoConstants.WEIGHT;
import static by.trubetski.constants.PlaintDtoConstants.WEIGHT_NOT_SHOULD_NULL;
import static by.trubetski.constants.ValidatorConstants.LIMITS;
import static by.trubetski.constants.ValidatorConstants.NAME_NOT_EMPTY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlantDto {

    @NotEmpty(message = NAME_NOT_EMPTY)
    @Size(min = 4, max = 26, message = LIMITS + CHARACTERS_OF_NAME)
    private String name;

    @Min(value = 0, message = LIMITS + LIMITS_MIN_FOR_AGE)
    @Max(value = 500, message = LIMITS + AGE)
    @NotNull(message = AGE_NOT_SHOULD_NULL)
    private int age;

    @Min(value = 1, message = LIMITS + LIMIT_MIN_FOR_WEIGHT)
    @Max(value = 1000, message = LIMITS + WEIGHT)
    @NotNull(message = WEIGHT_NOT_SHOULD_NULL)
    private int weight;

    @Min(value = 0, message = LIMITS + LIMIT_MIN_FOR_TIME_LIFE)
    @Max(value = 500, message = LIMITS + TIME_LIFE)
    @NotNull(message = TIME_LIFE_NOT_SHOULD_NULL)
    private int timeLife;

    @Min(value = 0, message = LIMITS + LIMIT_MIN_FOR_COUNT)
    @Max(value = 1000, message = LIMITS + COUNT)
    @NotNull(message = COUNT_NOT_SHOULD_NULL)
    private int count;
}

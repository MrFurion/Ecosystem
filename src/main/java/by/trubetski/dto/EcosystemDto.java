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

import java.util.ArrayList;
import java.util.List;

import static by.trubetski.constants.EcosystemDtoConstants.CHARACTERS_NAME;
import static by.trubetski.constants.EcosystemDtoConstants.C_MAX;
import static by.trubetski.constants.EcosystemDtoConstants.C_MIN;
import static by.trubetski.constants.EcosystemDtoConstants.HUMIDITY_MAX;
import static by.trubetski.constants.EcosystemDtoConstants.HUMIDITY_MIN;
import static by.trubetski.constants.EcosystemDtoConstants.HUMIDITY_NOT_SHOULD_NULL;
import static by.trubetski.constants.EcosystemDtoConstants.QUANTITY_EARTH_MAX;
import static by.trubetski.constants.EcosystemDtoConstants.QUANTITY_EARTH_MIN;
import static by.trubetski.constants.EcosystemDtoConstants.QUANTITY_EARTH_NOT_SHOULD_NULL;
import static by.trubetski.constants.EcosystemDtoConstants.QUANTITY_WATER_MAX;
import static by.trubetski.constants.EcosystemDtoConstants.QUANTITY_WATER_MIN;
import static by.trubetski.constants.EcosystemDtoConstants.QUANTITY_WATER_NOT_SHOULD_NULL;
import static by.trubetski.constants.EcosystemDtoConstants.TEMPERATURE_NOT_SHOULD_NULL;
import static by.trubetski.constants.ValidatorConstants.LIMITS;
import static by.trubetski.constants.ValidatorConstants.NAME_NOT_EMPTY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EcosystemDto {

    @NotEmpty(message = NAME_NOT_EMPTY)
    @Size(min = 4, max = 15, message = LIMITS + CHARACTERS_NAME)
    private String name;

    private List<AnimalDto> animals = new ArrayList<>();

    private List<PlantDto> plants = new ArrayList<>();

    @Min(value = -100, message = LIMITS + C_MIN)
    @Max(value = 100, message = LIMITS + C_MAX)
    @NotNull(message = TEMPERATURE_NOT_SHOULD_NULL)
    private int temperature;

    @Min(value = 0, message = LIMITS + HUMIDITY_MIN)
    @Max(value = 100, message = LIMITS + HUMIDITY_MAX)
    @NotNull(message = HUMIDITY_NOT_SHOULD_NULL)
    private int humidity;

    @Min(value = 0, message = LIMITS + QUANTITY_WATER_MIN)
    @Max(value = 2000000, message = LIMITS + QUANTITY_WATER_MAX)
    @NotNull(message = QUANTITY_WATER_NOT_SHOULD_NULL)
    private int quantityWater;

    @Min(value = 1, message = LIMITS + QUANTITY_EARTH_MIN)
    @Max(value = 1000000, message = LIMITS + QUANTITY_EARTH_MAX)
    @NotNull(message = QUANTITY_EARTH_NOT_SHOULD_NULL)
    private int quantityEarth;
}

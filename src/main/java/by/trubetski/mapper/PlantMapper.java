package by.trubetski.mapper;

import by.trubetski.dto.PlantDto;
import by.trubetski.models.Plant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlantMapper {

    PlantMapper INSTANCE = Mappers.getMapper(PlantMapper.class);

    PlantDto toDto(Plant plant);
    Plant toEntity(PlantDto plantDto);
}

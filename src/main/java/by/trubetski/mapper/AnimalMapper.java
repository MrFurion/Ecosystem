package by.trubetski.mapper;

import by.trubetski.dto.AnimalDto;
import by.trubetski.models.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnimalMapper {

    AnimalMapper INSTANCE = Mappers.getMapper(AnimalMapper.class);

    AnimalDto toDto(Animal animal);

    Animal toEntity(AnimalDto animalDto);


}

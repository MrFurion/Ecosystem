package by.trubetski.mapper;

import by.trubetski.dto.EcosystemDto;
import by.trubetski.models.Ecosystem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AnimalMapper.class, PlantMapper.class})
public interface EcosystemMapper {

    EcosystemMapper INSTANCE = Mappers.getMapper(EcosystemMapper.class);

    EcosystemDto toDto(Ecosystem ecosystem);

    Ecosystem toEntity(EcosystemDto ecosystemDto);
}

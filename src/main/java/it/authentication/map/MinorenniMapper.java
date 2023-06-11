package it.authentication.map;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.authentication.model.dto.MinorenniDTO;
import it.authentication.model.entity.Minorenni;

@Mapper
public interface MinorenniMapper {
	MinorenniMapper MAPPER = Mappers.getMapper(MinorenniMapper.class);
	
	Minorenni mapToMinorenni (MinorenniDTO minorenniDTO);
	MinorenniDTO mapToMinorenniDTO (Minorenni minorenni);

}

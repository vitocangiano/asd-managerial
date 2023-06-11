package it.authentication.map;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.authentication.model.dto.MaggiorenniDTO;
import it.authentication.model.entity.Maggiorenni;

@Mapper
public interface MaggiorenniMapper {
	MaggiorenniMapper MAPPER = Mappers.getMapper(MaggiorenniMapper.class);
	
	MaggiorenniDTO mapTOMaggiorenniDTO(Maggiorenni maggiorenni);
	Maggiorenni mapToMaggiorenni (MaggiorenniDTO maggiorenniDTO);
}
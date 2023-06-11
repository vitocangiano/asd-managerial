package it.authentication.map;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.authentication.model.dto.BorsellinoDTO;
import it.authentication.model.entity.Borsellino;

@Mapper
public interface BorsellinoMapper {
	BorsellinoMapper MAPPER = Mappers.getMapper(BorsellinoMapper.class);
	
	BorsellinoDTO mapToBorsellinoDTO(Borsellino borsellino);
	Borsellino mapToBorsellino(BorsellinoDTO borsellinoDTO);
}

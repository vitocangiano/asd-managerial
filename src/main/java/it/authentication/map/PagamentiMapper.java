package it.authentication.map;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.authentication.model.dto.PagamentiDTO;
import it.authentication.model.entity.Pagamenti;

@Mapper
public interface PagamentiMapper {
	PagamentiMapper Mapper = Mappers.getMapper(PagamentiMapper.class);
	
	PagamentiDTO mapToPagamentiDTO(Pagamenti pagamenti);
	Pagamenti mapToPagamenti(PagamentiDTO pagamentiDTO);
}

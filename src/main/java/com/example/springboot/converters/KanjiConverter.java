package com.example.springboot.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.springboot.entities.KanjiEntity;
import com.example.springbootdto.KanjiDTO;

@Component
public class KanjiConverter {
	public KanjiEntity toEntity(KanjiDTO kanjiDTO) {
		KanjiEntity kanjiEntity = new KanjiEntity();
		kanjiEntity.setId(kanjiDTO.getId());
		kanjiEntity.setCharacter(kanjiDTO.getCharacter());
		kanjiEntity.setOnyomi(kanjiDTO.getOnyomi());
		kanjiEntity.setKunyomi(kanjiDTO.getKunyomi());
		kanjiEntity.setChinese(kanjiDTO.getChinese());
		kanjiEntity.setVietnamese(kanjiDTO.getVietnamese());
		kanjiEntity.setDescription(kanjiDTO.getDescription());
		kanjiEntity.setImageLink(kanjiDTO.getImageLink());
		kanjiEntity.setId(kanjiDTO.getId());
		kanjiEntity.setId(kanjiDTO.getId());
		return kanjiEntity;
	}

	public KanjiDTO toDTO(KanjiEntity kanjiEntity) {
		KanjiDTO kanjiDTO = new KanjiDTO();
		kanjiDTO.setId(kanjiEntity.getId());
		kanjiDTO.setCharacter(kanjiEntity.getCharacter());
		kanjiDTO.setOnyomi(kanjiEntity.getOnyomi());
		kanjiDTO.setKunyomi(kanjiEntity.getKunyomi());
		kanjiDTO.setChinese(kanjiEntity.getChinese());
		kanjiDTO.setVietnamese(kanjiEntity.getVietnamese());
		kanjiDTO.setDescription(kanjiEntity.getDescription());
		kanjiDTO.setGifLink(kanjiEntity.getGifLink());
		kanjiDTO.setCharacter(kanjiEntity.getCharacter());
		kanjiDTO.setCharacter(kanjiEntity.getCharacter());
		return kanjiDTO;
	}

	public List<KanjiDTO> toDTOs(List<KanjiEntity> listEntities) {
		ArrayList<KanjiDTO> kanjiDTOs = new ArrayList<KanjiDTO>();
		for (KanjiEntity kanjiEntity : listEntities) {
			kanjiDTOs.add(toDTO(kanjiEntity));
		}
		return kanjiDTOs;
	}

}

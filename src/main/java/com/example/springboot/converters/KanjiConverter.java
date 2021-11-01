package com.example.springboot.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.springboot.dto.KanjiDTO;
import com.example.springboot.entities.KanjiEntity;

@Component
public class KanjiConverter {
	public KanjiEntity toEntity(KanjiDTO kanjiDTO) {
		KanjiEntity kanjiEntity = new KanjiEntity();
		kanjiEntity.setCharacter(kanjiDTO.getCharacter());
		kanjiEntity.setOnyomi(kanjiDTO.getOnyomi());
		kanjiEntity.setKunyomi(kanjiDTO.getKunyomi());
		kanjiEntity.setChinese(kanjiDTO.getChinese());
		kanjiEntity.setVietnamese(kanjiDTO.getVietnamese());
		kanjiEntity.setDescription(kanjiDTO.getDescription());
		kanjiEntity.setImageLink(kanjiDTO.getImageLink());
		kanjiEntity.setGifLink(kanjiDTO.getGifLink());
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
		kanjiDTO.setImageLink(kanjiEntity.getImageLink());
		kanjiDTO.setGifLink(kanjiEntity.getGifLink());
		kanjiDTO.setLesson_id(kanjiEntity.getLessonEntity().getId());
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

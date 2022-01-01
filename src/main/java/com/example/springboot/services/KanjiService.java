package com.example.springboot.services;

import java.util.List;

import com.example.springboot.dto.GetAllKanjiDTO;
import com.example.springboot.dto.VocabularyBaseDTO;
import com.example.springboot.entities.LessonEntity;
import com.example.springboot.entities.VocabularyEntity;
import com.example.springboot.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.KanjiConverter;
import com.example.springboot.dto.KanjiDTO;
import com.example.springboot.entities.KanjiEntity;
import com.example.springboot.repositories.KanjiRepository;

@Service
public class KanjiService {
	@Autowired
	KanjiRepository kanjiRepository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	KanjiConverter kanjiConverter;

	public List<KanjiDTO> getByLessionId(Long lesson_id) {
		List<KanjiEntity> kanjiEntities = kanjiRepository.getByLessonId(lesson_id);
		return kanjiConverter.toDTOs(kanjiEntities);
	}

	public List<GetAllKanjiDTO> getAllKanji() {
		List<GetAllKanjiDTO> listKanji = kanjiRepository.getAllKanji();
		return listKanji;
	}

	public GetAllKanjiDTO getKanjiById(Long id) {
		GetAllKanjiDTO kanji = kanjiRepository.getAllKanjiDTOById(id);
		return kanji;
	}

	public String addKanji(GetAllKanjiDTO kanjiDTO) {
		KanjiEntity kanjiEntity = kanjiRepository.getCharacter(kanjiDTO.getCharacter());
		if (kanjiEntity != null) {
			return "Đã tồn tại " + kanjiEntity.getCharacter() + " trong hệ thống";
		}
		KanjiEntity kanji = new KanjiEntity();
		LessonEntity lessonEntity = new LessonEntity();
		lessonEntity.setId(lessonRepository.getIdLessonByName(kanjiDTO.getLessonName()));
		lessonEntity.setLessonName(kanjiDTO.getLessonName());
		kanji.setLessonEntity(lessonEntity);
		kanji.setCharacter(kanjiDTO.getCharacter());
		kanji.setChinese(kanjiDTO.getChinese());
		kanji.setVietnamese(kanjiDTO.getVietnamese());
		kanji.setDescription(kanjiDTO.getDescription());
		kanji.setOnyomi(kanjiDTO.getOnyomi());
		kanji.setKunyomi(kanjiDTO.getKunyomi());
		kanji.setGifLink(kanjiDTO.getGifLink());
		kanji.setImageLink(kanjiDTO.getImageLink());
		kanjiRepository.save(kanji);
		return "Thêm " + kanjiDTO.getCharacter() + " thành công";
	}

	public void updateKanji(GetAllKanjiDTO kanjiDTO, Long id) throws Exception {
		KanjiEntity kanjiEntity = kanjiRepository.getCharacter(kanjiDTO.getCharacter());
		if (kanjiEntity == null || kanjiEntity.getId().equals(id)) {
			KanjiEntity kanji = kanjiRepository.getKanjiEntityById(id);
			LessonEntity lessonEntity = new LessonEntity();
			lessonEntity.setId(lessonRepository.getIdLessonByName(kanjiDTO.getLessonName()));
			lessonEntity.setLessonName(kanjiDTO.getLessonName());
			kanji.setLessonEntity(lessonEntity);
			kanji.setCharacter(kanjiDTO.getCharacter());
			kanji.setChinese(kanjiDTO.getChinese());
			kanji.setVietnamese(kanjiDTO.getVietnamese());
			kanji.setDescription(kanjiDTO.getDescription());
			kanji.setOnyomi(kanjiDTO.getOnyomi());
			kanji.setKunyomi(kanjiDTO.getKunyomi());
			kanji.setGifLink(kanjiDTO.getGifLink());
			kanji.setImageLink(kanjiDTO.getImageLink());
			kanjiRepository.save(kanji);
		}
		else {
			throw new Exception("Chữ kanji này đã tồn tại");
		}
	}

	public void deleteKanji(Long id) {
		KanjiEntity kanji = kanjiRepository.getKanjiEntityById(id);
		kanjiRepository.delete(kanji);
	}

}
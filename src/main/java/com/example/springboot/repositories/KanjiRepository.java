package com.example.springboot.repositories;

import java.util.List;

import com.example.springboot.dto.GetAllKanjiDTO;
import com.example.springboot.dto.GetAllVocabularyDTO;
import com.example.springboot.entities.VocabularyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot.entities.KanjiEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.springboot.entities.KanjiEntity;

public interface KanjiRepository extends JpaRepository<KanjiEntity, Long> {

	@Query(value = "select * from kanjis where lesson_id = ?1", nativeQuery = true)
	public List<KanjiEntity> getByLessonId(Long lesson_id);

	@Query("select new com.example.springboot.dto.GetAllKanjiDTO(k.id,l.lessonName,k.character,k.onyomi,k.kunyomi,k.chinese,k.vietnamese,"
			+ "k.description,k.imageLink,k.gifLink)  from KanjiEntity k left join k.lessonEntity l")
	List<GetAllKanjiDTO> getAllKanji();

	@Query("select new com.example.springboot.dto.GetAllKanjiDTO(k.id,l.lessonName,k.character,k.onyomi,k.kunyomi,k.chinese,k.vietnamese,"
			+ "k.description,k.imageLink,k.gifLink)  from KanjiEntity k left join k.lessonEntity l where k.id=:id")
	GetAllKanjiDTO getAllKanjiDTOById(@Param("id") Long id);

	@Query("select k from KanjiEntity k left join k.lessonEntity l where k.id=:id")
	KanjiEntity getKanjiEntityById(@Param("id") Long id);

	@Query(" Select k from KanjiEntity k where k.character = :character ")
	KanjiEntity getCharacter(@Param("character") String character);

}

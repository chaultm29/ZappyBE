package com.example.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot.entities.KanjiEntity;

public interface KanjiRepository extends JpaRepository<KanjiEntity, Long>{

	@Query(value ="select * from kanjis where lesson_id = ?1", nativeQuery = true)
	public List<KanjiEntity> getByLessonId(Long lesson_id );
	
}

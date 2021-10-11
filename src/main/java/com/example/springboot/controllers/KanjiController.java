package com.example.springboot.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entities.KanjiEntity;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repositories.KanjiRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/student")
public class KanjiController {
	@Autowired
	private KanjiRepository kanjiRepository;

	// get all kanji
	@GetMapping("/kanji")
	public List<KanjiEntity> getAllKanjis() {
		return (List<KanjiEntity>) kanjiRepository.findAll();
	}

	// create kanji rest api
	@PostMapping("/kanji")
	public KanjiEntity createKanji(@RequestBody KanjiEntity kanjiEntity) {
		return kanjiRepository.save(kanjiEntity);
	}

	// get kanji by id rest api
	@GetMapping("/kanji/{id}")
	public ResponseEntity<KanjiEntity> getKanjiById(@PathVariable Long id) {
		KanjiEntity kanjiEntity = kanjiRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Kanji not exist with id :" + id));
		return ResponseEntity.ok(kanjiEntity);
	}

	// update kanji rest api
	@PutMapping("/kanji/{id}")
	public ResponseEntity<KanjiEntity> updateKanji(@PathVariable Long id, @RequestBody KanjiEntity kanjiDetails) {
		KanjiEntity kanjiEntity = kanjiRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Kanji not exist with id :" + id));

		kanjiEntity.setCharacter(kanjiDetails.getCharacter());
		kanjiEntity.setOnyomi(kanjiDetails.getOnyomi());
		kanjiEntity.setKunyomi(kanjiDetails.getKunyomi());
		kanjiEntity.setChinese(kanjiDetails.getChinese());
		kanjiEntity.setVietnamese(kanjiDetails.getVietnamese());
		kanjiEntity.setDescription(kanjiDetails.getDescription());
		kanjiEntity.setImageLinh(kanjiDetails.getImageLinh());
		kanjiEntity.setGifLink(kanjiDetails.getGifLink());

		KanjiEntity updatedKanji = kanjiRepository.save(kanjiEntity);
		return ResponseEntity.ok(updatedKanji);
	}

	// delete kanji rest api
	@DeleteMapping("/kanji/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteKanji(@PathVariable Long id) {
		KanjiEntity kanjiEntity = kanjiRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Kanji not exist with id :" + id));

		kanjiRepository.delete(kanjiEntity);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

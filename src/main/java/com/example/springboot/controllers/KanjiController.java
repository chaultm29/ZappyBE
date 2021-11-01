package com.example.springboot.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.springboot.dto.GetAllKanjiDTO;
import com.example.springboot.dto.VocabularyBaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springboot.dto.KanjiDTO;
import com.example.springboot.services.KanjiService;

@CrossOrigin(origins = {"http://localhost:3000", "https://www.zappy-nihongo.com"})
@RestController
@RequestMapping("")
public class KanjiController {

	@Autowired
	private KanjiService kanjiService;

	@GetMapping("/kanji/lesson/{id}")
	public ResponseEntity<List<KanjiDTO>> getKanjiByLessonId(@PathVariable Long id) {
		List<KanjiDTO> kanjiDTO = new ArrayList<>();
		kanjiDTO = kanjiService.getByLessionId(id);
		return new ResponseEntity<List<KanjiDTO>>(kanjiDTO, HttpStatus.OK);
	}

	@GetMapping("/kanji")
	public ResponseEntity<List<GetAllKanjiDTO>> getAllKanji() {
		List<GetAllKanjiDTO> listKanji = new ArrayList<>();
		listKanji = kanjiService.getAllKanji();
		return new ResponseEntity<List<GetAllKanjiDTO>>(listKanji, HttpStatus.OK);
	}

	@GetMapping("/kanji/{id}")
	public ResponseEntity<GetAllKanjiDTO> getKanjiById(@PathVariable Long id) {
		GetAllKanjiDTO kanji = kanjiService.getKanjiById(id);
		return new ResponseEntity<GetAllKanjiDTO>(kanji, HttpStatus.OK);
	}

	@PostMapping("/kanji")
	public ResponseEntity<?> addKanji(@RequestBody GetAllKanjiDTO kanjiDTO) {
		kanjiService.addKanji(kanjiDTO);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PutMapping("/kanji/{id}")
	public ResponseEntity<?> updateKanji(@PathVariable Long id, @RequestBody GetAllKanjiDTO kanjiDTO) {
		kanjiService.updateKanji(kanjiDTO, id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping("/kanji/{id}")
	public ResponseEntity<?> deleteKanji(@PathVariable Long id) {
		kanjiService.deleteKanji(id);
		return new ResponseEntity(HttpStatus.OK);
	}

}
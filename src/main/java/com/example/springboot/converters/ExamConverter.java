package com.example.springboot.converters;

import com.example.springboot.dto.AnswerDTO;
import com.example.springboot.dto.ExamDTO;
import com.example.springboot.dto.QuestionDTO;
import com.example.springboot.dto.QuestionExamDTO;
import com.example.springboot.entities.AnswerEntity;
import com.example.springboot.entities.ExamEntity;
import com.example.springboot.entities.QuestionEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExamConverter {

    public QuestionExamDTO toDTO(QuestionEntity questionEntity) {
        QuestionExamDTO questionExamDTO = new QuestionExamDTO();
        questionExamDTO.setQuestionID(questionEntity.getId());
        questionExamDTO.setTypeName(questionEntity.getQuestionTypeEntity().getTypeName());
        questionExamDTO.setLessonName(questionEntity.getLessonEntity().getLessonName());
        questionExamDTO.setQuestion(questionEntity.getQuestion());
        questionExamDTO.setImgeLink(questionEntity.getImage_link());
        List<AnswerDTO> answerDTOs = new ArrayList<>();
        for (AnswerEntity answerEntity : questionEntity.getAnswerEntities()) {
            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO.setId(answerEntity.getId());
            answerDTO.setAnswer(answerEntity.getAnswer());
            answerDTOs.add(answerDTO);
        }
        questionExamDTO.setAnswer(answerDTOs);
        return questionExamDTO;
    }

    public List<QuestionExamDTO> toDTOs(List<QuestionEntity> listEntities) {
        List<QuestionExamDTO> questionExamDTOS = new ArrayList<>();
        for (QuestionEntity questionEntity : listEntities) {
            questionExamDTOS.add(toDTO(questionEntity));
        }
        return questionExamDTOS;
    }

    public ExamDTO toDTOEx(ExamEntity examEntity) {
        ExamDTO examDTO = new ExamDTO();
        examDTO.setScore(examEntity.getScore());
        examDTO.setTime(examEntity.getTime());
        examDTO.setCreatedDate(examEntity.getCreatedDate());
        return examDTO;
    }

    public List<ExamDTO> toDTOExs(List<ExamEntity> listEntities) {
        List<ExamDTO> questionExamDTOS = new ArrayList<>();
        for (ExamEntity examEntity : listEntities) {
            questionExamDTOS.add(toDTOEx(examEntity));
        }
        return questionExamDTOS;
    }
}

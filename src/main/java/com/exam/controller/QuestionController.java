package com.exam.controller;

import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;
import com.exam.models.exam.TestReport;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    //add question
    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    //update the question
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    //get all question of any quiz
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid) {

        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questions = quiz.getQuestions();
        List<Question> list = new ArrayList(questions);
        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
        }

        list.forEach((q) -> {
            q.setAnswer("");
        });

        Collections.shuffle(list);
        return ResponseEntity.ok(list);

    }

    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid) {
        Quiz quiz = new Quiz();
        quiz.setqId(qid);
        Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);
    }

    //get single question
    @GetMapping("/{quesId}")
    public Question getQuestion(@PathVariable("quesId") Long quesId) {
        return this.questionService.getQuestion(quesId);
    }

    //delete question
    @DeleteMapping("/{quesId}")
    public void deleteQuestion(@PathVariable("quesId") Long quesId) {
        this.questionService.deleteQuestion(quesId);
    }

    @PostMapping("/evaluate-quiz")
    public ResponseEntity<?> evaluateQuiz(@RequestBody List<Question> questionList) {

        int correctAnswers = 0, questionsAttempted = 0;
        double marksObtained = 0, singleQuestionMark;

        singleQuestionMark = Double.parseDouble(questionList.get(0).getQuiz().getMaxMarks()) / questionList.size();

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        for(Question question : questionList)
        {
            Question originalQuestion = this.questionService.getQuestionByQuestionId(question.getQuesId());

            if((question.getAnswerOfUser() != null) &&
                    (!question.getAnswerOfUser().isEmpty()) &&
                    (originalQuestion.getAnswer().equals(question.getAnswerOfUser().trim()))
            ) {
                correctAnswers++;
                marksObtained += singleQuestionMark;
            }

            if ((question.getAnswerOfUser() != null) && (!question.getAnswerOfUser().trim().isEmpty())) {
                questionsAttempted++;
            }
        }

        String marksObtainedStr = decimalFormat.format(marksObtained);

        TestReport testReport = new TestReport(Double.parseDouble(marksObtainedStr), correctAnswers, questionsAttempted);

        return ResponseEntity.ok(testReport);
    }

}

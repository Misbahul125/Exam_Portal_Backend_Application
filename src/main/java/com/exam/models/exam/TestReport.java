package com.exam.models.exam;

public class TestReport {

    double marksObtained = 0;
    int correctAnswers = 0;
    int questionsAttempted = 0;

    public TestReport() {
    }

    public TestReport(double marksObtained, int correctAnswers, int questionsAttempted) {
        this.marksObtained = marksObtained;
        this.correctAnswers = correctAnswers;
        this.questionsAttempted = questionsAttempted;
    }

    public double getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(int marksObtained) {
        this.marksObtained = marksObtained;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getQuestionsAttempted() {
        return questionsAttempted;
    }

    public void setQuestionsAttempted(int questionsAttempted) {
        this.questionsAttempted = questionsAttempted;
    }
}

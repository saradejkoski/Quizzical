package at.ac.fhcampuswien;

public class Question {
    String[] answerOptions = new String[4]; //this array is responsible for the answer options
    String question; //this string displays the question text
    private int correctAnswer; //here is the correct answer (either 1-4);

    public Question(String question, String[] answerOptions, int correctAnswer) {// alles in Parameter ist nur so lange da bis die Methode vorbei ist
        //es kommt erst die Frage, dann Antwortoptionen und dann die richtige Anwort
        this.question = question;
        this.answerOptions = answerOptions;
        this.correctAnswer = correctAnswer;

    }

    public String[] getAnswerOptions() {

        return answerOptions;
    }

    public String getQuestion() {

        return question;
    }

    public int getCorrectAnswer() {

        return correctAnswer;
    }


}

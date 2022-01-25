package at.ac.fhcampuswien;

public class Question {
    String[] answerOptions = new String[4]; //dieser Array
    String question; // der Text für eine Frage kommt da rein
    private int correctAnswer; //oder private int index;

    public Question(String question, String[] answerOptions, int correctAnswer ){// alles in Parameter ist nur so lange da bis die Methode vorbei ist
        //es kommt erst die Frage, dann Antwortoptionen und dann die richtige Anwort
        this.question = question;
        this.answerOptions=answerOptions;
        this.correctAnswer=correctAnswer;

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

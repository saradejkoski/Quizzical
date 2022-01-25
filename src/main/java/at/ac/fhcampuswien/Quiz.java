package at.ac.fhcampuswien;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz implements ActionListener {

    Question[] questions;


    int index; // number of question you currently are
    int correctAnswers = 0;
    int totalQuestions;


    public Quiz() { //the constructor starts here
        loadQuestions();
        GUI.getInstance().setQuiz(this);

        nextQuestion();     // jump to next question
    }

    /**
     * this method is used to load the questions from the file. Currently there are ten lines to be read.
     */
    public void loadQuestions() {
        ArrayList<String> lines = new ArrayList<>();//wir brauchen diese arry List damit alle Fragen hintereinander gestellt werden
        File file = new File("src/main/java/at/ac/fhcampuswien/quizdatabase.txt"); //hier ist das relativ gespeicherte file

        try (Scanner scanner = new Scanner(file)) { //wir möchten aus dem File die Fragen rauskopieren SOLANGE
            while (scanner.hasNextLine()) {// solange es eine nächste Linie gibt --Kommt noch was?
                lines.add(scanner.nextLine());//wir leseen die nächste Zeile und fügen sie zu Arraylist hinzu
            }
        } catch (FileNotFoundException e) { //wenn das File nicht gefunden wird - in dem Fall unmöglich aber JAVA möchte
            e.printStackTrace();//dass man den Fall berücksicht
        }

        totalQuestions = lines.size(); // nach dem Einlesen die Anzahl Elemente in der Lines Array list
        questions = new Question[totalQuestions];// Index:0 in jeder Zeile
        for (int i = 0; i < questions.length; i++) {
            String[] line = lines.get(i).split(";"); //splitten auf nach ;
            // line = {Question, Answer1, Answer2, Answer3, Answer4, Nr of correct answer}
            String[] arrayCopy = new String[4];
            System.arraycopy(line, 1, arrayCopy, 0, 4);


            questions[i] = new Question(line[0], arrayCopy, Integer.parseInt(line[5]));
            //eine Helfermethode aus der Wrapperklasse geht Element durch und wandelt es um in Integer

        }
    }

    /**
     * The nextQuestion method is used to prepare the next question.
     */
    public void nextQuestion() {

        GUI gui = GUI.getInstance();
        if (index >= totalQuestions) {        // if number of current question is a higher than the number of the total questions
            gui.displayResults(correctAnswers, totalQuestions);                      //jump to result
        } else {
            //we

            gui.displayQuestion(questions[index], index + 1);//eckige klammern beim index wird nur beim indizieren benötigt...otherwise its only  a number

        }
    }

    /**
     * this answer method takes the parameter of the selected method and hands it over.
     *
     * @param answerNumber is the number of the selected answer option
     */
    public void answerHandover(int answerNumber) {
        if (answerNumber == questions[index].getCorrectAnswer()) {//hier wird geschaut ist es richtig
            correctAnswers++;
        }


        index++;
        nextQuestion();
    }

    /**
     * that method is used to change the color after the user clicked on something and takes care of the interaction.
     *
     * @param e takes the parameter
     */

    @Override
    public void actionPerformed(ActionEvent e) {


    }


}

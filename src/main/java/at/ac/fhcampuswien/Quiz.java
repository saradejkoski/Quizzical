package at.ac.fhcampuswien;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz  {

    Question[] questions; //array wird erstellt wenn die fragen geladen werden und dann werden die befüllt..einfach eine referenz
//derzeit wert null

    int index; // number of question you currently are
    int correctAnswers = 0;
    int totalQuestions;




    public Quiz() { //the constructor starts here
        loadQuestions(); //methode load question
        GUI.getInstance().setQuiz(this); //das Quiz ruft auf dem GUI diese Setter Methode auf
        //und übergibt sich selbst als Parameter

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
        //dieser Constructor Scanner mit einem file verlangt dass mit diesem möglichen FEhler umgegangen wird

        totalQuestions = lines.size(); // nach dem Einlesen die Anzahl Elemente in der Lines Array list
        questions = new Question[totalQuestions];// Array wird erstellt
        for (int i = 0; i < questions.length; i++) { //Index:0 in jeder Zeile -
            String[] line = lines.get(i).split(";"); //splitten auf nach ;
            // line = {Question, Answer1, Answer2, Answer3, Answer4, Nr of correct answer}
            String[] answerOptions = new String[4]; //dieser Array wird erstellt damit wir die answer option dem constructor
            //von question übergeben können
            System.arraycopy(line, 1, answerOptions, 0, 4);//API methode die das array kopiert


            questions[i] = new Question(line[0], answerOptions, Integer.parseInt(line[5]));

            //es muss parseInt gemacht werden weil es sonst ein STring wäre
            //line[0] ist immer der Fragetext ..bleibt immer 0
            //[5] der index der richtigen antwort
            //answerOptions darf ein STring Array bleiben weil es die Antworten sind die stehen
            //eine Helfermethode aus der Wrapperklasse geht Element durch und wandelt es um in Integer

        }
    }

    /**
     * The nextQuestion method is used to prepare the next question.
     */
    public void nextQuestion() {

        GUI gui = GUI.getInstance(); //wir holen uns die GUI Instanz und speichern sie in einer Referenz
        if (index >= totalQuestions) {        // if number of current question is a higher than the number of the total questions
            gui.displayResults(correctAnswers, totalQuestions);                      //jump to result
        } else {
            gui.displayQuestion(questions[index], index + 1);//eckige klammern beim index wird nur beim indizieren benötigt...otherwise its only  a number

        } //das QUIZ sagt dem GUI waas es als nächstes anzeigen soll.
        //die Fragenummer wird mit übergeben,,Index beginnt mit 0 aber erste FRage ist 1 daher 0+1=1
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




}

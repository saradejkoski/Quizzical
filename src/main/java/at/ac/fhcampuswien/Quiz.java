package at.ac.fhcampuswien;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz implements ActionListener {

    //GUI Insanzt übergeben...set quiz..dieses erstellte quiz objekt//setter Methode

    String[] questions;
    String[][] options;
    char[] answers;

    char answer;    // A-D
    int index; // number of question you currently are
    int correctGuesses = 0;
    int totalQuestions;
    int result;


    JFrame frame = new JFrame();  //everything is on the frame (the background)
    JTextField textField = new JTextField();  //creating the textfield for the questions and the questionnumbertitle
    JTextArea textArea = new JTextArea();  // creating the textarea for the answers
    JButton buttonA = new JButton();    //creating buttons a-d to submit answer
    JButton buttonB = new JButton(); //we put label a,b,c, d on
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();

    JLabel answerLabelA = new JLabel();         //creating answerlabels a-d
    JLabel answerLabelB = new JLabel();         //we use this label because we use setbounds
    JLabel answerLabelC = new JLabel();
    JLabel answerLabelD = new JLabel();
/*
What is Jlabel?
Label is a class of java Swing .
JLabel is used to display a short string or an image icon.
JLabel can display text, image or both .
JLabel is only a display of text or image and it cannot get focus .
 */

    JTextField numberRight = new JTextField();     //creating textfield for number of right answered questions
    JTextField percentage = new JTextField();        // creating textfield for the precentage of the right answered questions


    public Quiz() {
        loadQuestions();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //behavior the frame at close
        frame.setSize(800, 550);    //size of the frame
        frame.getContentPane().setBackground(new Color(255, 251, 242)); //color of the background
        frame.setLayout(null);   //null layout means absolute positioning - you have to do all the work in your code
        frame.setResizable(false); //changed size of the window

        textField.setBounds(0, 0, 775, 50); //position of the questionnumbertitle
        /*
        The setBounds() method is used in such a situation to set the position and size.
         To specify the position and size of the components manually,
         the layout manager of the frame can be null.
         */
        textField.setBackground(new Color(255, 251, 242)); //background color
        textField.setForeground(new Color(50, 50, 50));  //color of the font
        textField.setFont(new Font("LCD", Font.BOLD, 30)); //font, fontweight and fontsize
        textField.setBorder(BorderFactory.createBevelBorder(1)); //outline of textfield
        textField.setHorizontalAlignment(JTextField.CENTER); //orientation of the questionnumbertitle
        textField.setEditable(false); //The code setEditable(false) makes the TextField uneditable.
        // It is still selectable and the user can copy data from it, but the user cannot change the TextField's contents directly.
        // The code setEnabled(false), disables this TextField.


        textArea.setBounds(0, 50, 800, 50);    //position of the questionnumbertitle
        textArea.setLineWrap(true);     //If true the lines will be wrapped if they are too long to fit within the allocated width
        textArea.setWrapStyleWord(true);    //Sets the style of wrapping, If true the lines will be wrapped at word boundaries (whitespace),if false, the lines will be wrapped at character boundaries.
        textArea.setBackground(new Color(255, 251, 242));    //color of the background
        textArea.setForeground(new Color(50, 50, 50));      //color of the letters
        textArea.setFont(new Font("LCD", Font.BOLD, 30));       //font, fontweight and fontsize
        textArea.setBorder(BorderFactory.createBevelBorder(1));     //outline of textfield
        textArea.setEditable(false); //The code setEditable(false) makes the TextField uneditable.
        // It is still selectable and the user can copy data from it, but the user cannot change the TextField's contents directly.
        // The code setEnabled(false), disables this TextField.

        buttonA.setBounds(0, 120, 75, 75); //position of the textfield and size of the button
        buttonA.setBackground(new Color(50, 50, 50));  //background color of the button
        buttonA.setForeground(new Color(255, 251, 242)); //color of the letters
        buttonA.setFont(new Font("LCD", Font.BOLD, 30));  //font, fontweight and fontsize
        buttonA.setFocusable(false);  //?
        buttonA.addActionListener(this);
        buttonA.setText("A");  //sets Text for Button


        buttonB.setBounds(0, 220, 75, 75);  //position of the textfield and size of the button
        buttonB.setBackground(new Color(50, 50, 50));  //background color of the button
        buttonB.setForeground(new Color(255, 251, 242)); //color of the letter
        buttonB.setFont(new Font("LCD", Font.BOLD, 30));    //font, fontweight and fontsize
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");   //sets Text for Button


        buttonC.setBounds(0, 320, 75, 75);  //position of the textfield and size of the button
        buttonC.setBackground(new Color(50, 50, 50));  //background color of the button
        buttonC.setForeground(new Color(255, 251, 242)); //color of the letter
        buttonC.setFont(new Font("LCD", Font.BOLD, 30));    //font, fontweight and fontsize
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");   //sets Text for Button


        buttonD.setBounds(0, 420, 75, 75);      //position of the textfield and size of the button
        buttonD.setBackground(new Color(50, 50, 50));  //background color of the button
        buttonD.setForeground(new Color(255, 251, 242)); //color of the letter
        buttonD.setFont(new Font("LCD", Font.BOLD, 30));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");   //sets Text for Button

//Array A,B,C;D schleife mit i ..y geht immer um 100 rauf...(1+i)*100
        answerLabelA.setBounds(125, 100, 500, 100);        //position of the answer label
        answerLabelA.setBackground(new Color(50, 50, 50));     //background color
        answerLabelA.setForeground(new Color(50, 50, 50));     //font color
        answerLabelA.setFont(new Font("LCD", Font.PLAIN, 35));     //font, fontweight and fontsize

        answerLabelB.setBounds(125, 200, 500, 100);        //position of the answer label
        answerLabelB.setBackground(new Color(50, 50, 50));     //background color
        answerLabelB.setForeground(new Color(50, 50, 50));     //font color
        answerLabelB.setFont(new Font("LCD", Font.PLAIN, 35));     //font, fontweight and fontsize

        answerLabelC.setBounds(125, 300, 500, 100);        //position of the answer label
        answerLabelC.setBackground(new Color(50, 50, 50));     //background color
        answerLabelC.setForeground(new Color(50, 50, 50)); //font color
        answerLabelC.setFont(new Font("LCD", Font.PLAIN, 35));     //font, fontweight and fontsize

        answerLabelD.setBounds(125, 400, 500, 100);  //position of the answer label
        answerLabelD.setBackground(new Color(50, 50, 50)); //background color
        answerLabelD.setForeground(new Color(50, 50, 50));  //font color
        answerLabelD.setFont(new Font("LCD", Font.PLAIN, 35));     //font, fontweight and fontsize


        numberRight.setBounds(170, 170 ,200,100); //position of the number right field
        numberRight.setBounds(170, 170 ,425,100); //position of the number right field
        numberRight.setBackground(new Color(255,251,242)); //background color
        numberRight.setForeground(new Color(50,50,50));    //font color
        numberRight.setFont(new Font("LCD",Font.BOLD,50));     //font, fontweight and fontsize
        numberRight.setBorder(BorderFactory.createBevelBorder(1));     //outline of textfield
        numberRight.setBorder(null);
        numberRight.setHorizontalAlignment(JTextField.CENTER);     //orientation of the number right field
        numberRight.setEditable(false);    //The code setEditable(false) makes the TextField uneditable.
        // It is still selectable and the user can copy data from it, but the user cannot change the TextField's contents directly.
        // The code setEnabled(false), disables this TextField.

        percentage.setBounds(170,270,200,100);
        percentage.setBounds(170,270,425,100);
        percentage.setBackground(new Color(255,251,242));   //background color
        percentage.setForeground(new Color(50,50,50));  //font color
        percentage.setFont(new Font("LCD",Font.BOLD,50));       //font, fontweight and fontsize
        percentage.setBorder(BorderFactory.createBevelBorder(1));       //outline of textfield
        percentage.setBorder(null);
        percentage.setHorizontalAlignment(JTextField.CENTER);       //orientation of the percentage
        percentage.setEditable(false);  //The code setEditable(false) makes the TextField uneditable.
        // It is still selectable and the user can copy data from it, but the user cannot change the TextField's contents directly.
        // The code setEnabled(false), disables this TextField.



        frame.add(answerLabelA);   //adding the answerlabels that we created to the frame
        frame.add(answerLabelB);
        frame.add(answerLabelC);
        frame.add(answerLabelD);

        frame.add(buttonA);  // adding the buttons that we created to the frame
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textArea);  // adding the questionnumbertitle that we created to the frame
        frame.add(textField);  // adding the questions that we created to the frame
        frame.setVisible(true);  //window does not open if false

        nextQuestion();     // jump to next question
    }

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
        questions = new String[totalQuestions];// Index:0 in jeder Zeile
        options = new String[totalQuestions][4];//Index: 1 2 3 4 in jeder Zeile
        answers = new char[totalQuestions];// Index 5
        for (int i = 0; i < totalQuestions; i++) { //wir nehmen aktuelle Zeile (String)
            String[] line = lines.get(i).split(";"); //splitten auf nach ;
            // line = {Question, Answer1, Answer2, Answer3, Answer4, Nr of correct answer}
            questions[i] = line[0]; //Position 0 im Array ist die Frage
            /* other possible solution: for (int j = 0; j < 4; j++) { // wir  beginnen mit 1 und enden bei 4
                options[i][j] = line[j + 1]; //i ist immer die Zeile in der wir sind
            }*/
            System.arraycopy(line, 1, options[i], 0, 4); /*
            1 Startposition und wie viele Elemente darau
            es nimmt lines ...beginnt bei Position 0
            */
            answers[i] = (char) (Integer.parseInt(line[5]) + 'A');
        }

    }

    public void nextQuestion() {

        if(index>= totalQuestions) {        // if number of current question is a higher than the number of the total questions
            results();                      //jump to result
        }
        else {
            textField.setText("Question "+(index+1));       //Questionnumber increasing when new question
            textArea.setText(questions[index]);         //inserting the right question to the right questionnumber
            answerLabelA.setText(options[index][0]);  // inserting the right options to the right answerlabels
            answerLabelB.setText(options[index][1]);
            answerLabelC.setText(options[index][2]);
            answerLabelD.setText(options[index][3]);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(e.getSource()==buttonA) {
            answer= 'A';
            if(answer == answers[index]) {      //if the player clicks on A and A is the right answer then
                correctGuesses++;              // correct guesses is increased (+1)
            }
        }
        if(e.getSource()==buttonB) {
            answer= 'B';
            if(answer == answers[index]) {
                correctGuesses++;
            }
        }
        if(e.getSource()==buttonC) {
            answer= 'C';
            if(answer == answers[index]) {
                correctGuesses++;
            }
        }
        if(e.getSource()==buttonD) {
            answer= 'D';
            if(answer == answers[index]) {
                correctGuesses++;
            }
        }
        displayAnswer(); //jumps to display answer
    }
    public void displayAnswer() {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(answers[index] != 'A')
            answerLabelA.setForeground(new Color(255,0,0));
        if(answers[index] != 'B')
            answerLabelB.setForeground(new Color(255,0,0));  // false answer turns red
        if(answers[index] != 'C')
            answerLabelC.setForeground(new Color(255,0,0));
        if(answers[index] != 'D')
            answerLabelD.setForeground(new Color(255,0,0));


        if(answers[index] == 'A')
            answerLabelA.setForeground(new Color(25,255,0));  //right answer turns green
        if(answers[index] == 'B')
            answerLabelB.setForeground(new Color(25,255,0));
        if(answers[index] == 'C')
            answerLabelC.setForeground(new Color(25,255,0));
        if(answers[index] == 'D')
            answerLabelD.setForeground(new Color(25,255,0));


        Timer pause = new Timer(2000, new ActionListener() { // after one answered question the player has to wait 2000 milliseconds

            @Override
            public void actionPerformed(ActionEvent e) {

                answerLabelA.setForeground(new Color(50,50,50));
                answerLabelB.setForeground(new Color(50,50,50));
                answerLabelC.setForeground(new Color(50,50,50));  //make the color of the answers black
                answerLabelD.setForeground(new Color(50,50,50));

                answer = ' ';
                buttonA.setEnabled(true);   // allows user to press button
                buttonB.setEnabled(true);   // allows user to press button
                buttonC.setEnabled(true);   // allows user to press button
                buttonD.setEnabled(true);   // allows user to press button
                index++;
                nextQuestion(); // jumps to nextquestion
            }
        });
        pause.setRepeats(false);
        pause.start();
    }

    public void results(){


        buttonA.setEnabled(false);  //disables the button
        buttonB.setEnabled(false);  //disables the button
        buttonC.setEnabled(false);  //disables the button
        buttonD.setEnabled(false);  //disables the button

        result = (int)((correctGuesses /(double) totalQuestions)*100);      //formula for the result

        textField.setText("RESULTS!");  // adding the text to the textfield
        textArea.setText("");       //adding no text to the textarea
        answerLabelA.setText("");  //adding no text to the textarea
        answerLabelB.setText("");  //adding no text to the textarea
        answerLabelC.setText("");  //adding no text to the textarea
        answerLabelD.setText("");  //adding no text to the textarea

        buttonA.setVisible(false);  // makes the button invisible
        buttonA.setText("");    //adding no text to the textarea
        buttonB.setVisible(false);  // makes the button invisible
        buttonB.setText("");    //adding no text to the textarea
        buttonC.setVisible(false);  // makes the button invisible
        buttonC.setText("");    //adding no text to the textarea
        buttonD.setVisible(false);  // makes the button invisible
        buttonD.setText("");    //adding no text to the textarea
        buttonA.setBackground(new Color(255,251,242));  //background color of the button
        buttonB.setBackground(new Color(255,251,242));  //background color of the button
        buttonC.setBackground(new Color(255,251,242));  //background color of the button
        buttonD.setBackground(new Color(255,251,242));  //background color of the button


        numberRight.setText("("+ correctGuesses +"/"+ totalQuestions +")");     //adding the text to the field
        percentage.setText(result+"%");     //adding the text to the field

        frame.add(numberRight);        //making the number right field visible by adding it into the frame
        frame.add(percentage);     //making the percentage field visible by adding it into the frame
        frame.setTitle("Label zentriert");




    }
}

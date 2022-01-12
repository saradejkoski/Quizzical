package at.ac.fhcampuswien;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Quiz implements ActionListener {

    String[] questions;
    String[][] options;
    char[] answers;

    char answer;    // A-D
    int index; // number of question you currently are
    int correct_guesses = 0;
    int total_questions;
    int result;


    JFrame frame = new JFrame();  //everything is on the frame (the background)
    JTextField textfield = new JTextField();  //creating the textfield for the questions and the questionnumbertitle
    JTextArea textarea = new JTextArea();  // creating the textarea for the answers
    JButton buttonA = new JButton();    //creating buttons a-d to submit answer
    JButton buttonB = new JButton(); //we put label a,b,c, d on
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();

    JLabel answer_labelA = new JLabel();         //creating answerlabels a-d
    JLabel answer_labelB = new JLabel();         //we use this label because we use setbounds
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
/*
What is Jlabel?
Label is a class of java Swing .
JLabel is used to display a short string or an image icon.
JLabel can display text, image or both .
JLabel is only a display of text or image and it cannot get focus .
 */

    JTextField number_right = new JTextField();     //creating textfield for number of right answered questions
    JTextField percentage = new JTextField();        // creating textfield for the precentage of the right answered questions


    public Quiz() {
        loadQuestions();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //behavior the frame at close
        frame.setSize(800, 550);    //size of the frame
        frame.getContentPane().setBackground(new Color(255, 251, 242)); //color of the background
        frame.setLayout(null);   //null layout means absolute positioning - you have to do all the work in your code
        frame.setResizable(false); //changed size of the window

        textfield.setBounds(0, 0, 775, 50); //position of the questionnumbertitle
        /*
        The setBounds() method is used in such a situation to set the position and size.
         To specify the position and size of the components manually,
         the layout manager of the frame can be null.
         */
        textfield.setBackground(new Color(255, 251, 242)); //background color
        textfield.setForeground(new Color(50, 50, 50));  //color of the font
        textfield.setFont(new Font("LCD", Font.BOLD, 30)); //font, fontweight and fontsize
        textfield.setBorder(BorderFactory.createBevelBorder(1)); //outline of textfield
        textfield.setHorizontalAlignment(JTextField.CENTER); //orientation of the questionnumbertitle
        textfield.setEditable(false); //The code setEditable(false) makes the TextField uneditable.
        // It is still selectable and the user can copy data from it, but the user cannot change the TextField's contents directly.
        // The code setEnabled(false), disables this TextField.


        textarea.setBounds(0, 50, 800, 50);    //position of the questionnumbertitle
        textarea.setLineWrap(true);     //If true the lines will be wrapped if they are too long to fit within the allocated width
        textarea.setWrapStyleWord(true);    //Sets the style of wrapping, If true the lines will be wrapped at word boundaries (whitespace),if false, the lines will be wrapped at character boundaries.
        textarea.setBackground(new Color(255, 251, 242));    //color of the background
        textarea.setForeground(new Color(50, 50, 50));      //color of the letters
        textarea.setFont(new Font("LCD", Font.BOLD, 30));       //font, fontweight and fontsize
        textarea.setBorder(BorderFactory.createBevelBorder(1));     //outline of textfield
        textarea.setEditable(false); //The code setEditable(false) makes the TextField uneditable.
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


        answer_labelA.setBounds(125, 100, 500, 100);        //position of the answer label
        answer_labelA.setBackground(new Color(50, 50, 50));     //background color
        answer_labelA.setForeground(new Color(50, 50, 50));     //font color
        answer_labelA.setFont(new Font("LCD", Font.PLAIN, 35));     //font, fontweight and fontsize

        answer_labelB.setBounds(125, 200, 500, 100);        //position of the answer label
        answer_labelB.setBackground(new Color(50, 50, 50));     //background color
        answer_labelB.setForeground(new Color(50, 50, 50));     //font color
        answer_labelB.setFont(new Font("LCD", Font.PLAIN, 35));     //font, fontweight and fontsize

        answer_labelC.setBounds(125, 300, 500, 100);        //position of the answer label
        answer_labelC.setBackground(new Color(50, 50, 50));     //background color
        answer_labelC.setForeground(new Color(50, 50, 50)); //font color
        answer_labelC.setFont(new Font("LCD", Font.PLAIN, 35));     //font, fontweight and fontsize

        answer_labelD.setBounds(125, 400, 500, 100);  //position of the answer label
        answer_labelD.setBackground(new Color(50, 50, 50)); //background color
        answer_labelD.setForeground(new Color(50, 50, 50));  //font color
        answer_labelD.setFont(new Font("LCD", Font.PLAIN, 35));     //font, fontweight and fontsize


        number_right.setBounds(170, 170 ,200,100); //position of the number right field
        number_right.setBounds(170, 170 ,425,100); //position of the number right field
        number_right.setBackground(new Color(255,251,242)); //background color
        number_right.setForeground(new Color(50,50,50));    //font color
        number_right.setFont(new Font("LCD",Font.BOLD,50));     //font, fontweight and fontsize
        number_right.setBorder(BorderFactory.createBevelBorder(1));     //outline of textfield
        number_right.setBorder(null);
        number_right.setHorizontalAlignment(JTextField.CENTER);     //orientation of the number right field
        number_right.setEditable(false);    //The code setEditable(false) makes the TextField uneditable.
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



        frame.add(answer_labelA);   //adding the answerlabels that we created to the frame
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);

        frame.add(buttonA);  // adding the buttons that we created to the frame
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textarea);  // adding the questionnumbertitle that we created to the frame
        frame.add(textfield);  // adding the questions that we created to the frame
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

        total_questions = lines.size(); // nach dem Einlesen die Anzahl Elemente in der Lines Array list
        questions = new String[total_questions];// Index:0 in jeder Zeile
        options = new String[total_questions][4];//Index: 1 2 3 4 in jeder Zeile
        answers = new char[total_questions];// Index 5
        for (int i = 0; i < total_questions; i++) { //wir nehmen aktuelle Zeile (String)
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

        if(index>=total_questions) {        // if number of current question is a higher than the number of the total questions
            results();                      //jump to result
        }
        else {
            textfield.setText("Question "+(index+1));       //Questionnumber increasing when new question
            textarea.setText(questions[index]);         //inserting the right question to the right questionnumber
            answer_labelA.setText(options[index][0]);  // inserting the right options to the right answerlabels
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);

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
                correct_guesses++;              // correct guesses is increased (+1)
            }
        }
        if(e.getSource()==buttonB) {
            answer= 'B';
            if(answer == answers[index]) {
                correct_guesses++;
            }
        }
        if(e.getSource()==buttonC) {
            answer= 'C';
            if(answer == answers[index]) {
                correct_guesses++;
            }
        }
        if(e.getSource()==buttonD) {
            answer= 'D';
            if(answer == answers[index]) {
                correct_guesses++;
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
            answer_labelA.setForeground(new Color(255,0,0));
        if(answers[index] != 'B')
            answer_labelB.setForeground(new Color(255,0,0));  // false answer turns red
        if(answers[index] != 'C')
            answer_labelC.setForeground(new Color(255,0,0));
        if(answers[index] != 'D')
            answer_labelD.setForeground(new Color(255,0,0));


        if(answers[index] == 'A')
            answer_labelA.setForeground(new Color(25,255,0));  //right answer turns green
        if(answers[index] == 'B')
            answer_labelB.setForeground(new Color(25,255,0));
        if(answers[index] == 'C')
            answer_labelC.setForeground(new Color(25,255,0));
        if(answers[index] == 'D')
            answer_labelD.setForeground(new Color(25,255,0));


        Timer pause = new Timer(2000, new ActionListener() { // after one answered question the player has to wait 2000 milliseconds

            @Override
            public void actionPerformed(ActionEvent e) {

                answer_labelA.setForeground(new Color(50,50,50));
                answer_labelB.setForeground(new Color(50,50,50));
                answer_labelC.setForeground(new Color(50,50,50));  //make the color of the answers black
                answer_labelD.setForeground(new Color(50,50,50));

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

        result = (int)((correct_guesses/(double)total_questions)*100);      //formula for the result

        textfield.setText("RESULTS!");  // adding the text to the textfield
        textarea.setText("");       //adding no text to the textarea
        answer_labelA.setText("");  //adding no text to the textarea
        answer_labelB.setText("");  //adding no text to the textarea
        answer_labelC.setText("");  //adding no text to the textarea
        answer_labelD.setText("");  //adding no text to the textarea

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


        number_right.setText("("+correct_guesses+"/"+total_questions+")");     //adding the text to the field
        percentage.setText(result+"%");     //adding the text to the field

        frame.add(number_right);        //making the number right field visible by adding it into the frame
        frame.add(percentage);     //making the percentage field visible by adding it into the frame
        frame.setTitle("Label zentriert");




    }
}

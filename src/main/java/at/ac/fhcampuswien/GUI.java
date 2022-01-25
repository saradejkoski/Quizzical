package at.ac.fhcampuswien;

import javax.swing.*; //this is used to
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    private static GUI instance; //private variable called instance from type GUI
    JFrame frame = new JFrame();  //everything is on the frame (the background)
    JTextField questionTextField = new JTextField();  //creating the textfield for the questions and the questionnumbertitle
    JTextArea textArea = new JTextArea();  // creating the textarea for the answers
    JButton[] buttons = new JButton[4];//we
    JLabel[] answers = new JLabel[4]; //we know that we have four answer options which we fill into[]. if the number should be changeable arraylist
    JTextField numberRight = new JTextField();     //creating textfield for number of right answered questions
    JTextField percentage = new JTextField();
    private Quiz quiz;
    Question currentQuestion;


    //we seperate the initialisation in two steps which is not so elegant
    //either the quiz class would've to be a static reference so GUI can access..or GUI will become not singleton
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }


//we need textfield and add it to a frame


    private GUI() {    //constructor doesnt have a seperate return type
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //behavior the frame at close
        frame.setSize(800, 550);    //size of the frame
        frame.getContentPane().setBackground(new Color(255, 251, 242)); //color of the background
        frame.setLayout(null);   //null layout means absolute positioning - you have to do all the work in your code
        frame.setResizable(false);
        questionTextField.setBounds(0, 0, 775, 50); //position of the questionnumbertitle
        questionTextField.setBackground(new Color(255, 251, 242)); //background color
        questionTextField.setForeground(new Color(50, 50, 50));  //color of the font
        questionTextField.setFont(new Font("LCD", Font.BOLD, 30)); //font, fontweight and fontsize
        questionTextField.setBorder(BorderFactory.createBevelBorder(1)); //outline of textfield
        questionTextField.setHorizontalAlignment(JTextField.CENTER); //orientation of the questionnumbertitle
        questionTextField.setEditable(false); //The code setEditable(false) makes the TextField uneditable.
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


        for (int i = 0; i < buttons.length; i++) {
            answers[i] = new JLabel();
            answers[i].setBounds(125, 100 + i * 100, 500, 100);        //position of the answer label
            answers[i].setBackground(new Color(50, 50, 50));     //background color
            answers[i].setForeground(new Color(50, 50, 50));     //font color
            answers[i].setFont(new Font("LCD", Font.PLAIN, 35));

            buttons[i] = new JButton();



            buttons[i].setBounds(0, 120 + i * 100, 75, 75); //position of the textfield and size of the button
            buttons[i].setBackground(new Color(50, 50, 50));  //background color of the button
            buttons[i].setForeground(new Color(255, 251, 242)); //color of the letters
            buttons[i].setFont(new Font("LCD", Font.BOLD, 30));  //font, fontweight and fontsize
            buttons[i].setFocusable(false);  //?
            buttons[i].addActionListener(this);

            buttons[i].setText((char) ('A' + i) + "");
            //other option: Character.valueOff((char)('a'+1)).toString();
            frame.add(answers[i]);//we add answers and buttons of the array to the frame at the end of the for loop
            frame.add(buttons[i]);

        }
        numberRight.setBounds(300, 140, 200, 100); //position of the number right field
        numberRight.setHorizontalAlignment(JTextField.CENTER);
        numberRight.setBackground(new Color(255, 251, 242)); //background color
        numberRight.setForeground(new Color(50, 50, 50));    //font color
        numberRight.setFont(new Font("LCD", Font.BOLD, 50));     //font, fontweight and fontsize
        numberRight.setBorder(BorderFactory.createBevelBorder(1));     //outline of textfield
        numberRight.setBorder(null);
        numberRight.setHorizontalAlignment(JTextField.CENTER);     //orientation of the number right field
        numberRight.setEditable(false);    //The code setEditable(false) makes the TextField uneditable.
        // It is still selectable and the user can copy data from it, but the user cannot change the TextField's contents directly.
        // The code setEnabled(false), disables this TextField.

        percentage.setBounds(300, 240, 200, 100);
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setBackground(new Color(255, 251, 242));   //background color
        percentage.setForeground(new Color(50, 50, 50));  //font color
        percentage.setFont(new Font("LCD", Font.BOLD, 50));       //font, fontweight and fontsize
        percentage.setBorder(BorderFactory.createBevelBorder(1));       //outline of textfield
        percentage.setBorder(null);
        percentage.setHorizontalAlignment(JTextField.CENTER);       //orientation of the percentage
        percentage.setEditable(false);  //The code setEditable(false) makes the TextField uneditable.
        // It is still selectable and the user can copy data from it, but the user cannot change the TextField's contents directly.
        // The code setEnabled(false), disables this TextField.


        frame.add(textArea);  // adding the questionnumbertitle that we created to the frame
        frame.add(questionTextField);  // adding the questions that we created to the frame
        frame.setVisible(true);  //window does not open if false
    }

    //return value is only GUI
    //getter method without parameter because it usually doesnt make sense unless we want to get sth from arraylist
    public static GUI getInstance() {
        if (instance == null) { //when we start the method it is first null
            instance = new GUI(); //the private constructor will be filled
        }
        return instance;
        /*
        we have a singleton class because we only have one instance
        and we can't create more because the constructor is private
         */


    }


    /**
     * This method is responsible for displaying the current question into the textarea for the question
     * @param question
     * @param questionNumber
     */

    public void displayQuestion(Question question, int questionNumber) {
        currentQuestion=question;
        textArea.setText(question.getQuestion());
        questionTextField.setText("Question: " + questionNumber);
        for (int i = 0; i < answers.length; i++) {
            answers[i].setText(question.getAnswerOptions()[i]);
        }
    }

    /**
     * This method disables the buttons after pressing the button once to summit the answer and it also turn the answers green/red
     * It also sets the timer and turns the answer black again (making it ready for the next question)
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);

        }




        for (int i = 0; i < answers.length; i++) {
            if (i == currentQuestion.getCorrectAnswer()) {
                answers[i].setForeground(new Color(25, 255, 0));
            } else {
                answers[i].setForeground(new Color(255, 0, 0));
            }


        }


        Timer pause = new Timer(2000, new ActionListener() { // after one answered question the player has to wait 2000 milliseconds

            @Override
            public void actionPerformed(ActionEvent a) {//die innere Variable überdeckt die äußere wenn die ganze Methode

                for (int i = 0; i < answers.length; i++) {
                    answers[i].setForeground(new Color(50, 50, 50)); //makes the answers black
                    buttons[i].setEnabled(true);// allows user to press button

                }
                for (int i = 0; i < buttons.length; i++) {//wir gehen 0-3 durch und schauen wars der der geklickt wurde?
                    if (e.getSource() == buttons[i]) {
                        quiz.answerHandover(i);
                    }
                }


               //  answer = ' ';

                // jumps to nextquestion
            }
        });
        pause.setRepeats(false);
        pause.start();
    }

    /**
     * this method is responsible for the results.
     * @param correctAnswers takes in the correct answers of the user
     * @param totalQuestions in comparison to the total questions.
     */
    public void displayResults(int correctAnswers, int totalQuestions) {
        double result = (int) ((correctAnswers / (double) totalQuestions) * 100);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);
            buttons[i].setVisible(false);
            buttons[i].setText("");
            buttons[i].setBackground(new Color(255, 251, 242));
            answers[i].setText("");//adding no text to the textarea
        }
        questionTextField.setText("RESULTS!");  // adding the text to the textfield
        textArea.setText("");       //adding no text to the textarea


        numberRight.setText("(" + correctAnswers + "/" + totalQuestions + ")");     //adding the text to the field
        percentage.setText(result + "%");     //adding the text to the field

        frame.add(numberRight);        //making the number right field visible by adding it into the frame
        frame.add(percentage);     //making the percentage field visible by adding it into the frame
        frame.setTitle("Label zentriert");
        ImageIcon smiley = new ImageIcon("src/main/java/at/ac/fhcampuswien/smileyresult.png");
        JLabel imageLabel = new JLabel(smiley);
        frame.add(imageLabel);
        imageLabel.setBounds(336, 350, 128, 128);
        imageLabel.setVisible(true);
    }
}



package at.ac.fhcampuswien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI implements ActionListener {
    private static GUI instance; //private variable called instance from type GUI
    JFrame frame = new JFrame();  //everything is on the frame (the background)
    JTextField textField = new JTextField();  //creating the textfield for the questions and the questionnumbertitle
    JTextArea textArea = new JTextArea();  // creating the textarea for the answers
    JButton[] buttons = new JButton[4];//we
    JLabel[] answers = new JLabel[4];//we know that we have four answer options which we fill into[]. if the number should be changeable arraylist
    JTextField numberRight = new JTextField();     //creating textfield for number of right answered questions
    JTextField percentage = new JTextField();
    private Quiz quiz;
    //we seperate the initialisation in two steps which is not so elegant
    //either the quiz class wouldve to be a static reference so GUI can access..or GUI will become not singleton
    public void setQuiz (Quiz quiz){
        this.quiz = quiz;
    }
    //das wäre die gettermethode >>
    // public Quiz getQuiz(){
    // return quiz;
    //setter method are always void because the object that calls the method wants to set & NOT get sth
    //wir wollen die QUIZ Referenz befüllen

   // ArrayList<Integer> Numbers = new ArrayList<>(5);

    //Array A,B,C;D schleife mit i ..y geht immer um 100 rauf...(1+i)*100


    private GUI() {    //constructor doesnt have a seperate return type

        for(int i=0;i<buttons.length;i++){
            answers[i].setBounds(125, 100+i*100, 500, 100);        //position of the answer label
            answers[i].setBackground(new Color(50, 50, 50));     //background color
            answers[i].setForeground(new Color(50, 50, 50));     //font color
            answers[i].setFont(new Font("LCD", Font.PLAIN, 35));


            buttons[i].setBounds(0, 120+i*100, 75, 75); //position of the textfield and size of the button
            buttons[i].setBackground(new Color(50, 50, 50));  //background color of the button
            buttons[i].setForeground(new Color(255, 251, 242)); //color of the letters
            buttons[i].setFont(new Font("LCD", Font.BOLD, 30));  //font, fontweight and fontsize
            buttons[i].setFocusable(false);  //?
            buttons[i].addActionListener(this);

            buttons[i].setText((char)('A'+i)+"");
            //other option: Character.valueOff((char)('a'+1)).toString();



        }

    }

    //return value is only GUI
    //getter method without parameter because it usually doesnt make sense unless we want to get sth from arraylist
    public static GUI getInstance() {
        if (instance == null) { //when we start the method it is first null
           instance=new GUI(); //the private constructor will be filled
        }
        return instance;
        /*
        we have a singleton class because we only have one instance
        and we can't create more because the constructor is private
         */

        /*
        es wird immer genau das GUI verändert, was wir vor uns haben.
         */


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i=0;i<buttons.length;i++){
          buttons[i].setEnabled(false);
        }

    }
}

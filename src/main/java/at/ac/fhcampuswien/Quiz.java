package at.ac.fhcampuswien;


import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Quiz implements ActionListener{


    String[] questions =   {
            "What is the speed of sound ?",
            "How many time zones are there ?",
            "What was Java originally called ?",
            "How long is the largest butterfly ?",
            "which metal is best for fabrication of airplane ?",
            "Which element has the lowest boiling point ?",
            "What is the unit used to measure energy ? ",
            "Who is  the first Pokemon in the Pokedex ?",
            "What is the nine-tail fox's name ?",
            "Who was the sixth hokage ?",



    };
    // code umschreiben, sodass es mit mehr fragen gehen soll
    // statt hardcoden ein file einfügen
    String[][] options =   {
            {"120 km/h","1,200km/h","400 ","700 "},
            {"16","24","32","8"},
            {"Apple","Latte","Oak","Koffing"},
            {"40cm","35cm","28 cm","20cm"},
            {"Iron","Copper","Aluminum","Silver"},
            {"Helium","Oxygen","Halogen","Carbon"},
            {"Pascal","Joule","Watt","Newton"},
            {"Mew","pikachu","Bulbasaur","Caterpie"},
            {"Kurama","Saiken","Isobu","shukaku"},
            {"Naruto","Neji","Kakashi","Nagato"}


    };

    char[] answers =      {
            'B',
            'B',
            'C',
            'A',
            'C',
            'A',
            'B',
            'C',
            'A',
            'C',

    };
    char guess;
    char answer;
    int index; //?
    int correct_guesses =0;
    int total_questions = questions.length;
    int result;


    JFrame frame = new JFrame();  //everything is on the frame (the background)
    JTextField textfield = new JTextField();
    JTextArea textarea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();

    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();


    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();


    public Quiz() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //behavior the frame at close
        frame.setSize(800,550);
        frame.getContentPane().setBackground(new Color(255,251,242)); //color of the background
        frame.setLayout(null);  //?
        frame.setResizable(false); //changed size of the window

        textfield.setBounds(0,0,775,50); //position of the questionnumbertitle
        textfield.setBackground(new Color(255,251,242)); //background color
        textfield.setForeground(new Color(50,50,50));  //color of the font
        textfield.setFont(new Font("LCD",Font.BOLD,30)); //font, fontweight and fontsize
        textfield.setBorder(BorderFactory.createBevelBorder(1)); //outline of textfield
        textfield.setHorizontalAlignment(JTextField.CENTER); //orientation of the questionnumbertitle
        textfield.setEditable(false); //The code setEditable(false) makes the TextField uneditable.
        // It is still selectable and the user can copy data from it, but the user cannot change the TextField's contents directly.
        // The code setEnabled(false), disables this TextField.


        textarea.setBounds(0,50,800,50);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setBackground(new Color(255,251,242));
        textarea.setForeground(new Color(50,50,50));
        textarea.setFont(new Font("LCD",Font.BOLD,30));
        textarea.setBorder(BorderFactory.createBevelBorder(1));
        textarea.setEditable(false);

        buttonA.setBounds(0,120,75,75); //position of the textfield and size of the button
        buttonA.setBackground(new Color(50,50,50));  //background color of the button
        buttonA.setForeground(new Color(255,251,242)); //color of the letter
        buttonA.setFont(new Font("LCD",Font.BOLD,30));  //font, fontweight and fontsize
        buttonA.setFocusable(false);  //?
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0,220,75,75);
        buttonB.setBackground(new Color(50,50,50));  //background color of the button
        buttonB.setForeground(new Color(255,251,242)); //color of the letter
        buttonB.setFont(new Font("LCD",Font.BOLD,30));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0,320,75,75);
        buttonC.setBackground(new Color(50,50,50));  //background color of the button
        buttonC.setForeground(new Color(255,251,242)); //color of the letter
        buttonC.setFont(new Font("LCD",Font.BOLD,30));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(0,420,75,75);
        buttonD.setBackground(new Color(50,50,50));  //background color of the button
        buttonD.setForeground(new Color(255,251,242)); //color of the letter
        buttonD.setFont(new Font("LCD",Font.BOLD,30));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        answer_labelA.setBounds(125,100,500,100);
        answer_labelA.setBackground(new Color(50,50,50));
        answer_labelA.setForeground(new Color(50,50,50));
        answer_labelA.setFont(new Font("LCD",Font.PLAIN,35));

        answer_labelB.setBounds(125,200,500,100);
        answer_labelB.setBackground(new Color(50,50,50));
        answer_labelB.setForeground(new Color(50,50,50));
        answer_labelB.setFont(new Font("LCD",Font.PLAIN,35));

        answer_labelC.setBounds(125,300,500,100);
        answer_labelC.setBackground(new Color(50,50,50));
        answer_labelC.setForeground(new Color(50,50,50));
        answer_labelC.setFont(new Font("LCD",Font.PLAIN,35));

        answer_labelD.setBounds(125,400,500,100);
        answer_labelD.setBackground(new Color(50,50,50));
        answer_labelD.setForeground(new Color(50,50,50));  //font color
        answer_labelD.setFont(new Font("LCD",Font.PLAIN,35));



        number_right.setBounds(170, 170 ,425,100);
        number_right.setBackground(new Color(255,251,242));
        number_right.setForeground(new Color(50,50,50));
        number_right.setFont(new Font("LCD",Font.BOLD,50));
        number_right.setBorder(null);
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        percentage.setBounds(170,270,425,100);
        percentage.setBackground(new Color(255,251,242));
        percentage.setForeground(new Color(50,50,50));
        percentage.setFont(new Font("LCD",Font.BOLD,50));
        percentage.setBorder(null);
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);


        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);

        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textarea);
        frame.add(textfield);
        frame.setVisible(true);  //window does not open if false

        nextQuestion();
    }
    public void nextQuestion() {

        if(index>=total_questions) {
            results();
        }
        else {
            textfield.setText("Question "+(index+1));
            textarea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);
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
            if(answer == answers[index]) {
                correct_guesses++;
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
        displayAnswer();
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


        Timer pause = new Timer(2000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                answer_labelA.setForeground(new Color(50,50,50));
                answer_labelB.setForeground(new Color(50,50,50));
                answer_labelC.setForeground(new Color(50,50,50));  //make the color of the answers black
                answer_labelD.setForeground(new Color(50,50,50));

                answer = ' ';
                buttonA.setEnabled(true);  // allows user to press button
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();
    }
    public void results(){
        // a,b,c,d bei results wegbringen und rahmen

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int)((correct_guesses/(double)total_questions)*100);

        textfield.setText("RESULTS!");
        textarea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        buttonA.setVisible(false);
        buttonA.setText("");
        buttonB.setVisible(false);
        buttonB.setText("");
        buttonC.setVisible(false);
        buttonC.setText("");
        buttonD.setVisible(false);
        buttonD.setText("");
        buttonA.setBackground(new Color(255,251,242));  //background color of the button
        buttonB.setBackground(new Color(255,251,242));
        buttonC.setBackground(new Color(255,251,242));
        buttonD.setBackground(new Color(255,251,242));


        number_right.setText("("+correct_guesses+"/"+total_questions+")");
        percentage.setText(result+"%");

        frame.add(number_right);
        frame.add(percentage);

    }
}

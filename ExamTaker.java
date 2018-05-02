import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class ExamTaker extends JPanel implements ActionListener, ChangeListener
{
    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 480;
    private static final String ACTION_LOAD_EXAM = "loadExam";
    private static final String ACTION_SAVE_ANSWERS = "saveAnswers";
    private static final String ACTION_QUESTION_ANSWER = "questionAnswer";

    private static Scanner mClientInput;
    private static String mClientName;
    private static String mExamName;
    private static boolean mLooping;
    private static Exam mExam;
    private static PrintWriter mClientAnswers;
    public static ArrayList<String> mStudentAnswers;
    
    public static JFrame mWindow;
    public static JTextField mNameTextField;
    public static JTextField mExamTextField;
    public static JPanel mQuestionPanel;

    public void stateChanged(ChangeEvent event)
    {
        //JSpinner source = (JSpinner)event.getSource();
        //System.out.println(source.getValue());
        Component comp = (Component) event.getSource();
        int i = 0;
        ArrayList<Question> questions = mExam.getQuestions();
        for(Component c : mQuestionPanel.getComponents())
        {
            if(c == comp)
            {
                break;
            }
            
            else if(c instanceof JLabel)
            {
                i++;
            }
        }
        JSpinner s = (JSpinner) comp;
        NumQuestion numQuest = (NumQuestion) questions.get(i - 1);
        double v = (int) s.getValue();
        double val = (double) v;
        numQuest.studentAnswer = numQuest.getNewAnswer( val );
        //System.out.println("I put " + val + " for question " + (i));
    }
    public void actionPerformed(ActionEvent event) 
    {
        switch(event.getActionCommand())
        {
            case ACTION_LOAD_EXAM:
                mExamName = mExamTextField.getText();
                if(mExamName.contains(".txt"))
                {
                    File f = new File(mExamName);
                
                    try
                    {
                        Scanner s = new Scanner(f);
                        mExam = new Exam(s);
                        s.close();
                    }

                    catch (Exception e) 
                    {
                        e.printStackTrace();
                    }

                    if(mExam != null)
                    {
                        System.out.println("Exam successfully loaded!");
                        mQuestionPanel.removeAll();
                        ArrayList<Question> questions = mExam.getQuestions();
                        int i = 0;
                        for(Question quest : questions)
                        {
                            if(quest instanceof SAQuestion)
                            {
                                SAQuestion q = (SAQuestion) quest;
                                JLabel label = new JLabel(q.text);
                                JTextField textField = new JTextField("", 20);
                                mQuestionPanel.add(label);
                                mQuestionPanel.add(textField);
                                textField.setActionCommand(ACTION_QUESTION_ANSWER);
                                textField.addActionListener( new ExamTaker() );
                                //System.out.println("Loaded SA Question: " + q.text);
                            }

                            else if(quest instanceof MCSAQuestion)
                            {
                                MCSAQuestion q = (MCSAQuestion) quest;
                                JLabel label = new JLabel(q.text);
                                mQuestionPanel.add(label);
                                
                                ArrayList<MCAnswer> answers = q.answers;
                                ButtonGroup group = new ButtonGroup();
                                for(MCAnswer ans : answers)
                                {
                                    JRadioButton button = new JRadioButton(ans.text);
                                    group.add(button);
                                    button.setActionCommand(ACTION_QUESTION_ANSWER);
                                    button.addActionListener( new ExamTaker() );
                                    mQuestionPanel.add(button);
                                }
                            }

                            else if(quest instanceof MCMAQuestion)
                            {
                                MCMAQuestion q = (MCMAQuestion) quest;
                                JLabel label = new JLabel(q.text);
                                mQuestionPanel.add(label);
                                
                                ArrayList<MCAnswer> answers = q.answers;
                                for(MCAnswer ans : answers)
                                {
                                    JCheckBox button = new JCheckBox(ans.text);
                                    button.setActionCommand(ACTION_QUESTION_ANSWER);
                                    button.addActionListener( new ExamTaker() );
                                    mQuestionPanel.add(button);
                                }
                            }

                            if(quest instanceof NumQuestion)
                            {
                                NumQuestion q = (NumQuestion) quest;
                                JLabel label = new JLabel(q.text);
                                JSpinner spinner = new JSpinner();
                                mQuestionPanel.add(label);
                                mQuestionPanel.add(spinner);
                                //spinner.setChangeCommand(ACTION_QUESTION_ANSWER);
                                spinner.addChangeListener( new ExamTaker() );
                                //System.out.println("Loaded SA Question: " + q.text);
                            }
                            

                            mWindow.revalidate();
                            mWindow.repaint();
                        }


                        //mExam.print();
                    }
                }
                break;
            
            case ACTION_SAVE_ANSWERS:
                mClientName = mNameTextField.getText().trim().toLowerCase().replaceAll("\\s+","") + "Answers.txt";
                if(!mClientName.equalsIgnoreCase("Answers.txt"))
                {
                    System.out.println("Saving answers to " + mClientName);

                    int i = 0;
                    ArrayList<Question> questions = mExam.getQuestions();
                    for(Component comp : mQuestionPanel.getComponents())
                    {
                        if(comp instanceof JTextField)
                        {
                            JTextField t = (JTextField) comp;
                            String text = t.getText();
                            SAQuestion saQuest = (SAQuestion)questions.get(i - 1);
                            
                            saQuest.studentAnswer = saQuest.getNewAnswer(text);
                        }

                        else if(comp instanceof JSpinner)
                        {
                            JSpinner s = (JSpinner) comp;
                            NumQuestion numQuest = (NumQuestion) questions.get(i - 1);
                            double v = (int) s.getValue();
                            double val = (double) v;
                            numQuest.studentAnswer = numQuest.getNewAnswer( val );
                        }
                        
                        else if(comp instanceof JLabel)
                        {
                            i++;
                        }
                    }

                    if(mClientAnswers == null)
                    {
                        try
                        {
                            mClientAnswers = new PrintWriter( mClientName );
                        }

                        catch(Exception e)
                        {
                            System.out.println("File not Found.\n");
                        }
                    }

                    if(mClientAnswers != null)
                    {
                        mClientAnswers.println(mClientName);
                        mClientAnswers.println(mExamName);
                        mExam.saveStudentAnswers(mClientAnswers);
                        mClientAnswers.flush();
                        System.out.println("Answers saved!");
                        break;
                    }
                }

                break;
            
            case ACTION_QUESTION_ANSWER:
                Component comp = (Component) event.getSource();
                int i = 0;
                ArrayList<Question> questions = mExam.getQuestions();
                for(Component c : mQuestionPanel.getComponents())
                {
                    if(c == comp)
                    {
                        break;
                    }
                    
                    else if(c instanceof JLabel)
                    {
                        i++;
                    }
                }

                Question quest = questions.get(i - 1);
                if(comp instanceof JTextField)
                {
                    JTextField t = (JTextField) comp;
                    String text = t.getText();
                    SAQuestion saQuest = (SAQuestion)quest;
                    
                    quest.studentAnswer = saQuest.getNewAnswer(text);
                    //questions.get(i - 1).studentAnswer = ans;
                    //System.out.println("Your answer for question " + (i) + " " + t.getText());
                }
                else if(comp instanceof JRadioButton)
                {
                    int j = 0;
                    int pos = 0;
                    for(Component c : mQuestionPanel.getComponents())
                    {
                        if(c == comp)
                        {
                            break;
                        }
                        
                        else if(c instanceof JLabel)
                        {
                            j++;
                        }

                        if(j == i)
                        {
                            pos++;
                        }
                    }
                    MCSAQuestion mcsaQuest = (MCSAQuestion) quest;
                    JRadioButton rb = (JRadioButton) comp;
                    mcsaQuest.setStudentAnswer(pos - 1);
                    //System.out.println("Your answer for question " + (i) + " " + rb.getText() + "position is " + (pos));
                }

                else if(comp instanceof JCheckBox)
                {
                    int j = 0;
                    int pos = 0;
                    for(Component c : mQuestionPanel.getComponents())
                    {
                        if(c == comp)
                        {
                            break;
                        }
                        
                        else if(c instanceof JLabel)
                        {
                            j++;
                        }

                        if(j == i)
                        {
                            pos++;
                        }
                    } 
                    
                    MCMAQuestion mcmaQuest = (MCMAQuestion) quest;
                    mcmaQuest.setStudentAnswer(pos - 1);
                    JCheckBox cb = (JCheckBox) comp;
                    //System.out.println("Your answer for question " + (i) + " " + cb.getText());
                }
                break;
        }
    }

    public static void setupWindow()
    {
        mWindow = new JFrame();
        mWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        mWindow.setLocationRelativeTo(null);
        mWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mWindow.setTitle("Exam Taker");
        JPanel mainPanel = new JPanel();
        mQuestionPanel = new JPanel();
        JScrollPane mQuestionScroll = new JScrollPane(mQuestionPanel);
        //mQuestionScroll.setLayout(new BoxLayout(mQuestionScroll, BoxLayout.Y_AXIS));
        mQuestionPanel.setLayout(new BoxLayout(mQuestionPanel, BoxLayout.Y_AXIS));
        
        //JRadioButton rButton =    new JRadioButton("Gotchu");
        JLabel nameLabel = new JLabel("Enter your name:");
        mNameTextField = new JTextField("", 20);
        JLabel examLabel = new JLabel("Enter the name of the exam you want to take:");
        mExamTextField = new JTextField("", 20);

        mExamTextField.setActionCommand(ACTION_LOAD_EXAM);
        mExamTextField.addActionListener( new ExamTaker() );

        mainPanel.add(nameLabel);
        mainPanel.add(mNameTextField);
        mainPanel.add(examLabel);
        mainPanel.add(mExamTextField);

        mainPanel.add(mQuestionScroll);

        JButton loadButton = new JButton("Load Exam");
        loadButton.setActionCommand(ACTION_LOAD_EXAM);
        loadButton.addActionListener( new ExamTaker() );

        JButton saveButton = new JButton("Save Answers");
        saveButton.setActionCommand(ACTION_SAVE_ANSWERS);
        saveButton.addActionListener( new ExamTaker() );

        mainPanel.add(loadButton);
        mainPanel.add(saveButton);
       
        mWindow.add(mainPanel);
        mWindow.setVisible(true);
    }
    
    private static void handleCommand(String command)
    {
        System.out.print("      ");
        switch(command.toLowerCase().trim())
        {
            case "name":
                System.out.print("Enter your name: ");
                mClientName = mClientInput.nextLine();
                break;

            case "exam":
                System.out.print("Enter the name of the exam file: ");
                mExamName = mClientInput.nextLine();
                File f = new File(mExamName);
                
                try
                {
                    Scanner s = new Scanner(f);
                    mExam = new Exam(s);
                    s.close();
                }

                catch (Exception e) 
                {
                    e.printStackTrace();
                }

                if(mExam != null)
                {
                    System.out.println("Exam successfully loaded!");
                    mExam.print();
                }

                break;
            
            case "answer":
                if(mExam == null)
                {
                    System.out.println("Error: please load an exam, before attempting to answer!");
                    break;
                }

                mExam.getAnswersForExamTaker();
                break;

            case "save":
                if(mExam == null)
                {
                    System.out.println("Error: please load an exam, before attempting to save!");
                    break;
                }

                if(mClientName == null)
                {
                   System.out.print("Enter your name: ");
                   mClientName = mClientInput.nextLine();
                }

                if(mClientAnswers == null)
                {
                    try
                    {
                        mClientAnswers = new PrintWriter( mClientName.trim().toLowerCase().replaceAll("\\s+","") + "Answers.txt" );
                    }

                    catch(Exception e)
                    {
                        System.out.println("File not Found.\n");
                    }
                }

                mExam.printStudentAnswers();

                if(mClientAnswers != null)
                {
                    System.out.print("Are you sure you want to save these answers? (answer <Yes> if you do): ");
                    String response = mClientInput.nextLine().trim();
                    if(!response.equalsIgnoreCase("yes"))
                    {
                        break;
                    }

                    mClientAnswers.println(mClientName);
                    mClientAnswers.println(mExamName);
                    mExam.saveStudentAnswers(mClientAnswers);
                    mClientAnswers.flush();
                    System.out.println("Answers saved!");
                    break;
                }
            
            case "quit":
                mLooping = false;
                System.out.println("Exiting program!");
                break;

            default:
                System.out.println("Error: invalid command, please enter another one!");
        }
    }

    public static void main(String[] args)
    {
        setupWindow();
        mLooping = true;
        while(mLooping)
        {

        }
        /*
        mClientInput = ScannerFactory.getKeyboardScanner();
        String[] commands = 
        {
            "Name: Allows the exam taker to enter their name",
            "Exam: Allows the exam taker to enter the file they want to",
            "Answer: Allows the exam taker to answer a question",
            "Save: Allows the exam taker to save their answers",
            "Quit: Quits the program"
        };


        while(mLooping)
        {
            System.out.println("Enter one of the following commands");

            for(int i = 0; i < commands.length; i++)
            {
                System.out.println("    " + commands[i]);
            }
            System.out.println("\n");
            
            System.out.print("Enter command: ");
            handleCommand( mClientInput.nextLine() );
        }
        */
    }
}
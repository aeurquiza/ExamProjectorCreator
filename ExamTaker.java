import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class ExamTaker
{
    private static Scanner mClientInput;
    private static String mClientName;
    private static String mExamName;
    private static boolean mLooping;
    private static Exam mExam;
    private static PrintWriter mClientAnswers;
    public static ArrayList<String> mStudentAnswers;

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
        mLooping = true;
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
    }
}
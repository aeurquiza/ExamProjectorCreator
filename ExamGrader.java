import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JFrame;



public class ExamGrader {

    public static JFrame frame;

    public static void main(String[] args) {

        frame = new JFrame();

        String q = "";

        while(!q.equals("q")) {

            JOptionPane.showMessageDialog(frame,"Welcome to ExamGrader");

            Scanner aInput = null;
            String fAns = null;

            //ScannerFactory userInputScanner1 = new ScannerFactory();
            //System.out.println("Answer File Name:");
            //userInputScanner1.getKeyboardScanner();
            //fAns = userInputScanner1.getKeyboardScanner().nextLine();

            fAns = JOptionPane.showInputDialog(frame, "Answer File name: ");

            try {
                aInput = new Scanner(new File(fAns));

            } catch (Exception e) {
                //System.out.println("File not found!");
                JOptionPane.showMessageDialog(frame,"File not found!");
            }

            //---------------------------------------

            String name = aInput.nextLine();
            String examFileName = aInput.nextLine();

            Scanner eInput = null;
            //String fExam = null;

            try {
                eInput = new Scanner(new File(examFileName));

            } catch (Exception e) {
                //System.out.println("File not found..");
                JOptionPane.showMessageDialog(frame,"File not found..");
            }

            Exam newExam = new Exam(eInput);
            //newExam.print();
            JOptionPane.showMessageDialog(frame, newExam.getString());
            newExam.restoreStudentAnswers(aInput);
            //newExam.reportQuestionValues();
            JOptionPane.showMessageDialog(frame, newExam.getReport());

            //---------------------------------------


            //Scanner kin = new Scanner(System.in);
            //System.out.println("Enter CSV File to print scores to");

            String writeFile = null;
            writeFile = JOptionPane.showInputDialog(frame, "Enter CSV File to print scores to: ");

            //String writeFile = kin.nextLine();

            PrintWriter pw = null;

            try {
                pw = new PrintWriter(new File(writeFile));

            } catch (Exception e) {
                //System.out.println("File not found");
                JOptionPane.showMessageDialog(frame,"File not found");
            }

            pw.print("Name: " + name + ", ");
            pw.print("Total: " + newExam.getValue() + ", ");
            pw.print(newExam.storeScoreToCSV());
            pw.close();

            //System.out.println("Enter q to quit..");
            //q = kin.nextLine();

            JOptionPane.showMessageDialog(frame,"File created. Exiting program.");
            System.exit(0);

        }
    } //end main
} // end class

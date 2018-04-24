import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JFrame;


public class ExamGrader {

    public static JFrame frame;

    public static void main(String[] args) {

        while(true) {

            frame = new JFrame();

                JOptionPane.showMessageDialog(frame, "Welcome to ExamGrader");

                Scanner aInput = null;
                String fAns = null;

                fAns = JOptionPane.showInputDialog(frame, "Answer File name: ");

                try {
                    aInput = new Scanner(new File(fAns));

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(frame, "File not found!");
                }

                //---------------------------------------

                String name = aInput.nextLine();
                String examFileName = aInput.nextLine();

                Scanner eInput = null;

                try {
                    eInput = new Scanner(new File(examFileName));

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "File not found..");
                }

                Exam newExam = new Exam(eInput);
                JOptionPane.showMessageDialog(frame, newExam.getString());
                newExam.restoreStudentAnswers(aInput);
                JOptionPane.showMessageDialog(frame, newExam.getReport());

                //---------------------------------------

                String writeFile = null;
                writeFile = JOptionPane.showInputDialog(frame, "Enter CSV File to print scores to: ");

                PrintWriter pw = null;

                try {
                    pw = new PrintWriter(new File(writeFile));

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "File not found");
                }

                pw.print("Name: " + name + ", ");
                pw.print("Total: " + newExam.getValue() + ", ");
                pw.print(newExam.storeScoreToCSV());
                pw.close();

                JOptionPane.showMessageDialog(frame, "File created.");

                String input = JOptionPane.showInputDialog(frame, "Enter q to Quit or any key to Continue");

                if (input == "q" || input == "Q") {

                    JOptionPane.showMessageDialog(frame, "Exiting program.");
                    System.exit(0);
                }
            }
        } //end main
    } // end class

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;


public class ExamGrader {

    public static void main(String[] args) {

        String q = "";

        while(!q.equals("q")) {

            Scanner aInput = null;
            String fAns = null;
            ScannerFactory userInputScanner1 = new ScannerFactory();

            System.out.println("Answer File Name:");

            userInputScanner1.getKeyboardScanner();
            fAns = userInputScanner1.getKeyboardScanner().nextLine();

            try {
                aInput = new Scanner(new File(fAns));

            } catch (Exception e) {
                System.out.println("File not found!");
            }

            //---------------------------------------

            String name = aInput.nextLine();
            String examFileName = aInput.nextLine();
            System.out.println(examFileName);

            Scanner eInput = null;
            //String fExam = null;

            try {
                eInput = new Scanner(new File(examFileName));

            } catch (Exception e) {
                System.out.println("File not found..");
            }

            Exam newExam = new Exam(eInput);
            aInput.nextLine();
            newExam.restoreStudentAnswers(aInput);
            newExam.printStudentAnswers(); 
            newExam.reportQuestionValues();

            //---------------------------------------


            Scanner kin = new Scanner(System.in);
            System.out.println("Enter CSV File to print scores to");
            String writeFile = kin.nextLine();
            PrintWriter pw = null;

            try {
                pw = new PrintWriter(new File(writeFile));

            } catch (Exception e) {
                System.out.println("File not found");
            }

            pw.print("Name: " + name + ", ");
            pw.print("Total: " + newExam.getValue() + ", ");
            pw.print(newExam.storeScoreToCSV());
            pw.close();

            System.out.println("Enter q to quit..");
            q = kin.nextLine();

        }
    } //end main
} // end class

import java.util.Scanner;
import java.io.PrintWriter;

public class NumQuestion extends Question{
		double tolerance;
	public NumQuestion(String text, double pMaxValue, double pTolerance){
		super(text,pMaxValue);
		tolerance=pTolerance;

	}

	public NumQuestion(Scanner scanner){
		super(scanner);
        rightAnswer = new NumAnswer(scanner.nextDouble());
        scanner.nextLine();
        tolerance= scanner.nextDouble();
        scanner.nextLine();
	}

	public Answer getNewAnswer(){
		return null;
	}

	public void getAnswerFromStudent(){

	}

	public double getValue(){
		return 0;
	}

	public void save(PrintWriter pw){
		pw.println("NumQuestion");
		pw.println(maxValue);
		pw.println(text);
		rightAnswer.save(pw);
		pw.println(tolerance);
	}


	    public void saveStudentAnswer(PrintWriter writer)
    {
        if(studentAnswer == null)
        {
            return;
        }
        
        writer.println("NumAnswer");
        studentAnswer.save(writer);
    }

    public void restoreStudentAnswers(Scanner scanner)
    {
        studentAnswer = new SAAnswer(scanner);
    }



}
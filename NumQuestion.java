import java.util.Scanner;
import java.io.PrintWriter;

public class NumQuestion extends Question{
		double tolerance;
	public NumQuestion(String text, double maxValue, double pTolerance){
		super(text,maxValue);
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
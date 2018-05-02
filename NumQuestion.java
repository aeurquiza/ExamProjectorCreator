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
        rightAnswer = new NumAnswer(scanner, false);
        tolerance= scanner.nextDouble();
	}

	public Answer getNewAnswer(){
		return new NumAnswer(0.0);
	}

	public Answer getNewAnswer(double val)
	{
		return new NumAnswer(val);
	}

	public void getAnswerFromStudent(){
		Scanner client=new Scanner(System.in);
		Double input=Double.parseDouble(client.nextLine());
		studentAnswer=new NumAnswer(input);
	}

	public double getValue(){
		return super.getValue();
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
        studentAnswer = new NumAnswer(scanner);
    }

    public void printStudentAnswers(){
    	        if(studentAnswer == null)
        {
            System.out.println("Your answer:");
            System.out.println("    You have not answered this question");
            return;
        }

        if(studentAnswer instanceof NumAnswer)
        {
            NumAnswer ans = (NumAnswer)studentAnswer;
            System.out.println("Your answer:");
            System.out.println("    "  + ans.value);
        }

    }



}
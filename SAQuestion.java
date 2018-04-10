import java.util.Scanner; 
import java.io.PrintWriter;

public class SAQuestion extends Question
{
    public SAQuestion(String text, double maxValue)
    {
        super(text, maxValue);
    }

    public SAQuestion(Scanner scanner)
    {
        super(scanner);
        rightAnswer = new SAAnswer(scanner.nextLine());
    }

    public Answer getNewAnswer()
    {
        SAAnswer answer = new SAAnswer("");
        // contents.add(answer);
        return answer;
    }

    public Answer getNewAnswer(String text)
    {
        SAAnswer answer = new SAAnswer(text);
        // contents.add(answer);
        return answer;
    }

    public void getAnswerFromStudent()
    {
        System.out.println( text );
        System.out.print("  Enter answer: ");
        Scanner client = new Scanner(System.in);
        String input = client.nextLine();
        studentAnswer = getNewAnswer(input);
    }

    public double getValue()
    {
        return super.getValue();
    }

    public static void main(String[] args)
    {
        System.out.println("Hello SAQuestion.java");

        SAQuestion test = new SAQuestion("What year did Marquise start prgrogramming?", 10.0);
        Answer a_1 = test.getNewAnswer("2011");
        test.rightAnswer = a_1;
        test.print();
        test.getAnswerFromStudent();
        System.out.println("Score: " + test.getValue() + "/" + test.maxValue);
    }

    public void save(PrintWriter writer)
    {
        writer.println("SAQuestion");
        writer.println(maxValue);
        writer.println(text);
        rightAnswer.save(writer);
    }

    public void saveStudentAnswer(PrintWriter writer)
    {
        if(studentAnswer == null)
        {
            return;
        }
        
        writer.println("SAAnswer");
        studentAnswer.save(writer);
    }

    public void restoreStudentAnswers(Scanner scanner)
    {
        studentAnswer = new SAAnswer(scanner);
    }


}
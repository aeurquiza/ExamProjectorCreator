import java.util.*;
import java.io.PrintWriter;

public abstract class Question
{
    protected String text;
    protected Answer rightAnswer;
    protected Answer studentAnswer;
    protected double maxValue;

    protected Question(String text, double maxValue)
    {
        this.text = text;
        this.maxValue = maxValue;
        // contents = new ArrayList<Answer>();
    }

    public Question(Scanner scanner)
    {
        maxValue = scanner.nextDouble();
        scanner.nextLine();
        text = scanner.nextLine();
    }

    public void print()
    {
        System.out.println(text);
        /*

        for(int i = 0; i < contents.size(); i++)
        {
            System.out.print("  " + ((char) ('A' + i)) + ") ");
            contents.get(i).print();
        }
        
        */

    }

    public String getString(){
        return text;
    }

    protected void printStudentAnswers()
    {
       System.out.println("     You have not answered this question");
    }

    public void setRightAnswer(Answer rightAnswer)
    {
        this.rightAnswer = rightAnswer;
    }

    public abstract Answer getNewAnswer();

    public void getAnswerFromStudent()
    {
        System.out.println("You need to complete getAnswerFromStudent()");
    }

    public double getValue()
    {
        if(rightAnswer == null || studentAnswer == null)
        {
            return 0.0;
        }
        return rightAnswer.getCredit(studentAnswer) * maxValue;
    }

    abstract public void save(PrintWriter writer);

    abstract public void saveStudentAnswer(PrintWriter writer);

    abstract public void restoreStudentAnswers(Scanner scanner);
}
import java.util.*;
import java.io.PrintWriter;

public class SAAnswer extends Answer
{
    protected String text;
    public SAAnswer(String text)
    {
        this.text = text;
    }

    public SAAnswer(Scanner scanner)
    {
        this.text = scanner.nextLine();
    }

    public void print()
    {
        System.out.println(text);
    }

    public double getCredit(Answer rightAnswer)
    {
        if( rightAnswer instanceof SAAnswer )
        {
            SAAnswer correctAnswer = (SAAnswer)rightAnswer;
            if( text.equalsIgnoreCase(correctAnswer.text) )
            {
                return 1.0;
            }
        }

        return 0.0;
    }

    public void save(PrintWriter writer)
    {
        writer.println(text);
    }

    public void saveStudentAnswer(PrintWriter writer)
    {
        writer.println(text);
    }

    public static void main(String args[])
    {
        System.out.println("Hello World");
        SAAnswer a1 = new SAAnswer("one");
        SAAnswer a2 = new SAAnswer("one point zero");
        a1.print();
        a2.print();
        System.out.println(a1.getCredit(a1));
        System.out.println(a1.getCredit(a2));
    }
}
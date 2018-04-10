import java.util.*;
import java.io.PrintWriter;

public class MCSAAnswer extends MCAnswer
{
    public MCSAAnswer(String text, double creditIfSelected)
    {
        super(text, creditIfSelected);
    }

    public MCSAAnswer(Scanner scanner)
    {
        super(scanner);
    }

    public double getCredit(Answer rightAnswer)
    {
        return 1.0;
    }

    public void save(PrintWriter writer)
    {
        super.save(writer);
    }

    public void saveStudentAnswer(PrintWriter writer)
    {
        super.save(writer);
    }

    public static void main(String args[])
    {
        System.out.println("Hello World2");
        MCSAAnswer a1 = new MCSAAnswer("one2", 10.0);
        MCSAAnswer a2 = new MCSAAnswer("one2 point zero", 0.0);
        a1.print();
        a2.print();
        System.out.println(a1.getCredit(a1));
        System.out.println(a1.getCredit(a2));
    }
}
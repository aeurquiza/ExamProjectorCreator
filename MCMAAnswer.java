import java.util.*;
import java.io.PrintWriter;

public class MCMAAnswer extends MCAnswer
{
    public MCMAAnswer(String text, double creditIfSelected)
    {
        super(text, creditIfSelected);
    }

    public MCMAAnswer(Scanner scanner)
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
        super.saveStudentAnswer(writer);
    }
}
import java.util.*;
import java.io.PrintWriter;

public abstract class MCAnswer extends Answer
{
    protected String text;
    protected boolean selected;
    protected double creditIfSelected;

    protected MCAnswer(String text, double creditIfSelected)
    {
        this.text = text;
        this.creditIfSelected = creditIfSelected;
        selected = false;
    }

    public MCAnswer(Scanner scanner)
    {
       if(scanner.hasNextDouble())
        {
            this.creditIfSelected = scanner.nextDouble();
        }
        if(scanner.hasNextLine())
        {
            this.text = scanner.nextLine();
        }
    }

    public void print()
    {
        System.out.println(text);
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public void save(PrintWriter writer)
    {
        writer.print(creditIfSelected);
        writer.println(" " + text);
    }

    public void saveStudentAnswer(PrintWriter writer)
    {
        writer.println(text);
    }
}
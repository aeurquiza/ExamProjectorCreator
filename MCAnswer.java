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

    public MCAnswer(Scanner scanner, boolean ignore)
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
    
    public MCAnswer(Scanner scanner)
    {
        if(!scanner.hasNextLine())
        {
           return;
        }

        String line = scanner.nextLine();
        System.out.println("Phase 0: " + line);

        while(true)
        {
            if(line.equalsIgnoreCase("MCMAAnswer") || line.equalsIgnoreCase("MCSAAnswer"))
            {
                System.out.println("made it: " + line);
                 if(scanner.hasNextDouble())
                {
                    this.creditIfSelected = scanner.nextDouble();
                    System.out.println("credit: " + this.creditIfSelected);
                }
                if(scanner.hasNextLine())
                {
                    this.text = scanner.nextLine();
                    System.out.println("text: " + this.text);
                }
                return;
            }

            if(!scanner.hasNextLine())
            {
                return;
            }

            line = scanner.nextLine();
            System.out.println("Phase 1: " + line);
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
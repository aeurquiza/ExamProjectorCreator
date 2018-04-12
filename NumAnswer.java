import java.util.Scanner;
import java.io.PrintWriter;

public class NumAnswer extends Answer
{
    protected double value;

    public NumAnswer(double value)
    {
        this.value = value;
    }

    public NumAnswer(Scanner scanner, boolean ignore)
   {
       value=scanner.nextDouble();
       scanner.nextLine();
   }
    
     public NumAnswer(Scanner scanner)
    {
        if(!scanner.hasNext())
        {
           return;
        }

        String line = scanner.nextLine();

        while(true)
        {
            if(line.equalsIgnoreCase("NumAnswer"))
            {
                 value=scanner.nextDouble();
                scanner.nextLine();
                return;
            }

            if(!scanner.hasNext())
            {
                return;
            }

            line = scanner.nextLine();
        }
    }
    

    public void print()
    {
        System.out.println(value);
    }

    public double getCredit(Answer rightAnswer)
    {
        if( rightAnswer instanceof NumAnswer )
        {
            NumAnswer correctAnswer = (NumAnswer)rightAnswer;
            if( correctAnswer.value == value )
            {
                return 1.0;
            }
        }

        return 0.0;
    }


    public void save(PrintWriter writer)
    {
        writer.println(value);
    }

    public void saveStudentAnswer(PrintWriter writer)
    {
        writer.println(value);
    }


    public static void main(String args[])
    {
        System.out.println("Hello World");
        NumAnswer a1 = new NumAnswer(1);
        NumAnswer a2 = new NumAnswer(1.0);
        a1.print();
        a2.print();
        System.out.println(a1.getCredit(a1));
        System.out.println(a1.getCredit(a2));
    }
}
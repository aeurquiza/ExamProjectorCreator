import java.util.*;
import java.io.PrintWriter;

public class MCMAQuestion extends MCQuestion
{
    protected double baseCredit;
    protected ArrayList<Answer> studentAnswers;

    public MCMAQuestion(String text, double maxValue, double baseValue)
    {
        super(text, maxValue);
        baseCredit = baseValue;

        studentAnswers = new ArrayList<Answer>();

    }

    public MCMAQuestion(Scanner scanner)
    {
        super(scanner);
        baseCredit = scanner.nextDouble();
        
        int amount = scanner.nextInt();

        for(int i = 0; i < amount; ++i)
        {
            addAnswer(new MCMAAnswer(scanner, false));
        }

        studentAnswers = new ArrayList<Answer>();
    }

    public Answer getNewAnswer()
    {
        return new MCMAAnswer("emptyAnswerEmptyAnswer", 0.0);
    }

    public Answer getNewAnswer(String text)
    {
        return new MCMAAnswer(text, 0.0);
    }

    public Answer getNewAnswer(String text, double creditIfSelected)
    {
        return new MCMAAnswer(text, creditIfSelected);
    }

    public void getAnswerFromStudent()
    {
        Scanner client = new Scanner(System.in);
        char input = client.next().charAt(0);
        input = Character.toUpperCase(input);
        int index = input - 'A';
        
        if(studentAnswers == null)
        {
            studentAnswers = new ArrayList<Answer>();
        }

        MCMAAnswer answer = (MCMAAnswer)answers.get(index);

        if(studentAnswers.contains(answer))
        {
            studentAnswers.remove(answer);
        }
        else
        {
            studentAnswers.add(answer);
        }
        
    }

    public void setStudentAnswer(int pos)
    {
        int index = pos;
        
        if(studentAnswers == null)
        {
            studentAnswers = new ArrayList<Answer>();
        }

        MCMAAnswer answer = (MCMAAnswer)answers.get(index);

        if(studentAnswers.contains(answer))
        {
            studentAnswers.remove(answer);
        }
        else
        {
            studentAnswers.add(answer);
        }
        
    }

    public double getValue()
    {
        double total_value = 0.0;
        MCMAAnswer answer;

        for(int i = 0; i < studentAnswers.size(); ++i)
        {
            answer = (MCMAAnswer)studentAnswers.get(i);
            total_value += answer.creditIfSelected;
        }

        return total_value;
    }

    protected void printStudentAnswers()
    {
        if(studentAnswers == null)
        {
            System.out.println("Your answer:");
            System.out.println("    You have not answered this question");
            return;
        }

        MCMAAnswer answer;
        System.out.println("Your answer:");

        if(studentAnswers.size() == 0)
        {
            System.out.println("    You have not answered this question");
        }

        for(int i = 0; i < studentAnswers.size(); ++i)
        {
            answer = (MCMAAnswer)studentAnswers.get(i);
            System.out.println("    "  + answer.text);
            
        }

    }

    public void save(PrintWriter writer)
    {
        writer.println("MCMAQuestion");
        writer.println(maxValue);
        writer.println(text);
        writer.println(baseCredit);
        writer.println(answers.size());

        MCMAAnswer answer;

        for(int i = 0; i < answers.size(); ++i)
        {
            answer = (MCMAAnswer)answers.get(i);
            answer.save(writer);

        }
    }

    public void saveStudentAnswer(PrintWriter writer)
    {
        writer.println("MCMAAnswer");
        writer.println(studentAnswers.size());
        
        for(int i = 0; i < studentAnswers.size(); ++i)
        {
            studentAnswers.get(i).save(writer);
        }
    }

    public void restoreStudentAnswers(Scanner scanner)
    {
        if(!scanner.hasNext())
        {
           return;
        }

        String line = scanner.nextLine();

        while(true)
        {
            if(line.equalsIgnoreCase("MCMAAnswer"))
            {
                 if(!scanner.hasNextInt())
                {
                    return;
                }
        
                int amount = scanner.nextInt();
                scanner.nextLine();

                for(int i = 0; i < amount; ++i)
                {
                    studentAnswers.add(new MCMAAnswer(scanner,false));
                }
                return;
            }

            if(!scanner.hasNext())
            {
                return;
            }

            line = scanner.nextLine();
        }
    }


        


}

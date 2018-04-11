import java.util.Scanner; 
import java.io.PrintWriter;

public class MCSAQuestion extends MCQuestion
{
    public MCSAQuestion(String text, double maxValue)
    {
        super(text, maxValue);
    }

    public MCSAQuestion(Scanner scanner)
    {
        super(scanner);
        int amount = scanner.nextInt();

        for(int i = 0; i < amount; ++i)
        {
            addAnswer(new MCSAAnswer(scanner));
        }

    }

    public MCSAAnswer getNewAnswer()
    {
        MCSAAnswer answer = new MCSAAnswer("", 0.0);
        //answers.add(answer);
        return answer;
    }

    public MCSAAnswer getNewAnswer(String text)
    {
        MCSAAnswer answer = new MCSAAnswer(text, 0.0);
        //answers.add(answer);
        return answer;
    }

    public MCSAAnswer getNewAnswer(String text, double creditIfSelected)
    {
        MCSAAnswer answer = new MCSAAnswer(text, creditIfSelected);
        //answers.add(answer);
        return answer;
    }

    public void getAnswerFromStudent()
    {
        Scanner client = new Scanner(System.in);
        char input = client.next().charAt(0);
        input = Character.toUpperCase(input);
        int index = input - 'A';
        
        MCSAAnswer answer;
        if((studentAnswer != null) && studentAnswer instanceof MCSAAnswer)
        {
            answer = (MCSAAnswer)studentAnswer;
            answer.setSelected(false);
        }
        answer = (MCSAAnswer) answers.get(index);
        answer.setSelected(true);
        studentAnswer = answer;
    }

    public double getValue()
    {
        if(studentAnswer == null)
        {
            return 0.0;
        }

        if(studentAnswer instanceof MCSAAnswer)
        {
            MCSAAnswer answer = (MCSAAnswer)studentAnswer;
            return (answer.getCredit(rightAnswer) * answer.creditIfSelected);
        }



        return 0.0;
    }

    public void save(PrintWriter writer)
    {
        writer.println("MCSAQuestion");
        writer.println(maxValue);
        writer.println(text);
        writer.println(answers.size());

        for(int i = 0; i < answers.size(); ++i)
        {
            answers.get(i).save(writer);
        }
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
        studentAnswer = new MCSAAnswer(scanner);
    }


}
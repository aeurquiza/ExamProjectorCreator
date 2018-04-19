import java.util.*;
import java.io.PrintWriter;

public abstract class MCQuestion extends Question
{
    protected ArrayList<MCAnswer> answers;

    public MCQuestion(String text, double maxValue)
    {
        super(text, maxValue);
        answers = new ArrayList<MCAnswer>();
    }

    public MCQuestion(Scanner scanner)
    {
        super(scanner); 
        answers = new ArrayList<MCAnswer>();  
    }

    public void print()
    {
        System.out.println(text);

        for(int i = 0; i < answers.size(); i++)
        {
            System.out.print("      " + ((char) ('A' + i)) + ") ");
            answers.get(i).print();
        }

    }

    public String getString(){ 
        String qString=text+"\n";
        for(int i=0; i < answers.size(); i++){
            qString+="      "+((char)('A'+ i))+") ";
            qString+=answers.get(i).getString()+"\n";
        }
        return qString;
    }

    public void addAnswer(MCAnswer answer)
    {
        answers.add(answer);
    }

    public void reorderAnswers()
    {
        MCAnswer temp;
        int pos;
        int rand_val;
        for(int i = 0; i < answers.size(); i++)
        {
            temp = answers.get(i);
            rand_val =  (int )(Math.random() * 10  );
            pos = rand_val % answers.size();
            answers.set(i, answers.get(pos));
            answers.set(pos, temp);
        }
    }
    
}
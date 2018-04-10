public class NumAnswer extends Answer
{
    protected Number value;

    public NumAnswer(Number value)
    {
        this.value = value;
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
            if( correctAnswer == rightAnswer )
            {
                return 1.0;
            }
        }

        return 0.0;
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
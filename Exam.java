import java.util.*;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Date;
public class Exam {
    private String title;
    private ArrayList<Question> questions;

    public Exam(String header){
        title = header;
        questions = new ArrayList<Question>();
    }

    public Exam(Scanner fin){
        title=fin.nextLine();
        questions = new ArrayList<Question>(1);
        while(fin.hasNextLine()){
            String line=fin.nextLine().trim();
            switch(line){
                case "SAQuestion": addQuestion(new SAQuestion(fin));
                    break;
                case "MCSAQuestion": addQuestion(new MCSAQuestion(fin));
                    break;
                case "MCMAQuestion": addQuestion(new MCMAQuestion(fin));
                    break;
                case "NumQuestion": addQuestion(new NumQuestion(fin));
            }
            
        }

    }


    public String getString(){
        String examString="Authors: Marquise Howard, Arturo Urquiza, Allan Velednitskiy \n";
        examString+="NetIds: mhowar27 ,aurqui8, aveled2\n\n";
        examString+="                " + title;
        examString+="\n\n";
        for(int i=0;i < questions.size();i++){
            examString+=(i+1)+". ";
            examString+=questions.get(i).getString();
            examString+="\n\n";
        }
        return examString;
    }

    public void print(){
        System.out.println("Authors: Marquise Howard, Arturo Urquiza, Allan Velednitskiy");
        System.out.println("NetIds: mhowar27 ,aurqui8, aveled2\n");
        System.out.println("                " + title);
        System.out.println("\n");

        for (int i=0;i < questions.size();i++){
            System.out.print((i+1) + ". ");
            questions.get(i).print();
            System.out.println("\n");
        }
    }

    public void printStudentAnswers()
    {
        for(int i = 0; i < questions.size(); i++)
        {
            System.out.print("Question " + (i + 1) + ": ");
            questions.get(i).printStudentAnswers();
        }
    }

    public void addQuestion(Question q){
        questions.add(q);
    }

    public void reorderQuestions(){
        Collections.shuffle(questions);
    }

    public void reorderMCAnswers(int pos){
        if (pos < 0){
            for (Question ques : questions) {
                if ( ques instanceof MCQuestion) 
                    ((MCQuestion)ques).reorderAnswers();               
            }
        }
        else
            ((MCQuestion)questions.get(pos - 1) ).reorderAnswers();
    }

    public void getAnswerFromStudent(int pos){
        if(pos<0){
            for(Question q: questions)
                q.getAnswerFromStudent();
        }
        else
            questions.get(pos).getAnswerFromStudent();
    }

    public void getAnswersForExamTaker()
    {
        boolean looping = true;
        int current_question = 0;
        String client_input;

        int min_number_questions = 0;
        int max_number_questions = questions.size();

        String commands[] = 
        {
            "< : will allow you to go to the previous question",
            "> : will allow you to go to the next question",
            "answer : will allow you to answer that question",
            "done : will allow you to stop answering questions"
        };

        while(looping)
        {
            System.out.println("Enter one of the following commands");
            
            for(int i = 0; i < commands.length; i++)
            {
                System.out.println("    " + commands[i]);
            }

            System.out.println("\n");

            System.out.print("Question " + (current_question + 1) + ": ");
            questions.get(current_question).print();
            questions.get(current_question).printStudentAnswers();
            System.out.println("");

            System.out.print("Please enter a command for answering: ");
            client_input = ScannerFactory.getKeyboardScanner().nextLine().trim().toLowerCase();

            switch(client_input)
            {
                case "<":
                    --current_question;
                    if(current_question < 0)
                    {
                        current_question = max_number_questions - 1;
                    }

                    break;

                case ">":
                    current_question = ( (current_question + 1) % max_number_questions );
                    break;
                
                case "answer":
                    System.out.print("Enter answer for question " + (current_question + 1) + ":");
                    questions.get(current_question).getAnswerFromStudent();
                    break;
                
                case "done":
                    looping = false;
                    System.out.println("done answering questions!");
                    break;

                default:
                    System.out.println("Error: invalid command, please enter another one!\n\n");
            }
        }
    }

    public double getValue(){
        double sum = 0.0;

        for (int i=0;i < questions.size();i++)
            sum += questions.get(i).getValue();
        return sum;

    }

    public void removeQuestion(int index){
        if(index>questions.size()){
            System.out.println("Not a valid question");
            return;
        }
        questions.remove(index-1);
        System.out.println("Question successfully removed.... \n Result: ");
        print(); 

    }

    public void reportQuestionValues(){
    System.out.println("____________________________");
    for(int i=0; i<questions.size();i++)
        System.out.println("| Question #"+(i+1)+" | value: " + questions.get(i).getValue()+" |");
    System.out.println("____________________________");
    }


    public void save(PrintWriter pw){
        pw.write(title+"\n\n");
        for(Question ques: questions){
            ques.save(pw);
            pw.write("\n");
        }

        pw.close();

    }

    public void saveStudentAnswers(PrintWriter writer) {

        Date date = new Date();

        writer.println(date.toString());
        writer.println("");

        for(int i = 0; i < questions.size(); ++i)
        {
            questions.get(i).saveStudentAnswer(writer);
            writer.println("");
        }
    }

    public void restoreStudentAnswers(Scanner scanner) {
        for(int i = 0; i < questions.size(); ++i)
        {
            questions.get(i).restoreStudentAnswers(scanner);
        }
    }


    public String storeScoreToCSV() {

        String s = "";
        for(int i = 0; i < questions.size(); i++) {

            s += "Question #" + (i + 1) + " Score: " + questions.get(i).getValue() + ", ";
        }
        return s;
    }
}

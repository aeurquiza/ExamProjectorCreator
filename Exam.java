
import java.util.*;
import java.util.Scanner;
import java.io.PrintWriter;

public class Exam {
    private String title;
    private ArrayList<Question> questions;

    public Exam(String header){
        title = header;
        questions = new ArrayList<Question>();
    }

    public Exam(Scanner fin){
        title=fin.nextLine();
        fin.nextLine();
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
            }
            
        }

    }


    public void print(){
        System.out.println("Arturo Urquiza ");
        System.out.println("NETID: aurqui8\n");
        System.out.println("                " + title);
        System.out.println("\n");

        for (int i=0;i < questions.size();i++){
            System.out.print((i+1) + ". ");
            questions.get(i).print();
            System.out.println("\n");
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
        pw.write(title+"\n"+(new Date()).toString()+"\n\n");
        for(Question ques: questions){
            ques.save(pw);
            pw.write("\n");
        }
        

        pw.close();

    }

    public void saveStudentAnswers(PrintWriter pw){
            for(Question q: questions){
               if(q instanceof MCMAQuestion){
                    pw.write("MCMAAnswer\n");
                    q.saveStudentAnswer(pw);
               }
               if(q instanceof SAQuestion){
                    pw.write("SAAnswer\n");
                    q.saveStudentAnswer(pw);
               }  
               if(q instanceof MCSAQuestion){
                    pw.write("MCSAAnswer \n");
                    q.saveStudentAnswer(pw);
               } 
            }
    }

    public void restoreStudentAnswers(Scanner scanner)
    {
        scanner.nextLine();
        scanner.nextLine();
        scanner.nextLine();

        for(int i = 0; i < questions.size(); ++i)
        {
            questions.get(i).restoreStudentAnswers(scanner);
            if(scanner.hasNext())
            {
                scanner.nextLine();
            }
        }
    }

    /*
    public void restoreStudentAnswers(Scanner fin){
        fin.nextLine();
        fin.nextLine();
        for(Question ques : questions){
            ques.restoreStudentAnswers(fin);
            fin.nextLine();
        }
    }
    */


    public String storeScoreToCSV() {
        String s="";
        for(int i=0; i<questions.size();i++) {

            s += "Question #" + (i + 1) + " Score: " + questions.get(i).getValue() + ", ";
        }
        return s;
    }
}

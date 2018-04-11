import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class ExamBuilder{
	public static void main(String[] args){
		//File redirect = new File(args[0]);

		Exam test=null;
		Scanner kin=new Scanner(System.in); 

		System.out.println("Select one of the following: Load a saved Exam from a file - L , Add questions interactively - AQ , "+
		" Remove questions interactively - R , Reorder questions, and/or answers - RQ , Print the exam - P , Save the exam - S , or Quit - Q");

		query_user: while(true){

			System.out.println("What would you like to do: ");

			String response=kin.nextLine().trim();
			
			switch (response.toUpperCase()){
				case "L":	System.out.println("Enter the name of the file to load exam from: ");
							try{
						  	File examFile=new File(kin.nextLine().trim());
							test=new Exam(new Scanner(examFile));
							}
							catch(Exception e){}
							break;

				case "AQ":	
							if(test==null){
								System.out.println("We are creating a new exam... .... .... ...");
								System.out.println("What would you like the title of the exam to be");
								String title=kin.nextLine();
								test=new Exam(title);
							}
							addQuestion(test);
							break;

				case "R": System.out.println("Remove questions");
							if(test==null)
								System.out.println("You have not loaded an exam. ");
							else{
								int index=Integer.parseInt(kin.nextLine());
								test.removeQuestion(index);
							}
							break;

				case "RQ":	System.out.println("Would you like to reorder questions: q , MCanswers: a");
							String reorder=kin.nextLine();
							if(reorder.equalsIgnoreCase("q"))
								test.reorderQuestions();
							else
								if(reorder.equalsIgnoreCase("a")){
									System.out.println("Specify which question index you would like to reorder answers for. Enter -1 to reorder all: ");
									test.reorderMCAnswers(Integer.parseInt(kin.nextLine().trim()));
								}
							break;

				case "P": System.out.println("Printing the exam");
							test.print();
							System.out.println("\n\n");
							break;

				case "S": System.out.println("Enter file name to save exam to: ");
							try{
							File saveFile=new File(kin.nextLine().trim());
							PrintWriter pw=new PrintWriter(saveFile);
							test.save(pw);
							}
							catch(Exception e){}
							break;

				case "Q": System.out.println("Quitting");
							break query_user;
				default:
					System.out.println("Not a valid command. Try again. ");

			}

		}
	}

		public static void addQuestion(Exam exam){
			Scanner kin=new Scanner(System.in);
			System.out.println("Which kind of question would you like to add, MCQuestion: MCMAQ, MCSAQuestion: MCSAQ, SAQuestion: SAQ");
			String questionType=kin.nextLine();
			System.out.println("Enter the text of the question: ");
			String questionText=kin.nextLine();
			System.out.println("Enter the max value of the question: ");
			double questionVal=Double.parseDouble(kin.nextLine());
			switch(questionType){
				case "MCMAQ":
					System.out.println("Enter the base credit:" );
					double baseCredit=Double.parseDouble(kin.nextLine());
					System.out.println("How many answers does this question have: ");
					int ansCount=Integer.parseInt(kin.nextLine());
					MCMAQuestion mcmaq=new MCMAQuestion(questionText,questionVal, baseCredit);
					addAnswers(mcmaq,ansCount);
					exam.addQuestion(mcmaq);
					break;
				case "MCSAQ":
					System.out.println("How many answers does this question have: ");
					int ans=Integer.parseInt(kin.nextLine());
					MCSAQuestion mcsaq=new MCSAQuestion(questionText,questionVal);
					addAnswers(mcsaq,ans);
					exam.addQuestion(mcsaq);
					break;
				case "SAQ":
					System.out.println("What is the right answer: ");
					String rightAns=kin.nextLine();
					SAQuestion saq=new SAQuestion(questionText,questionVal);
					saq.setRightAnswer(new SAAnswer(rightAns));
					exam.addQuestion(saq);
					break;
				default:
				System.out.println("Not a valid question option. ");

			}
		}

		public static void addAnswers(MCQuestion mcq, int amount){
			Scanner kin=new Scanner(System.in);
			for(int i=0;i<amount;i++){
				System.out.println("Answer #"+(i+1)+": \n Enter the text of the answer: " );
				String ansText=kin.nextLine();
				System.out.println("Enter the credit if selected: ");
				double cis=Double.parseDouble(kin.nextLine());
				if(mcq instanceof MCMAQuestion)
				{
					//mcq.addAnswer(((MCMAQuestion)mcq).getNewAnswer(ansText,cis)); // really....
					MCMAQuestion quest = (MCMAQuestion)mcq;
					MCAnswer ans = (MCAnswer)quest.getNewAnswer(ansText, cis);
					quest.addAnswer(ans);
				}
				else
					mcq.addAnswer(((MCSAQuestion)mcq).getNewAnswer(ansText,cis));

			}
		}

	}

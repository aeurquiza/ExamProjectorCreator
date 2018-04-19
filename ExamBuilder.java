import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import javax.swing.JFrame;


public class ExamBuilder{
	public static JFrame frame;

	public static void main(String[] args){
		frame=new JFrame();
		Exam test=null;
		Scanner kin=new Scanner(System.in); 
		JOptionPane.showMessageDialog(frame,"Welcome to Exam Builder! Press ok to continue.");
		String[] options={"L","AQ","R","RQ","P","S","Q"};
		String optionString="Select one of the following:\n ------- Load a saved Exam from a file - L \n ------- Add questions interactively - AQ \n "+
		"------- Remove questions interactively - R \n ------- Reorder questions, and/or answers - RQ \n ------- Print the exam - P \n ------- Save the exam - S , or Quit - Q";
		
		query_user: while(true){

			int responseIndex=JOptionPane.showOptionDialog(frame,optionString, "Choose one:", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null,options,"Option 1");
			
			switch (options[responseIndex].toUpperCase()){
				case "L":	try{
						  	File examFile=new File(JOptionPane.showInputDialog(frame, "Enter the name of the file to load exam from: "));
							test=new Exam(new Scanner(examFile));
							}
							catch(Exception e){}
							break;

				case "AQ":	
							if(test==null){
								JOptionPane.showMessageDialog(frame,"We are creating a new exam... .... .... ...");
								String title=JOptionPane.showInputDialog(frame,"What would you like the title of the exam to be");
								test=new Exam(title);
							}
							addQuestion(test);
							break;

				case "R": 
							if(test==null)
								JOptionPane.showMessageDialog(frame,"You have not loaded an exam. ");
							else{
								int index=Integer.parseInt(JOptionPane.showInputDialog("Enter the index for the question you would like to remove: "));
								test.removeQuestion(index);
							}
							break;

				case "RQ":	String reorder=JOptionPane.showInputDialog(frame,"Would you like to reorder questions: q , MCanswers: a");
							if(reorder.equalsIgnoreCase("q"))
								test.reorderQuestions();
							else
								if(reorder.equalsIgnoreCase("a")){
									test.reorderMCAnswers(Integer.parseInt(JOptionPane.showInputDialog(frame,"Enter the value for which question to reorder answer for. Enter -1 to reorder all.")));
								}
							break;

				case "P":   JOptionPane.showMessageDialog(new JFrame(),test.getString());
							break;

				case "S": 
							try{
							File saveFile=new File(JOptionPane.showInputDialog(frame,"Please enter the file name to save exam to: "));
							PrintWriter pw=new PrintWriter(saveFile);
							test.save(pw);
							}
							catch(Exception e){}
							break;

				case "Q": JOptionPane.showMessageDialog(frame,"Quitting");
							break query_user;

			}

		}
	}

		public static void addQuestion(Exam exam){
			String questionType=JOptionPane.showInputDialog(frame,"Which kind of question would you like to add, \n 	MCQuestion: MCMAQ \n 	MCSAQuestion: MCSAQ \n 		SAQuestion: SAQ \n 		NumQuestion: NumQ");
			String questionText=JOptionPane.showInputDialog(frame,"Enter the text of the question: ");
			double questionVal=Double.parseDouble(JOptionPane.showInputDialog(frame,"Enter the max value for he question: "));
			switch(questionType){
				case "MCMAQ":
					double baseCredit=Double.parseDouble(JOptionPane.showInputDialog(frame,"Enter the base credit:"));
					int ansCount=Integer.parseInt(JOptionPane.showInputDialog(frame,"How many answers does this question have:"));
					MCMAQuestion mcmaq=new MCMAQuestion(questionText,questionVal, baseCredit);
					addAnswers(mcmaq,ansCount);
					exam.addQuestion(mcmaq);
					break;
				case "MCSAQ":
					int ans=Integer.parseInt(JOptionPane.showInputDialog(frame,"How many answers does this question have:"));
					MCSAQuestion mcsaq=new MCSAQuestion(questionText,questionVal);
					addAnswers(mcsaq,ans);
					exam.addQuestion(mcsaq);
					break;
				case "SAQ":
					String rightAns=JOptionPane.showInputDialog(frame,"What is the right answer: ");
					SAQuestion saq=new SAQuestion(questionText,questionVal);
					saq.setRightAnswer(new SAAnswer(rightAns));
					exam.addQuestion(saq);
					break;
				case "NumQ":
					Double rightAnsNum=Double.parseDouble(JOptionPane.showInputDialog(frame,"What is the right answer: "));
					Double tolerance=Double.parseDouble(JOptionPane.showInputDialog(frame,"What is the tolerance? "));
					NumQuestion naq=new NumQuestion(questionText,questionVal,tolerance);
					naq.setRightAnswer(new NumAnswer(rightAnsNum));
					exam.addQuestion(naq);
					break;
			}
		}

		public static void addAnswers(MCQuestion mcq, int amount){
			Scanner kin=new Scanner(System.in);
			for(int i=0;i<amount;i++){
				String ansText=JOptionPane.showInputDialog(frame,"Answer #"+(i+1)+": \n Enter the text of the answer: " );
				double cis=Double.parseDouble(JOptionPane.showInputDialog(frame,"Enter the credit if selected: "));
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

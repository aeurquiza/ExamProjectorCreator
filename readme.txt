Name: Allan Velednitskiy, Arturo Urquiza, Marquise Howard
Net ID: aveled2, aurqui8, mhowar27
Class: CS 342


/************************/
/    Program 5 Readme    /
/************************/


For this program to run all you must do is get all the .java files into one directory with the makefile.
Once they are all together you will access your terminal or bash interface and access the directory with those files.

One you are in the directory you need to type in 'make' and you will get a message that says: 


"javac Exam.java"
"javac MCSAQuestion.java"
"javac SAQuestion.java"
"javac the rest of the .java files"


This will populate the directory with the .class files.
To actually run the program, you will then need to type 'java ExamBuilder', 'java ExamTaker', or 'java ExamGrader' depending on functionality you need.

Exam Builder will give the user a menu of different choices.
It will allow you to click on buttons that add questions, remove questions, reorder questions, load exam, print exam, save the exam, or quit.

Exam Taker will ask the user for information such as their name and what exam they want to use.
Then the user can answer the questions which will be displayed using a GUI. You will be able to answer Multiple Choice, Multiple Answer, and Free Response Questions in one place. 

Once you are finished inputting the answers you have an option to save those answers.
It will then save the students answers to a file where it will have:

1st line: Name
2nd line: Exam file name
3rd line: Date and time

Followed by the answers.


Exam Grader will ask for the student answers text and make sure to compare them to the exam based on the second line where it has the exam file name. 
Then it will display the exam while it evaluates the answers and then display them on the next prompt.
Then there will be a prompt for the user to enter the name of a CSV file they want to store the data in.
The user can then either continue and grade another exam by pressing any key or quit by typing in 'q' or 'Q'.

Allan - ExamGrader
Arturo - ExamBuilder
Marquise - ExamTaker

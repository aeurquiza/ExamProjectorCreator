Name: Allan Velednitskiy, Arturo Urquiza, Marquise Howard
Net ID: aveled2, aurqui8, mhowar27
Class: CS 342


/************************/
/    Program 4 Readme    /
/************************/


For this program to run all you have to do is get all the .java files into one directory with the makefile.
Once they are all together you will access your terminal or bash interface and access the directory with those files.

One you are in the directory you need to type in 'make' and you will get a message that says: 


"javac Exam.java"
"javac MCSAQuestion.java"
"javac SAQuestion.java"
"javac the rest of the .java files"


This will populate the directory with the .class files.
To actually run the program, you will then need to type 'java ExamBuilder', 'java ExamTaker', or 'java ExamGrader' depending on functionality you need.

Exam Builder will give the user a menu of different choices.
It will allow you to add questions, remove questions, reorder questions, load exam, print exam, save the exam, or quit.

Exam Taker will ask the user for information such as their name and what exam they want to use.
Then the user can answer the questions while having the option to move forward a question or back a question depending on what they want to answer at the time.


It will then save the students answers to a file where it will have:

1st line: Name
2nd line: Exam file name
3rd line: Date and time

Followed by the answers.


Exam Grader will input both the exam and student answers and make sure to compare them based on the second line where it has the exam file name. 
Then it will evaluate the answers and display them on the screen while also saving the results in a CSV file.
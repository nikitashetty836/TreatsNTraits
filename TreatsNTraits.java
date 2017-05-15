
/* Neha Jagathesan & Nikita Shetty
5/5/17
TreatsNTraits.java
 game*/

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.awt.event.*;
//github.com - sharing code

/* Parts of game
 * 1. writing text files for the how to and background info - making back buttons to return to main panel after tutorial is done
 * 2. drawing the sample dog - outline to fill in varying traits
 * 3. talk to mentor about which breed of dog to focus on/ should we focus on one breed
 * 4. creating all the panels - deciding how many are needed
 * 5. researching different traits on dog
 * 6. formulate quiz questios to reveal traits on the dog*/
//import abstract windowing toolkit, including Graphics, Image, Color, Font



//import necessary libraries

//class header


//declare field variables
//main method
//instantiate class
//call run method
public class TreatsNTraits
{
	//constructor
	//instantiate field variables

	// run method
	//create frame
	//set visible/size/location
	//instantiate panel
	public static void main( String[] args )
	{

		TreatsNTraits tnt = new TreatsNTraits();
		tnt.runIt();
	}



	public TreatsNTraits()
	{
	}

	public void runIt()
	{
		JFrame frame = new JFrame("Treats N' Traits");
		frame.setSize(1200, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(10,10);
		frame.setResizable(false);

		TreatsNTraitsPanel tntPanel = new TreatsNTraitsPanel();
		frame.add(tntPanel);

		frame.setVisible(true);



	}
}
class TreatsNTraitsPanel extends JPanel  ////////////////////////////// class with ActionListener  /////////////////
// 1.
{ ///////OPEN CLASS PANEL !!!!!!!!!!!!!!!!!!!


	private CardLayout cards;

	private StartPanel startP;
	private TutorialPanel1 tutorP1;
	private ChooseDogPanel cdp;
	private InstructionsPanel ip;
	private UnlockTraitsPanel utp;
	private ShowDog sd;



	Font f, smallf, mediumf;

	private int treatCount, db;
	private String individual, individual2;
	Color mainBlue;

	public TreatsNTraitsPanel()////////////// write constructor
	{
		f = new Font ("URW Gothic L", Font.PLAIN, 25);
		mediumf = new Font ("URW Gothic L", Font.PLAIN, 20);
		smallf = new Font ("URW Gothic L", Font.PLAIN, 15);

		treatCount = 0;
		mainBlue = new Color(183,255,255);


		cards = new CardLayout();
		setLayout(cards);


		startP = new StartPanel(this);
		add(startP, "Start");
		startP.setBackground(mainBlue);

		tutorP1= new TutorialPanel1(this);
		add(tutorP1, "Complete Dominance");
		tutorP1.setBackground(mainBlue);

		cdp = new ChooseDogPanel(this);
		add(cdp, "Choose Your Dog");
		cdp.setBackground(mainBlue);

		ip = new InstructionsPanel(this);
		add(ip, "Instructions");
		ip.setBackground(mainBlue);

		utp = new UnlockTraitsPanel(this);
		add(utp, "Unlock Traits");
		utp.setBackground(mainBlue);

		sd = new ShowDog(this);
		add(sd, "Show Dog");
		sd.setBackground(mainBlue);


	}
	public CardLayout getCards()
	{
		return cards;
	}
	class StartPanel extends JPanel implements ActionListener //class start panel extends JPanel
	{
		private TreatsNTraitsPanel tntPanel2;
		private Image start;
		private JButton level1, level2, level3;		//delcaring buttons
		private boolean l1, l2, l3;
		private String startImageName;
		//declare field variables
		public StartPanel(TreatsNTraitsPanel tp)

		{	//constructor
			Font f = new Font ("URW Gothic L", Font.PLAIN, 35);
			tntPanel2 = tp;
			startImageName = "/Users/nikit/Desktop/TreatsNTraits/startpanel.png";		//NEHAS LAPTOP IMAGE
			//startImageName = "/Users/nehajagathesan/Documents/workspace/TreatsNTraits.java/src/startpanel2.png";
			l1 = false;
			l2 = false;
			l3 = false;

			setLayout(null);//set layout null

			level1 = new JButton(" ");//declare both buttons
			level2 = new JButton(" ");
			level3 = new JButton(" ");

			level1.addActionListener(this);
			level2.addActionListener(this);
			level3.addActionListener(this);

			level1.setSize(280,80);			//add size and location to all buttons
			level1.setLocation(100,400);
			level1.setOpaque(false);
            level1.setBorderPainted(false);
            level1.setContentAreaFilled(false);
            level1.setFont(f);

			level2.setSize(270,80);			//add size and location to all buttons
			level2.setLocation(475,400);
			level2.setOpaque(false);
            level2.setBorderPainted(false);
            level2.setContentAreaFilled(false);
			level2.setFont(f);

			level3.setSize(270,80);
			level3.setLocation(840,400);
			level3.setOpaque(false);
            level3.setBorderPainted(false);
            level3.setContentAreaFilled(false);
			level3.setFont(f);

			getMyImage();
			repaint();

			add(level1);
			add(level2);
			add(level3);
			//add both buttons to panel
		}
		public void getMyImage()
		{
			try
			{
				start = ImageIO.read(new File(startImageName));
			}
			catch(IOException e)
			{
				System.err.println("\n\n" + startImageName + "cannot be found");
				e.printStackTrace();
			}
		}
		public void paintComponent(Graphics g)
		{

				g.drawImage(start, 10, 10 , 1200, 600, this);


		}

		public void actionPerformed(ActionEvent e)
		{

			if(e.getSource() == level1)							//setting booleans to true if each button is selected
			{
				getCards();
				l1 = true;											//show the next card if the user clicks on a certain button
				tntPanel2.getCards().show(tntPanel2, "Complete Dominance");
			}
			else if(e.getSource() == level2)
			{
				l2 = true;
			}
			else if(e.getSource() == level3)
			{
				l3 = true;
			}
		}
	}

	class TutorialPanel1 extends JPanel implements AdjustmentListener, ActionListener	//class tutorial panel extends Jpane
	{
		private TreatsNTraitsPanel tntPanel2;
		private Scanner input;											//declare field variables
		private TextArea backgroundInfo, howtoPlay;						//declaring the text area for the tutorial
		//	private JTextArea textArea1;									//declaring the text area for the tutorial
		private Font f;
		private Color textAreaShade;
		private JButton backButton, nextButton;
		private String line, fullTutorial, stuff, inFileName;

		public TutorialPanel1(TreatsNTraitsPanel tp)											//constructor
		{
			tntPanel2 = tp;

			setLayout(null);
			setVisible(true);

			setBackground(Color.PINK);
			stuff= "";

			Font f = new Font ("URW Gothic L", Font.PLAIN, 20);		//creating a new font for the full game
			Color textAreaShade = new Color(153,153,255);		//creating a color to make the textArea

			backgroundInfo= new TextArea(stuff,10,100);
			add(backgroundInfo);

			backgroundInfo.setLocation(25,20);					//setting variables for text area
			backgroundInfo.setSize(1150,250);
			backgroundInfo.setEditable(false);
			backgroundInfo.setFont(f);
			backgroundInfo.setBackground(textAreaShade);

			howtoPlay= new TextArea(stuff,10,100);
			add(howtoPlay);

			howtoPlay.setLocation(25,280);
			howtoPlay.setSize(1150,250);
			howtoPlay.setEditable(false);
			howtoPlay.setFont(f);
			howtoPlay.setBackground(textAreaShade);

			backButton= new JButton("Back");					//setting variables to the buttons
			backButton.addActionListener(this);
			add(backButton);

			backButton.setLocation(1000,530);
			backButton.setSize(100,50);
			backButton.setFont(f);
			backButton.setText("Back");
			backButton.setBackground(textAreaShade);

			nextButton= new JButton("Next");
			nextButton.addActionListener(this);
			add(nextButton);

			nextButton.setLocation(1100,530);
			nextButton.setSize(100,50);
			nextButton.setFont(f);
			nextButton.setText("Next");
			nextButton.setBackground(textAreaShade);

			inFileName = "Tutorial.txt";									//add both textAreas to panel

			line = "";
			fullTutorial = "";

			importTextFiles();
			getWords();

		}
		public void importTextFiles()										//method for try catch blocks to find the tutorial.txt text file
		{
			File inFile = new File(inFileName);
			try
			{
				input = new Scanner(inFile);

			}
			catch (FileNotFoundException e)
			{
				System.out.println("Error. Cannot Find/Open File " + inFileName );
				System.exit(1);

			}
		}
		public void getWords()										//method from reading input from the tutorial.txt file so we c an print the stuff from the tutorial.txt file on to the JTextArea
		{
			while(input.hasNext())
			{
				line = input.nextLine();

				fullTutorial = fullTutorial + "\n" + line;			//creating a string to add to the textArea
			}
			backgroundInfo.setText(fullTutorial);					//setting the text to what is in the tutorial.txt file
		}
		public void adjustmentValueChanged(AdjustmentEvent e)
		{
		}
		public void actionPerformed(ActionEvent e)
		{
			String command = e.getActionCommand();					//setting local vairable to command recieved from button
			if(command.equals("Back"))							//setting booleans to true if each button is selected
			{

				getCards();										//show the next card if the user clicks on a certain button
				tntPanel2.getCards().show(tntPanel2, "Start");
			}
			else if(command.equals("Next"))
			{
				getCards();										//calling get cards in order to have access to all cards in panels
				tntPanel2.getCards().show(tntPanel2, "Choose Your Dog");	//show the next card if the user clicks on a certain button
			}

		}
	}
	class ChooseDogPanel extends JPanel implements ActionListener
		{
		private TreatsNTraitsPanel tntPanel2;
		private JButton dogButt1, dogButt2, dogButt3;
		private Image mysteryDog;
		private Font f, big;
		//private String mysteryDogName;


		public ChooseDogPanel(TreatsNTraitsPanel tp)
		{
			setBackground(mainBlue);
			repaint();
			tntPanel2 = tp;

			setLayout(null);//set layout null


			f = new Font ("URW Gothic L", Font.PLAIN, 35);
			big = new Font ("URW Gothic L", Font.PLAIN, 70);

			//mysteryDogName = "mysterydog.png";



			dogButt1 = new JButton("x");//declare both buttons
			dogButt2 = new JButton("y");
			dogButt3 = new JButton("z");

			dogButt1.addActionListener(this);
			dogButt2.addActionListener(this);
			dogButt3.addActionListener(this);

			dogButt1.setSize(200,200);			//add size and location to all buttons
			dogButt1.setLocation(150, 250);
			dogButt1.setOpaque(false);
            dogButt1.setBorderPainted(false);
            dogButt1.setContentAreaFilled(false);
            dogButt1.setFont(f);

			dogButt2.setSize(200,200);
			dogButt2.setLocation(490, 250);
			dogButt2.setOpaque(false);
            dogButt2.setBorderPainted(false);
            dogButt2.setContentAreaFilled(false);
			dogButt2.setFont(f);

			dogButt3.setSize(200,200);
			dogButt3.setLocation(840, 250);
			dogButt3.setOpaque(false);
            dogButt3.setBorderPainted(false);
            dogButt3.setContentAreaFilled(false);
			dogButt3.setFont(f);

			repaint();

			add(dogButt1);
			add(dogButt2);
			add(dogButt3);

			//mysteryDog = new ImageIcon("mysterydog.png").getImage();
			//mysteryDog = new ImageIcon("/Users/nehajagathesan/Documents/workspace/TreatsNTraits.java/src/mysterydog.png").getImage();		//LINE OF CODE FOR NEHAS LAPTOP
			mysteryDog = new ImageIcon("/Users/nikit/Desktop/TreatsNTraits/mysterydog.png").getImage();		//LINE OF CODE FOR NEHAS LAPTOP
		}

		public void paintComponent(Graphics g)
		{
				//setBackground(mainBlue);
				g.drawImage(mysteryDog, 150, 250 , 200, 200, this);
				g.drawImage(mysteryDog, 490, 250 , 200, 200, this);
				g.drawImage(mysteryDog, 840, 250 , 200, 200, this);


				g.setFont(big);
				g.drawString("Choose a dog to adopt!", 200,150);



		}

		public void actionPerformed(ActionEvent e)
		{

			String command = e.getActionCommand();							//setting local vairable to command recieved from button
			if(command.equals("x"))											//setting booleans to true if each button is selected
			{
				getCards();
				db = 0;													//show the next card if the user clicks on a certain button
				tntPanel2.getCards().show(tntPanel2, "Instructions");
			}
			else if(command.equals("y"))											//setting booleans to true if each button is selected
			{
				getCards();
				db = 1;													//show the next card if the user clicks on a certain button
				tntPanel2.getCards().show(tntPanel2, "Instructions");
			}
			if(command.equals("z"))											//setting booleans to true if each button is selected
			{
				getCards();
				db = 2;													//show the next card if the user clicks on a certain button
				tntPanel2.getCards().show(tntPanel2, "Instructions");
			}


		}


	}
	class InstructionsPanel extends JPanel implements ActionListener
	{
		private TreatsNTraitsPanel tntPanel2;
		private JButton go;
		private JTextArea instructions;
		private Font bigF;
		public InstructionsPanel(TreatsNTraitsPanel tp)
		{
			tntPanel2 = tp;
			bigF = new Font ("URW Gothic L", Font.PLAIN, 50);
			setLayout(null);
			go = new JButton("GO");
			go.setSize(400,150);
			go.setLocation(350,400);
			go.setFont(bigF);
			go.setText("Go!");
			go.addActionListener(this);

			instructions = new JTextArea();
			instructions.setLineWrap(true);
			instructions.setLocation(50, 70);
			instructions.setSize(1100, 300);
			instructions.setFont(bigF);
			instructions.setBackground(mainBlue);
			instructions.setText("Answer questions to reveal traits of your dog to help find its parents! 1 right question = 1 trait revealed + 3 treats! 1 wrong question = -1 treat. The more treats you earn, the happier your dog! Click GO to start.");

			add(go);
			add(instructions);

		}

		public void actionPerformed(ActionEvent e)
		{
			String command = e.getActionCommand();
			if (command.equals("Go!"))
			{
				tntPanel2.getCards().show(tntPanel2, "Unlock Traits");
			}


		}

	}
		class UnlockTraitsPanel extends JPanel implements ActionListener	//this class contains two subclasses which are 2 panels on this panel
	{																		//users will answer questions on the left side of the panel for unlocked traits to be revealed on the right side of the panel
		private TreatsNTraitsPanel tntPanel2;
		private ShowTraits st;
		private Quiz q;
		boolean correct, wrong;
		int correctCount;
		public UnlockTraitsPanel(TreatsNTraitsPanel tp)						//panel on which traits will be written out
		{
			tntPanel2 = tp;
			setLayout(new GridLayout(1,2));

			correctCount = 0;


			st = new ShowTraits();
			q = new Quiz();

			correct = false;
			wrong = false;

			st.setBackground(Color.MAGENTA);


			add(q, "Quiz");
			add(st, "Show Traits");

		}
		public void actionPerformed(ActionEvent e)
		{

		}
		public void getter(){
			if (correctCount == 1)
			{
			st.getText();
			st.getWords();
			st.getTraits();
			st.setTraits();
			}
			else
			{
				st.setTraits();
			}

		}
		class Quiz extends JPanel implements ActionListener

		{

			private String inFileName, line, fullQuestion, fullTextFile,qNumber, choiceA, choiceB, choiceC, choiceD, fullQuestion2;
			private Scanner input;
			private String[]QuizQs;
			private int randomQuestion,x1,x2, x3;
			private boolean ca1, ca2, ca3, ca4, sp, a1select, a2select, a3select, a4select, correct, wrong;
			private JButton submit, next2;
			private ButtonGroup answers;
			private JRadioButton a1, a2, a3, a4;
			private JTextArea question, treatCountDisplay;
			private Font f, smallf, mediumf;
			private UnlockTraitsPanel utp;


			public Quiz()
			{

				setLayout(null);
				setBackground(mainBlue);

				question = new JTextArea();
				question.setLineWrap(true);
				question.setSize(530, 70);
				question.setLocation(30, 50);
				question.setFont(smallf);
				question.setBackground(mainBlue);

				add(question);

				treatCountDisplay = new JTextArea();
				treatCountDisplay.setLineWrap(true);
				treatCountDisplay.setSize(100, 100);
				treatCountDisplay.setLocation(500, 0);
				treatCountDisplay.setFont(smallf);
				treatCountDisplay.setOpaque(false);

				//treatCountDisplay.setBackground(mainBlue);

				add(treatCountDisplay);


				submit = new JButton("Submit");
				submit.setFont(mediumf);
				submit.setText("Submit");
				submit.setSize(100, 50);
				submit.setLocation(300,500);
				submit.addActionListener(this);

				add(submit);

				next2 = new JButton("Next");
				next2.setFont(mediumf);
				next2.setText("Next");
				next2.setSize(100, 50);
				next2.setLocation(400,500);
				next2.addActionListener(this);

				add(next2);

				answers = new ButtonGroup();			//adding a buttongroup
				a1 = new JRadioButton();
				a2 = new JRadioButton();
				a3 = new JRadioButton();
				a4 = new JRadioButton();


				answers.add(a1);						//adding buttons to a buttongroup
				answers.add(a2);
				answers.add(a3);
				answers.add(a4);

				a1.addActionListener(this);				//setting all attributes to the buttons
				a2.addActionListener(this);
				a3.addActionListener(this);
				a4.addActionListener(this);

				a1.setSize(600,40);
				a2.setSize(600,40);
				a3.setSize(600,40);
				a4.setSize(600,40);

				a1.setLocation(30, 130);
				a2.setLocation(30, 230);
				a3.setLocation(30, 330);
				a4.setLocation(30, 430);

				a1.setFont(smallf);
				a2.setFont(smallf);
				a3.setFont(smallf);
				a4.setFont(smallf);

				a1.setBackground(mainBlue);
				a2.setBackground(mainBlue);
				a3.setBackground(mainBlue);
				a4.setBackground(mainBlue);


				add(a1);
				add(a2);
				add(a3);
				add(a4);

				ca1 = false;								//initializing all booleans as false/ they are eventually true when the buttons are clicked
				ca2 = false;
				ca3 = false;
				ca4 = false;
				sp = false;
				a1select = false;
				a2select = false;
				a3select = false;
				a4select = false;

				correct = false;
				wrong = false;

				inFileName = "QuizQuestions.txt";
				line = "";
				fullQuestion = "";
				QuizQs = new String[30];


				randomQuestion = (int)((Math.random()*29)+1);		//randomizing an integer when the user clicks submit in the question
				getTextFile();
				getText();
				setVariables();
				setQuestion();
				displayRadioButtons();

			}
			public void getTextFile() 								//method is just for making sure that the textFile for the quiz questions can be found
			{
				File inFile = new File(inFileName);
				try
				{
					input = new Scanner(inFile);

				}
				catch (FileNotFoundException e)
				{
					System.out.println("Error. Cannot Find/Open File " + inFileName );
					System.exit(1);

				}
			}


			public void getText()									//method from reading input from the tutorial.txt file so we c an print the stuff from the tutorial.txt file on to the JTextArea
			{

				while(input.hasNext())
				{
					line = input.nextLine();

					fullTextFile = fullTextFile + "\n" + line;		//creating a string to add to the textArea
				}

				for(x1=0;x1<29;x1++)
				{

					fullQuestion = fullTextFile.substring(0, fullTextFile.indexOf("---")+3)	;		//this separates the big string that is the textFile of quiz questions into individual questions
					fullTextFile = fullTextFile.substring(fullQuestion.length()+4);					//resets the big string of the text file as the text file minus the string that was just read
					QuizQs[x1] = fullQuestion;														//adds question to a value on the array
				}
				//System.out.print(fullQuestion);					//setting the text to what is in the tutorial.txt file
			}
			public void setVariables()								//this method is only fully excecuted if the random number generated is the loop integer value
			{														/*when the random integer value has arrived then it separates the text file into choices ABCD to
																		to set the text to the radiobuttons*/


				for (x3 = 0; x3<29 ; x3++)
				{
					fullQuestion2 = QuizQs[x3];
					if(fullQuestion2.equals(""))
					{
						fullQuestion2 = QuizQs[x3 +1];
					}
					if (x3 == randomQuestion)
					{
						qNumber = fullQuestion2.substring((fullQuestion2.indexOf(">") +1), (fullQuestion2.indexOf("<")));
						fullQuestion2 = fullQuestion2.substring(qNumber.length() + 3);


						choiceA = fullQuestion2.substring(fullQuestion2.indexOf("a)")+2, fullQuestion2.indexOf("b)"));
						fullQuestion2 = fullQuestion2.substring(choiceA.length()+2);


						choiceB = fullQuestion2.substring(fullQuestion2.indexOf("b)")+2, fullQuestion2.indexOf("c)"));
						fullQuestion2 = fullQuestion2.substring(choiceB.length()+2);//choiceB = fullQuestion.substring(beginIndex, endIndex)

						choiceC = fullQuestion2.substring(fullQuestion2.indexOf("c)")+2, fullQuestion2.indexOf("d)"));
						fullQuestion2 = fullQuestion2.substring(choiceC.length() +2);//choiceB = fullQuestion2.substring(beginIndex, endIndex)


						choiceD = fullQuestion2.substring(fullQuestion2.indexOf("d)")+2, fullQuestion2.indexOf("---"));
						fullQuestion2 = fullQuestion2.substring(choiceD.length()+4);//choiceB = fullQuestion.substring(beginIndex, endIndex)

						QuizQs[x3] = "";
						if (choiceA.indexOf("!") == 0) 	//this if else block is to determine which one of the answers is correct, in the text file the correct answer has an ! in the front
						{								// boolean for each answer choice is set as true w respective correct answers
						//System.out.print(choiceA);
						ca1 = true;
						choiceA = choiceA.substring(1);
						}

						else if (choiceB.indexOf("!") == 0)
						{
						//System.out.print(choiceB);
						ca2 = true;
						choiceB = choiceB.substring(1);
						}

						else if (choiceC.indexOf("!") == 0)
						{
						//System.out.print(choiceC);
						ca3 = true;
						choiceC = choiceC.substring(1);
						}

						else if (choiceD.indexOf("!") == 0)
						{
						//System.out.print(choiceD);
						ca4 = true;
						choiceD = choiceD.substring(1);

						}

					}

				}
			}
			public void setQuestion()
			{
				question.setText(qNumber);

			}
			public void displayRadioButtons()
			{
				a1.setFont(smallf);
				a2.setFont(smallf);
				a3.setFont(smallf);
				a4.setFont(smallf);

				a1.setText(choiceA);
				a2.setText(choiceB);
				a3.setText(choiceC);
				a4.setText(choiceD);


			}
			public void actionPerformed(ActionEvent e){

				if(a1.isSelected())					//if else block for if a radiobutton is selected, respecitve booleans are set as true and others are set as false
				{
					a1select = true;
					a2select = false;
					a3select = false;
					a4select = false;
				}


				else if(a2.isSelected())
				{
					a2select = true;
					a1select = false;
					a3select = false;
					a4select = false;
				}

				else if(a3.isSelected())
				{
					a3select = true;
					a1select = false;
					a2select = false;
					a4select = false;

				}
				else if(a4.isSelected())
				{
					a4select = true;
					a1select = false;
					a2select = false;
					a3select = false;

				}
				String command = e.getActionCommand();
				if(command.equals("Submit"))			//tells the computer to go to changeQuestions when submit is pressed
				{
					sp = true;
					changeQuestions();

				}
				else if(command.equals("Next"))//&& correctCount >=6 )
				{

					System.out.print("/n + haha" + individual2);
					tntPanel2.getCards().show(tntPanel2, "Show Dog");

				}




			}
			public void changeQuestions()					//method that changes the text of the radiobuttons if the user answers correctly
			{

				//System.out.println();
				if(sp == true &&  a1select == true && ca1 == true)
				{

					sp = false;
					a1select = false;
					ca1 = false;
					correct = true;
				}
				else if(sp == true &&  a2select == true && ca2 == true)
				{

					correct = true;
					sp = false;
					a2select = false;
					ca2 = false;
				}
				else if(sp == true &&  a3select == true && ca3 == true)
				{

					correct = true;
					sp = false;
					a3select = false;
					ca3 = false;
				}
				else if(sp == true &&  a4select == true && ca4 == true)
				{

					correct = true;
					sp = false;
					a4select = false;
					ca4 = false;
				}
				else
				{
					correct = false;
					wrong = true;
				}

				if (correct == true)
				{
					randomQuestion = (int)((Math.random()*29)+1);	//randomizes integer for the next question

					getTextFile();
					setVariables();
					setQuestion();
					displayRadioButtons();
					correctCount++;
					treatCount = treatCount + 3;
					treatCountDisplay.setText("Treat Count \n" + treatCount);
					getter();

					//System.out.println("correct");
				}
				else if (wrong == true)
				{
					treatCount = treatCount - 1;
					treatCountDisplay.setText("Treat Count \n" + treatCount);
					//System.out.println("wrong");

				}



			}



		}
		class ShowTraits extends JPanel	implements ActionListener									//panel that reveals what the traits are using draw strings
		{
			JTextArea trait1, trait2, trait3, trait4, trait5, trait6;
			private Scanner input;
			private String traitsFile, line, fullTraits, hairColor, ears, eyes, hairLength, nose, legs;
			private String[]dogs;

			//
			public ShowTraits(){

				setLayout(null);
				fullTraits = "";
				trait1 = new JTextArea();
				trait1.setLineWrap(true);
				trait1.setSize(600, 100);
				trait1.setLocation(0, 0);
				trait1.setFont(smallf);
				trait1.setBackground(mainBlue);
				add(trait1);

				trait2 = new JTextArea();
				trait2.setLineWrap(true);
				trait2.setSize(600, 100);
				trait2.setLocation(0, 100);
				trait2.setFont(smallf);
				trait2.setBackground(mainBlue);
				add(trait2);

				trait3 = new JTextArea();
				trait3.setLineWrap(true);
				trait3.setSize(600, 100);
				trait3.setLocation(0, 200);
				trait3.setFont(smallf);
				trait3.setBackground(mainBlue);
				add(trait3);

				trait4 = new JTextArea();
				trait4.setLineWrap(true);
				trait4.setSize(600, 100);
				trait4.setLocation(0, 300);
				trait4.setFont(smallf);
				trait4.setBackground(mainBlue);
				add(trait4);

				trait5 = new JTextArea();
				trait5.setLineWrap(true);
				trait5.setSize(600, 100);
				trait5.setLocation(0, 400);
				trait5.setFont(smallf);
				trait5.setBackground(mainBlue);
				add(trait5);

				trait6 = new JTextArea();
				trait6.setLineWrap(true);
				trait6.setSize(600, 100);
				trait6.setLocation(0, 500);
				trait6.setFont(smallf);
				trait6.setBackground(mainBlue);
				add(trait6);

				traitsFile = "PuppyTraitsOnly.txt";

				dogs = new String[3];

				getText();
				getWords();

			}
			public void getText()
			{
				File inFile = new File(traitsFile);
				try
				{
					input = new Scanner(inFile);

				}
				catch (FileNotFoundException e)
				{
					System.out.println("Error. Cannot Find/Open File " + traitsFile );
					System.exit(1);

				}
			}
			public void getWords()
			{
				while(input.hasNext())
				{
					line = input.nextLine();

					fullTraits = fullTraits + line + "\n";			//creating a string to add to the textArea
				}

			}
			public void getTraits() 	//method is used to take the traits from the text file based off the bog the user chose in the use dog panel
			{
				//System.out.print(fullTraits);
				for(int x=0;x<=2;x++)	//separates the fullTraits String into seprate sections of traits for each dog
				{
					if (x==0 || x==1)
					{
					individual = fullTraits.substring(0, fullTraits.indexOf("---") + 3);
					dogs[x] = individual;
					fullTraits = fullTraits.substring(individual.length());
					}

					else if(x == 2)
					{
						//System.out.println(fullTraits);
						individual = fullTraits.substring(0, fullTraits.indexOf("---")+3);
						dogs[x] = individual;
					}

				}
				for(int x2=0;x2<=2;x2++)
				{
					if(x2 == db)
					{
						//ears, eyes, hairLength, nose, legs;

						individual2 = dogs[x2];
						System.out.println("\n xyz \n" + individual2);
						hairColor = (individual2.substring(individual2.indexOf("1)") +2, individual2.indexOf("2)")));
						ears = (individual2.substring(individual2.indexOf("2)") +2, individual2.indexOf("3)")));
						eyes = (individual2.substring(individual2.indexOf("3)") +2, individual2.indexOf("4)")));
						hairLength = (individual2.substring(individual2.indexOf("4)") +2, individual2.indexOf("5)")));
						nose = (individual2.substring(individual2.indexOf("5)") +2, individual2.indexOf("6)")));
						legs = (individual2.substring(individual2.indexOf("6)") +2, individual2.indexOf("---")));
					}
				}
			}
			public void setTraits()		//this method sets the text to each JTextArea depending on how many questions are answered correctly
			{			//one right answer, one more text area writes the designated trait

				if(correctCount == 1)
				{
				trait1.setText(hairColor);
				}
				else if(correctCount == 2)
				{
				trait2.setText(ears);
				}
				else if(correctCount == 3)
				{
				trait3.setText(eyes);
				}
				else if(correctCount == 4)
				{
				trait4.setText(hairLength);
				}
				else if(correctCount == 5)
				{
				trait5.setText(nose);
				}
				else if(correctCount == 6)
				{
				trait6.setText(legs);
				}
				System.out.println("\n nlj \n" + individual2);
			}

			public void actionPerformed(ActionEvent e){}			//
		}

	}
	class ShowDog extends JPanel implements ActionListener
	{
		private TreatsNTraitsPanel tntPanel2;
		private ShowOffspring so;
		private ParentPool pp;
		private Image dog1, dog2, dog3, mysteryParent;
		private JButton back;
		public ShowDog(TreatsNTraitsPanel tp)
		{
			setLayout(new GridLayout(1,2));
			tntPanel2 = tp;

			so = new ShowOffspring();
			pp = new ParentPool();
			add(so);
			add(pp);

			Font f = new Font ("URW Gothic L", Font.PLAIN, 35);
			dog1 = new ImageIcon("/Users/nikit/Desktop/TreatsNTraits/dog1.png").getImage();			//IMAGES ON NIKITAS LAPTOP
			dog2 = new ImageIcon("/Users/nikit/Desktop/TreatsNTraits/dog2.png").getImage();
			dog3 = new ImageIcon("/Users/nikit/Desktop/TreatsNTraits/dog3.png").getImage();
			mysteryParent = new ImageIcon("/Users/nikit/Desktop/TreatsNTraits/mysteryparent.png").getImage();


			//dog1 = new ImageIcon("/Users/nehajagathesan/Documents/workspace/TreatsNTraits.java/dog1.png").getImage();			//IMAGES ON NIKITAS LAPTOP
			//dog2 = new ImageIcon("/Users/nehajagathesan/Documents/workspace/TreatsNTraits.java/dog2.png").getImage();
			//dog3 = new ImageIcon("/Users/nehajagathesan/Documents/workspace/TreatsNTraits.java/dog3.png").getImage();

			back = new JButton("Back");					//setting variables to the buttons
			back.addActionListener(this);


			back.setLocation(1000,530);
			back.setSize(100,50);
			back.setFont(f);
			back.setText("Back");


		}


		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == back)							//setting booleans to true if each button is selected
			{
				getCards();										//show the next card if the user clicks on a certain button
				tntPanel2.getCards().show(tntPanel2, "Complete Dominance");
			}


		}
		class ShowOffspring extends JPanel
		{
			private JTextArea parentTraits, childTraits;
			public ShowOffspring()
			{
				setLayout(null);
				setBackground(Color.MAGENTA);

				parentTraits = new JTextArea();
				parentTraits.setLineWrap(true);
				parentTraits.setSize(250, 300);
				parentTraits.setLocation(0, 0);
				parentTraits.setFont(f);
				parentTraits.setBackground(Color.GREEN);
				add(parentTraits);

				childTraits = new JTextArea();
				childTraits.setLineWrap(true);
				childTraits.setSize(250, 300);
				childTraits.setLocation(350, 0);
				childTraits.setFont(smallf);
				childTraits.setBackground(Color.GREEN);
				add(childTraits);

				ShowTraits();

			}
			public void ShowTraits()
			{

				childTraits.setText(individual2);

			}
			public void paintComponent(Graphics g)
			{
				if (db ==0)
				g.drawImage(dog1, 100, 10 , 300, 400, this);
				else if (db ==1)
				g.drawImage(dog2, 100, 200 , 400, 400, this);
				else if(db ==2)
				g.drawImage(dog3, 100, 300 , 300, 200, this);
			}
		}
		class ParentPool extends JPanel
		{
				public ParentPool()
				{

				}
				public void paintComponent(Graphics g)
				{
					g.drawImage(mysteryParent, 50, 150 , 100, 84, this);
					g.drawImage(mysteryParent, 250, 150 , 100, 84, this);
					g.drawImage(mysteryParent, 450, 150 , 100, 84, this);
					g.drawImage(mysteryParent, 50, 300 , 100, 84, this);
					g.drawImage(mysteryParent, 250, 300 , 100, 84, this);
					g.drawImage(mysteryParent, 450, 300 , 100, 84, this);

				}

		}
	}

	}



//public class quizPanel
//declare field variables
//constructor
//set layout to grid layout
//initialize field variables
//add panels quiz and draw dog panel

//class quizQuestions
//declare field variables
//set layout to card layout
//declare radio buttons
//add radio buttons to panel
//displayQuestions method
//try catch block for file
//Math.random to randomize the questions
//assign a number to each question
//if/else to read from text file according to number from math.rand
//if/else block when the user answers a question
//add three treats if correct answer is selected
//calls repaint() if incorrect
//if/else blocks for what to do when user clicks next

//class drawDogPanel
//declare field variables
//constructor
//initialize variables
//set layout to null layout
//paintComponent()
//set each question to certain feature of dog
//if/else statement
//draw features as user answers correctly

//public class findParent
//declare field variables
//set layout to grid layout(1,2)
//add 2 panels to this panel

//class drawParents
//declare text area for instructions
//add to panel
//paintComponent()
//draw 5 dogs with different features using shapes
//if/else to see if a dog is clicked using mouseClicked
//and getX()/getY()
//when a dog is pressed, text box with info will pop up
//decideWhich()
//if/else for if the user wins

//public class finalPanel
//declare field variables
//constructor
//set layout to null layout
//try catch block for image
//paint component()
//draw image of dog to screen
//draw string for treat count
//button for next level

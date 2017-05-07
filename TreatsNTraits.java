
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
	private UnlockTraitsPanel utp;
	
	Color mainBlue;

	public TreatsNTraitsPanel()////////////// write constructor
	{
		mainBlue = new Color(188,255,255);


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
		
		utp = new UnlockTraitsPanel(this);
		add(utp, "Unlock Traits");
		utp.setBackground(mainBlue);


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
			startImageName = "/Users/nehajagathesan/Documents/workspace/TreatsNTraits.java/src/startpanel2.png";		//NEHAS LAPTOP IMAGE
			//startImageName = "/Users/nehajagathesan/Documents/workspace/TreatsNTraits.java/startPanel2";
			l1 = false;
			l2 = false;
			l3 = false;

			setLayout(null);//set layout null

			level1 = new JButton("Level 1");//declare both buttons
			level2 = new JButton("Level 2");
			level3 = new JButton("Level 3");

			level1.addActionListener(this);
			level2.addActionListener(this);
			level3.addActionListener(this);

			level1.setSize(500,80);			//add size and location to all buttons
			level1.setLocation(350,300);
			level1.setOpaque(false);
            level1.setBorderPainted(false);
            level1.setContentAreaFilled(false);
            level1.setFont(f);

			level2.setSize(500,80);
			level2.setLocation(350,390);
			level2.setOpaque(false);
            level2.setBorderPainted(false);
            level2.setContentAreaFilled(false);
			level2.setFont(f);

			level3.setSize(500,80);
			level3.setLocation(350,480);
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
			String command = e.getActionCommand();					//setting local vairable to command recieved from button
			if(command.equals("Level 1"))							//setting booleans to true if each button is selected
			{
				getCards();
				l1 = true;											//show the next card if the user clicks on a certain button
				tntPanel2.getCards().show(tntPanel2, "Complete Dominance");
			}
			else if(command.equals("Level 2"))
			{
				l2 = true;
			}
			else if(command.equals("Level 3"))
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
		private boolean db1, db2, db3;
		private Image mysteryDog;
		private Font f, big;
		//private String mysteryDogName;

		
		public ChooseDogPanel(TreatsNTraitsPanel tp)
		{
			setBackground(mainBlue);
			tntPanel2 = tp;
			
			setLayout(null);//set layout null
		
			f = new Font ("URW Gothic L", Font.PLAIN, 35);	
			big = new Font ("URW Gothic L", Font.PLAIN, 70);

			//mysteryDogName = "mysterydog.png";
			
			db1 = false;
			db2 = false;
			db3 = false;

			dogButt1 = new JButton(" ");//declare both buttons
			dogButt2 = new JButton(" ");
			dogButt3 = new JButton(" ");
			
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
			mysteryDog = new ImageIcon("/Users/nehajagathesan/Documents/workspace/TreatsNTraits.java/src/mysterydog.png").getImage();		//LINE OF CODE FOR NEHAS LAPTOP
		}

		public void paintComponent(Graphics g)
		{
				setBackground(mainBlue);
				g.drawImage(mysteryDog, 150, 250 , 200, 200, this);
				g.drawImage(mysteryDog, 490, 250 , 200, 200, this);
				g.drawImage(mysteryDog, 840, 250 , 200, 200, this);
				
					
				g.setFont(big);
				g.drawString("Choose a dog to adopt!", 200,150);
				
				
					
		}

		public void actionPerformed(ActionEvent e) 
		{
		
			String command = e.getActionCommand();					//setting local vairable to command recieved from button
			if(command.equals(" "))							//setting booleans to true if each button is selected
			{
				getCards();
				db1 = true;											//show the next card if the user clicks on a certain button
				tntPanel2.getCards().show(tntPanel2, "Unlock Traits");
			}
			
		}

	}
		class UnlockTraitsPanel extends JPanel implements ActionListener		//this class contains two subclasses which are 2 panels on this panel 
	{																		//users will answer questions on the left side of the panel for unlocked traits to be revealed on the right side of the panel 
		private TreatsNTraitsPanel tntPanel2;
		private ShowTraits st;
		private Quiz q;
		boolean correct, wrong;
		public UnlockTraitsPanel(TreatsNTraitsPanel tp)						//panel on whixh traits will be written out
		{
			tntPanel2 = tp;
			setLayout(new GridLayout(1,2));
			

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
		class Quiz extends JPanel implements ActionListener

		{

			private String inFileName, line, fullQuestion, fullTextFile, question,qNumber, choiceA, choiceB, choiceC, choiceD, fullQuestion2;
			private Scanner input;	
			private String[]QuizQs;
			private int randomQuestion,x1,x2, x3;
			private boolean ca1, ca2, ca3, ca4, sp, a1select, a2select, a3select, a4select, correct, wrong;
			private JButton submit;
			private ButtonGroup answers;
			private JRadioButton a1, a2, a3, a4;
			private Font f, smallf;

			public Quiz()
			{	
				f = new Font ("URW Gothic L", Font.PLAIN, 25);
				smallf = new Font ("URW Gothic L", Font.PLAIN, 15);
				setLayout(null);
				submit = new JButton("Submit");
				submit.setFont(f);
				submit.setText("Submit");
				submit.setSize(200, 70);
				submit.setLocation(350,490);
				submit.addActionListener(this);
				
				add(submit);
				
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
				
				a1.setLocation(10, 130);
				a2.setLocation(10, 230);
				a3.setLocation(10, 330);
				a4.setLocation(10, 430);
				
				a1.setFont(smallf);
				a2.setFont(smallf);
				a3.setFont(smallf);
				a4.setFont(smallf);
				
				
				add(a1);
				add(a2);
				add(a3);
				add(a4);
				
				ca1 = false;
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
				
				//getTextFile();
				//getText();
				

				randomQuestion = (int)((Math.random()*13)+1);		//randomizing an integer when the user clicks submit in the question
				//randomQuestion = 4;
				getTextFile();
				getText();
				setVariables();
				repaint();
				displayRadioButtons();
				//actionPerformed();
		
			}
			public void getTextFile()
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
			

			public void getText()										//method from reading input from the tutorial.txt file so we c an print the stuff from the tutorial.txt file on to the JTextArea
			{	

				while(input.hasNext())
				{
					line = input.nextLine();
					
					fullTextFile = fullTextFile + "\n" + line;			//creating a string to add to the textArea
				}
				
				for(x1=0;x1<29;x1++)
				{
			
					fullQuestion = fullTextFile.substring(0, fullTextFile.indexOf("---")+3)	;		//creating a string to add to the textArea
					/*if (x1 == 13)
					{
						//System.out.print(fullQuestion);
						//System.out.print(fullQuestion.length());
						//fullTextFile = fullTextFile.substring(fullQuestion.length());
					}*/
					
					fullTextFile = fullTextFile.substring(fullQuestion.length()+4);				
					QuizQs[x1] = fullQuestion;
				}
				//System.out.print(fullQuestion);					//setting the text to what is in the tutorial.txt file
			}
			public void setVariables()
			{


				for (x3 = 0; x3<29 ; x3++)
				{
					fullQuestion2 = QuizQs[x3];
					
					if (x3 == randomQuestion)
					{
						qNumber = fullQuestion2.substring((fullQuestion2.indexOf(">") +1), (fullQuestion2.indexOf("<")));
						fullQuestion2 = fullQuestion2.substring(qNumber.length() + 3); 
						//System.out.println(qNumber);
						
						choiceA = fullQuestion2.substring(fullQuestion2.indexOf("a)")+2, fullQuestion2.indexOf("b)"));
						fullQuestion2 = fullQuestion2.substring(choiceA.length()+2);
						
						
						choiceB = fullQuestion2.substring(fullQuestion2.indexOf("b)")+2, fullQuestion2.indexOf("c)"));
						fullQuestion2 = fullQuestion2.substring(choiceB.length()+2);//choiceB = fullQuestion.substring(beginIndex, endIndex)

						choiceC = fullQuestion2.substring(fullQuestion2.indexOf("c)")+2, fullQuestion2.indexOf("d)"));
						fullQuestion2 = fullQuestion2.substring(choiceC.length() +2);//choiceB = fullQuestion2.substring(beginIndex, endIndex)

						
						choiceD = fullQuestion2.substring(fullQuestion2.indexOf("d)")+2, fullQuestion2.indexOf("---"));
						fullQuestion2 = fullQuestion2.substring(choiceD.length()+4);//choiceB = fullQuestion.substring(beginIndex, endIndex)
					
						//System.out.print(choiceA);

						if (choiceA.indexOf("!") == 0)
						{
						System.out.print(choiceA);
						ca1 = true;
						choiceA = choiceA.substring(1);
						}
						
						else if (choiceB.indexOf("!") == 0)
						{
						System.out.print(choiceB);
						ca2 = true;
						choiceB = choiceB.substring(1);
						}
						
						else if (choiceC.indexOf("!") == 0)
						{
						System.out.print(choiceC);
						ca3 = true;
						choiceC = choiceC.substring(1);
						}
						
						else if (choiceD.indexOf("!") == 0)
						{
						System.out.print(choiceD);
						ca4 = true;
						choiceD = choiceD.substring(1);
						
						}

					}
					
				}
			}
			public void paintComponent(Graphics g)
			{
				g.setFont(smallf);
				g.drawString(qNumber, 30,100);
				
			}
			public void displayRadioButtons()
			{
				a1.setFont(smallf);
				a1.setText(choiceA);
				a2.setText(choiceB);
				a3.setText(choiceC);
				a4.setText(choiceD);
				
				
			}
			public void actionPerformed(ActionEvent e){
				String command = e.getActionCommand();
				if(command.equals("Submit"))
				{
					sp = true;
					changeQuestions();
					/*randomQuestion = (int)((Math.random()*13)+1);		//randomizing an integer when the user clicks submit in the question
					System.out.print(randomQuestion);
					getTextFile();
					getText();
					setVariables();
					repaint();
					displayRadioButtons();*/

				}
				
				if(a1.isSelected())
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
					
			}
			public void changeQuestions()
			{

				System.out.println();
				if(sp == true &&  a1select == true && ca1 == true)
				{
					getTextFile();
					getText();
					setVariables();
					repaint();
					displayRadioButtons();
					sp = false;
					a1select = false;
					ca1 = false;
					correct = true;
				}
				else if(sp == true &&  a2select == true && ca2 == true)
				{
					getTextFile();
					getText();
					setVariables();
					repaint();
					displayRadioButtons();
					correct = true;
					sp = false;
					a2select = false;
					ca2 = false;
				}
				else if(sp == true &&  a3select == true && ca3 == true)
				{
					getTextFile();
					getText();
					setVariables();
					repaint();
					displayRadioButtons();
					correct = true;
					sp = false;
					a3select = false;
					ca3 = false;
				}
				else if(sp == true &&  a4select == true && ca4 == true)
				{
					getTextFile();
					getText();
					setVariables();
					repaint();
					displayRadioButtons();
					correct = true;
					sp = false;
					a4select = false;
					ca4 = false;
				}
	
					
			
			}
			
		
	
		}
		class ShowTraits extends JPanel	implements ActionListener									//panel that reveals what the traits are using draw strings
		{
			
			public ShowTraits(){

			}	
			public void actionPerformed(ActionEvent e){}			//
		}
		
	}
	}
	//class UnlockTraitsPanel extends JPanel implements ActionListener
	//}//CLOSING THE BASE PANEL
	/*class TutorialPanel1 extends JPanel implements AdjustmentListener	//class tutorial panel extends Jpane
//{
	private Scanner input;											//declare field variables
	private String inFileName;
	private JTextArea textArea1;									//declaring the text area for the tutorial
	private String line, fullTutorial;

	public TutorialPanel1()											//constructor
//set layout null
	{
		textArea1 = new JTextArea();
		add(textArea1);												//add text area to the panel 

		inFileName = "Tutorial.txt";									//add both textAreas to panel
		setLayout(null);
		setVisible(true);

		line = "";
		fullTutorial = "";
	}
	public void importTextFiles()										//method for try catch blocks 
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
		input.nextLine();

		fullTutorial = fullTutorial + "\n" + line;
		}

	}
	public void adjustmentValueChanged(AdjustmentEvent e)

	{
	}
}*/
//CLOSING BASE PANEL												//set size and locations of components
//start method
//if/else for if the start method is pressed
//goes to tutorial if button is pressed




//declare both textAreas

//set size and locations of components



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
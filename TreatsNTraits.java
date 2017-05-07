
/* Neha Jagathesan & Nikita Shetty
5/5/17
TreatsNTraits.java
 game*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
test

//github.com - sharing code

/* Parts of game
 * 1. writing text files for the how to and background info - making back buttons to return to main panel after tutorial is done
 * 2. drawing the sample dog - outline to fill in varying traits
 * 3. talk to mentor about which breed of dog to focus on/ should we focus on one breed
 * 4. creating all the panels - deciding how many are needed
 * 5. researching different traits on dog
 * 6. formulate quiz questios to reveal traits on the dog*/
//import abstract windowing toolkit, including Graphics, Image, Color, Font

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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

	public TreatsNTraitsPanel()////////////// write constructor
	{



		cards = new CardLayout();
		setLayout(cards);

		startP = new StartPanel(this);
		add(startP, "Start");

		tutorP1= new TutorialPanel1(this);
		add(tutorP1, "Complete Dominance");

		cdp = new ChooseDogPanel(this);
		add(cdp, "Choose Your Dog");

		utp = new UnlockTraitsPanel(this);
		add(utp, "Unlock Traits");


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
			//startImageName = "/Documents/workspace/TreatsNTraits.java/src/StartPanel.png";
			startImageName = "startPanel.png";
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

			mysteryDog = new ImageIcon("mysterydog.png").getImage();
		}

		public void paintComponent(Graphics g)
		{

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
		public UnlockTraitsPanel(TreatsNTraitsPanel tp)						//panel on whixh traits will be written out
		{
			tntPanel2 = tp;
			setLayout(new GridLayout(1,2));


			st = new ShowTraits();
			q = new Quiz();

			st.setBackground(Color.MAGENTA);


			add(st, "Show Traits");
			add(q, "Quiz");
		}
		public void actionPerformed(ActionEvent e)
		{

		}
		class Quiz extends JPanel implements ActionListener

		{


			private int randomQuestion;
			public Quiz()
			{
				//quizCards = new CardLayout();
				//setLayout(quizCards);

				/*class Question1 extends JPanel implements ActionListener
				{
				private CardLayout quizCards;

				private JButton submit;
				private ButtonGroup answers1;
				private JRadioButton a1;
				private JRadioButton a2;
				private JRadioButton a3;
				private JRadioButton a4;

					public Question1()
					{
						setLayout(null);
					}
					public void actionPerformed(ActionEvent e)
					{
					if(rb1.isSelected())
					signal = 1;

					else if(rb2.isSelected())
					signal = 2;

					else if(rb3.isSelected())
					signal = 3;

					else if(rb4.isSelected())
					signal = 4;

					else
					signal = 5;

				ep.repaint();//OK
			}*/
			}
			public void actionPerformed(ActionEvent e){}
		}

		class ShowTraits extends JPanel	implements ActionListener									//panel that reveals what the traits are using draw strings
		{
			public ShowTraits(){}
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

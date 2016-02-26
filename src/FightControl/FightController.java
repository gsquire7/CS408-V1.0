package FightControl;

import java.util.ArrayList;

import javax.swing.JFrame;

public class FightController {

	static boolean mainScreen = false;
	static boolean speech = true;
	static boolean moves = false;
	static boolean moveUsed = false;
	static boolean change = false;
	static boolean pack = false;
	static boolean playerControl = true;
	static boolean enemyHit = false;
	static boolean pokemonChanged = false;
	boolean run = false, changeablePokemon;
	static int L = 10;

	static int xPos = 7 * L, yPos = 76 * L, totalWidth = 1000, totalHeight = 1000;
	static int ballLeft = 7 * L, ballRight = 52 * L, writingLeft = 10 * L, writingRight = 55 * L, ppWidth = 30 * L, 
			upperWriting = 78 * L, lowerWriting = 88 * L;
	static int expPercent, EnemyHPPercent, PlayerHPPercent;
	public static String spoken;

	FightMoves FM = new FightMoves();
	RandomGen RM = new RandomGen();
	OwnedPokemon OP = new OwnedPokemon();
	FightView FV = new FightView();
	AI.Random AIR = new AI.Random();
	static ArrayList<PokemonState> playerParty = new ArrayList<PokemonState>();
	static ArrayList<PokemonState> enemyParty = new ArrayList<PokemonState>();
	static int playerCurrent = 0, enemyCurrent = 0;
	static boolean playerAttackable = true, enemyAttackable = true;
	static boolean struggle = false;

	//temporary attack statistics
	private int accuracy, base, damage, choice;
	public static boolean playerTurn = true, playable = true;
	static int temppercent;

	public ArrayList<PokemonState> runBattle(ArrayList<PokemonState> currentPokemon, ArrayList<PokemonState> enemyPokemon){	
		playerCurrent = 0;
		enemyCurrent = 0;
		FM.populateMoves();
		playerParty = new ArrayList<PokemonState>(currentPokemon);
		enemyParty = new ArrayList<PokemonState>(enemyPokemon);
		JFrame jf = new JFrame();
//		totalWidth = totalWidth * L;
//		totalHeight = totalHeight * L;
		jf.setSize(100 * L, 100* L);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(FV);
		//		System.out.println("Here");
		spoken = "A wild " + enemyParty.get(enemyCurrent).getName() + " appeared";
		FV.Pause(1000);
		spoken = "Wild " + enemyParty.get(enemyCurrent).getName() + " attacked";
		FV.Pause(1000);
		spoken = playerParty.get(playerCurrent).getName() + " GO!";
		FV.Pause(1000);
		System.out.println("Here");

		while(playable == true){
			mainScreen = true;
			speech = false;
			playerTurn();
			enemyTurn();
		}

		return playerParty;
	}



	public void enemyTurn() {
		choice = 0;
		isStruggle(enemyParty, enemyCurrent);
		changeablePokemon();
		while(playerTurn != true && playable == true)
		{
			System.out.println("LUGIA TURN");
			choice = AIR.generateRandomChoice(struggle, changeablePokemon);
			choices();
		}
	}

	public void playerTurn(){
		

		isStruggle(playerParty, playerCurrent);
		while(playerTurn == true && playable == true)
		{
			FV.Pause(10);
			//if move has been selected
			if(speech == true && pokemonChanged == false)
			{
				if(xPos == 7 * L && yPos == 76 * L)
				{
					choice = 1;
				}
				if(xPos == 52 * L && yPos == 76 * L)
				{
					choice = 2;
				}
				if(xPos == 7 * L && yPos == 86 * L)
				{
					choice = 3;
				}
				if(xPos == 52 * L && yPos == 86 * L)
				{
					choice = 4;
				}
				if(struggle == true)
				{
					choice = 5;
				}
				
				choices();
			}
			//if pokemon has been changed
			else if(speech == false && pokemonChanged == true)
			{
				change = false;
				speech = true;
				FV.Pause(1000);
				spoken = playerParty.get(playerCurrent).getName() + " I CHOOSE YOU!";
				FV.Pause(1000);
				xPos = 7 * L;
				yPos = 76 * L;
				pokemonChanged = false;
				playerTurn = false;
			}
		}
	}
	//dpes player need to use struggle
	public static void isStruggle(ArrayList<PokemonState> x, int y)
	{
		if(x.get(y).getMove1PP() + x.get(y).getMove2PP() + x.get(y).getMove3PP() + x.get(y).getMove4PP() == 0)
		{
			struggle = true;;
		}
		else
		{
			struggle = false;
		}
	}

	public void choices()
	{
		int pos, oppPos;
		ArrayList<PokemonState> list, oppList;
		if(playerTurn == true)
		{
			pos = playerCurrent;
			oppPos = enemyCurrent;
			list = playerParty;
			oppList = enemyParty;


		}
		else
		{
			pos = enemyCurrent;
			oppPos = playerCurrent;
			list = enemyParty;
			oppList = playerParty;
		}
		boolean moveUsed = true;
		boolean healthChange = false;
		double healthChangeRatio = 0.0;

		if (choice == 1)
		{
			if(list.get(pos).getMove1PP() == 0)
			{
				moveUsed = false;
			}
			
			if (moveUsed == true)
			{
				System.out.println("used " + list.get(pos).getMove1());
				getStats(list.get(pos).getName(), list.get(pos).getMove1(), list.get(pos).getMove1PP());
				list.get(pos).decrementMove1PP();
				healthChangeRatio = FM.getHealthRegeneration(list.get(pos).getMove1());
				if( healthChangeRatio != 0.0)
				{
					healthChange = true;
				}
			}
			else if(moveUsed == false)
			{
				noPP();	
			}
		}
		else if (choice == 2)
		{
			if(list.get(pos).getMove2PP() == 0)
			{
				moveUsed = false;
			}
			if(moveUsed == true)
			{
				System.out.println("used " + list.get(pos).getMove2());
				getStats(list.get(pos).getName(), list.get(pos).getMove2(), list.get(pos).getMove2PP());
				list.get(pos).decrementMove2PP();
				healthChangeRatio = FM.getHealthRegeneration(list.get(pos).getMove2());
				if( healthChangeRatio != 0.0)
				{
					healthChange = true;
				}
			}
			else if(moveUsed == false)
			{
				noPP();	
			}
		}
		else if (choice == 3)
		{
			if(list.get(pos).getMove3PP() == 0)
			{
				moveUsed = false;
			}
			if(moveUsed == true)
			{
				System.out.println("used " + list.get(pos).getMove3());
				getStats(list.get(pos).getName(), list.get(pos).getMove3(), list.get(pos).getMove3PP());
				list.get(pos).decrementMove3PP();
				healthChangeRatio = FM.getHealthRegeneration(list.get(pos).getMove3());
				if( healthChangeRatio != 0.0)
				{
					healthChange = true;
				}
			}
			else if(moveUsed == false)
			{
				noPP();	
			}
		}
		else if (choice == 4)
		{
			if(list.get(pos).getMove4PP() == 0)
			{
				moveUsed = false;
			}
			if(moveUsed == true)
			{
				System.out.println("used " + list.get(pos).getMove4());
				getStats(list.get(pos).getName(), list.get(pos).getMove4(), list.get(pos).getMove4PP());
				list.get(pos).decrementMove4PP();
				healthChangeRatio = FM.getHealthRegeneration(list.get(pos).getMove4());
				if( healthChangeRatio != 0.0)
				{
					healthChange = true;
				}
			}
			else if(moveUsed == false)
			{
				noPP();	
			}

		}
		else if (choice == 5)
		{
			getStats(list.get(pos).getName(), "STRUGGLE", 1);
			healthChangeRatio = FM.getHealthRegeneration("STRUGGLE");
			healthChange = true;
		}

		if (choice < 6 && choice > 0)
		{
			//does the move hit due to accuracy
			if(accuracy < 100)
			{
				playerAttackable = accuracy(accuracy);				
			}
			//if the pokemon can attack then calculate damage
			if (playerAttackable != false || enemyAttackable != false)
			{
				if(base != 0)
				{
					calcDamage(list.get(pos).getLevel(), list.get(pos).getAttack(), oppList.get(oppPos).getDefence(), base);
					oppList.get(oppPos).setHP(attackStrength(oppList.get(oppPos).getHP(), damage));
					System.out.println("Enemy " + oppList.get(oppPos).getName() + " hit.");
					//if move regenerates player hp, regenerate it
					if(healthChange == true)
					{
						int k = (int)(list.get(pos).getHP() + (healthChangeRatio * damage));
						if(k > list.get(pos).getHPTotal())
						{
							list.get(pos).setHP(list.get(pos).getHPTotal());
						}
						else
						{
							list.get(pos).setHP((int) (list.get(pos).getHP() + (healthChangeRatio * damage)));
							System.out.println("Player regenerates health");
						}
					}
				}
				if(playerTurn == true)
				{
					spoken = list.get(pos).getName() + " hit!";
					xPos = 7 * L;
					yPos = 76 * L;
					FV.Pause(1000);
					System.out.println(oppList.get(oppPos).getName() + " health Points remaining " + oppList.get(oppPos).getHP());
					System.out.println(list.get(pos).getName() + " health Points remaining " + list.get(pos).getHP());
					playerAttackable = true;
					playerTurn = false;
				}
				else
				{
					spoken = "Enemy " + enemyParty.get(enemyCurrent).getName() + " hit!";
					FV.Pause(1000);
					System.out.println(playerParty.get(playerCurrent).getName() + " hit. Health Points remaining " + playerParty.get(playerCurrent).getHP());
					System.out.println(enemyParty.get(enemyCurrent).getName() + " Health Points remaining " + enemyParty.get(enemyCurrent).getHP());
					playerAttackable = true;
					playerTurn = true;
				}

			}
			else
			{
				spoken = list.get(pos).getName() + " missed!";
				FV.Pause(1000);
				if(playerTurn == true)
				{
					playerTurn = false;
				}
				else
				{
					playerTurn = true;
				}
			}

			//Did the opponent faint
			//if yes
			if(oppList.get(oppPos).getHP() <= 0)
			{
				//tell user opponent fainted
				if(playerTurn == false)
				{
					spoken = "Enemy " + oppList.get(oppPos).getName() + " fainted!";
					System.out.println("Enemy " + oppList.get(oppPos).getName() + " fainted.");
				}
				else
				{
					spoken = list.get(pos).getName() + " fainted.";
					System.out.println(list.get(pos).getName() + " fainted.");
				}
				FV.Pause(1000);
				//check to see if there are any other pokemon in the party
				//if not then fight won
				boolean out = true;
				ArrayList<Integer> left = new ArrayList<Integer>();

				for(int z = 0; z < oppList.size(); z++)
				{
					if(oppList.get(z).getHP() > 0)
					{

						left.add(z);
						out = false;
					}
				}

				if(out == true)
				{
					spoken = list.get(pos).getName() + " has won the fight";
					FV.Pause(1000);
					System.out.println(list.get(pos).getName() + " has won the fight");
					playable = false;
				}
				//else determine which pokemon is selected
				else
				{
					if(playerTurn == false)
					{
						spoken = ("ENEMY: " + oppList.get(oppPos).getName() + " THATS ENOUGH");
						FV.Pause(1000);
						enemyCurrent = left.get(RM.GenerateNumber(left.size()));
						spoken = ("ENEMY: " + oppList.get(enemyCurrent).getName() + " I CHOOSE YOU");
						FV.Pause(1000);
						speech = false;
						mainScreen = true;
						playerTurn = true;
					}
					else
					{
						playerCurrent = left.get(0);
						playerTurn = false;
					}
				}

			}
		}
		else if (choice == 6)
		{
			
			/*
			 * Code for allowing the user to change pokemon
			 * move to AI as this should be an AI choice
			 */
			boolean allowed = false;
			
			while(allowed == false)
			{
				System.out.println("IN THE LOOP");
				int use = RM.GenerateNumber(enemyParty.size());
				if(use == enemyCurrent)
				{
					allowed = false;
				}
				else if(enemyParty.get(use).getHP() < 1)
				{
					allowed = false;
				}
				else
				{
					spoken = ("ENEMY: " + enemyParty.get(enemyCurrent).getName() + " THATS ENOUGH!");
					FV.Pause(1000);
					System.out.println("Enemy: " + enemyParty.get(use).getName() + " I CHOOSE YOU!");
					spoken = "Enemy: " + enemyParty.get(use).getName() + " I CHOOSE YOU!";
					FV.Pause(1000);
					enemyCurrent = use;
					allowed = true;
					speech = false;
					mainScreen = true;
					playerTurn = true;
					
				}
			}
//			playerTurn = true;
		}//end of choice between 0 and 6
	}


	public void noPP()
	{
		System.out.println("NO PP LEFT");
		choice = 0;
		if(playerTurn == true)
		{
			speech = true;
			mainScreen = false;
			spoken= "NO PP LEFT";
			FV.Pause(1000);
			mainScreen = true;
			speech = false;
		}
	}


	public int attackStrength(int healthpoints, int strength) {

		healthpoints = healthpoints - strength;
		if(healthpoints < 0)
		{
			healthpoints = 0;
		}
		return healthpoints;
	}

	public boolean accuracy(int x)
	{
		boolean hit;
		int accurate = RM.GenerateNumber(100);
		//		System.out.println("Accuracy Number" + accurate);
		if(accurate <= x)
		{
			hit = true;
		}
		else
		{
			hit = false;

		}
		return hit;

	}


	public void calcDamage(int level, int attack, int defense, int base)
	{
		double x = Math.floor((((2 * (double)level + 10) / 250) * ((double)attack / 
				(double)defense) * (double)base) + 2);

		damage = (int)x;
		//		System.out.println("Damage = " + damage);

	}
	public void getStats(String name, String move, int pp)
	{
		pp--;
		if(pp >= 0)
		{
			spoken = name + " used " + move;
			FV.Pause(1000);
			System.out.println(name + " used " + move);
			base = FM.getStrength(move);
			accuracy = FM.getAccuracy(move);
		}
	}
	public static void playerHpPercent()
	{
		double x = ((double)playerParty.get(playerCurrent).getHP() / (double)playerParty.get(playerCurrent).getHPTotal()) * 100;

		PlayerHPPercent = (int)x; 
	}

	public static int HpPercent(int z)
	{
		double x = ((double)playerParty.get(z).getHP() / (double)playerParty.get(z).getHPTotal()) * 100;

		temppercent = (int)x;
		return temppercent; 
	}

	public static void enemyHpPercent()
	{
		double x = ((double)enemyParty.get(enemyCurrent).getHP() / (double)enemyParty.get(enemyCurrent).getHPTotal()) * 100;
		EnemyHPPercent = (int)x;
	}

	public static void expPercentage()
	{
		double x = ((double)playerParty.get(playerCurrent).getXP() / (double)playerParty.get(playerCurrent).getXPTotal()) * 100;
		expPercent = (int)x;
	}

	public void changeablePokemon()
	{
		/*
		 * Code to stop trying to change pokemon when only one is useable
		 * prevents infinite looping later
		 */
		int check = 0;
		for(int b = 0; b < enemyParty.size(); b++)
		{
			if(enemyParty.get(b).getHP() > 0)
			{
				check++;
			}
		}
		
		if(check == 1)
		{
			changeablePokemon = false;
		}
		else
		{
			changeablePokemon = true;
		}
	}
}
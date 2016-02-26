package FightControl;

import java.awt.event.KeyEvent;

public class UserControl {
/*
 * Class for dealing with all button presses, originally in view
 * has no business being in the view class though so i made it a user control method
 */
	public static void keyPress(int key)
	{
		

		if(key ==KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
		{
			System.out.println("LEFT");
			FightController.xPos = 7 * FightController.L;
		}
		
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
		{
			System.out.println("RIGHT");
			FightController.xPos = 52 * FightController.L;
		}
		
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W)
		{
			System.out.println("UP");
			if(FightController.change == false && FightController.pack == false)
			{
				FightController.yPos = 76 * FightController.L;
			}
			else if(FightController.change == true)
			{
				if(FightController.yPos == (int)(75.5 * FightController.L) && FightController.playerParty.size() == 5)
				{
					FightController.yPos = (int)(55.5 * FightController.L);
				}
				else if(FightController.yPos == (int)(75.5 * FightController.L) && FightController.playerParty.size() == 4)
				{
					FightController.yPos = (int)(45.5 * FightController.L);
				}
				else if(FightController.yPos == (int)(75.5 * FightController.L) && FightController.playerParty.size() == 3)
				{
					FightController.yPos = (int)(35.5 * FightController.L);
				}
				else if(FightController.yPos == (int)(75.5 * FightController.L) && FightController.playerParty.size() == 2)
				{
					FightController.yPos = (int)(25.5 * FightController.L);
				}
				else if(FightController.yPos == (int)(75.5 * FightController.L) && FightController.playerParty.size() == 1)
				{
					FightController.yPos = (int)(15.5 * FightController.L);
				}
				else if(FightController.yPos == (int)(75.5 * FightController.L))
				{
					FightController.yPos = FightController.yPos - 10 * FightController.L;
				}
				else if(FightController.yPos != (int)(15.5 * FightController.L))
				{
					FightController.yPos = FightController.yPos - 10 * FightController.L;
				}
			}
		}
		
		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S)
		{
			System.out.println("DOWN");
			if(FightController.change == false && FightController.pack == false)
			{
				FightController.yPos = 86 * FightController.L;
			}
			else if(FightController.change == true)
			{
				if(FightController.yPos == (int)(15.5 * FightController.L) && FightController.playerParty.size() > 1)
				{
					FightController.yPos = FightController.yPos + 10 * FightController.L;
				}
				else if(FightController.yPos == (int)(25.5 * FightController.L)&& FightController.playerParty.size() > 2)
				{
					FightController.yPos = FightController.yPos + 10 * FightController.L;
				}
				else if(FightController.yPos == (int)(35.5 * FightController.L)&& FightController.playerParty.size() > 3)
				{
					FightController.yPos = FightController.yPos + 10 * FightController.L;
				}
				else if(FightController.yPos == (int)(45.5 * FightController.L)&& FightController.playerParty.size() > 4)
				{
					FightController.yPos = FightController.yPos + 10 * FightController.L;
				}
				else if(FightController.yPos == (int)(55.5 * FightController.L) && FightController.playerParty.size() > 5)
				{
					FightController.yPos = FightController.yPos + 10 * FightController.L;
				}
				else if(FightController.yPos == (int)(65.5 * FightController.L))
				{
					FightController.yPos = FightController.yPos + 10 * FightController.L;
				}
				else
				{
					FightController.yPos = (int)(75.5 * FightController.L);
				}
			}
		}
		//A BUTTON
		if (key == KeyEvent.VK_L)
		{
			System.out.println("A");
			//on main screen
			if(FightController.mainScreen == true)
			{
				//selecting moves
				FightController.isStruggle(FightController.playerParty, FightController.playerCurrent);
				if(FightController.xPos == 7 * FightController.L && FightController.yPos == 76 * FightController.L && FightController.struggle == false)
				{
					FightController.mainScreen = false;
					FightController.moves = true;
				}
				if(FightController.xPos == 7 * FightController.L && FightController.yPos == 76 * FightController.L && FightController.struggle == true)
				{
					FightController.mainScreen = false;
					FightController.speech = true;
				}
				if(FightController.xPos == 52 * FightController.L && FightController.yPos == 76 * FightController.L)
				{
					FightController.mainScreen = false;
					FightController.change = true;
					FightController.yPos = (int)(15.5 * FightController.L);
				}
				if(FightController.xPos == 52 * FightController.L && FightController.yPos == 86 * FightController.L)
				{
					System.exit(0);
				}
			}
			//on move screen
			else if(FightController.moves == true)
			{
				FightController.moves = false;
				FightController.speech = true;

			}
			//on pokemon screen
			else if(FightController.change == true)
			{
				if(FightController.yPos == (int)(15.5 * FightController.L) && FightController.playerCurrent !=0 && FightController.playerParty.size() > 0 && FightController.playerParty.get(0).getHP() > 0)
				{
					FightController.spoken = (FightController.playerParty.get(FightController.playerCurrent).getName() + " THATS ENOUGH!");
					FightController.pokemonChanged = true;
					FightController.playerCurrent = 0;
				}
				if(FightController.yPos == (int)(25.5 * FightController.L) && FightController.playerCurrent !=1 && FightController.playerParty.size() > 1 && FightController.playerParty.get(1).getHP() > 0)
				{
					FightController.spoken = (FightController.playerParty.get(FightController.playerCurrent).getName() + " THATS ENOUGH!");
					FightController.pokemonChanged = true;
					FightController.playerCurrent = 1;
				}
				if(FightController.yPos == (int)(35.5 * FightController.L) && FightController.playerCurrent !=2 && FightController.playerParty.size() > 2 && FightController.playerParty.get(2).getHP() > 0)
				{
					FightController.spoken = (FightController.playerParty.get(FightController.playerCurrent).getName() + " THATS ENOUGH!");
					FightController.pokemonChanged = true;
					FightController.playerCurrent = 2;
				}
				if(FightController.yPos == (int)(45.5 * FightController.L) && FightController.playerCurrent !=3 && FightController.playerParty.size() > 3 && FightController.playerParty.get(3).getHP() > 0)
				{
					FightController.spoken = (FightController.playerParty.get(FightController.playerCurrent).getName() + " THATS ENOUGH!");
					FightController.pokemonChanged = true;
					FightController.playerCurrent = 3;
				}
				if(FightController.yPos == (int)(55.5 * FightController.L) && FightController.playerCurrent !=4 && FightController.playerParty.size() > 4 && FightController.playerParty.get(4).getHP() > 0)
				{
					FightController.spoken = (FightController.playerParty.get(FightController.playerCurrent).getName() + " THATS ENOUGH!");
					FightController.pokemonChanged = true;
					FightController.playerCurrent = 4;
				}
				if(FightController.yPos == (int)(65.5 * FightController.L) && FightController.playerCurrent !=5 && FightController.playerParty.size() > 5 && FightController.playerParty.get(5).getHP() > 0)
				{
					FightController.spoken = (FightController.playerParty.get(FightController.playerCurrent).getName() + " THATS ENOUGH!");
					FightController.pokemonChanged = true;
					FightController.playerCurrent = 5;
				}
				
			}
			

		}
		//B BUTTON
		if (key == KeyEvent.VK_K)
		{
			System.out.println("B");
			if(FightController.moves == true || FightController.change == true)
			{

				FightController.mainScreen = true;
				FightController.change = false;
				FightController.moves = false;
				FightController.xPos = 7 * FightController.L;
				FightController.yPos = 76 * FightController.L;

			}
		}
		
		if (key == KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
		}
	}
	
}

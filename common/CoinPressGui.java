package assets.modjam3.common;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

public class CoinPressGui extends GuiContainer{
	private CoinPressTile goldInventory;
	public CoinPressGui(InventoryPlayer inventory,CoinPressTile gold) {
		super(new CoinPressContainer(gold,inventory));
		// TODO Auto-generated constructor stub
		goldInventory = gold;
	}
	  protected void drawGuiContainerForegroundLayer(int par1, int par2)
      {
               fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 0xffffff);
      }

      /**
               * Draw the background layer for the GuiContainer (everything behind the items)
               */
      protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
      {
             /*  int i = mc.renderEngine.getTexture("/industrialisation/furnace.png");
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               mc.renderEngine.bindTexture(i);
               int j = (width - xSize) / 2;
               int k = (height - ySize) / 2;
               drawTexturedModalRect(j, k, 0, 0, xSize, ySize);

               if (goldInventory.isBurning())
               {
                       int burn = goldInventory.getBurnTimeRemainingScaled(14);
                       drawTexturedModalRect(j + 73, k+59, 176, 16, burn, 10);
               }

               int update = goldInventory.getCookProgressScaled(16);
               drawTexturedModalRect(j+ 89, k+55, 191, 15,-update , -update);
      }*/
      }
}

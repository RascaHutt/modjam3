package assets.modjam3.common;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class CoinPressGui extends GuiContainer{
	private CoinPressTile goldInventory;
	public int var9=0;
	World world;
	int xx;
	int yy;
	int var5;
	int var6;
	int zz;
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
    	  //int var4 = this.mc.renderEngine.bindTexture("/gui/furnace.png");
	         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	         this.mc.renderEngine.bindTexture(DefaultProps.COIN_PRESS_GUI);
	         var5 = (this.width - this.xSize) / 2;
	         var6 = (this.height - this.ySize) / 2;

	         this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
	         int var7=0;

	        /* if (this.furnaceInventory.isBurning())
	         {
	                 var7 = this.furnaceInventory.getBurnTimeRemainingScaled(12);
	                 this.drawTexturedModalRect(var5 + 56, var6 + 36 + 12 - var7, 176, 12 - var7, 14, var7 + 2);
	         }
	         if (world.getBlockTileEntity(xx, yy, zz)!= null){

	         tile = ((CoinPressTile) world.getBlockTileEntity(xx, yy, zz));
	         world.markBlockForUpdate(xx, yy, zz);
	         GL11.glColor4f(51/255F, 255/255F, 51/255F, 1.0F);
	         this.drawTexturedModalRect(var5 + 142, var6 +65 -tile.gastank/5, 176, 65-tile.gastank/5, 14, tile.gastank/5);
	         }
	         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	         var7 = this.furnaceInventory.getCookProgressScaled(24);
	         this.drawTexturedModalRect(var5 + 79, var6 + 34, 176, 14, var7 + 1, 16);
	         //System.out.println(var7);*/

      
      }
}

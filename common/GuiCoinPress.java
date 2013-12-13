package assets.modjam3.common;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;

public class GuiCoinPress extends GuiContainer{
	private TileCoinPress coinpressInventory;
	public int var9=0;
	World world;
	int xx;
	int yy;
	int var5;
	int var6;
	int zz;
	TileCoinPress tile;
	public GuiCoinPress(InventoryPlayer player, TileCoinPress tilecoinpress){
		super(new ContainerCoinPress(player, tilecoinpress));
		this.coinpressInventory = tilecoinpress;
		world = tilecoinpress.getWorldObj();
		xx = tilecoinpress.xCoord;
		yy = tilecoinpress.yCoord;
		zz = tilecoinpress.zCoord;
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2){
		this.fontRenderer.drawString("Coin Press", 38, 6, 4210752);
		drawLedgers(par1, par2);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
	         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	         var5 = (this.width - this.xSize) / 2;
	         var6 = (this.height - this.ySize) / 2;

	         this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
	         int var7=0;

	         if (this.coinpressInventory.isBurning())
	         {
	                 var7 = this.coinpressInventory.getBurnTimeRemainingScaled(12);
	                 this.drawTexturedModalRect(var5 + 56, var6 + 36 + 12 - var7, 176, 12 - var7, 14, var7 + 2);
	         }
	         if (world.getBlockTileEntity(xx, yy, zz)!= null){

	         tile = ((TileCoinPress) world.getBlockTileEntity(xx, yy, zz));
	         world.markBlockForUpdate(xx, yy, zz);
	         }
	         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	         var7 = this.coinpressInventory.getCookProgressScaled(24);
	         this.drawTexturedModalRect(var5 + 79, var6 + 34, 176, 14, var7 + 1, 16);



	}
	protected void drawLedgers(int mouseX, int mouseY) {
		if (var5+142<mouseX && mouseX<var5+142+14 ){
		int xPos = 8;
<<<<<<< HEAD
			String tooltip ="power";
=======

>>>>>>> af785883ccfd60be7d77ef2d69be568c6e3a79f4
			int startX = mouseX - ((this.width - this.xSize) / 2) + 12;
			int startY = mouseY - ((this.height - this.ySize) / 2) - 12;

			int textWidth = fontRenderer.getStringWidth(tooltip);
			drawGradientRect(startX - 3, startY - 3, startX + textWidth + 3, startY + 8 + 3, 0xc0000000, 0xc0000000);
			fontRenderer.drawStringWithShadow(tooltip, startX, startY, -1);
		}
	}
}
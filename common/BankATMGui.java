package assets.modjam3.common;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.StatCollector;

public class BankATMGui extends GuiContainer{
	int var5;
	int var6;
	int x;
	int y;
	

	BankATMTile tile;
	String text;
	EntityPlayer player;
	Boolean a = true;
	public BankATMGui(InventoryPlayer inventory,BankATMTile gold) {
		super(new BankATMContainer(gold,inventory));
		tile = gold;
		player = inventory.player;
	}

	  protected void drawGuiContainerForegroundLayer(int par1, int par2)
      {
		  	   int k = (this.width - this.xSize) / 2;
 	  	 	   int l = (this.height - this.ySize) / 2;
 	  	 	   var5 = (this.width - this.xSize) / 2;
 	  	 	   var6 = (this.height - this.ySize) / 2;
 	  	 	   x = (this.width - this.xSize) /2 + 100;
 	  	 	   y = (this.height - this.ySize) /2 + 52;
 	  	 	   
               fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 0xffffff);
               this.fontRenderer.drawString("Stock Trader", 62, 6, 4210752);
              
               buttonList.add(new GuiButton(1, x, y, 50, 20, "Sell"));
   
 }

	
	    
	  public static boolean isInteger(String s) {
		    try { 
		        Integer.parseInt(s); 
		    } catch(NumberFormatException e) { 
		        return false; 
		    }
		    // only got here if we didn't return false
		    return true;
		}
	  protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
      {
    	  	 int k = (this.width - this.xSize) / 2;
    	  	 int l = (this.height - this.ySize) / 2;
	         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	         this.mc.renderEngine.bindTexture(DefaultProps.STOCK_TRADER_GUI);
	         var5 = (this.width - this.xSize) / 2;
	         var6 = (this.height - this.ySize) / 2;

	         this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
	         int var7=0;
      }
	  protected void drawGuiContainerForegroundLayer(float par1, int par2, int pa3){
		  this.fontRenderer.drawString("Coin Press", 66, 6, 4210752);
	  }

}

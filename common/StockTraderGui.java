package assets.modjam3.common;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.StatCollector;
import net.minecraft.world.storage.ISaveFormat;

public class StockTraderGui extends GuiContainer{
	int var5;
	int var6;
	int x;
	int y;
	
	GuiTextField textReason;
	StockTraderTile tile;
	String text;
	EntityPlayer player;
	Boolean a = true;
	public StockTraderGui(InventoryPlayer inventory,StockTraderTile gold) {
		super(new StockTraderContainer(gold,inventory));
		tile = gold;
		player = inventory.player;
	}
public void initGui(){
	textReason= new GuiTextField(fontRenderer, 100, 22, 50, 20);
    textReason.setFocused(true);
    textReason.setEnabled(true);
    textReason.setMaxStringLength(20);
    super.initGui();
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
             
               textReason.drawTextBox();
 }
	  public void keyTyped(char c, int i){
		  super.keyTyped(c, i);
		 
		  textReason.textboxKeyTyped(c, i);
		  
	       

	        if (i == 28 || i == 156)
	        {
	            this.actionPerformed((GuiButton)this.buttonList.get(0));
	        }
		  }
	  public void updateScreen(){
		super.updateScreen();
		  text= textReason.getText();
		  }
	  protected void actionPerformed(GuiButton par1GuiButton)
	    {
	  if (a==true){
	          
	                
	                
	                	
	                if (isInteger(text)){
	                	PacketDispatcher.sendPacketToServer(packet());
	                a=false;}
	  }
	        
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
public Packet packet(){
	Random random = new Random();
	

	ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
	DataOutputStream outputStream = new DataOutputStream(bos);
	try {
	       outputStream.writeUTF(text);
	       outputStream.writeBoolean(false);
	       outputStream.writeInt(tile.xCoord);
	       outputStream.writeInt(tile.yCoord);
	       outputStream.writeInt(tile.zCoord);
	} catch (Exception ex) {
	        ex.printStackTrace();
	}

	Packet250CustomPayload packet = new Packet250CustomPayload();
	packet.channel = "stocktrader";
	packet.data = bos.toByteArray();
	packet.length = bos.size();
	return packet;
}
}

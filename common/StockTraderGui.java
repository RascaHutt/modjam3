package assets.modjam3.common;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

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
	GuiTextField textReason;
	StockTraderTile tile;
	String text;
	EntityPlayer player;
	public StockTraderGui(InventoryPlayer inventory,StockTraderTile gold) {
		super(new StockTraderContainer(gold,inventory));
		tile = gold;
		player = inventory.player;
	}
public void initGui(){
	textReason= new GuiTextField(fontRenderer, width / 2 -30, height / 2 -70, 98, 20);
    textReason.setFocused(true);
    textReason.setEnabled(true);
    textReason.setMaxStringLength(20);
    super.initGui();
}
	  protected void drawGuiContainerForegroundLayer(int par1, int par2)
      {
               fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 0xffffff);
               this.fontRenderer.drawString("Stock Trader", 66, 6, 4210752);
              
               buttonList.add(new GuiButton(1, width / 2 + 2, height / 2 -40, 98, 20, "Submit"));
             
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
	        if (par1GuiButton.enabled)
	        {
	            if (par1GuiButton.id == 1)
	            {
	                System.out.println("test");
	                if (Integer.getInteger(text) != null)
	                	//tile.getStackInSlot(0), Integer.valueOf(text), false, player);
	                PacketDispatcher.sendPacketToServer(packet());
	            }
	            else if (par1GuiButton.id == 0)
	            {
	               
	            }
	        }
	    }
	  
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		// TODO Auto-generated method stub
		
	}
/*public void drawScreen(int i,int j,float f){
	textReason.drawTextBox();
	super.drawScreen(i, j, f);
}*/
public Packet packet(){
	Random random = new Random();
	int randomInt1 = random.nextInt();
	int randomInt2 = random.nextInt();

	ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
	DataOutputStream outputStream = new DataOutputStream(bos);
	try {
	        outputStream.writeInt(randomInt1);
	        outputStream.writeInt(randomInt2);
	} catch (Exception ex) {
	        ex.printStackTrace();
	}

	Packet250CustomPayload packet = new Packet250CustomPayload();
	packet.channel = "GenericRandom";
	packet.data = bos.toByteArray();
	packet.length = bos.size();
	return packet;
}
}

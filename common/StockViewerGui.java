package assets.modjam3.common;

import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class StockViewerGui extends GuiContainer{
	int var5;
	int var6;
	StockViewerTile tile;
	public StockViewerGui(InventoryPlayer inventory,StockViewerTile gold) {
		super(new StockViewerContainer(gold,inventory));
		tile = gold;
	}

	  protected void drawGuiContainerForegroundLayer(int par1, int par2)
      {
               fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 0xffffff);
               this.fontRenderer.drawString("Stock Viewer", 51, 6, 4210752);
      }

	@Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
  	  	 int k = (this.width - this.xSize) / 2;
  	  	 int l = (this.height - this.ySize) / 2;
	         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	         this.mc.renderEngine.bindTexture(DefaultProps.STOCK_VIEWER_GUI);
	         var5 = (this.width - this.xSize) / 2;
	         var6 = (this.height - this.ySize) / 2;

	         this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
	         int var7=0;
    }
	@Override
    protected void drawItemStackTooltip(ItemStack par1ItemStack, int par2, int par3)
    {
        List list = par1ItemStack.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);
        if (par1ItemStack.stackTagCompound!=null){
        list.add(String.valueOf(par1ItemStack.getItemDamage()));
      
        	list.add(par1ItemStack.stackTagCompound.getString("username"));
        }
        for (int k = 0; k < list.size(); ++k)
        {
            if (k == 0)
            {
                list.set(k, "\u00a7" + Integer.toHexString(par1ItemStack.getRarity().rarityColor) + (String)list.get(k));
            }
            else
            {
                list.set(k, EnumChatFormatting.GRAY + (String)list.get(k));
            }
        }

        FontRenderer font = par1ItemStack.getItem().getFontRenderer(par1ItemStack);
        drawHoveringText(list, par2, par3, (font == null ? fontRenderer : font));
    }

}

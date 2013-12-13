package assets.modjam3.common;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TileCoinPress extends TileEntity implements IInventory{
	ItemStack[] coinItemStacks = new ItemStack[3];
	
	public int activationTime = 0;
	World world;
	public int currentItemBurnTime = 0;
	public int coinpressProcessTime = 0;


public int getSizeInventory(){
	return this.coinItemStacks.length;
}

public ItemStack getStackInSlot(int par1){
	return this.coinItemStacks[par1];
}
public ItemStack decrStackSize(int par1, int par2){
	if(this.coinItemStacks[par1] !=null){
		ItemStack var3;
		
		if(this.coinItemStacks[par1].stackSize <= par2){
			var3 = this.coinItemStacks[par1];
			this.coinItemStacks[par1] = null;
			return var3;
		}else{
			var3 = this.coinItemStacks[par1].splitStack(par2);
			
			if(this.coinItemStacks[par1].stackSize == 0){
				this.coinItemStacks[par1] = null;
			}
			
			return var3;
		}
	}else
    {
        return null;
}
}

public ItemStack getStackInSlotOnClosing(int par1)
{
if (this.coinItemStacks[par1] != null)
{
        ItemStack var2 = this.coinItemStacks[par1];
        this.coinItemStacks[par1] = null;
        return var2;
}
else
{
        return null;
}
}

public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
{
this.coinItemStacks[par1] = par2ItemStack;

if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
{
        par2ItemStack.stackSize = this.getInventoryStackLimit();
}
}

public String getInvName()
{
return "container.furnace";
}
//Read NBT values
public void readFromNBT(NBTTagCompound par1NBTTagCompound)
{
super.readFromNBT(par1NBTTagCompound);
NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
this.coinItemStacks = new ItemStack[this.getSizeInventory()];

for (int var3 = 0; var3 < var2.tagCount(); ++var3)
{
        NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
        byte var5 = var4.getByte("Slot");

        if (var5 >= 0 && var5 < this.coinItemStacks.length)
        {
                this.coinItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
        }
}

this.activationTime = par1NBTTagCompound.getShort("BurnTime");
this.coinpressProcessTime = par1NBTTagCompound.getShort("CookTime");
this.currentItemBurnTime = getItemBurnTime(this.coinItemStacks[1]);
}


public int getInventoryStackLimit()
{
return 64;
}

@SideOnly(Side.CLIENT)

public int getCookProgressScaled(int par1)
{

return this.coinpressProcessTime * par1 / 200;
}


@SideOnly(Side.CLIENT)

public int getBurnTimeRemainingScaled(int par1)
{
if (this.currentItemBurnTime == 0)
{
     //   this.currentItemBurnTime = 200;
}

return this.activationTime * par1 / this.currentItemBurnTime;
}

public boolean isBurning()
{
return this.activationTime > 0;
}

public void updateEntity()
{
boolean var1 = this.activationTime > 0;
boolean var2 = false;

if (this.activationTime > 0)
{
        --this.activationTime;
}

if (!this.worldObj.isRemote)
{
        if (this.activationTime == 0 && this.canSmelt())
        {
                this.currentItemBurnTime = this.activationTime = getItemBurnTime(this.coinItemStacks[1]);

                if (this.activationTime > 0)
                {
                        var2 = true;

                        if (this.coinItemStacks[1] != null)
                        {
                                --this.coinItemStacks[1].stackSize;

                                if (this.coinItemStacks[1].stackSize == 0)
                                {
                                        this.coinItemStacks[1] = this.coinItemStacks[1].getItem().getContainerItemStack(coinItemStacks[1]);
                                }
                        }
                }
        }

        if (this.isBurning() && this.canSmelt())
        {
                ++this.coinpressProcessTime;

                if (this.coinpressProcessTime == 200)
                {
                        this.coinpressProcessTime = 0;
                        //this.smeltItem();
                        var2 = true;
                }
        }
        else
        {
                this.coinpressProcessTime = 0;
        }

        if (var1 != this.activationTime > 0)
        {
                var2 = true;
                BlockCoinPress.updateFurnaceBlockState(this.activationTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        }
}

if (var2)
{
        this.onInventoryChanged();
}
}

private boolean canSmelt()
{
if (this.coinItemStacks[0] == null)
{
        return false;
}
else
{
        ItemStack var1 =FurnaceRecipes.smelting().getSmeltingResult(this.coinItemStacks[0]);
        if (var1 == null) return false;
        if (this.coinItemStacks[2] == null) return true;
        if (!this.coinItemStacks[2].isItemEqual(var1)) return false;
        int result = coinItemStacks[2].stackSize + var1.stackSize;
        return (result <= getInventoryStackLimit() && result <= var1.getMaxStackSize());
}
}
public void activateTile(){

}

@Override
public Packet getDescriptionPacket()
{
NBTTagCompound var1 = new NBTTagCompound();
this.writeToNBT(var1);
return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 2, var1);
}

@Override
public void onDataPacket(INetworkManager netManager, Packet132TileEntityData packet)
{
readFromNBT(packet.data);
}
//Builds and Sends packet for Server -> Client Communication of gas level.
public void packetSend(){
if (this.worldObj.isRemote==false){
// Player player = this.
//PacketDispatcher.sendPacketToServer(this.getDescriptionPacket());




this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
DataOutputStream outputStream = new DataOutputStream(bos);
try {
 	
     outputStream.writeInt(this.xCoord);
     outputStream.writeInt(this.yCoord);
     outputStream.writeInt(this.zCoord);
  
} catch (Exception ex) {
     ex.printStackTrace();
}

Packet250CustomPayload packet = new Packet250CustomPayload();
packet.channel = "CatalyticDecomp";
packet.data = bos.toByteArray();
packet.length = bos.size();
PacketDispatcher.sendPacketToAllPlayers(packet);
}else{



}



}
public static int getItemBurnTime(ItemStack par0ItemStack)
{
if (par0ItemStack == null)
{
        return 0;
}
else
{
        int var1 = par0ItemStack.getItem().itemID;
        Item var2 = par0ItemStack.getItem();

        if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[var1] != null)
        {
                Block var3 = Block.blocksList[var1];

                if (var3 == Block.woodSingleSlab)
                {
                        return 150;
                }

                if (var3.blockMaterial == Material.wood)
                {
                        return 300;
                }
        }

        if (var2 instanceof ItemTool && ((ItemTool) var2).getToolMaterialName().equals("WOOD")) return 200;
        if (var2 instanceof ItemSword && ((ItemSword) var2).getToolMaterialName().equals("WOOD")) return 200;
        if (var2 instanceof ItemHoe && ((ItemHoe) var2).getMaterialName().equals("WOOD")) return 200;
        if (var1 == Item.stick.itemID) return 100;
        if (var1 == Item.coal.itemID) return 1600;
        if (var1 == Item.bucketLava.itemID) return 20000;
        if (var1 == Block.sapling.blockID) return 100;
        if (var1 == Item.blazeRod.itemID) return 2400;
	 	 if(var1 == Item.blazeRod.itemID) return 2400;
        return GameRegistry.getFuelValue(par0ItemStack);
}
}

public static boolean isItemFuel(ItemStack par0ItemStack)
{
return getItemBurnTime(par0ItemStack) > 0;
}

public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
{
return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
}

public void openChest() {}

public void closeChest() {}

public int getSizeInventorySide(ForgeDirection side)
{
return 1;
}

public boolean func_102007_a(int i, ItemStack itemstack, int j) {
// TODO Auto-generated method stub
return false;
}

public boolean func_102008_b(int i, ItemStack itemstack, int j) {
// TODO Auto-generated method stub
return false;
}

public boolean isInvNameLocalized() {
// TODO Auto-generated method stub
return false;
}

public boolean isStackValidForSlot(int i, ItemStack itemstack) {
// TODO Auto-generated method stub
return false;
}
@Override
public boolean isItemValidForSlot(int i, ItemStack itemstack) {
// TODO Auto-generated method stub
return false;
}
}


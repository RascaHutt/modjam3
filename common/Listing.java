package assets.modjam3.common;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class Listing {
	public ItemStack items;
	public int price=0;
	public boolean buy = true;
	public String username;
	public void readFromNBT(NBTTagCompound nbttagcompound) {
        // TODO Auto-generated method stub
       
      
	 
		NBTTagCompound tagList =(NBTTagCompound) nbttagcompound.getTag("Inventory");
              
              
               byte slot = tagList.getByte("Slot");
              
               if(slot >= 0)
                      items = ItemStack.loadItemStackFromNBT(tagList);
               
       
       
       price = nbttagcompound.getInteger("price");
        buy = nbttagcompound.getBoolean("buy");
        username = nbttagcompound.getString("username");
               }

public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
	if(items != null){
        NBTTagCompound tag = new NBTTagCompound();
       
        tag.setByte("Slot", (byte) 1);
        items.writeToNBT(tag);
        nbttagcompound.setTag("Inventory", tag);
	}
    nbttagcompound.setInteger("price", price);
    nbttagcompound.setBoolean("buy", buy);
    nbttagcompound.setString("username", username);

    return nbttagcompound;

}


}

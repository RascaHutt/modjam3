package assets.modjam3.common;
import net.minecraft.world.World;
public class StockExchange 
{
	public StockExchange(World worldObj) {
		
		 if(worldObj.villageCollectionObj.getVillageList().iterator().hasNext()){
			System.out.println(worldObj.villageCollectionObj.findNearestVillage(0, 0, 0, 1000).getCenter().posX);
			System.out.println(worldObj.villageCollectionObj.findNearestVillage(0, 0, 0, 1000).getCenter().posY);
			System.out.println(worldObj.villageCollectionObj.findNearestVillage(0, 0, 0, 1000).getCenter().posZ);
		 }
	}

}

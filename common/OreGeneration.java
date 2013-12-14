package assets.modjam3.common;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class OreGeneration implements IWorldGenerator{
	
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider){
		
		switch(world.provider.dimensionId){
		
		case -1: generateNether(world, rand, chunkX*16, chunkZ*16);
		case 0: generateSurface(world, rand, chunkX*16, chunkZ*16);
		case +1: generateEnd(world, rand, chunkX*16, chunkZ*16);
		}
	}
	
	private void generateNether(World world, Random rand, int x, int z){
		
	
	}
	
	private void generateSurface(World world, Random rand, int x, int z){
		for(int k = 0; k<25; k++){
			int chunkX = x + rand.nextInt(16);
			int chunkY = rand.nextInt(30); //Level 
			int chunkZ = z + rand.nextInt(16);
			
			//Number = amount in vein
			(new WorldGenMinable(FinancialExpansion.blocknickelOre.blockID, 4)).generate(world, rand, chunkX, chunkY, chunkZ);
		}
	}
	
	private void generateEnd(World world, Random rand, int x, int z){
		
	}
	
}

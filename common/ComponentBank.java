package assets.modjam3.common;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentVillage;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentBank  extends ComponentVillage{
	  private int averageGroundLevel = -1;
	public ComponentBank(){
		
	}
	 public ComponentBank(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5)
	    {
	        super(par1ComponentVillageStartPiece, par2);
	        this.coordBaseMode = par5;
	        this.boundingBox = par4StructureBoundingBox;
	    }
	@Override
	public boolean addComponentParts(World world, Random random,
			StructureBoundingBox structureboundingbox) {
		// TODO Auto-generated method stub
		  if (this.averageGroundLevel < 0)
	        {
	            this.averageGroundLevel = this.getAverageGroundLevel(world, structureboundingbox);

	            if (this.averageGroundLevel < 0)
	            {
	                return true;
	            }

	            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 2, 0);
	        }

	        /**
	         * arguments: (World worldObj, StructureBoundingBox structBB, int minX, int minY, int minZ, int maxX, int maxY, int
	         * maxZ, int placeBlockId, int replaceBlockId, boolean alwaysreplace)
	         */

	        this.fillWithBlocks(world, structureboundingbox, 1, 0, 0, 7, 0, 6, Block.oreDiamond.blockID, Block.oreDiamond.blockID, false); //Base
	        this.fillWithBlocks(world, structureboundingbox, 0, 0, 1, 0, 0, 5, Block.oreDiamond.blockID, Block.oreDiamond.blockID, false);
	        this.fillWithBlocks(world, structureboundingbox, 8, 0, 1, 8, 0, 5, Block.oreDiamond.blockID, Block.oreDiamond.blockID, false);
	        this.fillWithBlocks(world, structureboundingbox, 0, 1, 0, 9, 3, 7, 0, 0, false);

	       // this.fillWithMetaBlocks(world, structureboundingbox, 2, 0, 1, 6, 2, 5, TRepo.smeltery.blockID, 2, TRepo.smeltery.blockID, 2, false); //Basin
	        this.fillWithBlocks(world, structureboundingbox, 3, 1, 2, 5, 2, 4, 0, 0, false);

	        this.placeBlockAtCurrentPosition(world, Block.oreDiamond.blockID, 0, 1, 1, 2, structureboundingbox);
	        this.placeBlockAtCurrentPosition(world, Block.oreDiamond.blockID, 2, 1, 1, 4, structureboundingbox);
	        this.placeBlockAtCurrentPosition(world, Block.oreDiamond.blockID, 0, 7, 1, 2, structureboundingbox);
	        this.placeBlockAtCurrentPosition(world, Block.oreDiamond.blockID, 2, 7, 1, 4, structureboundingbox);

	        for (int l = 1; l < 6; ++l)
	        {
	            for (int i1 = 0; i1 < 9; ++i1)
	            {
	                this.clearCurrentPositionBlocksUpwards(world, i1, 9, l, structureboundingbox);
	                this.fillCurrentPositionBlocksDownwards(world, Block.oreDiamond.blockID, 0, i1, -1, l, structureboundingbox);
	            }
	        }

	        for (int l = 0; l < 7; ++l)
	        {
	            for (int i1 = 1; i1 < 8; ++i1)
	            {
	                this.clearCurrentPositionBlocksUpwards(world, i1, 9, l, structureboundingbox);
	                this.fillCurrentPositionBlocksDownwards(world, Block.oreDiamond.blockID, 0, i1, -1, l, structureboundingbox);
	            }
	        }
	        return true;
	}
    public static ComponentBank buildComponent (ComponentVillageStartPiece villagePiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, 9, 3, 7, p4);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(pieces, structureboundingbox) == null ? new ComponentBank(villagePiece, p5, random,
                structureboundingbox, p4) : null;
    }
    int remapDirection (int direction)
    {
        //TConstruct.logger.info("Direction: " + direction);
        switch (direction)
        {
        case 0:
            return 2;
        case 1:
            return 3;
        case 2:
            return 1;
        case 3:
            return 0;
        }
        //TConstruct.logger.severe("This shouldn't happen (remapDirection in tconstruct.worldgen.village.ComponentSmeltery)");
        return -1;
    }
}

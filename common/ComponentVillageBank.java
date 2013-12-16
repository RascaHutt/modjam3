package assets.modjam3.common;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentVillage;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentVillageBank extends ComponentVillage {
	  private int averageGroundLevel = -1;
	  private static final int HEIGHT = 2;

	  public ComponentVillageBank(ComponentVillageStartPiece par1ComponentVillageStartPiece, int componentType, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5) {
	        super(par1ComponentVillageStartPiece, componentType);
	        this.coordBaseMode = par5;
	        this.boundingBox = par4StructureBoundingBox;
	 }

	 public static ComponentVillageBank buildComponent(ComponentVillageStartPiece startPiece, List par1List, Random random, int par3, int par4, int par5, int par6, int par7) {
	  StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 12, HEIGHT, 10, par6);
	  return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(par1List, var8) == null ? new ComponentVillageBank(startPiece, par7, random, var8, par6) : null;
	 }

	// this method create house
	 public boolean addComponentParts(World world, Random par2Random, StructureBoundingBox sbb) {
	 if (this.averageGroundLevel < 0) {
	  this.averageGroundLevel = this.getAverageGroundLevel(world, sbb);
	   if (this.averageGroundLevel < 0) {
	        return true;
	   }

	   this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + HEIGHT - 1, 0);
	  }

	// place generation house code here

	 this.fillWithBlocks(world, sbb, 1, 0, 0, 7, 0, 6, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false); //Base
     this.fillWithBlocks(world, sbb, 0, 0, 1, 0, 0, 5, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
     this.fillWithBlocks(world, sbb, 8, 0, 1, 8, 0, 5, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
     this.fillWithBlocks(world, sbb, 0, 1, 0, 9, 3, 7, 0, 0, false);
     return true;
	 }
	 

	// add this method if villagers will be spawn in your building
	 protected int getVillagerType(int par1) {
	  return 100;
	 }
	}

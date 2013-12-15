package assets.modjam3.common;

import java.util.List;
import java.util.Random;

import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureVillagePieceWeight;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class VillageBankHandler implements IVillageCreationHandler {

	@Override
	public StructureVillagePieceWeight getVillagePieceWeight(Random random,
			int i) {
		// TODO Auto-generated method stub
		 return new StructureVillagePieceWeight(ComponentBank.class, 9, i + random.nextInt(10) == 0 ? 1 : 0);
	}

	@Override
	public Class<?> getComponentClass() {
		// TODO Auto-generated method stub
		return ComponentBank.class;
	}

	@Override
	public Object buildComponent(StructureVillagePieceWeight villagePiece,
			ComponentVillageStartPiece startPiece, List pieces, Random random,
			int p1, int p2, int p3, int p4, int p5) {
		// TODO Auto-generated method stub
		  return ComponentBank.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
	}

}

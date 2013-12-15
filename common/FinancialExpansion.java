package assets.modjam3.common;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import assets.modjam3.common.*;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels={"stocktrader"}, packetHandler = PacketHandler.class)
public class FinancialExpansion {
	@Instance(Reference.MOD_ID)
	public static FinancialExpansion instance;
	 private GuiHandler guiHandler = new GuiHandler();
	int nickelOreID;
	
	int coinPressID;
	int stockViewerID;
	int nickelBlockID;
	int nickelIngotID;
	int stockTraderID;
	int nickelCoinID;
	int bankCardID;
	public MarketManager market;
	
	
	public static Block blocknickelOre;
	public static Block blockcoinPress;
	public static Block blockstockViewer;

	public static Block blocknickelBlock;

	public static Block blockstockTrader;

	public static Item itemnickelIngot;
	public static Item itemnickelCoin;
	public static Item itembankCard;
	
	OreGeneration oregeneration = new OreGeneration();
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event){
		
		ModMetadata data = event.getModMetadata();
		data.name = Reference.MOD_NAME;
		data.version = Reference.MOD_VERSION;
		data.authorList = Arrays.asList(new String[] {"Scorp"});
		data.description = Reference.MOD_DESCRIPTION;
		data.autogenerated = false;
		
		//Config
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		//Block
		coinPressID = config.get("Machine IDs", "Coin Press ID", 700).getInt();
		stockViewerID =config.get("Machine IDs", "StockViewer  ID", 701).getInt();
		nickelOreID = config.get("Ore IDs", "Nickel Ore ID", 800).getInt();

		nickelBlockID=config.get("Other IDs", "Nickel Block ID", 801).getInt();
		

		stockTraderID = config.get("Machine IDs", "StockTrader  ID", 702).getInt();

		//Ingot
		nickelIngotID = config.get("Item IDs", "Nickel Ingot ID", 1000).getInt();
		//item
	
		nickelCoinID = config.get("Item IDs", "Nickel Coin ID", 1001).getInt();
		bankCardID =config.get("Item IDs", "Bank Card ID", 1002).getInt();
		
	}
	
	@Init
	public void load(FMLInitializationEvent event){
		
		
		VillagerRegistry.instance().registerVillagerId(100);
		VillagerRegistry.instance().registerVillagerSkin(100, DefaultProps.BANKER_SKIN);
		BankerTradeHandler bankerTradeHandler = new BankerTradeHandler();
		VillagerRegistry.instance().registerVillageTradeHandler(100, bankerTradeHandler);
		
		  VillagerRegistry.instance().registerVillageCreationHandler(new VillageBankHandler());
          try
          {
              // if (new CallableMinecraftVersion(null).minecraftVersion().equals("1.6.4"))
              // {
             // MapGenStructureIO.func_143031_a(ComponentBank.class, "assets:ToolWorkshopStructure");
              
              // }
          }
          catch (Throwable e)
          {
              System.out.println("Error registering TConstruct Structures with Vanilla Minecraft: this is expected in versions earlier than 1.6.4");
          }
		//Block
		blockcoinPress = new BlockCoinPress(coinPressID);
		registerBlock(blockcoinPress,"Coin Press", blockcoinPress.getUnlocalizedName());
		blockstockViewer = new BlockStockViewer(stockViewerID);
		registerBlock(blockstockViewer,"Stock Viewer", blockstockViewer.getUnlocalizedName());
		
		blocknickelBlock = new BlockNickelBlock(nickelBlockID);
		registerBlock(blocknickelBlock, "Nickel Block", blocknickelBlock.getUnlocalizedName());
		//Tiles
		blockstockTrader = new BlockStockTrader(stockTraderID);
		registerBlock(blockstockTrader,"Stock Trader", blockstockTrader.getUnlocalizedName());
	//Ore
		blocknickelOre = new BlockNickelOre(nickelOreID);
		registerBlock(blocknickelOre, "Nickel Ore", blocknickelOre.getUnlocalizedName());
		
		
	//Ingot
		itemnickelIngot = new ItemNickelIngot(nickelIngotID);
		registerItem(itemnickelIngot, "Nickel Ingot", itemnickelIngot.getUnlocalizedName());
		
	//Other
		itemnickelCoin = new ItemNickelCoin(nickelCoinID);
		registerItem(itemnickelCoin, "Nickel Coin", itemnickelCoin.getUnlocalizedName());
		itembankCard = new ItemBankCard(bankCardID);
		registerItem(itembankCard, "Bank Card", itembankCard.getUnlocalizedName());
		
		GameRegistry.addSmelting(nickelOreID, new ItemStack(itemnickelIngot, 1), 1F);
		GameRegistry.registerWorldGenerator(oregeneration);
		 GameRegistry.registerTileEntity(CoinPressTile.class, "CoinPressTile");
		 GameRegistry.registerTileEntity(StockViewerTile.class, "StockViewerTile");
		 GameRegistry.registerTileEntity(StockTraderTile.class, "StockTraderTile");
		networkRegisters();
		market = new MarketManager();
		GameRegistry.addRecipe(new ItemStack(FinancialExpansion.blocknickelBlock, 1),
				new Object[]{
			"nnn",
			"nnn",
			"nnn",
			'n', FinancialExpansion.itemnickelIngot
		});
		GameRegistry.addRecipe(new ItemStack(FinancialExpansion.blockcoinPress, 1),
				new Object[]{
			"ibi",
			"idi",
			"ibi",
			'i', Item.ingotIron,
			'b', FinancialExpansion.blocknickelBlock, 
			'd', Item.diamond,
		});
	}
	
	public static void registerBlock(Block block, String name, String unlocalizedName){
		GameRegistry.registerBlock(block, Reference.MOD_ID + unlocalizedName);
		LanguageRegistry.addName(block, name);
	}
	
	public static void registerItem(Item item, String name, String unlocalizedName){
		GameRegistry.registerItem(item, Reference.MOD_ID + unlocalizedName);
		LanguageRegistry.addName(item, name);
		
	}
	 public void networkRegisters(){
         NetworkRegistry.instance().registerGuiHandler(instance, guiHandler);
 }
}


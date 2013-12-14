package assets.modjam3.common;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class MarketManager {
	public static String readFile(String filename) throws IOException {
	    File file = new File(filename);
	    int len = (int) file.length();
	    byte[] bytes = new byte[len];
	    FileInputStream fis = null;
	    try {
	        fis = new FileInputStream(file);
	        assert len == fis.read(bytes);
	    } catch (IOException e) {
	        close(fis);
	        throw e;
	    }
	    return new String(bytes, "UTF-8");
	}
	public static void close(Closeable closeable) {
	    try {
	        closeable.close();
	    } catch(IOException ignored) {
	    }
	}
	public static void writeFile(String filename, String text) throws IOException {
	    FileOutputStream fos = null;
	    try {
	        fos = new FileOutputStream(filename);
	        fos.write(text.getBytes("UTF-8"));
	    } catch (IOException e) {
	        close(fos);
	        throw e;
	    }
	}
	public static void addLine(String filename, String text) throws IOException{
		Writer output;
		output = new BufferedWriter(new FileWriter(filename));
		output.append(text);
		output.close();
	}
	public MarketManager(){
		File f = new File("market_listings.txt");
		if(f.exists()) { /* do something */
			
		}else{
		try {
			writeFile("market_listings.txt","test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		try {
			addLine("market_listings.txt","test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
				
	}
	public void listTrade(ItemStack offer,int price,int buy,EntityPlayer player){
		String line = offer.itemID+"/i"+offer.stackSize+"/s"+price+"/p"+buy+"/b"+player.username+"/n";
		try {
			addLine("market_listings.txt",line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

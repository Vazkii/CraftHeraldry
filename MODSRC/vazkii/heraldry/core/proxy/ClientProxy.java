package vazkii.heraldry.core.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import vazkii.heraldry.CraftHeraldry;
import vazkii.heraldry.client.render.BannerItemRender;
import vazkii.heraldry.client.render.RenderTileBanner;
import vazkii.heraldry.content.TileEntityBanner;
import vazkii.heraldry.lib.LibResources;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	public static List<String> iconNames = new ArrayList();

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		// ICON PARSING SCRIPT =========================================================================
		//		try {
		//			File configDir = event.getModConfigurationDirectory();
		//			File parseDir = new File(configDir, "parse");
		//
		//			File output = new File(configDir, "output.png");
		//			output.createNewFile();
		//
		//			File names = new File(configDir, "names.txt");
		//			names.createNewFile();
		//			BufferedWriter writer = new BufferedWriter(new FileWriter(names));
		//
		//			BufferedImage image = new BufferedImage(2048, 4096, BufferedImage.TYPE_INT_RGB);
		//
		//			ImageObserver observer = new ImageObserver() { @Override public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) { return false; } };
		//
		//			File[] icons = parseDir.listFiles();
		//			Arrays.sort(icons);
		//			int x = 0;
		//			int y = 0;
		//
		//			for(File icon : icons) {
		//				BufferedImage iconImage = ImageIO.read(icon);
		//				image.getGraphics().drawImage(iconImage, x * 64, y * 64, 64, 64, observer);
		//				x++;
		//				if(x == 32) {
		//					x = 0;
		//					y++;
		//				}
		//
		//				writer.write(WordUtils.capitalizeFully(icon.getName().replaceAll("\\.png", "").replaceAll("-", " ")));
		//				writer.write("\n");
		//			}
		//
		//			writer.close();
		//			ImageIO.write(image, "png", output);
		//		} catch(IOException e) {
		//			e.printStackTrace();
		//		}
		// ===========================================================================================================
	}

	@Override
	public void init() {
		super.init();

		readIconNames();
	}

	private void readIconNames() {
		try {
			InputStream stream = CraftHeraldry.class.getResourceAsStream(LibResources.ICON_NAMES);
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

			String line;
			while((line = reader.readLine()) != null)
				iconNames.add(line);

			reader.close();
		} catch (IOException e) {
			throw new RuntimeException("(CraftHeraldy) Failed to load icon names!", e);
		}
	}

	@Override
	void initContent() {
		super.initContent();

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBanner.class, new RenderTileBanner());
		MinecraftForgeClient.registerItemRenderer(itemHeraldry, new BannerItemRender());
	}
}

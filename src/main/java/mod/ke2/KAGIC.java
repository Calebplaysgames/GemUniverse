package mod.ke2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import com.google.gson.Gson;

import mod.ke2.init.Ke2Blocks;
import mod.ke2.init.Ke2Configs;
import mod.ke2.init.Ke2Cruxes;
import mod.ke2.init.Ke2Enchants;
import mod.ke2.init.Ke2Entities;
import mod.ke2.init.Ke2Handles;
import mod.ke2.init.Ke2Items;
import mod.ke2.init.Ke2Recipes;
import mod.ke2.init.Ke2Sounds;
import mod.ke2.init.Ke2TileEntities;
import mod.ke2.init.Ke2Variants;
import mod.ke2.init.Ke2WorldGen;
import mod.ke2.proxies.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.util.ScreenShotHelper;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

@Mod(modid = KAGIC.MODID, version = KAGIC.VERSION, acceptedMinecraftVersions = KAGIC.MCVERSION)
public class KAGIC {
    public static final String VERSION = "@version";
    public static final String MCVERSION = "1.12.2";
	public static final String MODID = "ke2";

	public static final KeyBinding KEY_BUG_REPORT = new KeyBinding("key.bug.report", Keyboard.KEY_GRAVE, "key.ke2.category");
	public static final String EYEBALL_ENDPOINT = "https://akriv.us/report";
	
	public static final Logger LOGGER = LogManager.getLogger(KAGIC.MODID);
	public static final Gson JSON = new Gson();
	
	@SidedProxy(clientSide = "mod.ke2.proxies.ClientProxy", serverSide = "mod.ke2.proxies.ServerProxy")
	public static CommonProxy proxy;
	
    @Instance
    public static KAGIC instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	Runtime.getRuntime().addShutdownHook(new Thread() {
    		@Override
    		public void run() {
    			String report = "crash-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date());
    			String[] sides = new String[] {"client", "server"};
    			for (int i = 0; i < sides.length; ++i) {
    				String crash = "crash-reports/" + report + "-" + sides[i] + ".txt";
	    			if (new File(crash).exists()) {
	    				try {
	    					String crashReport = new String(Files.readAllBytes(Paths.get("./" + crash)), StandardCharsets.UTF_8);
	    					if (crashReport.contains("at mod.ke2")) {
	    						KAGIC.submitReport("Minecraft automatically", "a " + sides[i] + "-side crash occurred involving KAGIC.", crash, "logs/latest.log");
	    						return;
	    					}
	    				} catch (Exception e) {
	    					return;
	    				}
	    			}
    			}
    		}
    	});
    	Ke2WorldGen.register();
    	Ke2Recipes.register();
    	Ke2Entities.register(0);
    	proxy.preInit(event);
    }
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	Ke2Handles.register();
    	Ke2TileEntities.register();
    	Ke2Entities.register(1);
    	Ke2Variants.register();
    	proxy.init(event);
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	Ke2Configs.register();
    	Ke2Cruxes.register();
    	proxy.postInit(event);
    }
    public static boolean isClient() {
    	return proxy.isClient();
    }
    public static boolean isServer() {
    	return proxy.isServer();
    }
    public static String submitReport(String name, String description, String... files) {
    	try {
	    	FileOutputStream f = new FileOutputStream("./report.zip"); ZipOutputStream zip = new ZipOutputStream(f);
			for (int i = 0; i < files.length; ++i) {
				zip.putNextEntry(new ZipEntry(files[i]));
				FileInputStream input = new FileInputStream("./" + files[i]);
	            byte[] buffer = new byte[1024]; int offset;
	            while ((offset = input.read(buffer)) > 0) {
	                zip.write(buffer, 0, offset);
	            }
	            zip.closeEntry();
	            input.close();
			}
	        zip.close();
	        f.close();
			CloseableHttpClient client = HttpClients.createDefault();
		    HttpPost post = new HttpPost(KAGIC.EYEBALL_ENDPOINT + "?name=" + URLEncoder.encode(name, "UTF-8") + "&desc=" + URLEncoder.encode(description, "UTF-8"));
		    post.setEntity(new FileEntity(new File("./report.zip"), ContentType.DEFAULT_BINARY));
		    CloseableHttpResponse response = client.execute(post);
		    String body = EntityUtils.toString(response.getEntity(), "UTF-8");
		    client.close();
		    return body;
    	} catch (Exception e) {
    		return e.getMessage();
		}
    }
    @Mod.EventBusSubscriber(modid = KAGIC.MODID)
	public static class RegistrationHandler {
    	@SubscribeEvent
		public static void registerEnchants(RegistryEvent.Register<Enchantment> event) {
			Ke2Enchants.register(event);
		}
    	@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			Ke2Items.register(event);
		}
    	@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			Ke2Blocks.register(event);
		}
		@SubscribeEvent
		public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
			Ke2Sounds.register(event);
		}
		@SubscribeEvent
		public static void useKeyBindings(KeyInputEvent event) {
			if (KAGIC.isClient() && KAGIC.KEY_BUG_REPORT.isPressed()) {
				EntityPlayerSP sender = Minecraft.getMinecraft().player;
				try {
					ScreenShotHelper.saveScreenshot(Minecraft.getMinecraft().mcDataDir, "latest.png", Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().getFramebuffer());
					sender.sendMessage(new TextComponentString(KAGIC.submitReport(sender.getName() + " automatically", "pressed bug report button.", "screenshots/latest.png", "logs/latest.log")));
				} catch (Exception e) {
					sender.sendMessage(new TextComponentString("Command failed; " + e.getMessage()));
				}
			}
		}
	}
}
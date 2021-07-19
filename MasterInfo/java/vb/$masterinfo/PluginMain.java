package vb.$masterinfo;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.event.*;
import org.bukkit.plugin.java.*;

public class PluginMain extends JavaPlugin implements Listener {

	private static PluginMain instance;

	@Override
	public void onEnable() {
		instance = this;
		getDataFolder().mkdir();
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(GUIManager.getInstance(), this);
		try {
			PluginMain.getInstance().getLogger()
					.info(String.valueOf(PluginMain.getInstance().getConfig().get("Lenguaje.OnEnable.Line1")));
			PluginMain.getInstance().getLogger()
					.info(String.valueOf(PluginMain.getInstance().getConfig().get("Lenguaje.OnEnable.Line2")));
			PluginMain.getInstance().getLogger()
					.info(String.valueOf(PluginMain.getInstance().getConfig().get("Lenguaje.OnEnable.Line3")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		GUIManager.getInstance().register("info", guiPlayer -> {
			try {
				org.bukkit.inventory.Inventory guiInventory = Bukkit.createInventory(new GUIIdentifier("info"),
						((int) 45d), "Informaci\u00F3n de MasterHCF");
				guiInventory.setItem(((Number) PluginMain.getInstance().getConfig().get("Item1.Slot")).intValue(),
						PluginMain.getNamedItem(
								((org.bukkit.Material) (Object) PluginMain.getInstance().getConfig()
										.get("Item1.Material")),
								String.valueOf(PluginMain.getInstance().getConfig().get("Item1.Nombre"))));
				guiInventory.setItem(((Number) PluginMain.getInstance().getConfig().get("Item2.Slot")).intValue(),
						PluginMain.getNamedItem(
								((org.bukkit.Material) (Object) PluginMain.getInstance().getConfig()
										.get("Item2.Material")),
								String.valueOf(PluginMain.getInstance().getConfig().get("Item2.Nombre"))));
				guiInventory.setItem(((Number) PluginMain.getInstance().getConfig().get("Item3.Slot")).intValue(),
						PluginMain.getNamedItem(
								((org.bukkit.Material) (Object) PluginMain.getInstance().getConfig()
										.get("Item3.Material")),
								String.valueOf(PluginMain.getInstance().getConfig().get("Item3.Nombre"))));
				guiInventory.setItem(((int) 45d),
						((org.bukkit.inventory.ItemStack) (Object) org.bukkit.Material.RED_WOOL));
				return guiInventory;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		});
	}

	@Override
	public void onDisable() {
		try {
			PluginMain.getInstance().getLogger()
					.info(String.valueOf(PluginMain.getInstance().getConfig().get("Lenguaje.OnDisable.Line1")));
			PluginMain.getInstance().getLogger()
					.info(String.valueOf(PluginMain.getInstance().getConfig().get("Lenguaje.OnDisable.Line2")));
			PluginMain.getInstance().getLogger()
					.info(String.valueOf(PluginMain.getInstance().getConfig().get("Lenguaje.OnDisable.Line3")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] commandArgs) {
		if (command.getName().equalsIgnoreCase("MasterInfo")) {
			try {
				org.bukkit.Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
						"&7<--------------------------------------------------------->"));
				org.bukkit.Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
						"&bMasterInfo 1.3&2 Developed By: &dMasterrecording"));
				org.bukkit.Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
						"&7<--------------------------------------------------------->"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		if (command.getName().equalsIgnoreCase("info")) {
			try {
				if ((commandSender instanceof org.bukkit.entity.Player)) {
					GUIManager.getInstance().open("info", ((org.bukkit.entity.Player) (Object) commandSender));
				} else {
					org.bukkit.Bukkit.broadcastMessage(
							String.valueOf(PluginMain.getInstance().getConfig().get("Lenguaje.Consola")));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return true;
	}

	public static void procedure(String procedure, List procedureArgs) throws Exception {
	}

	public static Object function(String function, List functionArgs) throws Exception {
		return null;
	}

	public static List createList(Object obj) {
		List list = new ArrayList<>();
		if (obj.getClass().isArray()) {
			int length = java.lang.reflect.Array.getLength(obj);
			for (int i = 0; i < length; i++) {
				list.add(java.lang.reflect.Array.get(obj, i));
			}
		} else if (obj instanceof Collection<?>) {
			list.addAll((Collection<?>) obj);
		} else if (obj instanceof Iterator) {
			((Iterator<?>) obj).forEachRemaining(list::add);
		} else {
			list.add(obj);
		}
		return list;
	}

	public static void createResourceFile(String path) {
		Path file = getInstance().getDataFolder().toPath().resolve(path);
		if (Files.notExists(file)) {
			try (InputStream inputStream = PluginMain.class.getResourceAsStream("/" + path)) {
				Files.createDirectories(file.getParent());
				Files.copy(inputStream, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static PluginMain getInstance() {
		return instance;
	}

	@EventHandler
	public void onGUIClick(GUIClickEvent event) throws Exception {
		if (event.getID().equalsIgnoreCase("info")) {
			if (PluginMain.checkEquals(event.getCurrentItem(),
					PluginMain.getInstance().getConfig().get("Item1.Material"))) {
				org.bukkit.Bukkit.broadcastMessage(String.valueOf(PluginMain.getInstance().getConfig()
						.get(String.valueOf(PluginMain.getInstance().getConfig().get("Item1.Respuesta")))));
			} else {
				if (PluginMain.checkEquals(event.getCurrentItem(),
						PluginMain.getInstance().getConfig().get("Item2.Material"))) {
					org.bukkit.Bukkit.broadcastMessage(
							String.valueOf(PluginMain.getInstance().getConfig().get("Item2.Respuesta")));
				} else {
					if (PluginMain.checkEquals(event.getCurrentItem(),
							PluginMain.getInstance().getConfig().get("Item3.Material"))) {
						org.bukkit.Bukkit.broadcastMessage(
								String.valueOf(PluginMain.getInstance().getConfig().get("Item3.Respuesta")));
					} else {
						if (PluginMain.checkEquals(event.getCurrentItem(), org.bukkit.Material.RED_WOOL)) {
							event.getView().close();
						} else {
							org.bukkit.Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
									"&1&l[&r&bMasterInfo&r&1&l] &r&bDa click en alg\u00FAn \u00EDtem!"));
						}
					}
				}
			}
			return;
		}
	}

	public static org.bukkit.inventory.ItemStack getNamedItem(Material material, String name) {
		org.bukkit.inventory.ItemStack item = new org.bukkit.inventory.ItemStack(material);
		org.bukkit.inventory.meta.ItemMeta itemMeta = item.getItemMeta();
		if (itemMeta != null) {
			itemMeta.setDisplayName(name);
			item.setItemMeta(itemMeta);
		}
		return item;
	}

	public static boolean checkEquals(Object o1, Object o2) {
		if (o1 == null || o2 == null) {
			return false;
		}
		return o1 instanceof Number && o2 instanceof Number
				? ((Number) o1).doubleValue() == ((Number) o2).doubleValue()
				: o1.equals(o2);
	}
}

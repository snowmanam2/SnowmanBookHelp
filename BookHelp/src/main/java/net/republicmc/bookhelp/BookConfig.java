package net.republicmc.bookhelp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class BookConfig extends Config {

	private static BookConfig i = new BookConfig();
	public static BookConfig get() { return i; }
	
	private Map<String, ItemStack> books;
	
	private BookConfig() {
		super("books.yml");
		this.books = new HashMap<String, ItemStack>();
	}
	
	@Override
	public void load(JavaPlugin plugin) {
		super.load(plugin);
		
		loadBooks();
	}
	
	private void loadBooks() {
		if (!getConfig().contains("books")) {
			getConfig().createSection("books");
		}
		ConfigurationSection section = getConfig().getConfigurationSection("books");
		
		for (String key : section.getKeys(false)) {
			Object value = section.get(key);
			
			if (!(value instanceof ItemStack)) {
				getPlugin().getLogger().warning("Book '"+key+"' is not an ItemStack.");
				continue;
			}
			
			ItemStack item = (ItemStack) value;
			
			if (!item.getType().equals(Material.WRITTEN_BOOK)) {
				getPlugin().getLogger().warning("Book '"+key+"' is not a Signed Book.");
				continue;
			}
			
			books.put(key, item);
		}
	}
	
	public ItemStack getBook(String key) {
		return books.get(key);
	}
	
	public Set<String> getBookKeys() {
		return books.keySet();
	}
	
	public void setBook(String key, ItemStack item) {
		books.put(key, item);
		
		getConfig().getConfigurationSection("books").set(key, item);
		
		saveConfig();
	}

}

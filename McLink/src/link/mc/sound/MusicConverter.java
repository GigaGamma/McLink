package link.mc.sound;

import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

public class MusicConverter {
	
	public MusicConverter() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			//p.playSound(p.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 3.0F, 0.5F);
			//p.playSound(p.getLocation(), "", SoundCategory.VOICE, 3, 1);
		}
	}
	
}

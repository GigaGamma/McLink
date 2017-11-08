package link.mc.currency;

import java.text.DecimalFormat;

import org.bukkit.entity.Player;

import link.mc.util.PlayerUtil;

public class CurrencyData {
	
	private static final DecimalFormat FORMATTER = new DecimalFormat("#,###.00");
	
	private Player player;
	private long money;
	
	public static CurrencyData getCurrencyData(Player player) {
		CurrencyData c = new CurrencyData();
		c.setPlayer(player);
		c.setMoney(PlayerUtil.getMoney(player.getUniqueId().toString()));
		return c;
	}
	
	public long getDebt() {
		return money < 0 ? Math.abs(money) : 0;
	}
	
	public boolean isInDebt() {
		return getDebt() != 0;
	}
	
	public String getFormatted() {
		return FORMATTER.format(money);
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}
	
}

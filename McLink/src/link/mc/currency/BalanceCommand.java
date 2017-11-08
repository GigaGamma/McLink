package link.mc.currency;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import link.mc.util.CommandConstruct;
import link.mc.util.PlayerUtil;

public class BalanceCommand extends Command {
	
	private static final String[] PAY = new String[] {"pay", "player", "number"};
	
	public static BalanceCommand create() {
		ArrayList<String> a = new ArrayList<String>();
		
		a.add("bal");
		a.add("money");
		a.add("$");
		
		return new BalanceCommand(a);
	}
	
	private BalanceCommand(ArrayList<String> a) {
		super("balance", "Your money", "/balance", a);
	}
	
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (!(sender instanceof Player))
			return true;
		Player player = (Player) sender;
		if (args.length == 0)
			sender.sendMessage("Balance: " + CurrencyData.getCurrencyData(player).getFormatted());
		else if (CommandConstruct.match(args, PAY)) {
			PlayerUtil.setMoney(PlayerUtil.getPlayer(args[1]).getUniqueId().toString(), PlayerUtil.getMoney(PlayerUtil.getPlayer(args[1]).getUniqueId().toString()) + Math.abs(Float.valueOf(args[2])));
		} else {
			sender.sendMessage(CommandConstruct.getNoArgsMessage());
		}
		
		return true;
	}

}

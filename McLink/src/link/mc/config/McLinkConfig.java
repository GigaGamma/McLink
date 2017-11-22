package link.mc.config;

import link.mc.currency.CurrencyConfig;
import link.mc.external.discord.BotConfig;

public class McLinkConfig {
	
	@RequiredField
	public String username;
	
	@OptionalField
	@DetailedField
	public BotConfig bot = new BotConfig();
	
	@OptionalField
	public CurrencyConfig currency = new CurrencyConfig();
	
	@OptionalField
	public String lang = "en_US";
	
}

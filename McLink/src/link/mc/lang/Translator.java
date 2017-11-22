package link.mc.lang;

import java.util.ListIterator;

import link.mc.util.MarkupUtil;
import link.mc.util.Placeholder;

public class Translator {
	
	public static String translate(String lang, String s) {
		ListIterator<Translation> li = Translation.translations.listIterator(Translation.translations.size());
		
		while (li.hasPrevious()) {
			Translation t = li.previous();
			if (t.getId().equalsIgnoreCase(lang) && t.getConfig().getString(s) != null)
				return MarkupUtil.markupToChat(Placeholder.replace(t.getConfig().getString(s)));
			else
				return s;
		}
		
		return s;
	}
	
}

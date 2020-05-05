package fr.maxlego08.bungeequeue.utils.enums;

public enum EnumPlugin {

	JPREMIUM("JPremium"),
	
	;
	
	private final String pluginName;

	private EnumPlugin(String pluginName) {
		this.pluginName = pluginName;
	}

	public String getPluginName() {
		return pluginName;
	}

}

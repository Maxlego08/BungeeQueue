package fr.maxlego08.bungeequeue.utils;

public enum LogType {
	ERROR("§c"), INFO("§7"), WARNING("§6"), SUCCESS("§2");

	private final String color;

	private LogType(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}
}

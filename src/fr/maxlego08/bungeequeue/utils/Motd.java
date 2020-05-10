package fr.maxlego08.bungeequeue.utils;

import java.util.Arrays;
import java.util.List;

public class Motd {

	private final String upMessage;
	private final String downMessage;
	private final List<String> messages;

	/**
	 * @param upMessage
	 * @param downMessage
	 * @param messages
	 */
	public Motd(String upMessage, String downMessage, List<String> messages) {
		super();
		this.upMessage = upMessage;
		this.downMessage = downMessage;
		this.messages = messages;
	}

	/**
	 * @param upMessage
	 * @param downMessage
	 * @param messages
	 */
	public Motd(String upMessage, String downMessage, String... messages) {
		this(upMessage, downMessage, Arrays.asList(messages));
	}

	/**
	 * @return the upMessage
	 */
	public String getUpMessage() {
		return upMessage;
	}

	/**
	 * @return the downMessage
	 */
	public String getDownMessage() {
		return downMessage;
	}

	/**
	 * @return the messages
	 */
	public List<String> getMessages() {
		return messages;
	}

}

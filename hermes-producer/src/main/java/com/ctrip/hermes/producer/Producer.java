package com.ctrip.hermes.producer;

import java.util.concurrent.Future;

import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.unidal.lookup.ContainerLoader;

import com.ctrip.hermes.channel.SendResult;

public abstract class Producer {
	public abstract MessageHolder message(String topic, Object body);

	public static Producer getInstance() {
		try {
			return ContainerLoader.getDefaultContainer().lookup(Producer.class);
		} catch (ComponentLookupException e) {
			throw new IllegalStateException(String.format("Error: Unable to lookup %s!", Producer.class.getName()), e);
		}
	}

	public interface MessageHolder {
		public MessageHolder withKey(String key);

		public Future<SendResult> send();

		public MessageHolder withPriority();
		
		public MessageHolder withPartition(String partition);
	}
}

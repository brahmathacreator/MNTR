package com.org.mntr.events.eventcaster;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.ResolvableType;

public class DistributiveEventMulticaster implements ApplicationEventMulticaster {

	private ApplicationEventMulticaster asyncEventMulticaster;
	private ApplicationEventMulticaster syncEventMulticaster;

	@Override
	public void addApplicationListener(ApplicationListener<?> listner) {
		if (listner.getClass().getAnnotation(AsyncListner.class) != null)
			asyncEventMulticaster.addApplicationListener(listner);
		else
			syncEventMulticaster.addApplicationListener(listner);
	}

	@Override
	public void multicastEvent(ApplicationEvent event, ResolvableType arg1) {
		asyncEventMulticaster.multicastEvent(event);
		syncEventMulticaster.multicastEvent(event);
	}

	@Override
	public void removeAllListeners() {
		syncEventMulticaster.removeAllListeners();
		asyncEventMulticaster.removeAllListeners();
	}

	@Override
	public void removeApplicationListener(ApplicationListener<?> listener) {
		asyncEventMulticaster.removeApplicationListener(listener);
		syncEventMulticaster.removeApplicationListener(listener);
	}

	@Override
	public void multicastEvent(ApplicationEvent event) {
	}

	@Override
	public void addApplicationListenerBean(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeApplicationListenerBean(String arg0) {
		// TODO Auto-generated method stub

	}

	public void setAsyncEventMulticaster(ApplicationEventMulticaster asyncEventMulticaster) {
		this.asyncEventMulticaster = asyncEventMulticaster;
	}

	public void setSyncEventMulticaster(ApplicationEventMulticaster syncEventMulticaster) {
		this.syncEventMulticaster = syncEventMulticaster;
	}

}

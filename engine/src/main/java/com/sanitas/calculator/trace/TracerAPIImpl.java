package com.sanitas.calculator.trace;

import io.corp.calculator.TracerAPI;
import io.corp.calculator.TracerImpl;
import org.springframework.stereotype.Component;

@Component
public class TracerAPIImpl implements TracerAPI {

	private final TracerImpl tracerImpl;

	public TracerAPIImpl() {
		tracerImpl = new TracerImpl();
	}

	@Override
	public <T> void trace(final T result) {
		this.tracerImpl.trace(result);
	}
}

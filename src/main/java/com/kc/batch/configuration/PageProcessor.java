package com.kc.batch.configuration;

import java.util.List;

public interface PageProcessor<T> {
	void process(List<T> page);
}
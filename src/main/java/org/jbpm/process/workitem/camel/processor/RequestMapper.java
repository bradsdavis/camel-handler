package org.jbpm.process.workitem.camel.processor;

import org.apache.camel.Processor;
import org.kie.api.runtime.process.WorkItem;

public interface RequestMapper {
	public Processor mapToRequest(WorkItem workItem);
}
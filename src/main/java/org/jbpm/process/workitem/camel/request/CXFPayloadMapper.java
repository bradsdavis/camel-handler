package org.jbpm.process.workitem.camel.request;

import org.apache.camel.Processor;
import org.kie.api.runtime.process.WorkItem;

import com.redhat.demo.ws.Customer;

public class CXFPayloadMapper extends RequestPayloadMapper {

	public CXFPayloadMapper() {
		super("payload");
	}
	
	@Override
	public Processor mapToRequest(WorkItem workItem) {
		workItem.getParameters().put("payload", new Customer());
		return super.mapToRequest(workItem);
	}
}

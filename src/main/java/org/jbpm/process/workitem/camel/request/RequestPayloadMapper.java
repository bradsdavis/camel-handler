package org.jbpm.process.workitem.camel.request;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.kie.api.runtime.process.WorkItem;

public class RequestPayloadMapper implements RequestMapper {
 
	private final String requestLocation;
	
	public RequestPayloadMapper() {
		this.requestLocation = "request";
	}
	public RequestPayloadMapper(String requestLocation) {
		this.requestLocation = requestLocation;
	}
	
	public Processor mapToRequest(WorkItem workItem) {
		Object request = (Object)workItem.getParameters().get(requestLocation);
		workItem.getParameters().remove(requestLocation);
		return new RequestProcessor(request);
	}

	private class RequestProcessor implements Processor {
		private Object payload;
		
		public RequestProcessor(Object payload) {
			this.payload = payload;
		}
		
		@Override
		public void process(Exchange exchange) throws Exception {
			exchange.getIn().setBody(payload);
		}
	}
}

package com.example.CamelHandler;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.drools.core.process.instance.WorkItemHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class CamelServiceWorkItemHandler implements WorkItemHandler {
	private static final Logger LOG = LoggerFactory.getLogger(CamelServiceWorkItemHandler.class);
	private final CamelContext context;
	
	public CamelServiceWorkItemHandler() {
		context = new DefaultCamelContext();
		
	}

	@Override
	public void abortWorkItem(org.kie.api.runtime.process.WorkItem workItem,
			org.kie.api.runtime.process.WorkItemManager workItemManager) {
		workItemManager.abortWorkItem(workItem.getId());
	}

	@Override
	public void executeWorkItem(org.kie.api.runtime.process.WorkItem workItem,
			org.kie.api.runtime.process.WorkItemManager workItemManager) {
		
		LOG.info("Starting...");
		final String endpointUri = (String)workItem.getParameter("to");
		final Object payload = (String)workItem.getParameter("payload");
		
		LOG.info("Sending to endpoint URI: "+endpointUri);
		
		try {
			context.start();
			ProducerTemplate template = context.createProducerTemplate();
			template.sendBody(endpointUri, payload);
			LOG.info("Sent to endpoint URI: "+endpointUri);
		}
		catch(Exception e) {
			LOG.error("Exception executing template.", e);
		}
		finally {
			workItemManager.completeWorkItem(workItem.getId(), null);
		}
		
	}
}

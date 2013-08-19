package org.jbpm.process.workitem.camel;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.drools.core.process.instance.WorkItemHandler;
import org.jbpm.process.workitem.camel.processor.RequestMapper;
import org.jbpm.process.workitem.camel.processor.RequestPayloadMapper;
import org.jbpm.process.workitem.camel.processor.ResponseMapper;
import org.jbpm.process.workitem.camel.processor.ResponsePayloadMapper;
import org.jbpm.process.workitem.camel.uri.URIMapper;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CamelHandler implements WorkItemHandler {
	private static final Logger LOG = LoggerFactory.getLogger(CamelHandler.class);

	private final ResponseMapper responseMapper;
	private final RequestMapper requestMapper;
	private final URIMapper uriConverter;
	
	public CamelHandler(URIMapper converter) {
		this.uriConverter = converter;
		this.requestMapper = new RequestPayloadMapper();
		this.responseMapper = new ResponsePayloadMapper();
	}
	
	public CamelHandler(URIMapper converter, RequestMapper processorMapper) {
		this.uriConverter = converter;
		this.requestMapper = processorMapper;
		this.responseMapper = new ResponsePayloadMapper();
	}
	
	public CamelHandler(URIMapper converter, RequestMapper processorMapper, ResponseMapper responseMapper) {
		this.uriConverter = converter;
		this.requestMapper = processorMapper;
		this.responseMapper = responseMapper;
	}
	
	private void send(WorkItem workItem) throws URISyntaxException {
		CamelContext context = CamelContextService.getInstance();
		ProducerTemplate template = context.createProducerTemplate();
		
		Processor processor = requestMapper.mapToRequest(workItem);
		URI uri = uriConverter.toURI(workItem.getParameters());
		Endpoint endpoint = context.getEndpoint(uri.toString());
		 
		Exchange exchange = template.send(endpoint, processor);
		this.responseMapper.mapFromResponse(exchange, workItem);
	}
	
	@Override
	public void executeWorkItem(org.kie.api.runtime.process.WorkItem workItem, org.kie.api.runtime.process.WorkItemManager workItemManager) {
		try {
			this.send(workItem);
		}
		catch(Exception e) {
			LOG.error("Exception executing template.", e);
		}
		finally {
			workItemManager.completeWorkItem(workItem.getId(), null);
		}
	}
	
	@Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		manager.abortWorkItem(workItem.getId());
	}
}

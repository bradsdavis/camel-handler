package com.example.CamelHandler;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.jbpm.process.instance.ProcessInstance;
import org.jbpm.process.workitem.camel.CamelHandlerFactory;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCamelHandler {

	private static Logger LOG = LoggerFactory.getLogger(TestCamelHandler.class);
	@Test
	public void TestBasicHandlerInvocation() {
		
		//Create a knowledge session and register the Camel Service handler to it
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("handler-test.bpmn"), ResourceType.BPMN2);
		KieBase kbase = kbuilder.newKnowledgeBase();
		KieSession ksession = kbase.newKieSession();

		ksession.getWorkItemManager().registerWorkItemHandler("File", CamelHandlerFactory.fileHandler());
		ksession.getWorkItemManager().registerWorkItemHandler("XSLT", CamelHandlerFactory.xsltHandler());
		
		//Set-up the process instance
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		LOG.info("Before:");
		org.kie.api.runtime.process.ProcessInstance processInstance = ksession.createProcessInstance("test.camelHandler", params);
		
		//Make sure everything is read to fire
		assertEquals(ProcessInstance.STATE_PENDING, processInstance.getState());
		assertEquals(1, ksession.getProcessInstances().size());
		
		//Start the process
		ksession.startProcessInstance(processInstance.getId());
		
		//Make sure process completed correctly
		assertEquals(ProcessInstance.STATE_COMPLETED, processInstance.getState());
		assertEquals(0, ksession.getProcessInstances().size());
		LOG.info("Complete!");
		ksession.dispose();
	}
}

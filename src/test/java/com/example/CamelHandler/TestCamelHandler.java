package com.example.CamelHandler;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessInstance;
import org.junit.Test;

public class TestCamelHandler {

	@Test
	public void TestBasicHandlerInvocation() {
		//Create a knowledge session and register the Camel Service handler to it
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("handler-test.bpmn"), ResourceType.BPMN2);
		KnowledgeBase kbase = kbuilder.newKnowledgeBase();
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		ksession.getWorkItemManager().registerWorkItemHandler("Camel Service", new CamelServiceWorkItemHandler());
		
		//Set-up the process instance
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("to", "ftp:camel");
		ProcessInstance processInstance = ksession.createProcessInstance("test.camelHandler", params);
		
		//Make sure everything is read to fire
		assertEquals(ProcessInstance.STATE_PENDING, processInstance.getState());
		assertEquals(1, ksession.getProcessInstances().size());
		
		//Start the process
		ksession.startProcessInstance(processInstance.getId());
		
		//Make sure process completed correctly
		assertEquals(ProcessInstance.STATE_COMPLETED, processInstance.getState());
		assertEquals(0, ksession.getProcessInstances().size());
		
		ksession.dispose();
	}
}

package com.example.CamelHandler;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.jbpm.process.instance.ProcessInstance;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

public class TestCamelHandler {

	@Test
	public void TestBasicHandlerInvocation() {
		//Create a knowledge session and register the Camel Service handler to it
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("handler-test.bpmn"), ResourceType.BPMN2);
		KieBase kbase = kbuilder.newKnowledgeBase();
		KieSession ksession = kbase.newKieSession();
		ksession.getWorkItemManager().registerWorkItemHandler("Camel Service", new CamelServiceWorkItemHandler());
		
		//Set-up the process instance
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("to", "file:///tmp/output?fileName=text.txt");
		params.put("payload", "testing!!!");
		org.kie.api.runtime.process.ProcessInstance processInstance = ksession.startProcess("test.camelHandler", params);
		
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

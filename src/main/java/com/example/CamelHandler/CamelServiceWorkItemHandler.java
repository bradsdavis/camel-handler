package com.example.CamelHandler;

import org.drools.process.instance.WorkItemHandler;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemManager;

public class CamelServiceWorkItemHandler implements WorkItemHandler{

	public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
		System.out.println("Executing fake camel route with to=" + workItem.getParameter("to"));
		workItemManager.completeWorkItem(workItem.getId(), null);
	}

	public void abortWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
		workItemManager.abortWorkItem(workItem.getId());
	}
}

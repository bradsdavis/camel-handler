Business cases often have the need for both Human Task and System Integration functionality.  jBPM 6 provides Human Task and workflow persistence, while Apache Camel provides many out of the box components for System Integration.

This project provides a Proof of Concept for the integration of Camel Components from the jBPM 6 Human Task WorkItemHandler interface.  This serves only as a Proof of Concept.  Many areas need to be fleshed out for this POC to become a reality, including but not limited to:

1. Concepts of Transactions
2. Exception Management
3. Scalability Considerations
4. Request/Response Handling (the current example is Out only)

Once the pattern is extended, jBPM Work Item Definitions would be created for relevant Camel components to allow for easy integration with the jBPM BPMN2 editor.  Some high priority shapes include:

1. SOAP
2. Rest
3. HTTP
4. SQL / JDBC
5. JMS
6. FTP
7. File
8. XSLT
9. EJB

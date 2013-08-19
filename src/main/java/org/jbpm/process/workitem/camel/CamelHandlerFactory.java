package org.jbpm.process.workitem.camel;

import org.jbpm.process.workitem.camel.processor.RequestPayloadMapper;
import org.jbpm.process.workitem.camel.uri.CXFURIMapper;
import org.jbpm.process.workitem.camel.uri.FTPURIMapper;
import org.jbpm.process.workitem.camel.uri.FileURIMapper;
import org.jbpm.process.workitem.camel.uri.JMSURIMapper;
import org.jbpm.process.workitem.camel.uri.SQLURIMapper;

public class CamelHandlerFactory {
	
	public static CamelHandler sftpHandler() {
		return new CamelHandler(new FTPURIMapper("sftp"));
	}
	
	public static CamelHandler ftpHandler() {
		return new CamelHandler(new FTPURIMapper("ftp"));
	}
	
	public static CamelHandler ftpsHandler() {
		return new CamelHandler(new FTPURIMapper("ftps"));
	}
	
	public static CamelHandler cxfHandler() {
		return new CamelHandler(new CXFURIMapper());
	}
	
	public static CamelHandler fileHandler() {
		return new CamelHandler(new FileURIMapper(), new RequestPayloadMapper("payload"));
	}
	
	public static CamelHandler jmsHandler() {
		return new CamelHandler(new JMSURIMapper());
	}
	
	public static CamelHandler sqlHandler() {
		return new CamelHandler(new SQLURIMapper());
	}
}


package com.redhat.demo.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.redhat.demo.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Customer_QNAME = new QName("http://ws.demo.redhat.com/", "Customer");
    private final static QName _PrintCustomer_QNAME = new QName("http://ws.demo.redhat.com/", "printCustomer");
    private final static QName _PrintCustomerResponse_QNAME = new QName("http://ws.demo.redhat.com/", "printCustomerResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.redhat.demo.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PrintCustomer }
     * 
     */
    public PrintCustomer createPrintCustomer() {
        return new PrintCustomer();
    }

    /**
     * Create an instance of {@link PrintCustomerResponse }
     * 
     */
    public PrintCustomerResponse createPrintCustomerResponse() {
        return new PrintCustomerResponse();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Customer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.demo.redhat.com/", name = "Customer")
    public JAXBElement<Customer> createCustomer(Customer value) {
        return new JAXBElement<Customer>(_Customer_QNAME, Customer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintCustomer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.demo.redhat.com/", name = "printCustomer")
    public JAXBElement<PrintCustomer> createPrintCustomer(PrintCustomer value) {
        return new JAXBElement<PrintCustomer>(_PrintCustomer_QNAME, PrintCustomer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintCustomerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.demo.redhat.com/", name = "printCustomerResponse")
    public JAXBElement<PrintCustomerResponse> createPrintCustomerResponse(PrintCustomerResponse value) {
        return new JAXBElement<PrintCustomerResponse>(_PrintCustomerResponse_QNAME, PrintCustomerResponse.class, null, value);
    }

}

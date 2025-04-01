package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.models.Invoice;
import app.domain.models.Order;
import app.ports.InvoicePort;
import app.ports.OrderPort;
@Service
public class SellerService {
    @Autowired
    private OrderPort ordenPort;
    @Autowired
    private InvoicePort invoicedPort;

    public void saveInvoice(Invoice invoice) throws Exception {
        invoicedPort.saveInvoice(invoice);
    }

    public Order getOrderByOrderId(long ordenId)throws Exception {
        Order orden = ordenPort.findByOrderId(ordenId);
        if(orden == null){
            throw new Exception("No existe una orden con ese ID");
        }
        System.out.println(orden.getOrderStatus());
        return orden;
    }

    public Invoice getInvoicedByInvoicedId(long invoiceId) throws Exception {
        Invoice invoiced = invoicedPort.findByInvoiceId(invoiceId);
        if(invoiced == null){
            throw new Exception("No existe una factura con ese ID");
        }
        System.out.println(invoiced.getOrder().getOrderStatus());
        return invoiced;
    }

    public List<Invoice> getAllInvoiced() throws Exception{
       return invoicedPort.getAllInvoice();
    }

    public List<Order> getAllOrder() throws Exception {
        return ordenPort.getAllOrder();
    }

    public Invoice getInvoiceByInvoiceId(long invoiceId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInvoiceByInvoiceId'");
    }

}

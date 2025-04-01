package app.ports;

import java.util.List;
import app.domain.models.Invoice;

public interface InvoicePort {
    public void saveInvoice(Invoice invoice)throws Exception;
    public Invoice findByInvoiceId(long invoiceId)throws Exception;
    public List<Invoice> getAllInvoice()throws Exception;
}

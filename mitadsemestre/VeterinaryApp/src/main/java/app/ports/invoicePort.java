package app.ports;

import java.util.List;
import app.domain.models.invoice;

public interface invoicePort {
    public  List<invoice> getAllInvoice();
    public List<invoice> getInvoicesByVet();
    public List<invoice> getInvioceBySeller();
    
}

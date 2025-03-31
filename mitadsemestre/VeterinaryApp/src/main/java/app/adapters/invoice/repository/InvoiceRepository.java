package app.adapters.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.adapters.invoice.entity.InvoiceEntity;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>{
    public InvoiceEntity findByOrdenOrderId(long orderId);
    public InvoiceEntity findByOrdenPetPetId(long petId);
    public InvoiceEntity findByOrdenPersonDocument(long personDocument);
    public InvoiceEntity findByOrdenUserPersonDocument(long personDocument);
    public InvoiceEntity findByInvoiceId(long invoiceId);
} 
package app.adapters.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.adapters.invoice.entity.InvoiceEntity;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>{
    public InvoiceEntity findByOrderOrderId(long orderId);
    public InvoiceEntity findByOrderPetPetId(long petId);
    public InvoiceEntity findByOrderPersonDocument(long personDocument);
    public InvoiceEntity findByOrderUserPersonDocument(long personDocument);
    public InvoiceEntity findByInvoiceId(long invoiceId);
} 
package app.adapters.inputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapters.inputs.utils.InvoiceValidator;
import app.adapters.inputs.utils.OrderValidator;
import app.adapters.inputs.utils.Utils;
import app.domain.models.Invoice;
import app.domain.models.Order;
import app.domain.services.SellerService;
import app.ports.InputPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Component
public class SellerInput implements InputPort{
    @Autowired
    private OrderValidator orderValidator;

    @Autowired
    private InvoiceValidator invoicedValidator;

    @Autowired
    private SellerService sellerService;
    private final String MENU = "Ingrese la opción: "+"\n 1. Crear Factura"+"\n 2. Ver Factura"+"\n 3. Ver Todas las facturas" + "\n 4. Ver Order" + "\n 5. Ver Todas las orderes"+"\n 6. Salir";
    @Override
    public void menu() throws Exception {
        boolean sesion = true;
        while(sesion){
            sesion = options();
        }
    }

    private boolean options(){
        try {
            System.out.println(MENU);
            String option = Utils.getReader().nextLine();
            switch (option) {
                case "1":
                    this.createInvoiced();
                    return true;
                case "2":
                    this.getInvoiced();
                    return true;
                case "3":
                    sellerService.getAllInvoiced().forEach(invoiced -> System.out.println(toStringInvoice(invoiced)));
                    System.out.println();
                    return true;
                case "4":
                    this.getOrder();
                    return true;
                case "5":
                    sellerService.getAllOrder().forEach(order -> System.out.println(toStringOrder(order)));
                    System.out.println();
                    return true;
                case "6":
                    return false;
                default:
                    System.out.println("Opción no valida");
                    return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private void createInvoiced() throws Exception{
        System.out.print("Ingrese el id de la order: ");
        long orderId = orderValidator.orderIdValidator(Utils.getReader().nextLine());
        Order order = new Order();
        order.setOrderId(orderId);
        order = sellerService.getOrderByOrderId(orderId);
        System.out.println();
        System.out.println(toStringOrder(order));
        System.out.println();
        String product = anotherProduct();
        System.out.print("Precio: ");
        double amount = invoicedValidator.amoutValidator(Utils.getReader().nextLine());
        System.out.print("Cantidad de medicamentos: ");
        long medicationQuantity = invoicedValidator.quantityValidator(Utils.getReader().nextLine());
        Invoice invoice = new Invoice();
        invoice.setOrder(order);
        invoice.setAmount(amount);
        invoice.setMedicationQuantity(medicationQuantity);
        invoice.setItem(product);
        sellerService.saveInvoice(invoice);
        System.out.println("Factura creada");
        System.out.println(toStringInvoice(invoice));
    }

    private void getInvoiced(){
        try {
            Invoice invoice = new Invoice();
            System.out.print("Ingrese el id de la factura: ");
            long invoiceId =    InvoiceValidator.invoiceIdValidator(Utils.getReader().nextLine());
            invoice = sellerService.getInvoiceByInvoiceId(invoiceId);
            System.out.println(toStringInvoice(invoice));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private void getOrder(){
        try {
            Order order = new Order();
            System.out.print("Ingrese el id de la order: ");
            long orderId = orderValidator.orderIdValidator(Utils.getReader().nextLine());
            order = sellerService.getOrderByOrderId(orderId);
            System.out.println(toStringOrder(order));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private String toStringOrder(Order order){
        return "\nID Order: "+order.getOrderId()+"\nId Mascota: "+order.getPet().getPetId()+"\nCedula Dueño: "+order.getOwner().getDocument()
        +"\nCedula Veterinario: "+order.getVeterinarian().getDocument()+"\nNombre medigamento: "+order.getMedicationName()
        +"\nDosis: "+order.getMedicationDosis()+"\nFecha Order: "+order.getDate()+"\nEstado: "+order.getOrderStatus()+"\n";
    }

    private String toStringInvoice(Invoice invoice){
        return "FacturaId: " + invoice.getInvoiceId() + toStringOrder(invoice.getOrder()) + "\n" + "Otros productos: " + invoice.getItem()
        + "\n" + "Precio: " + invoice.getAmount() + "\n" + "Cantidad de medicamentos: " + invoice.getMedicationQuantity() + "\n" 
        + "Fecha Factura: " + invoice.getDate()+"\n";
    }

    private String anotherProduct() {
        String option = "";
        boolean validOption = false;
        while (!validOption) {
            try {
                System.out.println("¿Desea agregar otro producto? 1. Si 2. No");
                option = Utils.getReader().nextLine();
                switch (option) {
                    case "1":
                        validOption = true;
                        return addProductsLoop();
                    case "2":
                        validOption = true;
                        return "No";
                    default:
                        System.out.println("Opción inválida. Por favor ingrese 1 para Sí o 2 para No.");
                        break;
                }
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return "No";
    }

    private String addProductsLoop() {
        boolean condition = true;
        StringBuilder product = new StringBuilder();  // Usamos StringBuilder para concatenar de forma más eficiente
        while (condition) {
            System.out.print("Nombre del producto: ");
            product.append(" ").append(Utils.getReader().nextLine());
            System.out.println("¿Desea agregar otro producto? 1. Si 2. No");
            String options = Utils.getReader().nextLine();
            switch (options) {
                case "1":
                    break;
                case "2":
                    condition = false; 
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        return product.toString().trim();
    }
}

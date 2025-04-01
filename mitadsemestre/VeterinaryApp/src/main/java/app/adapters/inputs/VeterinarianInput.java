package app.adapters.inputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapters.inputs.utils.ClinicaValidator;
import app.adapters.inputs.utils.OrderValidator;
import app.adapters.inputs.utils.PersonValidator;
import app.adapters.inputs.utils.PetValidator;
import app.adapters.inputs.utils.UserValidator;
import app.domain.services.VeterinaryServices;
import app.ports.MedicalReportPort;
import app.ports.InputPort;
import app.ports.OrderPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Component
public class VeterinarianInput implements InputPort {
    @Autowired
    private PersonValidator personValidator;
    @Autowired
    private VeterinaryServices veterinaryServices;
    @Autowired
    private PetValidator petValidator;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private OrderValidator orderValidator;
    @Autowired
    private ClinicaValidator medicalReportValidator;
    @Autowired
    private MedicalReportPort medicalReportPort;
    @Autowired
    private OrderPort ordenPort;

    private final String  MENU = "\n 1.Crear Dueño" + "\n 2.Crear mascota\3" + "\n 3.Generar Orden" +  "\n 4.Generar Historia Clínica" + "\n 5.Ver ordenes"  +"\n 6. Obetener todas las ordenes"+ 
                                "\n 7.Ver Historia Clinica" +"\n 8.Ver todas las historias clinicas"+ "\n 9.Editar Historia Clínica" +"\n 10.Anular Orden" + "\n 11.Cerrar Sesión";
    public void menu() throws Exception{
        boolean sesion = true;
        while(sesion){
            sesion = options();    
                    }
                }
                private boolean options() {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'options'");
                }
}



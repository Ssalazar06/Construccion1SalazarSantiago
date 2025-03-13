package app.ports;

import java.util.List;

public interface orderPort {
    public List<orderPort> getAllOrderPort();
    public List<orderPort> getAllOrderPortByVet();
    public List<orderPort> getAllOrderPortBySeller();
}

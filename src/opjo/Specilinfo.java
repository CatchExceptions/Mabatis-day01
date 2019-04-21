package opjo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Specilinfo implements Serializable {
    private int spilinfoid;
    private int depinfoid;
    private int spilinfocode;
    private String spilinfoname;
    private String spilinfoaim;
    private String spilinfoprodire;
}

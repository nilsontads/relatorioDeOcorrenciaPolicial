
package servidor.bean;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("servidorpolicial")
public class ServidorPolicialBean extends ServidorBean implements Serializable{
    
}

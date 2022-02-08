
package servidor.bean;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("servidoradmin")
public class ServidorAdminBean extends ServidorBean implements Serializable{
    
}

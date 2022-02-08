
package tipoprovidencia.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import ocorrencia.bean.OcorrenciaBean;


@Entity
@Table(name = "tipoprovidencia")
public class TipoProvidenciaBean implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_tipoprovidencia", sequenceName = "tipoprovidencia_codigo_seq")
    @GeneratedValue(generator = "seq_tipoprovidencia")
    @Column(name = "codigo")
    private Integer codigo;

   
    @Column(name = "codprovidencia")
    private Integer codProvidencia;

//    @NotBlank(message = "Descrição não pode ser nullo!")
    @Column(name = "descricao", nullable = true)
    private String descricao;

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the codProvidencia
     */
    public Integer getCodProvidencia() {
        return codProvidencia;
    }

    /**
     * @param codProvidencia the codProvidencia to set
     */
    public void setCodProvidencia(Integer codProvidencia) {
        this.codProvidencia = codProvidencia;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.codigo);
        hash = 79 * hash + Objects.hashCode(this.codProvidencia);
        hash = 79 * hash + Objects.hashCode(this.descricao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoProvidenciaBean other = (TipoProvidenciaBean) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.codProvidencia, other.codProvidencia)) {
            return false;
        }
        return true;
    }

   
}

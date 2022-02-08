package tipoocorrencia.bean;

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
@Table(name = "tipoocorrencia")
public class TipoOcorrenciaBean implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_tipoocorrencia", sequenceName = "tipoocorrencia_codigo_seq")
    @GeneratedValue(generator = "seq_tipoocorrencia")

    @Column(name = "codigo")
    private Integer codigo;

    @Column(name = "codocorrencia")
    private String codOcorrencia;

//    @NotBlank(message = "Descrição não pode ser nulo!")
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
     * @return the codOcorrencia
     */
    public String getCodOcorrencia() {
        return codOcorrencia;
    }

    /**
     * @param codOcorrencia the codOcorrencia to set
     */
    public void setCodOcorrencia(String codOcorrencia) {
        this.codOcorrencia = codOcorrencia;
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
        hash = 37 * hash + Objects.hashCode(this.codigo);
        hash = 37 * hash + Objects.hashCode(this.codOcorrencia);
        hash = 37 * hash + Objects.hashCode(this.descricao);
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
        final TipoOcorrenciaBean other = (TipoOcorrenciaBean) obj;
        if (!Objects.equals(this.codOcorrencia, other.codOcorrencia)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

   

}

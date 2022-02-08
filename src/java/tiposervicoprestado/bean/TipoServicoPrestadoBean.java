
package tiposervicoprestado.bean;

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
@Table(name = "tiposervicoprestado")
public class TipoServicoPrestadoBean implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_tiposervicoprestado", sequenceName = "tiposervicoprestado_codigo_seq")
    @GeneratedValue(generator = "seq_tiposervicoprestado")
    @Column(name = "codigo")
    private Integer codigo;

  
    @Column(name = "codservicoprestado")
    private Integer codServicoPrestado;

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
     * @return the codServicoPrestado
     */
    public Integer getCodServicoPrestado() {
        return codServicoPrestado;
    }

    /**
     * @param codServicoPrestado the codServicoPrestado to set
     */
    public void setCodServicoPrestado(Integer codServicoPrestado) {
        this.codServicoPrestado = codServicoPrestado;
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
        hash = 17 * hash + Objects.hashCode(this.codigo);
        hash = 17 * hash + Objects.hashCode(this.codServicoPrestado);
        hash = 17 * hash + Objects.hashCode(this.descricao);
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
        final TipoServicoPrestadoBean other = (TipoServicoPrestadoBean) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.codServicoPrestado, other.codServicoPrestado)) {
            return false;
        }
        return true;
    }

    
}

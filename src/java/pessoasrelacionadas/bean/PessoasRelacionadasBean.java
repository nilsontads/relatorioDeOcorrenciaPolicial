
package pessoasrelacionadas.bean;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "pessoasrelacionadas")
public class PessoasRelacionadasBean implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_pessoasrelacionadas", sequenceName = "pessoasrelacionadas_codigo_seq")
    @GeneratedValue(generator = "seq_pessoasrelacionadas")
    @Column(name = "codigo")
    private Integer codigo;
    
//    @NotBlank(message = "Situação não pode esta vazio!")
    @Column(name = "situacao", nullable = true)
    private String situacao;
    
//    @NotBlank(message = "Nome não pode está vazio!")
    @Column(name = "nome", nullable = true)
    private String nome;
    
    
    @Column(name = "idade")
    private Integer idade;
    
//    @NotBlank(message = "Estado civil não pode está vazio!")
    @Column(name = "estadocivil", nullable = true)
    private String estadoCivil;
    
//    @NotBlank(message = "Endereço não pode está vazio!")
    @Column(name = "endereco", nullable = true)
    private String endereco;
    
//    @NotBlank(message = "RG não pode está vazio!")
    @Column(name = "rg", nullable = true)
    private String rg;
    
    
    @Column(name = "cnh")
    private String cnh;
    
//    @NotBlank(message = "Profissão não pode está vazio!")
    @Column(name = "profissao", nullable = true)
    private String profissao;
    

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.codigo);
        hash = 23 * hash + Objects.hashCode(this.situacao);
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.idade);
        hash = 23 * hash + Objects.hashCode(this.estadoCivil);
        hash = 23 * hash + Objects.hashCode(this.endereco);
        hash = 23 * hash + Objects.hashCode(this.rg);
        hash = 23 * hash + Objects.hashCode(this.cnh);
        hash = 23 * hash + Objects.hashCode(this.profissao);
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
        final PessoasRelacionadasBean other = (PessoasRelacionadasBean) obj;
        if (!Objects.equals(this.situacao, other.situacao)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.estadoCivil, other.estadoCivil)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.rg, other.rg)) {
            return false;
        }
        if (!Objects.equals(this.cnh, other.cnh)) {
            return false;
        }
        if (!Objects.equals(this.profissao, other.profissao)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.idade, other.idade)) {
            return false;
        }
        return true;
    }
    
}

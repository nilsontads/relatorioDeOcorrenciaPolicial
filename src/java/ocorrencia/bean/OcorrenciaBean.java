package ocorrencia.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import pessoasrelacionadas.bean.PessoasRelacionadasBean;
import servidor.bean.ServidorPolicialBean;
import tipoocorrencia.bean.TipoOcorrenciaBean;
import tipoprovidencia.bean.TipoProvidenciaBean;
import tiposervicoprestado.bean.TipoServicoPrestadoBean;

@Entity
@Table(name = "ocorrencia")
public class OcorrenciaBean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Integer codigo;

    @Column(name = "serie")
    private Character serie;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "vtr")
    private String vtr;

    @Column(name = "data")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;

    @Column(name = "setor")
    private String setor;

    @Column(name = "horatransmissao")
    private String horaTransmissao;

    @Column(name = "rua")
    private String rua;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "referencia")
    private String referencia;

    @Column(name = "horainicial")
    private String horaInicial;

    @Column(name = "horachegada")
    private String horaChegada;

    @Column(name = "horafinal")
    private String horaFinal;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<TipoOcorrenciaBean> tipoOcorrencia;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<TipoProvidenciaBean> tipoProvidencia;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<TipoServicoPrestadoBean> tipoServicoPrestado;

    @Column(name = "kminicial")
    private Integer kmInicial;

    @Column(name = "kmfinal")
    private Integer kmFinal;

    @Column(name = "materiais")
    private String materiais;

    @Column(name = "historico")
    private String historico;

    @ManyToMany(cascade = CascadeType.ALL )
    private List<PessoasRelacionadasBean> pessoas;
    
    @ManyToOne
    private ServidorPolicialBean policial;

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
     * @return the serie
     */
    public Character getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(Character serie) {
        this.serie = serie;
    }

    /**
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * @return the vtr
     */
    public String getVtr() {
        return vtr;
    }

    /**
     * @param vtr the vtr to set
     */
    public void setVtr(String vtr) {
        this.vtr = vtr;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the setor
     */
    public String getSetor() {
        return setor;
    }

    /**
     * @param setor the setor to set
     */
    public void setSetor(String setor) {
        this.setor = setor;
    }

    /**
     * @return the horaTransmissao
     */
    public String getHoraTransmissao() {
        return horaTransmissao;
    }

    /**
     * @param horaTransmissao the horaTransmissao to set
     */
    public void setHoraTransmissao(String horaTransmissao) {
        this.horaTransmissao = horaTransmissao;
    }

    /**
     * @return the rua
     */
    public String getRua() {
        return rua;
    }

    /**
     * @param rua the rua to set
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the horaInicial
     */
    public String getHoraInicial() {
        return horaInicial;
    }

    /**
     * @param horaInicial the horaInicial to set
     */
    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    /**
     * @return the horaChegada
     */
    public String getHoraChegada() {
        return horaChegada;
    }

    /**
     * @param horaChegada the horaChegada to set
     */
    public void setHoraChegada(String horaChegada) {
        this.horaChegada = horaChegada;
    }

    /**
     * @return the horaFinal
     */
    public String getHoraFinal() {
        return horaFinal;
    }

    /**
     * @param horaFinal the horaFinal to set
     */
    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    /**
     * @return the tipoOcorrencia
     */
    public List<TipoOcorrenciaBean> getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    /**
     * @param tipoOcorrencia the tipoOcorrencia to set
     */
    public void setTipoOcorrencia(List<TipoOcorrenciaBean> tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    /**
     * @return the tipoProvidencia
     */
    public List<TipoProvidenciaBean> getTipoProvidencia() {
        return tipoProvidencia;
    }

    /**
     * @param tipoProvidencia the tipoProvidencia to set
     */
    public void setTipoProvidencia(List<TipoProvidenciaBean> tipoProvidencia) {
        this.tipoProvidencia = tipoProvidencia;
    }

    /**
     * @return the tipoServicoPrestado
     */
    public List<TipoServicoPrestadoBean> getTipoServicoPrestado() {
        return tipoServicoPrestado;
    }

    /**
     * @param tipoServicoPrestado the tipoServicoPrestado to set
     */
    public void setTipoServicoPrestado(List<TipoServicoPrestadoBean> tipoServicoPrestado) {
        this.tipoServicoPrestado = tipoServicoPrestado;
    }

    /**
     * @return the kmInicial
     */
    public Integer getKmInicial() {
        return kmInicial;
    }

    /**
     * @param kmInicial the kmInicial to set
     */
    public void setKmInicial(Integer kmInicial) {
        this.kmInicial = kmInicial;
    }

    /**
     * @return the kmFinal
     */
    public Integer getKmFinal() {
        return kmFinal;
    }

    /**
     * @param kmFinal the kmFinal to set
     */
    public void setKmFinal(Integer kmFinal) {
        this.kmFinal = kmFinal;
    }

    /**
     * @return the materiais
     */
    public String getMateriais() {
        return materiais;
    }

    /**
     * @param materiais the materiais to set
     */
    public void setMateriais(String materiais) {
        this.materiais = materiais;
    }

    /**
     * @return the historico
     */
    public String getHistorico() {
        return historico;
    }

    /**
     * @param historico the historico to set
     */
    public void setHistorico(String historico) {
        this.historico = historico;
    }

    /**
     * @return the pessoas
     */
    public List<PessoasRelacionadasBean> getPessoas() {
        return pessoas;
    }

    /**
     * @param pessoas the pessoas to set
     */
    public void setPessoas(List<PessoasRelacionadasBean> pessoas) {
        this.pessoas = pessoas;
    }

    /**
     * @return the policial
     */
    public ServidorPolicialBean getPolicial() {
        return policial;
    }

    /**
     * @param policial the policial to set
     */
    public void setPolicial(ServidorPolicialBean policial) {
        this.policial = policial;
    }

   
    
}

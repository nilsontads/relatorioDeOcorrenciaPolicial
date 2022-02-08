package usuario.controlador;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpSession;
import ocorrencia.bean.OcorrenciaBean;
import ocorrencia.dao.OcorrenciaDaoImpl;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import pessoasrelacionadas.bean.PessoasRelacionadasBean;
import relatorio.RelatorioServlet;

import servidor.bean.ServidorAdminBean;
import servidor.bean.ServidorBean;
import servidor.bean.ServidorDelegadoBean;
import servidor.bean.ServidorPolicialBean;
import servidor.dao.ServidorDaoImpl;
import servidorAdmin.dao.ServidorAdminDaoImpl;
import servidorDelegado.dao.ServidorDelegadoDaoImpl;
import servidorPolicial.dao.ServidorPolicialDaoImpl;
import tipoocorrencia.bean.TipoOcorrenciaBean;
import tipoocorrencia.dao.TipoOcorrenciaDaoImpl;
import tipoprovidencia.bean.TipoProvidenciaBean;
import tipoprovidencia.dao.TipoProvidenciaDaoImpl;
import tiposervicoprestado.bean.TipoServicoPrestadoBean;
import tiposervicoprestado.dao.TipoServicoPrestadoDaoImpl;
import usuario.bean.UsuarioBean;
import usuario.dao.UsuarioDaoImpl;
import util.DaoException;
import util.FrameWork;
import util.JSFUtil;

@ManagedBean
@SessionScoped
public class usuarioControler implements Serializable {

    private UsuarioBean ub = new UsuarioBean();
    private UsuarioBean ubOFF = new UsuarioBean();
    private ServidorBean servidor;
    private ServidorAdminBean admin = new ServidorAdminBean();
    private ServidorAdminBean adminOFF = new ServidorAdminBean();
    private ServidorDelegadoBean delegado = new ServidorDelegadoBean();
    private ServidorAdminDaoImpl adminDao = new ServidorAdminDaoImpl();
    private ServidorPolicialBean policial = new ServidorPolicialBean();
    private PessoasRelacionadasBean pessoa = new PessoasRelacionadasBean();
    private OcorrenciaBean ocorrencia = new OcorrenciaBean();
    private TipoOcorrenciaBean tipoOcorrencia = new TipoOcorrenciaBean();
    private TipoProvidenciaBean tipoProvidencia = new TipoProvidenciaBean();
    private TipoServicoPrestadoBean tipoServico = new TipoServicoPrestadoBean();

    private UsuarioDaoImpl ud = new UsuarioDaoImpl();
    private ServidorPolicialDaoImpl policialDao = new ServidorPolicialDaoImpl();
    private ServidorDelegadoDaoImpl delegadoDao = new ServidorDelegadoDaoImpl();
    private ServidorDaoImpl servidorDao = new ServidorDaoImpl();
    private OcorrenciaDaoImpl ocorrenciaDao = new OcorrenciaDaoImpl();
    private TipoOcorrenciaDaoImpl tipoOcorrenciaDao = new TipoOcorrenciaDaoImpl();
    private TipoProvidenciaDaoImpl tipoProvidenciaDao = new TipoProvidenciaDaoImpl();
    private TipoServicoPrestadoDaoImpl tipoServicoDao = new TipoServicoPrestadoDaoImpl();

    private List<ServidorAdminBean> listAdmin = new ArrayList<ServidorAdminBean>();
    private List<ServidorDelegadoBean> listDelegado = new ArrayList<ServidorDelegadoBean>();
    private List<ServidorPolicialBean> listPolicial = new ArrayList<ServidorPolicialBean>();
    private List<OcorrenciaBean> listOcorrencias = new ArrayList<OcorrenciaBean>();
    private List<TipoOcorrenciaBean> listTipoOcorrencias = new ArrayList<TipoOcorrenciaBean>();
    private List<TipoOcorrenciaBean> listTipoOcorrencias2 = new ArrayList<TipoOcorrenciaBean>();
    private List<TipoProvidenciaBean> listTipoProvidencias = new ArrayList<TipoProvidenciaBean>();
    private List<TipoProvidenciaBean> listTipoProvidencias2 = new ArrayList<TipoProvidenciaBean>();
    private List<TipoServicoPrestadoBean> listTipoServicos = new ArrayList<TipoServicoPrestadoBean>();
    private List<TipoServicoPrestadoBean> listTipoServicos2 = new ArrayList<TipoServicoPrestadoBean>();
    private List<PessoasRelacionadasBean> pessoas = new ArrayList<PessoasRelacionadasBean>();

    private int idTocorencia;
    private int idTprovidencia;
    private int idTservico;
    private String senha;
    private String tipoUsuario;
    private String confirmaSenha;
    private Boolean renderizar = false;
    private List<UsuarioBean> listUb = new ArrayList<UsuarioBean>();

    public usuarioControler() {
    }

    public void gerarRelatorioDeUsuário(Integer i) {
        RelatorioServlet relatorio = new RelatorioServlet();
        relatorio.gerarRelatorioRop(i);
    }

    //Metodos que o Admin usa para EXCLUIR usuarios
    public void excluirDelegado(Integer i) {
        try {
            getDelegadoDao().excluir(getDelegadoDao().selecionar(i));
            messageSucesso("Usuário Excluído com Sucesso!", "formMenu:mgsGeral");
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodo para alterar seu dados de Usuario ADMIN Logado
    public void alterarDadosUsuarioAdmin() throws DaoException {
        getServidorDao().alterar(getAdmin());
        messageSucesso("Dados Alterado com Sucesso!", "formMenu:mgsGeral");
    }

    //Metodo para alterar seu dados de Usuario Delegado Logado
    public void alterarDadosUsuarioDelegado() throws DaoException {
        getServidorDao().alterar(getDelegado());
        messageSucesso("Dados Alterado com Sucesso!", "formMenu:mgsGeral");
    }

    //Metodo para alterar seu dados de Usuario Policial Logado
    public void alterarDadosUsuarioPolicial() throws DaoException {
        getServidorDao().alterar(getPolicial());
        messageSucesso("Dados Alterado com Sucesso!", "formMenu:mgsGeral");
    }

    //Metodo para alterar seu dados de Usuario  OFF
    public void alterarDadosUsuarioOFF2() throws DaoException {
        getUd().alterar(ub);
        messageSucesso("Dados Alterado com Sucesso!", "formMenu:mgsGeral");
    }

//Metodo para alterar seu dados de Usuario OFF
    public String alterarDadosUsuarioOFF(Integer i) throws DaoException {
        this.ubOFF = getUd().seleciona2(i);
        return "dadosUsuario?faces-redirect=true";
    }

    //RECUPERAR SENHA
    public void recuperarSenha() {

    }

    // ALTERAR SENHA
    public void alterarSenha() {
        if (getSenha().equals(getConfirmaSenha())) {

            this.getUb().setSenha(FrameWork.criptografar(getSenha()));

            try {
                getUd().alterar(getUb());
                messageSucesso("Senha Alteração com Sucesso!", "formMenu:mgsGeral");

            } catch (DaoException ex) {
                Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            messageError("Senhas Diferentes!", null);
            RequestContext.getCurrentInstance().update(Arrays.asList("formMenu:mgsGeral"));
        }

    }

    public String tipoUsuario() {
        if (getTipoUsuario().equals("Administrador")) {
            return "cadUsuarioAdmin?faces-redirect=true";
        }
        if (getTipoUsuario().equals("Policial")) {
            return "cadUsuarioPolicial?faces-redirect=true";
        }
        if (getTipoUsuario().equals("Delegado")) {
            return "cadUsuarioDelegado?faces-redirect=true";
        }
        return null;
    }

    //Insere Usuario de tipo AdminBean
    public void inserirUbAdmin() {
        if (getSenha().equals(getConfirmaSenha())) {
            this.getUbOFF().setSenha(FrameWork.criptografar(getSenha()));
            try {
                this.ubOFF.setCargo("Administrador");
                this.adminOFF.setCargo("Administrador");
                this.ubOFF.setServidor(adminOFF);
                getUd().inserir(this.getUbOFF());
                this.ubOFF = new UsuarioBean();
                this.adminOFF = new ServidorAdminBean();
                RequestContext.getCurrentInstance().update(Arrays.asList("form2"));
                JSFUtil.adicionarMensagemSucesso("inserido com sucesso!");
            } catch (DaoException ex) {
                Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
                JSFUtil.adicionarMensagemErro(ex.getMessage());
            }
        } else {
            messageError("Senha Diferentes", null);
        }

    }

    //Insere Usuario de tipo PolicialBean
    public void inserirUbPolicial() {
        if (getSenha().equals(getConfirmaSenha())) {
            this.getUbOFF().setSenha(FrameWork.criptografar(getSenha()));
            try {
                this.ubOFF.setCargo("Policial");
                this.policial.setCargo("Policial");
                this.ubOFF.setServidor(policial);
                getUd().inserir(this.getUbOFF());
                this.ubOFF = new UsuarioBean();
                this.policial = new ServidorPolicialBean();
                RequestContext.getCurrentInstance().update(Arrays.asList("form2"));
                JSFUtil.adicionarMensagemSucesso("inserido com sucesso!");
            } catch (DaoException ex) {
                Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
                JSFUtil.adicionarMensagemErro(ex.getMessage());
            }
        } else {
            messageError("Senha Diferentes", null);
        }

    }

    //Insere Usuario de tipo DelegadoBean
    public void inserirUbDelegado() {
        if (getSenha().equals(getConfirmaSenha())) {
            this.getUbOFF().setSenha(FrameWork.criptografar(getSenha()));
            try {
                this.ubOFF.setCargo("Delegado");
                this.delegado.setCargo("Delegado");
                this.ubOFF.setServidor(delegado);
                getUd().inserir(this.getUbOFF());
                this.ubOFF = new UsuarioBean();
                this.delegado = new ServidorDelegadoBean();
                RequestContext.getCurrentInstance().update(Arrays.asList("form2"));
                JSFUtil.adicionarMensagemSucesso("inserido com sucesso!");
            } catch (DaoException ex) {
                Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
                JSFUtil.adicionarMensagemErro(ex.getMessage());
            }
        } else {
            messageError("Senha Diferentes", null);
        }

    }

    // VALIDAR LOGIN E SENHA
    public Boolean validarLoginSenha() throws DaoException {
        List<UsuarioBean> listLogin = getUd().listarObj("login", this.getUb().getLogin());
        if (listLogin.isEmpty() == false) {
            UsuarioBean u = listLogin.get(0);
            if (u.getSenha().equals(FrameWork.criptografar(getUb().getSenha()))) {
                this.setUb(u);
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    public String logarSistema() throws DaoException {

        if (validarLoginSenha().equals(true)) {
            if (this.getUb().getCargo().equals("Administrador")) {
                this.setAdmin((ServidorAdminBean) getUb().getServidor());
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(false);
                session.setAttribute("usuario", this.getUb());
                return "/app/admin/inicio?faces-redirect=true";

            }

            if (this.getUb().getCargo().equals("Delegado")) {
                this.setDelegado((ServidorDelegadoBean) getUb().getServidor());
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(false);
                session.setAttribute("usuario", this.getUb());
                return "/app/central/paginaInicial?faces-redirect=true";

            }

            if (this.getUb().getCargo().equals("Policial")) {
                this.setPolicial((ServidorPolicialBean) getUb().getServidor());
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(false);
                session.setAttribute("usuario", getUb());
                return "/app/policial/inicio?faces-redirect=true";

            }
            return null;
        } else {
            error();
            return null;
        }

    }

    //MONSTRA A IMAGEM setada no objeto
    public String mostrarImagenSetada(byte[] img) throws DaoException {
        String filename = "imgSetada";
        byte[] data = img;

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "imagens" + File.separator + filename + ".png";
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            return "imagens/imgSetada.png";
        } catch (IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
    }

    //MONSTRA A IMAGEM DO BANCO NA TELA DO USUÁRIO LOGADO
    public String mostrarImagenBanco(byte[] img) throws DaoException {
        String filename = "imgBanco";
        byte[] data = img;

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "imagens" + File.separator + filename + ".png";
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            return "imagens/imgBanco.png";
        } catch (IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
    }

    //MONSTRA A IMAGEM pesquisada no banco
    public String mostrarPesqImagenBanco(byte[] img) throws DaoException {
        String filename = "imgPesqBanco";
        byte[] data = img;

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "imagens" + File.separator + filename + ".png";
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            return "imagens/imgPesqBanco.png";
        } catch (IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
    }

    //LISTAR IMAGENS DO BANCO NA TELA
    public String listarImagensBanco(byte[] img) throws DaoException {
        String filename = "imgBanco";
        this.setIdTocorencia(this.getIdTocorencia() + 1);
        byte[] data = img;

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName = externalContext.getRealPath("") + File.separator + "resources"
                + File.separator + "imagens" + File.separator + getIdTocorencia() + filename + ".png";
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            return "imagens/" + getIdTocorencia() + "imgBanco.png";
        } catch (IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }

    }

    //Mostrar imagem Servidor Selecionado na tela
    public String imagemServidor() throws DaoException {
        if (this.getUbOFF().getServidor().getImagem() == null) {
            return "imagens/foto.gif";
        } else {
            return mostrarPesqImagenBanco(this.getServidor().getImagem());
        }
    }

    //Mostrar imagem Admin do banco
    public String imagemAdmin() throws DaoException {
        if (this.getAdmin().getImagem() == null) {
            return "imagens/foto.gif";
        } else {
            return mostrarImagenBanco(this.getAdmin().getImagem());
        }
    }

    //Mostrar imagem Policial do banco
    public String imagemPolicial() throws DaoException {
        if (this.getPolicial().getImagem() == null) {
            return "imagens/foto.gif";
        } else {
            return mostrarImagenBanco(this.getPolicial().getImagem());
        }
    }

    //Mostrar imagem Delegado do banco
    public String imagemDelegado() throws DaoException {
        if (this.getDelegado().getImagem() == null) {
            return "imagens/foto.gif";
        } else {
            return mostrarImagenBanco(this.getDelegado().getImagem());
        }
    }

    //---------------------------------------------------
    //Mostrar imagem ADMIN OFF inserida(setada)
    public String imgAdmin() throws DaoException {
        if (this.getAdminOFF().getImagem() == null) {
            return "imagens/foto.gif";
        } else {
            return mostrarImagenSetada(this.getAdminOFF().getImagem());
        }
    }

    //Mostrar imagem Policial inserida(setada)
    public String imgPolicial() throws DaoException {
        if (this.getPolicial().getImagem() == null) {
            return "imagens/foto.gif";
        } else {
            return mostrarImagenSetada(this.getPolicial().getImagem());
        }
    }

    //Mostrar imagem Delegado inserida(setada)
    public String imgDelegado() throws DaoException {
        if (this.getDelegado().getImagem() == null) {
            return "imagens/foto.gif";
        } else {
            return mostrarImagenSetada(this.getDelegado().getImagem());
        }
    }

    //---------------------------------------------------
    //INSERI IMAGEM NO OBJETO ADMIN
    public void inseriImagemAdmin(FileUploadEvent event) {
        this.getAdminOFF().setImagem(event.getFile().getContents());
        RequestContext.getCurrentInstance().update(Arrays.asList("form2:imgPanel"));
    }

    //INSERI IMAGEM NO OBJETO POLICIAL
    public void inseriImagemPolicial(FileUploadEvent event) {
        this.getPolicial().setImagem(event.getFile().getContents());
        RequestContext.getCurrentInstance().update(Arrays.asList("form2:imgPanel"));
    }

    //INSERI IMAGEM NO OBJETO DELEGADO
    public void inseriImagemDelegado(FileUploadEvent event) {
        this.getDelegado().setImagem(event.getFile().getContents());
        RequestContext.getCurrentInstance().update(Arrays.asList("form2:imgPanel"));
    }

//ALTERA IMAGEM do ADMIN NO BANCO
    public void handleFileUpload(FileUploadEvent event) {

        this.getAdmin().setImagem(event.getFile().getContents());
//        FacesMessage message = new FacesMessage("Imagem Alterada com Sucesso!", event.getFile().getFileName() );
//        FacesContext.getCurrentInstance().addMessage(null, message);
        try {
            this.getAdminDao().alterar(getAdmin());
            messageSucesso("Imagem Alterada com Sucesso!", "formMenu:mgsGeral");
        } catch (DaoException ex) {
            messageError("Error ao Alterar Imagem", "formMenu:mgsGeral");
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //ALTERA IMAGEM do SERVIDOR NO BANCO
    public void handleFileUploadServidor(FileUploadEvent event) {

        this.getServidor().setImagem(event.getFile().getContents());
        try {
            this.getServidorDao().alterar(getServidor());
            messageSucesso("Imagem Alterada com Sucesso!", "formMenu:mgsGeral");
//            update("form2:panelPrincipal", null);
        } catch (DaoException ex) {
            messageError("Error ao Alterar Imagem", "formMenu:mgsGeral");
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //ALTERA IMAGEM do DELEGADO NO BANCO
    public void handleFileUploadDelegado(FileUploadEvent event) {

        this.getDelegado().setImagem(event.getFile().getContents());
        try {
            this.getDelegadoDao().alterar(getDelegado());
            messageSucesso("Imagem Alterada com Sucesso!", "formMenu:mgsGeral");
        } catch (DaoException ex) {
            messageError("Error ao Alterar Imagem", "formMenu:mgsGeral");
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance()
                .getExternalContext().invalidateSession();
        return "/seguranca/login?faces-redirect=true";
    }

    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
    }

    public void warn() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Watch out for PrimeFaces."));
    }

    public void error() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou Senha Inválido!", "Contate o administrador."));
        RequestContext.getCurrentInstance().update(Arrays.asList("formLogin:msg"));
    }

    public void fatal() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
    }

    public void messageSucesso(String a, String update) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(a));
        RequestContext.getCurrentInstance().update(Arrays.asList(update));
//        context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
    }

    public void messageError(String a, String update) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:", a));
        RequestContext.getCurrentInstance().update(Arrays.asList(update));
    }

    /*-------------- Tipo Providência ------------------*/
    public TipoProvidenciaBean addTipoProvidencias(Integer i) {
        try {
            return getTipoProvidenciaDao().seleciona2(i);
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     public void inserirTipoProvidencia( ) {
        try {
            getTipoProvidenciaDao().inserir(this.tipoProvidencia);
            this.tipoProvidencia = new TipoProvidenciaBean();
            JSFUtil.adicionarMensagemSucesso("salvo com sucesso!");
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.adicionarMensagemErro(ex.getMessage());
        }

    }

    public void excluirTipoProvidencia(TipoProvidenciaBean tipoProvidencia) {
        try {
            getTipoProvidenciaDao().excluir(tipoProvidencia);
            JSFUtil.adicionarMensagemSucesso("excluido com sucesso!");
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.adicionarMensagemErro("Objeto Relacionado");
        }

    }

    /*-------------- Tipo Serviço Prestado ------------------*/
    public TipoServicoPrestadoBean addTipoServicos(Integer i) {
        try {
            return getTipoServicoDao().seleciona2(i);
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void excluirTipoServicoPrestado(TipoServicoPrestadoBean tipoServicoPrestado) {
        try {
            getTipoServicoDao().excluir(tipoServicoPrestado);
            JSFUtil.adicionarMensagemSucesso("excluido com sucesso!");
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.adicionarMensagemErro("Objeto Relacionado");
        }

    }

    /*-------------- Tipo Ocorrências ------------------*/
    public TipoOcorrenciaBean addTipoOcorrencias(Integer i) {
        try {
            return getTipoOcorrenciaDao().seleciona2(i);
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void inserirTipoOcorrencia( ) {
        try {
            getTipoOcorrenciaDao().inserir(this.tipoOcorrencia);
            this.tipoOcorrencia = new TipoOcorrenciaBean();
            JSFUtil.adicionarMensagemSucesso("salvo com sucesso!");
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.adicionarMensagemErro(ex.getMessage());
        }

    }

    public void excluirTipoOcorrencia(TipoOcorrenciaBean tipoOcorrencia) {
        try {
            getTipoOcorrenciaDao().excluir(tipoOcorrencia);
            JSFUtil.adicionarMensagemSucesso("excluido com sucesso!");
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.adicionarMensagemErro("Objeto Relacionado");
        }

    }

    public TipoOcorrenciaBean selecionarTipoOcorrencia(Integer id) {
        try {
            this.tipoOcorrencia = getTipoOcorrenciaDao().seleciona2(id);
            return this.tipoOcorrencia;
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void editarTipoOcorrencia() {
        try {
            getTipoOcorrenciaDao().alterar(this.tipoOcorrencia);
            JSFUtil.adicionarMensagemSucesso("Alterado com sucesso!");
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.adicionarMensagemErro("Erro na alterar!");
        }
    }

    public void fecharFormPessoa() {
        this.renderizar = false;
    }

    public void limparFormOcorrencia() {
        this.ocorrencia = new OcorrenciaBean();
    }

    public String inserirOcorrencia() {
        try {
            this.ocorrencia.setTipoOcorrencia(listTipoOcorrencias2);
            this.ocorrencia.setTipoProvidencia(listTipoProvidencias2);
            this.ocorrencia.setTipoServicoPrestado(listTipoServicos2);
            this.ocorrencia.setPessoas(pessoas);
            this.ocorrencia.setData(Date.from(Instant.now()));
            this.ocorrencia.setPolicial((ServidorPolicialBean) ub.getServidor());
            ocorrenciaDao.insereOuAltera(ocorrencia);
            return "inicio?faces-redirect=true";
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.adicionarMensagemErro(ex.getMessage());
        }
        return null;
    }

    public void addNovaPessoa() {
        this.renderizar = true;
        this.pessoa = new PessoasRelacionadasBean();
    }

    public void addPessoa() {
        this.pessoas.add(getPessoa());
        this.renderizar = false;
    }

    public void removerPessoa(PessoasRelacionadasBean p) {
        this.pessoas.remove(p);
    }

    /**
     * @return the ub
     */
    public UsuarioBean getUb() {
        return ub;
    }

    /**
     * @param ub the ub to set
     */
    public void setUb(UsuarioBean ub) {
        this.ub = ub;
    }

    /**
     * @return the admin
     */
    public ServidorAdminBean getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(ServidorAdminBean admin) {
        this.admin = admin;
    }

    /**
     * @return the delegado
     */
    public ServidorDelegadoBean getDelegado() {
        return delegado;
    }

    /**
     * @param delegado the delegado to set
     */
    public void setDelegado(ServidorDelegadoBean delegado) {
        this.delegado = delegado;
    }

    /**
     * @return the adminDao
     */
    public ServidorAdminDaoImpl getAdminDao() {
        return adminDao;
    }

    /**
     * @param adminDao the adminDao to set
     */
    public void setAdminDao(ServidorAdminDaoImpl adminDao) {
        this.adminDao = adminDao;
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

    /**
     * @return the ocorrencia
     */
    public OcorrenciaBean getOcorrencia() {
        return ocorrencia;
    }

    /**
     * @param ocorrencia the ocorrencia to set
     */
    public void setOcorrencia(OcorrenciaBean ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    /**
     * @return the tipoOcorrencia
     */
    public TipoOcorrenciaBean getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    /**
     * @param tipoOcorrencia the tipoOcorrencia to set
     */
    public void setTipoOcorrencia(TipoOcorrenciaBean tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    /**
     * @return the tipoProvidencia
     */
    public TipoProvidenciaBean getTipoProvidencia() {
        return tipoProvidencia;
    }

    /**
     * @param tipoProvidencia the tipoProvidencia to set
     */
    public void setTipoProvidencia(TipoProvidenciaBean tipoProvidencia) {
        this.tipoProvidencia = tipoProvidencia;
    }

    /**
     * @return the tipoServico
     */
    public TipoServicoPrestadoBean getTipoServico() {
        return tipoServico;
    }

    /**
     * @param tipoServico the tipoServico to set
     */
    public void setTipoServico(TipoServicoPrestadoBean tipoServico) {
        this.tipoServico = tipoServico;
    }

    /**
     * @return the ud
     */
    public UsuarioDaoImpl getUd() {
        return ud;
    }

    /**
     * @param ud the ud to set
     */
    public void setUd(UsuarioDaoImpl ud) {
        this.ud = ud;
    }

    /**
     * @return the policialDao
     */
    public ServidorPolicialDaoImpl getPolicialDao() {
        return policialDao;
    }

    /**
     * @param policialDao the policialDao to set
     */
    public void setPolicialDao(ServidorPolicialDaoImpl policialDao) {
        this.policialDao = policialDao;
    }

    /**
     * @return the delegadoDao
     */
    public ServidorDelegadoDaoImpl getDelegadoDao() {
        return delegadoDao;
    }

    /**
     * @param delegadoDao the delegadoDao to set
     */
    public void setDelegadoDao(ServidorDelegadoDaoImpl delegadoDao) {
        this.delegadoDao = delegadoDao;
    }

    /**
     * @return the servidorDao
     */
    public ServidorDaoImpl getServidorDao() {
        return servidorDao;
    }

    /**
     * @param servidorDao the servidorDao to set
     */
    public void setServidorDao(ServidorDaoImpl servidorDao) {
        this.servidorDao = servidorDao;
    }

    /**
     * @return the ocorrenciaDao
     */
    public OcorrenciaDaoImpl getOcorrenciaDao() {
        return ocorrenciaDao;
    }

    /**
     * @param ocorrenciaDao the ocorrenciaDao to set
     */
    public void setOcorrenciaDao(OcorrenciaDaoImpl ocorrenciaDao) {
        this.ocorrenciaDao = ocorrenciaDao;
    }

    /**
     * @return the tipoOcorrenciaDao
     */
    public TipoOcorrenciaDaoImpl getTipoOcorrenciaDao() {
        return tipoOcorrenciaDao;
    }

    /**
     * @param tipoOcorrenciaDao the tipoOcorrenciaDao to set
     */
    public void setTipoOcorrenciaDao(TipoOcorrenciaDaoImpl tipoOcorrenciaDao) {
        this.tipoOcorrenciaDao = tipoOcorrenciaDao;
    }

    /**
     * @return the tipoProvidenciaDao
     */
    public TipoProvidenciaDaoImpl getTipoProvidenciaDao() {
        return tipoProvidenciaDao;
    }

    /**
     * @param tipoProvidenciaDao the tipoProvidenciaDao to set
     */
    public void setTipoProvidenciaDao(TipoProvidenciaDaoImpl tipoProvidenciaDao) {
        this.tipoProvidenciaDao = tipoProvidenciaDao;
    }

    /**
     * @return the tipoServicoDao
     */
    public TipoServicoPrestadoDaoImpl getTipoServicoDao() {
        return tipoServicoDao;
    }

    /**
     * @param tipoServicoDao the tipoServicoDao to set
     */
    public void setTipoServicoDao(TipoServicoPrestadoDaoImpl tipoServicoDao) {
        this.tipoServicoDao = tipoServicoDao;
    }

    /**
     * @return the listAdmin
     */
    public List<ServidorAdminBean> getListAdmin() {
        try {
            return getAdminDao().listar();
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @param listAdmin the listAdmin to set
     */
    public void setListAdmin(List<ServidorAdminBean> listAdmin) {
        this.listAdmin = listAdmin;
    }

    /**
     * @return the listDelegado
     */
    public List<ServidorDelegadoBean> getListDelegado() {
        try {
            return getDelegadoDao().listar();
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @param listDelegado the listDelegado to set
     */
    public void setListDelegado(List<ServidorDelegadoBean> listDelegado) {
        this.listDelegado = listDelegado;
    }

    /**
     * @return the listPolicial
     */
    public List<ServidorPolicialBean> getListPolicial() {
        try {
            return getPolicialDao().listar();
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @param listPolicial the listPolicial to set
     */
    public void setListPolicial(List<ServidorPolicialBean> listPolicial) {
        this.listPolicial = listPolicial;
    }

    /**
     * @return the listOcorrencias
     */
    public List<OcorrenciaBean> getListOcorrencias() {
        try {
            return getOcorrenciaDao().listar();
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @param listOcorrencias the listOcorrencias to set
     */
    public void setListOcorrencias(List<OcorrenciaBean> listOcorrencias) {
        this.listOcorrencias = listOcorrencias;
    }

    /**
     * @return the listTipoOcorrencias
     */
    public List<TipoOcorrenciaBean> getListTipoOcorrencias() {
        try {
            return getTipoOcorrenciaDao().listar();
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @param listTipoOcorrencias the listTipoOcorrencias to set
     */
    public void setListTipoOcorrencias(List<TipoOcorrenciaBean> listTipoOcorrencias) {
        this.listTipoOcorrencias = listTipoOcorrencias;
    }

    /**
     * @return the listTipoProvidencias
     */
    public List<TipoProvidenciaBean> getListTipoProvidencias() {
        try {
            return getTipoProvidenciaDao().listar();
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @param listTipoProvidencias the listTipoProvidencias to set
     */
    public void setListTipoProvidencias(List<TipoProvidenciaBean> listTipoProvidencias) {
        this.listTipoProvidencias = listTipoProvidencias;
    }

    /**
     * @return the listTipoServicos
     */
    public List<TipoServicoPrestadoBean> getListTipoServicos() {
        try {
            return getTipoServicoDao().listarDesc();
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @param listTipoServicos the listTipoServicos to set
     */
    public void setListTipoServicos(List<TipoServicoPrestadoBean> listTipoServicos) {
        this.listTipoServicos = listTipoServicos;
    }

    /**
     * @return the idTocorencia
     */
    public int getIdTocorencia() {
        return idTocorencia;
    }

    /**
     * @param idTocorencia the idTocorencia to set
     */
    public void setIdTocorencia(int idTocorencia) {
        this.idTocorencia = idTocorencia;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the tipoUsuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * @return the confirmaSenha
     */
    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    /**
     * @param confirmaSenha the confirmaSenha to set
     */
    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    /**
     * @return the renderizar
     */
    public Boolean getRenderizar() {
        return renderizar;
    }

    /**
     * @param renderizar the renderizar to set
     */
    public void setRenderizar(Boolean renderizar) {
        this.renderizar = renderizar;
    }

    /**
     * @return the listUb
     */
    public List<UsuarioBean> getListUb() {
        try {
            return getUd().listar();
        } catch (DaoException ex) {
            Logger.getLogger(usuarioControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @param listUb the listUb to set
     */
    public void setListUb(List<UsuarioBean> listUb) {
        this.listUb = listUb;
    }

    /**
     * @return the ubOFF
     */
    public UsuarioBean getUbOFF() {
        return ubOFF;
    }

    /**
     * @param ubOFF the ubOFF to set
     */
    public void setUbOFF(UsuarioBean ubOFF) {
        this.ubOFF = ubOFF;
    }

    /**
     * @return the servidor
     */
    public ServidorBean getServidor() {
        return servidor;
    }

    /**
     * @param servidor the servidor to set
     */
    public void setServidor(ServidorBean servidor) {
        this.servidor = servidor;
    }

    /**
     * @return the idTprovidencia
     */
    public int getIdTprovidencia() {
        return idTprovidencia;
    }

    /**
     * @param idTprovidencia the idTprovidencia to set
     */
    public void setIdTprovidencia(int idTprovidencia) {
        this.idTprovidencia = idTprovidencia;
    }

    /**
     * @return the idTservico
     */
    public int getIdTservico() {
        return idTservico;
    }

    /**
     * @param idTservico the idTservico to set
     */
    public void setIdTservico(int idTservico) {
        this.idTservico = idTservico;
    }

    /**
     * @return the listTipoOcorrencias2
     */
    public List<TipoOcorrenciaBean> getListTipoOcorrencias2() {
        return listTipoOcorrencias2;
    }

    /**
     * @param listTipoOcorrencias2 the listTipoOcorrencias2 to set
     */
    public void setListTipoOcorrencias2(List<TipoOcorrenciaBean> listTipoOcorrencias2) {
        this.listTipoOcorrencias2 = listTipoOcorrencias2;
    }

    /**
     * @return the listTipoProvidencias2
     */
    public List<TipoProvidenciaBean> getListTipoProvidencias2() {
        return listTipoProvidencias2;
    }

    /**
     * @param listTipoProvidencias2 the listTipoProvidencias2 to set
     */
    public void setListTipoProvidencias2(List<TipoProvidenciaBean> listTipoProvidencias2) {
        this.listTipoProvidencias2 = listTipoProvidencias2;
    }

    /**
     * @return the listTipoServicos2
     */
    public List<TipoServicoPrestadoBean> getListTipoServicos2() {
        return listTipoServicos2;
    }

    /**
     * @param listTipoServicos2 the listTipoServicos2 to set
     */
    public void setListTipoServicos2(List<TipoServicoPrestadoBean> listTipoServicos2) {
        this.listTipoServicos2 = listTipoServicos2;
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
     * @return the pessoa
     */
    public PessoasRelacionadasBean getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(PessoasRelacionadasBean pessoa) {
        this.pessoa = pessoa;
    }

    /**
     * @return the adminOFF
     */
    public ServidorAdminBean getAdminOFF() {
        return adminOFF;
    }

    /**
     * @param adminOFF the adminOFF to set
     */
    public void setAdminOFF(ServidorAdminBean adminOFF) {
        this.adminOFF = adminOFF;
    }

}

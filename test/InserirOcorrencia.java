
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ocorrencia.bean.OcorrenciaBean;
import ocorrencia.dao.OcorrenciaDaoImpl;
import pessoasrelacionadas.bean.PessoasRelacionadasBean;
import pessoasrelacionadas.dao.PessoasRelacionadasDaoImpl;
import tipoocorrencia.bean.TipoOcorrenciaBean;
import tipoocorrencia.dao.TipoOcorrenciaDaoImpl;
import tipoprovidencia.bean.TipoProvidenciaBean;
import tipoprovidencia.dao.TipoProvidenciaDaoImpl;
import tiposervicoprestado.bean.TipoServicoPrestadoBean;
import tiposervicoprestado.dao.TipoServicoPrestadoDaoImpl;
import util.DaoException;

public class InserirOcorrencia {

    public static void main(String[] args) {
        PessoasRelacionadasBean pessoa1 = new PessoasRelacionadasBean();
//        pessoa1.setCodigo(1);
        pessoa1.setCnh("1231");
        pessoa1.setEndereco("Av. Jaime Brasil Nº73 Centro");
        pessoa1.setEstadoCivil("Casado");
        pessoa1.setIdade(26);
        pessoa1.setNome("Jonas");
        pessoa1.setProfissao("Vendedor");
        pessoa1.setRg("6661");
        pessoa1.setSituacao("Situação Não Sei");

        PessoasRelacionadasBean pessoa2 = new PessoasRelacionadasBean();
//        pessoa2.setCodigo(2);
        pessoa2.setCnh("67391");
        pessoa2.setEndereco("Av. Getúlio Vagas Nº333 Centro");
        pessoa2.setEstadoCivil("Casada");
        pessoa2.setIdade(26);
        pessoa2.setNome("Pedro");
        pessoa2.setProfissao("Médico");
        pessoa2.setRg("45541");
        pessoa2.setSituacao("Situação Não Sei");

        PessoasRelacionadasBean pessoa3 = new PessoasRelacionadasBean();
//        pessoa3.setCodigo(3);
        pessoa3.setCnh("1231231");
        pessoa3.setEndereco("Av. N5 Nº333 Pitolândia");
        pessoa3.setEstadoCivil("Solteiro");
        pessoa3.setIdade(26);
        pessoa3.setNome("José Lima");
        pessoa3.setProfissao("Ladrão");
        pessoa3.setRg("4321");
        pessoa3.setSituacao("Situação Não Sei");

        OcorrenciaBean ocorrencia = new OcorrenciaBean();
        ocorrencia.setBairro("Centro2");
        ocorrencia.setNumero(222);
        ocorrencia.setRua("Av. Jaime Brasil2");
        ocorrencia.setReferencia("Loja2");
        ocorrencia.setSerie('R');
        ocorrencia.setSetor("Setor2");
        ocorrencia.setData(Date.from(Instant.now()));
        ocorrencia.setHoraTransmissao("14:30");
        ocorrencia.setHoraInicial("142:30");
        ocorrencia.setHoraChegada("14:40");
        ocorrencia.setHoraFinal("14:59");
        ocorrencia.setHistorico("historinha narrada pelo policial");
        ocorrencia.setKmInicial(20);
        ocorrencia.setKmFinal(40);
        ocorrencia.setMateriais("Nada");
        ocorrencia.setVtr("RODA");

        PessoasRelacionadasDaoImpl pessoaDao = new PessoasRelacionadasDaoImpl();
        OcorrenciaDaoImpl ocDao = new OcorrenciaDaoImpl();

        TipoOcorrenciaDaoImpl tOc = new TipoOcorrenciaDaoImpl();
        TipoProvidenciaDaoImpl tProv = new TipoProvidenciaDaoImpl();
        TipoServicoPrestadoDaoImpl tServ = new TipoServicoPrestadoDaoImpl();


        try {

//            pessoaDao.insereOuAltera(pessoa1);
////            pessoaDao.insereOuAltera(pessoa3);
//            pessoaDao.insereOuAltera(pessoa2);

            List<TipoOcorrenciaBean> listTipoOc = tOc.listar();
            List<TipoProvidenciaBean> listTipoProv = tProv.listar();
            List<TipoServicoPrestadoBean> listTipoServ = tServ.listar();

            ocorrencia.setTipoOcorrencia(listTipoOc);
            ocorrencia.setTipoProvidencia(listTipoProv);
            ocorrencia.setTipoServicoPrestado(listTipoServ);

            List<PessoasRelacionadasBean> p = new ArrayList<PessoasRelacionadasBean>();
            p.add(pessoa1);
//            p.add(pessoa2);
//            p.add(pessoa3);

            ocorrencia.setPessoas(p);
            ocDao.inserir(ocorrencia);
        } catch (DaoException ex) {
            Logger.getLogger(InserirOcorrencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

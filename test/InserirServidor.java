
import java.time.Instant;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.bean.ServidorAdminBean;
import servidor.bean.ServidorDelegadoBean;
import servidor.bean.ServidorPolicialBean;
import servidor.dao.ServidorDaoImpl;
import servidorAdmin.dao.ServidorAdminDaoImpl;
import servidorDelegado.dao.ServidorDelegadoDaoImpl;
import servidorPolicial.dao.ServidorPolicialDaoImpl;
import util.DaoException;

public class InserirServidor {

    public static void main(String[] args) {
        ServidorPolicialBean policial = new ServidorPolicialBean();
        policial.setNome("Janilson de Oliveira");
        policial.setCargo("Policial");
        policial.setCpf("11111111111");
        policial.setNum_matricula("32312124-22");
        policial.setRg("123123");
        policial.setSexo('M');
        policial.setDataNascimento(Date.from(Instant.now()));
        ServidorPolicialDaoImpl dao = new ServidorPolicialDaoImpl();

        ServidorDelegadoBean delegado = new ServidorDelegadoBean();
        delegado.setCargo("Delegado");
        delegado.setCpf("22222222222");
        delegado.setNome("Maria Jeane");
        delegado.setNum_matricula("32312124-22");
        delegado.setRg("123123");
        delegado.setSexo('F');
        delegado.setDataNascimento(Date.from(Instant.now()));
        ServidorDelegadoDaoImpl delegadoDao = new ServidorDelegadoDaoImpl();

        ServidorAdminBean admin = new ServidorAdminBean();
        admin.setCargo("Administrador");
        admin.setCpf("11111111111");
        admin.setNome("janilson");
        admin.setNum_matricula("32312124-22");
        admin.setRg("123123");
        admin.setSexo('M');
        admin.setDataNascimento(Date.from(Instant.now()));
        ServidorAdminDaoImpl adminDao = new ServidorAdminDaoImpl();
        ServidorDaoImpl sdao = new ServidorDaoImpl();

        try {
            adminDao.inserir(admin);
//            dao.inserir(policial);
//            delegadoDao.inserir(delegado);
        } catch (DaoException ex) {
            Logger.getLogger(InserirServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}


import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.dao.ServidorDaoImpl;
import usuario.bean.UsuarioBean;
import usuario.dao.UsuarioDaoImpl;
import util.DaoException;
import util.FrameWork;

public class InserirUsuario {

    public static void main(String[] args) {

        ServidorDaoImpl sd = new ServidorDaoImpl();

        UsuarioBean ub1 = new UsuarioBean();
        ub1.setLogin("policial");
        ub1.setSenha(FrameWork.criptografar("policial"));
        ub1.setCargo("Policial");

        UsuarioBean ub2 = new UsuarioBean();
        ub2.setLogin("admin");
        ub2.setSenha(FrameWork.criptografar("123"));
        ub2.setCargo("Administrador");

        UsuarioBean ub3 = new UsuarioBean();
        ub3.setLogin("delegado2");
        ub3.setCargo("Delegado");
        ub3.setSenha(FrameWork.criptografar("delegado2"));

        UsuarioDaoImpl ud = new UsuarioDaoImpl();

        ServidorDaoImpl servidorDao = new ServidorDaoImpl();

        try {
            ub2.setServidor(servidorDao.seleciona2(2));
//            ub2.setServidor(servidorDao.seleciona2(1));
//            ub3.setServidor(servidorDao.seleciona2(4));
//            ud.inserir(ub1);
//            ud.inserir(ub2);
            ud.inserir(ub2);
        } catch (DaoException ex) {
            Logger.getLogger(InserirUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

//      
    }
}

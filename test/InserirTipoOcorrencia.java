
import java.util.logging.Level;
import java.util.logging.Logger;
import tipoocorrencia.bean.TipoOcorrenciaBean;
import tipoocorrencia.dao.TipoOcorrenciaDaoImpl;
import util.DaoException;


public class InserirTipoOcorrencia {
    public static void main(String[] args) {
        TipoOcorrenciaBean tipoOcorrencia = new TipoOcorrenciaBean();
        tipoOcorrencia.setCodOcorrencia("005");
//        tipoOcorrencia.setDescricao("Furto");
//        tipoOcorrencia.setDescricao("Roubo");
//        tipoOcorrencia.setDescricao("Homicídio");
//        tipoOcorrencia.setDescricao("Suicídio");
        tipoOcorrencia.setDescricao("Latrocínio");
        
        TipoOcorrenciaDaoImpl dao = new TipoOcorrenciaDaoImpl();
        try {
            dao.inserir(tipoOcorrencia);
        } catch (DaoException ex) {
            Logger.getLogger(InserirTipoOcorrencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

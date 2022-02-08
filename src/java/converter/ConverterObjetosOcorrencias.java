package converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import tipoocorrencia.bean.TipoOcorrenciaBean;
import tipoocorrencia.dao.TipoOcorrenciaDaoImpl;
import util.DaoException;

@FacesConverter(forClass = TipoOcorrenciaBean.class, value = "converteTipoOcorrencia")
public class ConverterObjetosOcorrencias implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TipoOcorrenciaBean tipoOcorrencia = null;
        TipoOcorrenciaDaoImpl tipoOcDao = new TipoOcorrenciaDaoImpl();
        if (value != null) {
            try {
                tipoOcorrencia = tipoOcDao.seleciona2(new Integer(value));
                return tipoOcorrencia;
            } catch (DaoException ex) {
                Logger.getLogger(ConverterObjetosOcorrencias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Integer codigo = ((TipoOcorrenciaBean) value).getCodigo();
            String retorno = (codigo == null ? null : codigo.toString());

            return retorno;
        }

        return "";
    }

}

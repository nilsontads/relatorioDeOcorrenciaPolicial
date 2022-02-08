package converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import tipoprovidencia.bean.TipoProvidenciaBean;
import tipoprovidencia.dao.TipoProvidenciaDaoImpl;
import util.DaoException;

@FacesConverter(forClass = TipoProvidenciaBean.class, value = "converteTipoProvidencia")
public class ConverterObjetosProvidencia implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TipoProvidenciaBean tipoProvidencia = null;
        TipoProvidenciaDaoImpl tipoProvDao = new TipoProvidenciaDaoImpl();
        if (value != null) {
            try {
                tipoProvidencia = tipoProvDao.seleciona2(new Integer (value));
                return tipoProvidencia;
            } catch (DaoException ex) {
                Logger.getLogger(ConverterObjetosOcorrencias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Integer codigo = ((TipoProvidenciaBean) value).getCodigo();
            String retorno = (codigo == null ? null : codigo.toString());

            return retorno;
        }

        return "";
    }

}

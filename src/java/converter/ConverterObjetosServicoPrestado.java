package converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import tiposervicoprestado.bean.TipoServicoPrestadoBean;
import tiposervicoprestado.dao.TipoServicoPrestadoDaoImpl;
import util.DaoException;

@FacesConverter(forClass = TipoServicoPrestadoBean.class, value = "converteTipoServicoPrestado")
public class ConverterObjetosServicoPrestado implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TipoServicoPrestadoBean tipoServico = null;
        TipoServicoPrestadoDaoImpl tipoServicoDao = new TipoServicoPrestadoDaoImpl();
        if (value != null) {
            try {
                tipoServico = tipoServicoDao.seleciona2(new Integer(value));
                return tipoServico;
            } catch (DaoException ex) {
                Logger.getLogger(ConverterObjetosOcorrencias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Integer codigo = ((TipoServicoPrestadoBean) value).getCodigo();
            String retorno = (codigo == null ? null : codigo.toString());

            return retorno;
        }

        return "";
    }

}

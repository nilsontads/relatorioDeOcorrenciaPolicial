package converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pessoasrelacionadas.bean.PessoasRelacionadasBean;
import pessoasrelacionadas.dao.PessoasRelacionadasDaoImpl;
import util.DaoException;

@FacesConverter(forClass = PessoasRelacionadasBean.class, value = "converteTipoPessoa")
public class ConverterObjetosPessoa implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        PessoasRelacionadasBean pessoa = null;
        PessoasRelacionadasDaoImpl pessoaDao = new PessoasRelacionadasDaoImpl();
        if (value != null) {
            try {
                pessoa = pessoaDao.seleciona2(new Integer(value));
                return pessoa;
            } catch (DaoException ex) {
                Logger.getLogger(ConverterObjetosOcorrencias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Integer codigo = ((PessoasRelacionadasBean) value).getCodigo();
            String retorno = (codigo == null ? null : codigo.toString());

            return retorno;
        }

        return "";
    }

}

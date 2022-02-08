/*
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License
 * 
 * By exercising the Licensed Rights (defined below), You accept and agree to be bound by
 * the terms and conditions of this Creative Commons Attribution-NonCommercial-NoDerivatives
 * 4.0 International Public License ("Public License"). To the extent this Public License
 * may be interpreted as a contract, You are granted the Licensed Rights in consideration of
 * Your acceptance of these terms and conditions, and the Licensor grants You such rights in
 * consideration of benefits the Licensor receives from making the Licensed Material
 * available under these terms and conditions.
 */
package tipoprovidencia.dao;

import tipoprovidencia.bean.TipoProvidenciaBean;
import util.BaseDaoImpl;

/**
 *
 * @author boss
 */
public class TipoProvidenciaDaoImpl extends BaseDaoImpl<TipoProvidenciaBean> implements TipoProvidenciaDao {

    public TipoProvidenciaDaoImpl() {
        super(TipoProvidenciaBean.class);
    }

}

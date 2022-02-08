
package servidorPolicial.dao;

import servidor.bean.ServidorPolicialBean;
import util.BaseDaoImpl;


public class ServidorPolicialDaoImpl extends BaseDaoImpl<ServidorPolicialBean> implements ServidorPolicialDao{
    
    public ServidorPolicialDaoImpl() {
        super(ServidorPolicialBean.class);
    }
    
}

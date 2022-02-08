
package servidor.dao;

import servidor.bean.ServidorBean;
import util.BaseDaoImpl;


public class ServidorDaoImpl extends BaseDaoImpl<ServidorBean> implements ServidorDao{
    
    public ServidorDaoImpl() {
        super(ServidorBean.class);
    }
    
}

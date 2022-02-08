/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorAdmin.dao;

import servidor.bean.ServidorAdminBean;
import util.BaseDaoImpl;

/**
 *
 * @author pc
 */
public class ServidorAdminDaoImpl extends BaseDaoImpl<ServidorAdminBean> implements ServidorAdminDao{
    
    public ServidorAdminDaoImpl() {
        super(ServidorAdminBean.class);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorDelegado.dao;

import servidor.bean.ServidorDelegadoBean;
import util.BaseDaoImpl;


/**
 *
 * @author Jeane
 */
public class ServidorDelegadoDaoImpl extends BaseDaoImpl<ServidorDelegadoBean> implements servidorDelegadoDao{

    public ServidorDelegadoDaoImpl() {
        super(ServidorDelegadoBean.class);
    }
    
}

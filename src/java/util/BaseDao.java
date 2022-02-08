
package util;

import java.util.List;


public interface BaseDao<T> {

    public T inserir(T obj) throws DaoException;

    public T alterar(T obj) throws DaoException;

    public T selecionar(Integer id) throws DaoException;
    
    public T seleciona2 (Integer id) throws DaoException;
    
    public T selecionaParecerPorCodReq (Integer id) throws DaoException;

    public Boolean excluir(T obj) throws DaoException;

    public List<T> listar() throws DaoException;
    
    public List<T> listarDesc() throws DaoException;

    public T insereOuAltera(T obj) throws DaoException;

    public Integer size() throws DaoException;

    public List<T> listarObj(String coluna, String codigo) throws DaoException;
    
    public List<T> listarReqDeDelegadoStatus(String coluna, String valor) throws DaoException;
    
    public List<T> listarPorStatus() throws DaoException;
    
    public List<T> listarRequisicaoStatusConcluido(String coluna, String valor) throws DaoException;
    
    public List<T> listarObjInt(String coluna, String valor) throws DaoException;
}

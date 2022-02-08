package util;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


public class BaseDaoImpl<T> implements BaseDao<T> {

    protected Session session = null;
    private Class classe = null;

    public BaseDaoImpl(Class classe) {
        this.classe = classe;
    }

    @Override
    public T inserir(T obj) throws DaoException {
        Transaction transaction = null;
        try {
            this.session = Conexao.getSessionFactory().openSession();
            transaction = this.session.beginTransaction();
            this.session.save(obj);//insert do Hibernate
            transaction.commit();
            return obj;
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException h) {
                    System.out.println(h.getMessage());
                }
            }
            throw new DaoException(e);
        } finally {
            if (this.session != null) {
                try {
                    session.close();
                } catch (HibernateException h) {
                    System.out.println(h.getMessage());
                }
            }
        }
    }

    @Override
    public T alterar(T obj) throws DaoException {
        Transaction transaction = null;
        try {
            this.session = Conexao.getSessionFactory().openSession();
            transaction = this.session.beginTransaction();
            this.session.update(obj);
            transaction.commit();
            return obj;
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException h) {
                    System.out.println(h.getMessage());
                }
            }
            throw new DaoException(e);
        } finally {
            if (this.session != null) {
                try {
                    session.close();
                } catch (HibernateException h) {
                    System.out.println(h.getMessage());
                }
            }
        }
    }

    @Override
    public Boolean excluir(T obj) throws DaoException {
        Transaction transaction = null;
        try {
            this.session = Conexao.getSessionFactory().openSession();
            transaction = this.session.beginTransaction();
            this.session.delete(obj);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException h) {
                    System.out.println(h.getMessage());
                }
            }
            throw new DaoException(e);
        } finally {
            if (this.session != null) {
                try {
                    session.close();
                } catch (HibernateException h) {
                    System.out.println(h.getMessage());
                }
            }
        }
    }

    @Override
    public List<T> listar() throws DaoException {
        try {
            this.session = Conexao.getSessionFactory().openSession();
            List objs = this.session.createQuery("from " + classe.getName()).list();
            return objs;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            if (this.session != null) {
                try {
                    session.close();
                } catch (HibernateException h) {
                    System.out.println(h.getMessage());
                }
            }
        }
    }

    ///Fechar a conexão na camada de visualização
    //depois que consumir todos os recursos do objeto;
    @Override
    public T selecionar(Integer pk) throws DaoException {
        try {
            this.session = Conexao.getSessionFactory().openSession();
            Object obj = this.session.load(classe, pk);
            return (T) obj;
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public T insereOuAltera(T obj) throws DaoException {
        Transaction transaction = null;
        try {
            this.session = Conexao.getSessionFactory().openSession();
            transaction = this.session.beginTransaction();
            this.session.saveOrUpdate(obj);//insert do Hibernate
            transaction.commit();
            return obj;
        } catch (HibernateException e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException h) {
                    System.out.println(h.getMessage());
                }
            }
            throw new DaoException(e);
        } finally {
            if (this.session != null) {
                try {
                    session.close();
                } catch (HibernateException h) {
                    System.out.println(h.getMessage());
                }
            }
        }
    }

    // SIZE() - RETORNA O MAIOR CODIGO DA TABELA
    @Override
    public Integer size() throws DaoException {

        try {
            this.session = Conexao.getSessionFactory().openSession();
            Criteria cr = this.session.createCriteria(classe).addOrder(Order.desc("codigo"));
            Integer objs = cr.list().size();
            return objs;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            if (this.session != null) {
                try {
                    session.close();
                } catch (HibernateException h) {
                    System.out.println(h.getMessage());
                }
            }
        }

    }

    @Override
    public List<T> listarObj(String coluna, String txt) throws DaoException {

        try {
            this.session = Conexao.getSessionFactory().openSession();
            Criteria cr = this.session.createCriteria(classe).add(Restrictions.ilike(coluna, txt));
            List objs = cr.list();
            return objs;
        } catch (Exception e) {
            System.out.println("Usuario não encontrado");
            return null;
        } finally {
            if (this.session != null) {
                try {
                    session.close();
                } catch (HibernateException h) {
                    System.out.println(h.getMessage());
                }
            }
        }
    }

    @Override
    public List<T> listarDesc() throws DaoException {
        try {
            this.session = Conexao.getSessionFactory().openSession();
            Criteria cr = this.session.createCriteria(classe).addOrder(Order.desc("codigo"));
            List objs = cr.list();
            return objs;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            if (this.session != null) {
                try {
                    session.close();
                } catch (HibernateException h) {
                    System.out.println(h.getMessage());
                }
            }
        }
    }

    @Override
    public List<T> listarReqDeDelegadoStatus(String coluna, String valor) throws DaoException {
        try {
            this.session = Conexao.getSessionFactory().openSession();
            List objs = this.session.createQuery("from " + classe.getName() + " c " + " WHERE c.status != '3'AND c." + coluna + "=" + valor).list();
            return objs;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            if (this.session != null) {
                try {
                    session.close();
                } catch (HibernateException h) {
                    System.out.println(h.getMessage());
                }
            }
        }
    }

    @Override
    public List<T> listarObjInt(String coluna, String valor) throws DaoException {
        try {
            this.session = Conexao.getSessionFactory().openSession();
            List objs = this.session.createQuery("from " + classe.getName() + " c " + " WHERE c." + coluna + "=" + valor).list();
            return objs;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            if (this.session != null) {
                try {
                    session.close();
                } catch (HibernateException h) {
                    System.out.println(h.getMessage());
                }
            }
        }
    }

    @Override
    public T seleciona2(Integer id) throws DaoException {

        try {
            this.session = Conexao.getSessionFactory().openSession();
            Object obj = this.session.createQuery("from " + classe.getName() + " c " + " WHERE c.codigo =" + id).uniqueResult();
            return (T) obj;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            if (this.session != null) {
                try {
                    session.close();
                } catch (HibernateException h) {
                    System.out.println(h.getMessage());
                }
            }
        }
    }

    @Override
    public List<T> listarRequisicaoStatusConcluido(String coluna, String valor) throws DaoException {
        try {
            this.session = Conexao.getSessionFactory().openSession();
            List objs = this.session.createQuery("from " + classe.getName() + " c " + " WHERE c.status = 3 AND c." + coluna + "=" + valor).list();
            return objs;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            if (this.session != null) {
                try {
                    session.close();
                } catch (HibernateException h) {
                    System.out.println(h.getMessage());
                }
            }
        }
    }

    @Override
    public List<T> listarPorStatus() throws DaoException {
        try {
            this.session = Conexao.getSessionFactory().openSession();
            List objs = this.session.createQuery("from " + classe.getName() + " c " + " WHERE c.status != '3'").list();
            return objs;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            if (this.session != null) {
                try {
                    session.close();
                } catch (HibernateException h) {
                    System.out.println(h.getMessage());
                }
            }
        }
    }

    @Override
    public T selecionaParecerPorCodReq(Integer id) throws DaoException {
        try {
            this.session = Conexao.getSessionFactory().openSession();
            Object obj = this.session.createQuery("from " + classe.getName() + " c " + " WHERE c.requisicao =" + id).uniqueResult();
            return (T) obj;
        } catch (Exception e) {
            System.out.println("erro no metodo selecionaParecerPorCodReq");
            throw new DaoException(e);
        } finally {
            if (this.session != null) {
                try {
                    session.close();
                } catch (HibernateException h) {
                    System.out.println("erro na finalização de sessão do metodo selecionaParecerPorCodReq");
                    System.out.println(h.getMessage());
                }
            }
        }
    }

}

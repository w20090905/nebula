package it.trace.dao;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * 抽象Dao类，用于持久化操作
 * 
 * @version 1.0
 */
public abstract class BaseDao<T> {

	private static Log log = LogFactory.getLog(BaseDao.class);

	/**
	 * 获取Hibernate的Session对象
	 */
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}

	/**
	 * 根据主键得到对象
	 */
	public T getObject(Class clazz, Serializable id) {
		return (T) getSession().get(clazz, id);
	}

	/**
	 * 保存对象
	 */
	public void saveObject(T t) {
		Session session = getSession();
		Transaction tx = beginTransaction(session);
		try {
			session.saveOrUpdate(t);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.error("保存对象失败");
		}
	}

	/**
	 * 更新对象
	 */
	public void updateObject(T t) {
		Session session = getSession();
		Transaction tx = beginTransaction(session);
		try {
			session.update(t);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.error("更新对象失败");
		}
	}

	/**
	 * 删除对象
	 */
	public void removeObject(T t) {
		Session session = getSession();
		Transaction tx = beginTransaction(session);
		try {
			session.delete(t);
			System.out.println("delete success!");
			tx.commit();
		} catch (Exception e) {
			System.out.println("delete error!");
			tx.rollback();
			log.error("删除对象失败");
		}
	}

	/**
	 * 根据主键删除对象
	 */
	public void removeObject(Class clazz, Serializable id) {
		Session session = getSession();
		Transaction tx = beginTransaction(session);
		try {
			session.delete(getObject(clazz, id));
			tx.commit();
		} catch (Exception e) {
			System.out.println("remove error:"+e.getMessage());
			e.printStackTrace();
			tx.rollback();
			log.error("根据主键删除对象失败");
		}
	}

	/**
	 * 创建事务
	 */
	private Transaction beginTransaction(Session session) {
		return session.beginTransaction();
	}
}

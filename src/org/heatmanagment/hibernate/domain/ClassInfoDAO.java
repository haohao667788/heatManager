package org.heatmanagment.hibernate.domain;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * ClassInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.ClassInfo
 * @author MyEclipse Persistence Tools
 */

public class ClassInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ClassInfoDAO.class);
	// property constants
	public static final String CLSNAME = "clsname";
	public static final String COMM = "comm";

	protected void initDao() {
		// do nothing
	}

	public void save(ClassInfo transientInstance) {
		log.debug("saving ClassInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ClassInfo persistentInstance) {
		log.debug("deleting ClassInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ClassInfo findById(java.lang.Long id) {
		log.debug("getting ClassInfo instance with id: " + id);
		try {
			ClassInfo instance = (ClassInfo) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.ClassInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ClassInfo> findByExample(ClassInfo instance) {
		log.debug("finding ClassInfo instance by example");
		try {
			List<ClassInfo> results = (List<ClassInfo>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ClassInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ClassInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ClassInfo> findByClsname(Object clsname) {
		return findByProperty(CLSNAME, clsname);
	}

	public List<ClassInfo> findByComm(Object comm) {
		return findByProperty(COMM, comm);
	}

	public List findAll() {
		log.debug("finding all ClassInfo instances");
		try {
			String queryString = "from ClassInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPage(final int start, final int limit) {
		log.debug("finding all ClassInfo instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from ClassInfo";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all ClassInfo with boundary failed", re);
			throw re;
		}
	}

	public ClassInfo merge(ClassInfo detachedInstance) {
		log.debug("merging ClassInfo instance");
		try {
			ClassInfo result = (ClassInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ClassInfo instance) {
		log.debug("attaching dirty ClassInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ClassInfo instance) {
		log.debug("attaching clean ClassInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ClassInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ClassInfoDAO) ctx.getBean("ClassInfoDAO");
	}
}
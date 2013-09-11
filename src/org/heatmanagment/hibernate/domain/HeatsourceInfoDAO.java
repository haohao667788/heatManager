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
 * HeatsourceInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.HeatsourceInfo
 * @author MyEclipse Persistence Tools
 */

public class HeatsourceInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(HeatsourceInfoDAO.class);
	// property constants
	public static final String SRCNAME = "srcname";
	public static final String SRCADDRESS = "srcaddress";
	public static final String HEATTYPE = "heattype";
	public static final String COMM = "comm";

	protected void initDao() {
		// do nothing
	}

	public void save(HeatsourceInfo transientInstance) {
		log.debug("saving HeatsourceInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(HeatsourceInfo persistentInstance) {
		log.debug("deleting HeatsourceInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public HeatsourceInfo findById(java.lang.Long id) {
		log.debug("getting HeatsourceInfo instance with id: " + id);
		try {
			HeatsourceInfo instance = (HeatsourceInfo) getHibernateTemplate()
					.get("org.heatmanagment.hibernate.domain.HeatsourceInfo",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<HeatsourceInfo> findByExample(HeatsourceInfo instance) {
		log.debug("finding HeatsourceInfo instance by example");
		try {
			List<HeatsourceInfo> results = (List<HeatsourceInfo>) getHibernateTemplate()
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
		log.debug("finding HeatsourceInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from HeatsourceInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<HeatsourceInfo> findBySrcname(Object srcname) {
		return findByProperty(SRCNAME, srcname);
	}

	public List<HeatsourceInfo> findBySrcaddress(Object srcaddress) {
		return findByProperty(SRCADDRESS, srcaddress);
	}

	public List<HeatsourceInfo> findByHeattype(Object heattype) {
		return findByProperty(HEATTYPE, heattype);
	}

	public List<HeatsourceInfo> findByComm(Object comm) {
		return findByProperty(COMM, comm);
	}

	public List findAll() {
		log.debug("finding all HeatsourceInfo instances");
		try {
			String queryString = "from HeatsourceInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPage(final int start, final int limit) {
		log.debug("finding all HeatsourceInfo instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from HeatsourceInfo";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all HeatsourceInfo with boundary failed", re);
			throw re;
		}
	}

	public HeatsourceInfo merge(HeatsourceInfo detachedInstance) {
		log.debug("merging HeatsourceInfo instance");
		try {
			HeatsourceInfo result = (HeatsourceInfo) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(HeatsourceInfo instance) {
		log.debug("attaching dirty HeatsourceInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(HeatsourceInfo instance) {
		log.debug("attaching clean HeatsourceInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static HeatsourceInfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (HeatsourceInfoDAO) ctx.getBean("HeatsourceInfoDAO");
	}
}
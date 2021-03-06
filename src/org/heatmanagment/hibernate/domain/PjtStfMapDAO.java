package org.heatmanagment.hibernate.domain;

import java.sql.SQLException;
import java.util.List;

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
 * PjtStfMap entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.PjtStfMap
 * @author MyEclipse Persistence Tools
 */

public class PjtStfMapDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(PjtStfMapDAO.class);
	// property constants
	public static final String DESP = "desp";
	public static final String ISVALID = "isvalid";

	protected void initDao() {
		// do nothing
	}

	public void save(PjtStfMap transientInstance) {
		log.debug("saving PjtStfMap instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PjtStfMap persistentInstance) {
		log.debug("deleting PjtStfMap instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PjtStfMap findById(java.lang.Long id) {
		log.debug("getting PjtStfMap instance with id: " + id);
		try {
			PjtStfMap instance = (PjtStfMap) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.PjtStfMap", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<PjtStfMap> findByExample(PjtStfMap instance) {
		log.debug("finding PjtStfMap instance by example");
		try {
			List<PjtStfMap> results = (List<PjtStfMap>) getHibernateTemplate()
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
		log.debug("finding PjtStfMap instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PjtStfMap as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<PjtStfMap> findByDesp(Object desp) {
		return findByProperty(DESP, desp);
	}

	public List<PjtStfMap> findByIsvalid(Object isvalid) {
		return findByProperty(ISVALID, isvalid);
	}

	public List findAll() {
		log.debug("finding all PjtStfMap instances");
		try {
			String queryString = "from PjtStfMap";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPage(final int start, final int limit) {
		log.debug("finding all PjtStfMap instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from PjtStfMap as d where d.isvalid=:valid";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					query.setBoolean("valid", true);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all PjtStfMap with boundary failed", re);
			throw re;
		}
	}

	public Long count() {
		log.debug("count PjtStfMaps");
		String hql = "select count(*) from PjtStfMap where isvalid=true";
		return (Long) getHibernateTemplate().find(hql).listIterator().next();
	}

	public PjtStfMap merge(PjtStfMap detachedInstance) {
		log.debug("merging PjtStfMap instance");
		try {
			PjtStfMap result = (PjtStfMap) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PjtStfMap instance) {
		log.debug("attaching dirty PjtStfMap instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PjtStfMap instance) {
		log.debug("attaching clean PjtStfMap instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PjtStfMapDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PjtStfMapDAO) ctx.getBean("PjtStfMapDAO");
	}
}
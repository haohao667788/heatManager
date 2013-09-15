package org.heatmanagment.hibernate.domain;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * ClsStfMap entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.ClsStfMap
 * @author MyEclipse Persistence Tools
 */

public class ClsStfMapDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ClsStfMapDAO.class);
	// property constants
	public static final String DESP = "desp";
	public static final String ISVALID = "isvalid";

	protected void initDao() {
		// do nothing
	}

	public void save(ClsStfMap transientInstance) {
		log.debug("saving ClsStfMap instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ClsStfMap persistentInstance) {
		log.debug("deleting ClsStfMap instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ClsStfMap findById(java.lang.Long id) {
		log.debug("getting ClsStfMap instance with id: " + id);
		try {
			ClsStfMap instance = (ClsStfMap) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.ClsStfMap", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ClsStfMap> findByExample(ClsStfMap instance) {
		log.debug("finding ClsStfMap instance by example");
		try {
			List<ClsStfMap> results = (List<ClsStfMap>) getHibernateTemplate()
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
		log.debug("finding ClsStfMap instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ClsStfMap as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ClsStfMap> findByDesp(Object desp) {
		return findByProperty(DESP, desp);
	}

	public List<ClsStfMap> findByIsvalid(Object isvalid) {
		return findByProperty(ISVALID, isvalid);
	}

	public List findAll() {
		log.debug("finding all ClsStfMap instances");
		try {
			String queryString = "from ClsStfMap as c where c.isvalid=true";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ClsStfMap merge(ClsStfMap detachedInstance) {
		log.debug("merging ClsStfMap instance");
		try {
			ClsStfMap result = (ClsStfMap) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ClsStfMap instance) {
		log.debug("attaching dirty ClsStfMap instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ClsStfMap instance) {
		log.debug("attaching clean ClsStfMap instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ClsStfMapDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ClsStfMapDAO) ctx.getBean("ClsStfMapDAO");
	}
}
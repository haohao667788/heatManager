package org.heatmanagment.hibernate.domain;

import java.sql.SQLException;
import java.sql.Timestamp;
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
 * DealInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.DealInfo
 * @author MyEclipse Persistence Tools
 */

public class DealInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(DealInfoDAO.class);
	// property constants
	public static final String CURBALANCE = "curbalance";
	public static final String CURCHARGE = "curcharge";
	public static final String CURMONEY = "curmoney";
	public static final String DEALNAME = "dealname";
	public static final String DESP = "desp";

	protected void initDao() {
		// do nothing
	}

	public void save(DealInfo transientInstance) {
		log.debug("saving DealInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DealInfo persistentInstance) {
		log.debug("deleting DealInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DealInfo findById(java.lang.Long id) {
		log.debug("getting DealInfo instance with id: " + id);
		try {
			DealInfo instance = (DealInfo) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.DealInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<DealInfo> findByExample(DealInfo instance) {
		log.debug("finding DealInfo instance by example");
		try {
			List<DealInfo> results = (List<DealInfo>) getHibernateTemplate()
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
		log.debug("finding DealInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from DealInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<DealInfo> findByCurbalance(Object curbalance) {
		return findByProperty(CURBALANCE, curbalance);
	}

	public List<DealInfo> findByCurcharge(Object curcharge) {
		return findByProperty(CURCHARGE, curcharge);
	}

	public List<DealInfo> findByCurmoney(Object curmoney) {
		return findByProperty(CURMONEY, curmoney);
	}

	public List<DealInfo> findByDealname(Object dealname) {
		return findByProperty(DEALNAME, dealname);
	}

	public List<DealInfo> findByDesp(Object desp) {
		return findByProperty(DESP, desp);
	}

	public List findAll() {
		log.debug("finding all DealInfo instances");
		try {
			String queryString = "from DealInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPage(final int start, final int limit) {
		log.debug("finding all DealInfo instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from DealInfo";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all DealInfo with boundary failed", re);
			throw re;
		}
	}

	public DealInfo merge(DealInfo detachedInstance) {
		log.debug("merging DealInfo instance");
		try {
			DealInfo result = (DealInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DealInfo instance) {
		log.debug("attaching dirty DealInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DealInfo instance) {
		log.debug("attaching clean DealInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DealInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (DealInfoDAO) ctx.getBean("DealInfoDAO");
	}
}
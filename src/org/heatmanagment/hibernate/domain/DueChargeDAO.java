package org.heatmanagment.hibernate.domain;

import java.sql.SQLException;
import java.sql.Timestamp;
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
 * DueCharge entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.DueCharge
 * @author MyEclipse Persistence Tools
 */

public class DueChargeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(DueChargeDAO.class);
	// property constants
	public static final String DEALNAME = "dealname";
	public static final String DEALMONTH = "dealmonth";
	public static final String CHGTYPE = "chgtype";
	public static final String CHGNUMBER = "chgnumber";
	public static final String REALCHGNUMBER = "realchgnumber";

	protected void initDao() {
		// do nothing
	}

	public void save(DueCharge transientInstance) {
		log.debug("saving DueCharge instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DueCharge persistentInstance) {
		log.debug("deleting DueCharge instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DueCharge findById(java.lang.Long id) {
		log.debug("getting DueCharge instance with id: " + id);
		try {
			DueCharge instance = (DueCharge) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.DueCharge", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<DueCharge> findByExample(DueCharge instance) {
		log.debug("finding DueCharge instance by example");
		try {
			List<DueCharge> results = (List<DueCharge>) getHibernateTemplate()
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
		log.debug("finding DueCharge instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from DueCharge as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<DueCharge> findByDealname(Object dealname) {
		return findByProperty(DEALNAME, dealname);
	}

	public List<DueCharge> findByDealmonth(Object dealmonth) {
		return findByProperty(DEALMONTH, dealmonth);
	}

	public List<DueCharge> findByChgtype(Object chgtype) {
		return findByProperty(CHGTYPE, chgtype);
	}

	public List<DueCharge> findByChgnumber(Object chgnumber) {
		return findByProperty(CHGNUMBER, chgnumber);
	}

	public List<DueCharge> findByRealchgnumber(Object realchgnumber) {
		return findByProperty(REALCHGNUMBER, realchgnumber);
	}

	public List findAll() {
		log.debug("finding all DueCharge instances");
		try {
			String queryString = "from DueCharge";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPage(final int start, final int limit) {
		log.debug("finding all DueCharge instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from DueCharge";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all DueCharge with boundary failed", re);
			throw re;
		}
	}

	public DueCharge merge(DueCharge detachedInstance) {
		log.debug("merging DueCharge instance");
		try {
			DueCharge result = (DueCharge) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DueCharge instance) {
		log.debug("attaching dirty DueCharge instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DueCharge instance) {
		log.debug("attaching clean DueCharge instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DueChargeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (DueChargeDAO) ctx.getBean("DueChargeDAO");
	}
}
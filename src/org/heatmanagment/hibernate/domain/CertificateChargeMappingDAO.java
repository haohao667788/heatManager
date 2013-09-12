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
 * CertificateChargeMapping entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see org.heatmanagment.hibernate.domain.CertificateChargeMapping
 * @author MyEclipse Persistence Tools
 */

public class CertificateChargeMappingDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(CertificateChargeMappingDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(CertificateChargeMapping transientInstance) {
		log.debug("saving CertificateChargeMapping instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CertificateChargeMapping persistentInstance) {
		log.debug("deleting CertificateChargeMapping instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CertificateChargeMapping findById(java.lang.Long id) {
		log.debug("getting CertificateChargeMapping instance with id: " + id);
		try {
			CertificateChargeMapping instance = (CertificateChargeMapping) getHibernateTemplate()
					.get("org.heatmanagment.hibernate.domain.CertificateChargeMapping",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<CertificateChargeMapping> findByExample(
			CertificateChargeMapping instance) {
		log.debug("finding CertificateChargeMapping instance by example");
		try {
			List<CertificateChargeMapping> results = (List<CertificateChargeMapping>) getHibernateTemplate()
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
		log.debug("finding CertificateChargeMapping instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CertificateChargeMapping as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all CertificateChargeMapping instances");
		try {
			String queryString = "from CertificateChargeMapping";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPage(final int start, final int limit) {
		log.debug("finding all CertificateChargeMapping instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from CertificateChargeMapping";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all CertificateChargeMapping with boundary failed", re);
			throw re;
		}
	}

	public CertificateChargeMapping merge(
			CertificateChargeMapping detachedInstance) {
		log.debug("merging CertificateChargeMapping instance");
		try {
			CertificateChargeMapping result = (CertificateChargeMapping) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CertificateChargeMapping instance) {
		log.debug("attaching dirty CertificateChargeMapping instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CertificateChargeMapping instance) {
		log.debug("attaching clean CertificateChargeMapping instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CertificateChargeMappingDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (CertificateChargeMappingDAO) ctx
				.getBean("CertificateChargeMappingDAO");
	}
}
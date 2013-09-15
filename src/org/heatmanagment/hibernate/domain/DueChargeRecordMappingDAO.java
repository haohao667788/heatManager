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
 * DueChargeRecordMapping entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.DueChargeRecordMapping
 * @author MyEclipse Persistence Tools
 */

public class DueChargeRecordMappingDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(DueChargeRecordMappingDAO.class);
	// property constants
	public static final String ISVALID = "isvalid";

	protected void initDao() {
		// do nothing
	}

	public void save(DueChargeRecordMapping transientInstance) {
		log.debug("saving DueChargeRecordMapping instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DueChargeRecordMapping persistentInstance) {
		log.debug("deleting DueChargeRecordMapping instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DueChargeRecordMapping findById(java.lang.Long id) {
		log.debug("getting DueChargeRecordMapping instance with id: " + id);
		try {
			DueChargeRecordMapping instance = (DueChargeRecordMapping) getHibernateTemplate()
					.get("org.heatmanagment.hibernate.domain.DueChargeRecordMapping",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<DueChargeRecordMapping> findByExample(
			DueChargeRecordMapping instance) {
		log.debug("finding DueChargeRecordMapping instance by example");
		try {
			List<DueChargeRecordMapping> results = (List<DueChargeRecordMapping>) getHibernateTemplate()
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
		log.debug("finding DueChargeRecordMapping instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DueChargeRecordMapping as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<DueChargeRecordMapping> findByIsvalid(Object isvalid) {
		return findByProperty(ISVALID, isvalid);
	}

	public List findAll() {
		log.debug("finding all DueChargeRecordMapping instances");
		try {
			String queryString = "from DueChargeRecordMapping as c where c.isvalid=true";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPage(final int start, final int limit) {
		log.debug("finding all DueChargeRecordMapping instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from DueChargeRecordMapping as d where d.isvalid=:valid";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					query.setBoolean("valid", true);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all DueChargeRecordMapping with boundary failed",
					re);
			throw re;
		}
	}

	public void remove(Long id) {
		DueChargeRecordMapping mapping = new DueChargeRecordMapping();
		mapping.setMapid(id);
		mapping.setIsvalid(false);
		attachDirty(mapping);
	}

	public DueChargeRecordMapping merge(DueChargeRecordMapping detachedInstance) {
		log.debug("merging DueChargeRecordMapping instance");
		try {
			DueChargeRecordMapping result = (DueChargeRecordMapping) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DueChargeRecordMapping instance) {
		log.debug("attaching dirty DueChargeRecordMapping instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DueChargeRecordMapping instance) {
		log.debug("attaching clean DueChargeRecordMapping instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DueChargeRecordMappingDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DueChargeRecordMappingDAO) ctx
				.getBean("DueChargeRecordMappingDAO");
	}
}
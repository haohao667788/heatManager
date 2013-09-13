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
 * DealGenerator entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.DealGenerator
 * @author MyEclipse Persistence Tools
 */

public class DealGeneratorDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(DealGeneratorDAO.class);
	// property constants
	public static final String DEALNAME = "dealname";

	protected void initDao() {
		// do nothing
	}

	public void save(DealGenerator transientInstance) {
		log.debug("saving DealGenerator instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DealGenerator persistentInstance) {
		log.debug("deleting DealGenerator instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DealGenerator findById(java.lang.Long id) {
		log.debug("getting DealGenerator instance with id: " + id);
		try {
			DealGenerator instance = (DealGenerator) getHibernateTemplate()
					.get("org.heatmanagment.hibernate.domain.DealGenerator", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<DealGenerator> findByExample(DealGenerator instance) {
		log.debug("finding DealGenerator instance by example");
		try {
			List<DealGenerator> results = (List<DealGenerator>) getHibernateTemplate()
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
		log.debug("finding DealGenerator instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DealGenerator as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<DealGenerator> findByDealname(Object dealname) {
		return findByProperty(DEALNAME, dealname);
	}

	public List findAll() {
		log.debug("finding all DealGenerator instances");
		try {
			String queryString = "from DealGenerator";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPage(final int start, final int limit) {
		log.debug("finding all DealGenerator instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from DealGenerator";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all DealGenerator with boundary failed", re);
			throw re;
		}
	}

	public DealGenerator merge(DealGenerator detachedInstance) {
		log.debug("merging DealGenerator instance");
		try {
			DealGenerator result = (DealGenerator) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DealGenerator instance) {
		log.debug("attaching dirty DealGenerator instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DealGenerator instance) {
		log.debug("attaching clean DealGenerator instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DealGeneratorDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DealGeneratorDAO) ctx.getBean("DealGeneratorDAO");
	}
}
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
 * Config entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.Config
 * @author MyEclipse Persistence Tools
 */

public class ConfigDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(ConfigDAO.class);
	// property constants
	public static final String DEALNAME = "dealname";

	protected void initDao() {
		// do nothing
	}

	public void save(Config transientInstance) {
		log.debug("saving Config instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Config persistentInstance) {
		log.debug("deleting Config instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Config findById(java.lang.Long id) {
		log.debug("getting Config instance with id: " + id);
		try {
			Config instance = (Config) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.Config", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Config> findByExample(Config instance) {
		log.debug("finding Config instance by example");
		try {
			List<Config> results = (List<Config>) getHibernateTemplate()
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
		log.debug("finding Config instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Config as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Config> findByDealname(Object dealname) {
		return findByProperty(DEALNAME, dealname);
	}

	public List findAll() {
		log.debug("finding all Config instances");
		try {
			String queryString = "from Config";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPage(final int start, final int limit) {
		log.debug("finding all Config instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from Config as d where d.isvalid=:valid";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					query.setBoolean("valid", true);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all Config with boundary failed", re);
			throw re;
		}
	}

	public Config merge(Config detachedInstance) {
		log.debug("merging Config instance");
		try {
			Config result = (Config) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Config instance) {
		log.debug("attaching dirty Config instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Config instance) {
		log.debug("attaching clean Config instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ConfigDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ConfigDAO) ctx.getBean("ConfigDAO");
	}
}
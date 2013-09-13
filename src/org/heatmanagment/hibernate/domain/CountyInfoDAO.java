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
 * CountyInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.CountyInfo
 * @author MyEclipse Persistence Tools
 */

public class CountyInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(CountyInfoDAO.class);
	// property constants
	public static final String TOWNNAME = "townname";
	public static final String CITYNAME = "cityname";
	public static final String DESP = "desp";

	protected void initDao() {
		// do nothing
	}

	public void save(CountyInfo transientInstance) {
		log.debug("saving CountyInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CountyInfo persistentInstance) {
		log.debug("deleting CountyInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CountyInfo findById(java.lang.Long id) {
		log.debug("getting CountyInfo instance with id: " + id);
		try {
			CountyInfo instance = (CountyInfo) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.CountyInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<CountyInfo> findByExample(CountyInfo instance) {
		log.debug("finding CountyInfo instance by example");
		try {
			List<CountyInfo> results = (List<CountyInfo>) getHibernateTemplate()
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
		log.debug("finding CountyInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from CountyInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<CountyInfo> findByTownname(Object townname) {
		return findByProperty(TOWNNAME, townname);
	}

	public List<CountyInfo> findByCityname(Object cityname) {
		return findByProperty(CITYNAME, cityname);
	}

	public List<CountyInfo> findByDesp(Object desp) {
		return findByProperty(DESP, desp);
	}

	public List findAll() {
		log.debug("finding all CountyInfo instances");
		try {
			String queryString = "from CountyInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPage(final int start, final int limit) {
		log.debug("finding all CountyInfo instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from CountyInfo";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all CountyInfo with boundary failed", re);
			throw re;
		}
	}

	public CountyInfo merge(CountyInfo detachedInstance) {
		log.debug("merging CountyInfo instance");
		try {
			CountyInfo result = (CountyInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CountyInfo instance) {
		log.debug("attaching dirty CountyInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CountyInfo instance) {
		log.debug("attaching clean CountyInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CountyInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CountyInfoDAO) ctx.getBean("CountyInfoDAO");
	}
}
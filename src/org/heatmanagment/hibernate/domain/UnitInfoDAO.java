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
 * UnitInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.UnitInfo
 * @author MyEclipse Persistence Tools
 */

public class UnitInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(UnitInfoDAO.class);
	// property constants
	public static final String UNTNAME = "untname";
	public static final String GIS = "gis";
	public static final String PICADDRESS = "picaddress";
	public static final String DESP = "desp";
	public static final String ISVALID = "isvalid";

	protected void initDao() {
		// do nothing
	}

	public void save(UnitInfo transientInstance) {
		log.debug("saving UnitInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UnitInfo persistentInstance) {
		log.debug("deleting UnitInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UnitInfo findById(java.lang.Long id) {
		log.debug("getting UnitInfo instance with id: " + id);
		try {
			UnitInfo instance = (UnitInfo) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.UnitInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<UnitInfo> findByExample(UnitInfo instance) {
		log.debug("finding UnitInfo instance by example");
		try {
			List<UnitInfo> results = (List<UnitInfo>) getHibernateTemplate()
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
		log.debug("finding UnitInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UnitInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<UnitInfo> findByUntname(Object untname) {
		return findByProperty(UNTNAME, untname);
	}

	public List<UnitInfo> findByGis(Object gis) {
		return findByProperty(GIS, gis);
	}

	public List<UnitInfo> findByPicaddress(Object picaddress) {
		return findByProperty(PICADDRESS, picaddress);
	}

	public List<UnitInfo> findByDesp(Object desp) {
		return findByProperty(DESP, desp);
	}

	public List<UnitInfo> findByIsvalid(Object isvalid) {
		return findByProperty(ISVALID, isvalid);
	}

	public List findAll() {
		log.debug("finding all UnitInfo instances");
		try {
			String queryString = "from UnitInfo as c where c.isvalid=true";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPage(final int start, final int limit) {
		log.debug("finding all UnitInfo instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from UnitInfo as d where d.isvalid=:valid";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					query.setBoolean("valid", true);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all UnitInfo with boundary failed", re);
			throw re;
		}
	}

	public void remove(Long id) {
		UnitInfo unt = new UnitInfo();
		unt.setUntid(id);
		unt.setIsvalid(false);
		attachDirty(unt);
	}

	public Long count() {
		log.debug("count UnitInfos");
		String hql = "select count(*) from UnitInfo where isvalid=true";
		return (Long) getHibernateTemplate().find(hql).listIterator().next();
	}

	public UnitInfo merge(UnitInfo detachedInstance) {
		log.debug("merging UnitInfo instance");
		try {
			UnitInfo result = (UnitInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UnitInfo instance) {
		log.debug("attaching dirty UnitInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UnitInfo instance) {
		log.debug("attaching clean UnitInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UnitInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UnitInfoDAO) ctx.getBean("UnitInfoDAO");
	}
}
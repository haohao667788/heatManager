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
 * CourseInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.CourseInfo
 * @author MyEclipse Persistence Tools
 */

public class CourseInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(CourseInfoDAO.class);
	// property constants
	public static final String CRSNUM = "crsnum";
	public static final String CRSNAME = "crsname";
	public static final String DESP = "desp";
	public static final String DEALNAME = "dealname";
	public static final String ISVALID = "isvalid";

	protected void initDao() {
		// do nothing
	}

	public void save(CourseInfo transientInstance) {
		log.debug("saving CourseInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CourseInfo persistentInstance) {
		log.debug("deleting CourseInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CourseInfo findById(java.lang.Long id) {
		log.debug("getting CourseInfo instance with id: " + id);
		try {
			CourseInfo instance = (CourseInfo) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.CourseInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<CourseInfo> findByExample(CourseInfo instance) {
		log.debug("finding CourseInfo instance by example");
		try {
			List<CourseInfo> results = (List<CourseInfo>) getHibernateTemplate()
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
		log.debug("finding CourseInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from CourseInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<CourseInfo> findByCrsnum(Object crsnum) {
		return findByProperty(CRSNUM, crsnum);
	}

	public List<CourseInfo> findByCrsname(Object crsname) {
		return findByProperty(CRSNAME, crsname);
	}

	public List<CourseInfo> findByDesp(Object desp) {
		return findByProperty(DESP, desp);
	}

	public List<CourseInfo> findByDealname(Object dealname) {
		return findByProperty(DEALNAME, dealname);
	}

	public List<CourseInfo> findByIsvalid(Object isvalid) {
		return findByProperty(ISVALID, isvalid);
	}

	public List findAll() {
		log.debug("finding all CourseInfo instances");
		try {
			String queryString = "from CourseInfo where isvalid=true";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPage(final int start, final int limit) {
		log.debug("finding all CourseInfo instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from CourseInfo as d where d.isvalid=:valid";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					query.setBoolean("valid", true);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all CourseInfo with boundary failed", re);
			throw re;
		}
	}
	
	public Long count() {
		log.debug("count CourseInfos");
		String hql = "select count(*) from CourseInfo where isvalid=true";
		return (Long) getHibernateTemplate().find(hql).listIterator().next();
	}

	public CourseInfo merge(CourseInfo detachedInstance) {
		log.debug("merging CourseInfo instance");
		try {
			CourseInfo result = (CourseInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CourseInfo instance) {
		log.debug("attaching dirty CourseInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CourseInfo instance) {
		log.debug("attaching clean CourseInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CourseInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CourseInfoDAO) ctx.getBean("CourseInfoDAO");
	}
}
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
 * DistrictInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.DistrictInfo
 * @author MyEclipse Persistence Tools
 */

public class DistrictInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(DistrictInfoDAO.class);
	// property constants
	public static final String DSTNAME = "dstname";
	public static final String DESP = "desp";
	public static final String ISVALID = "isvalid";

	protected void initDao() {
		// do nothing
	}

	public void save(DistrictInfo transientInstance) {
		log.debug("saving DistrictInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DistrictInfo persistentInstance) {
		log.debug("deleting DistrictInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DistrictInfo findById(java.lang.Long id) {
		log.debug("getting DistrictInfo instance with id: " + id);
		try {
			DistrictInfo instance = (DistrictInfo) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.DistrictInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<DistrictInfo> findByExample(DistrictInfo instance) {
		log.debug("finding DistrictInfo instance by example");
		try {
			List<DistrictInfo> results = (List<DistrictInfo>) getHibernateTemplate()
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
		log.debug("finding DistrictInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DistrictInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<DistrictInfo> findByDstname(Object dstname) {
		return findByProperty(DSTNAME, dstname);
	}

	public List<DistrictInfo> findByDesp(Object desp) {
		return findByProperty(DESP, desp);
	}

	public List<DistrictInfo> findByIsvalid(Object isvalid) {
		return findByProperty(ISVALID, isvalid);
	}

	public List findAll() {
		log.debug("finding all DistrictInfo instances");
		try {
			String queryString = "from DistrictInfo where isvalid=true";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPage(final int start, final int limit) {
		log.debug("finding all DistrictInfo instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from DistrictInfo as d where d.isvalid=:valid";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					query.setBoolean("valid", true);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all DistrictInfo with boundary failed", re);
			throw re;
		}
	}

	public void remove(Long id) {
		DistrictInfo dst = new DistrictInfo();
		dst.setDstid(id);
		dst.setIsvalid(false);
		attachDirty(dst);
	}

	public DistrictInfo merge(DistrictInfo detachedInstance) {
		log.debug("merging DistrictInfo instance");
		try {
			DistrictInfo result = (DistrictInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Long count() {
		log.debug("count DistrictInfos");
		String hql = "select count(*) from DistrictInfo where isvalid=true";
		return (Long) getHibernateTemplate().find(hql).listIterator().next();
	}

	public void attachDirty(DistrictInfo instance) {
		log.debug("attaching dirty DistrictInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DistrictInfo instance) {
		log.debug("attaching clean DistrictInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DistrictInfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DistrictInfoDAO) ctx.getBean("DistrictInfoDAO");
	}
}
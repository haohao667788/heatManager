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
 * CommunityInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.CommunityInfo
 * @author MyEclipse Persistence Tools
 */

public class CommunityInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(CommunityInfoDAO.class);
	// property constants
	public static final String CMTNAME = "cmtname";
	public static final String BRIEFNAME = "briefname";
	public static final String CMTADDRESS = "cmtaddress";
	public static final String GIS = "gis";
	public static final String PICADDRESS = "picaddress";
	public static final String DESP = "desp";
	public static final String ISVALID = "isvalid";

	protected void initDao() {
		// do nothing
	}

	public void save(CommunityInfo transientInstance) {
		log.debug("saving CommunityInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CommunityInfo persistentInstance) {
		log.debug("deleting CommunityInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CommunityInfo findById(java.lang.Long id) {
		log.debug("getting CommunityInfo instance with id: " + id);
		try {
			CommunityInfo instance = (CommunityInfo) getHibernateTemplate()
					.get("org.heatmanagment.hibernate.domain.CommunityInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<CommunityInfo> findByExample(CommunityInfo instance) {
		log.debug("finding CommunityInfo instance by example");
		try {
			List<CommunityInfo> results = (List<CommunityInfo>) getHibernateTemplate()
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
		log.debug("finding CommunityInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CommunityInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<CommunityInfo> findByCmtname(Object cmtname) {
		return findByProperty(CMTNAME, cmtname);
	}

	public List<CommunityInfo> findByBriefname(Object briefname) {
		return findByProperty(BRIEFNAME, briefname);
	}

	public List<CommunityInfo> findByCmtaddress(Object cmtaddress) {
		return findByProperty(CMTADDRESS, cmtaddress);
	}

	public List<CommunityInfo> findByGis(Object gis) {
		return findByProperty(GIS, gis);
	}

	public List<CommunityInfo> findByPicaddress(Object picaddress) {
		return findByProperty(PICADDRESS, picaddress);
	}

	public List<CommunityInfo> findByDesp(Object desp) {
		return findByProperty(DESP, desp);
	}

	public List<CommunityInfo> findByIsvalid(Object isvalid) {
		return findByProperty(ISVALID, isvalid);
	}

	public List findAll() {
		log.debug("finding all CommunityInfo instances");
		try {
			String queryString = "from CommunityInfo as c where c.isvalid=true";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPage(final int start, final int limit) {
		log.debug("finding all CommunityInfo instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from CommunityInfo as d where d.isvalid=:valid";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					query.setBoolean("valid", true);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all CommunityInfo with boundary failed", re);
			throw re;
		}
	}

	public Long count() {
		log.debug("count CommunityInfos");
		String hql = "select count(*) from CommunityInfo where isvalid=true";
		return (Long) getHibernateTemplate().find(hql).listIterator().next();
	}

	public void remove(Long id) {
		CommunityInfo cmt = new CommunityInfo();
		cmt.setCmtid(id);
		cmt.setIsvalid(false);
		attachDirty(cmt);
	}

	public CommunityInfo merge(CommunityInfo detachedInstance) {
		log.debug("merging CommunityInfo instance");
		try {
			CommunityInfo result = (CommunityInfo) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CommunityInfo instance) {
		log.debug("attaching dirty CommunityInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CommunityInfo instance) {
		log.debug("attaching clean CommunityInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CommunityInfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (CommunityInfoDAO) ctx.getBean("CommunityInfoDAO");
	}
}
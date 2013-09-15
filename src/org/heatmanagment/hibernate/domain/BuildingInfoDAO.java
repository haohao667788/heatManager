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
 * BuildingInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.BuildingInfo
 * @author MyEclipse Persistence Tools
 */

public class BuildingInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(BuildingInfoDAO.class);
	// property constants
	public static final String BLDNAME = "bldname";
	public static final String ADDRESS = "address";
	public static final String HEATTYPE = "heattype";
	public static final String GIS = "gis";
	public static final String PICADDRESS = "picaddress";
	public static final String DESP = "desp";
	public static final String ISVALID = "isvalid";

	protected void initDao() {
		// do nothing
	}

	public void save(BuildingInfo transientInstance) {
		log.debug("saving BuildingInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BuildingInfo persistentInstance) {
		log.debug("deleting BuildingInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Long count() {
		log.debug("count BuildingInfos");
		String hql = "select count(*) from BuildingInfo  where isvalid=true";
		return (Long) getHibernateTemplate().find(hql).listIterator().next();
	}

	public Long countByCmt(Long cmtid) {
		log.debug("count BuildingInfos by cmtid");
		String sql = "select count(*) from building_info where cmtid=" + cmtid;
		Query query = getHibernateTemplate().getSessionFactory().openSession()
				.createSQLQuery(sql);
		return new Long(query.list().size());
	}

	public BuildingInfo findById(java.lang.Long id) {
		log.debug("getting BuildingInfo instance with id: " + id);
		try {
			BuildingInfo instance = (BuildingInfo) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.BuildingInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BuildingInfo> findByExample(BuildingInfo instance) {
		log.debug("finding BuildingInfo instance by example");
		try {
			List<BuildingInfo> results = (List<BuildingInfo>) getHibernateTemplate()
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
		log.debug("finding BuildingInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BuildingInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BuildingInfo> findByBldname(Object bldname) {
		return findByProperty(BLDNAME, bldname);
	}

	public List<BuildingInfo> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<BuildingInfo> findByHeattype(Object heattype) {
		return findByProperty(HEATTYPE, heattype);
	}

	public List<BuildingInfo> findByGis(Object gis) {
		return findByProperty(GIS, gis);
	}

	public List<BuildingInfo> findByPicaddress(Object picaddress) {
		return findByProperty(PICADDRESS, picaddress);
	}

	public List<BuildingInfo> findByDesp(Object desp) {
		return findByProperty(DESP, desp);
	}

	public List<BuildingInfo> findByIsvalid(Object isvalid) {
		return findByProperty(ISVALID, isvalid);
	}

	public List findAll() {
		log.debug("finding all BuildingInfo instances");
		try {
			String queryString = "from BuildingInfo as c where c.isvalid=true";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findAllByCmtId(Long id) {
		log.debug("finding all BuildingInfo instances by cmtId");
		try {
			String sql = "select * from building_info where isvalid=1 and cmtid="
					+ id;
			Query query = getHibernateTemplate().getSessionFactory()
					.openSession().createSQLQuery(sql);
			return query.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPageByCmtid(Long id, Long start, Long limit) {
		log.debug("finding all BuildingInfo instances");
		try {
			String sql = "select * from building_info where isvalid=1 and cmtid="
					+ id;
			Query query = getHibernateTemplate().getSessionFactory()
					.openSession().createSQLQuery(sql)
					.setFirstResult(start.intValue())
					.setMaxResults(limit.intValue());
			return query.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPage(final int start, final int limit) {
		log.debug("finding all BuildingInfo instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from BuildingInfo as d where d.isvalid=:valid";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					query.setBoolean("valid", true);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all BuildingInfo with boundary failed", re);
			throw re;
		}
	}

	public void remove(Long id) {
		BuildingInfo bld = new BuildingInfo();
		bld.setBldid(id);
		bld.setIsvalid(false);
		attachDirty(bld);
	}

	public BuildingInfo merge(BuildingInfo detachedInstance) {
		log.debug("merging BuildingInfo instance");
		try {
			BuildingInfo result = (BuildingInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BuildingInfo instance) {
		log.debug("attaching dirty BuildingInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BuildingInfo instance) {
		log.debug("attaching clean BuildingInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BuildingInfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BuildingInfoDAO) ctx.getBean("BuildingInfoDAO");
	}
}
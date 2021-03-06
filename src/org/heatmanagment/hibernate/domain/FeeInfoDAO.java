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
 * FeeInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.FeeInfo
 * @author MyEclipse Persistence Tools
 */

public class FeeInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(FeeInfoDAO.class);
	// property constants
	public static final String AREA = "area";
	public static final String REALAREA = "realarea";
	public static final String FEEAREA = "feearea";
	public static final String FEETYPE = "feetype";
	public static final String FEERATE = "feerate";
	public static final String DISCOUNT = "discount";
	public static final String REDUCEFEE = "reducefee";
	public static final String HEATSTATE = "heatstate";
	public static final String HEATBASE = "heatbase";
	public static final String HEATRATE = "heatrate";
	public static final String HOUSETYPE = "housetype";
	public static final String DESP = "desp";
	public static final String ISVALID = "isvalid";

	protected void initDao() {
		// do nothing
	}

	public void save(FeeInfo transientInstance) {
		log.debug("saving FeeInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FeeInfo persistentInstance) {
		log.debug("deleting FeeInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FeeInfo findById(java.lang.Long id) {
		log.debug("getting FeeInfo instance with id: " + id);
		try {
			FeeInfo instance = (FeeInfo) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.FeeInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<FeeInfo> findByExample(FeeInfo instance) {
		log.debug("finding FeeInfo instance by example");
		try {
			List<FeeInfo> results = (List<FeeInfo>) getHibernateTemplate()
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
		log.debug("finding FeeInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from FeeInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<FeeInfo> findByArea(Object area) {
		return findByProperty(AREA, area);
	}

	public List<FeeInfo> findByRealarea(Object realarea) {
		return findByProperty(REALAREA, realarea);
	}

	public List<FeeInfo> findByFeearea(Object feearea) {
		return findByProperty(FEEAREA, feearea);
	}

	public List<FeeInfo> findByFeetype(Object feetype) {
		return findByProperty(FEETYPE, feetype);
	}

	public List<FeeInfo> findByFeerate(Object feerate) {
		return findByProperty(FEERATE, feerate);
	}

	public List<FeeInfo> findByDiscount(Object discount) {
		return findByProperty(DISCOUNT, discount);
	}

	public List<FeeInfo> findByReducefee(Object reducefee) {
		return findByProperty(REDUCEFEE, reducefee);
	}

	public List<FeeInfo> findByHeatstate(Object heatstate) {
		return findByProperty(HEATSTATE, heatstate);
	}

	public List<FeeInfo> findByHeatbase(Object heatbase) {
		return findByProperty(HEATBASE, heatbase);
	}

	public List<FeeInfo> findByHeatrate(Object heatrate) {
		return findByProperty(HEATRATE, heatrate);
	}

	public List<FeeInfo> findByHousetype(Object housetype) {
		return findByProperty(HOUSETYPE, housetype);
	}

	public List<FeeInfo> findByDesp(Object desp) {
		return findByProperty(DESP, desp);
	}

	public List<FeeInfo> findByIsvalid(Object isvalid) {
		return findByProperty(ISVALID, isvalid);
	}

	public List findAll() {
		log.debug("finding all FeeInfo instances");
		try {
			String queryString = "from FeeInfo as c where c.isvalid=true";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPage(final int start, final int limit) {
		log.debug("finding all FeeInfo instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from FeeInfo as d where d.isvalid=:valid";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					query.setBoolean("valid", true);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all FeeInfo with boundary failed", re);
			throw re;
		}
	}

	public void remove(Long id) {
		FeeInfo fee = new FeeInfo();
		fee.setFeeid(id);
		fee.setIsvalid(false);
		attachDirty(fee);
	}

	public FeeInfo merge(FeeInfo detachedInstance) {
		log.debug("merging FeeInfo instance");
		try {
			FeeInfo result = (FeeInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FeeInfo instance) {
		log.debug("attaching dirty FeeInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FeeInfo instance) {
		log.debug("attaching clean FeeInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FeeInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FeeInfoDAO) ctx.getBean("FeeInfoDAO");
	}
}
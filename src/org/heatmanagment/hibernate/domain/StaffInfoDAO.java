package org.heatmanagment.hibernate.domain;

import java.sql.SQLException;
import java.sql.Timestamp;
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
 * StaffInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.StaffInfo
 * @author MyEclipse Persistence Tools
 */

public class StaffInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(StaffInfoDAO.class);
	// property constants
	public static final String STFNAME = "stfname";
	public static final String STFNUMBER = "stfnumber";
	public static final String PHONE = "phone";
	public static final String LOGINNAME = "loginname";
	public static final String PWD = "pwd";
	public static final String DEPARTMENT = "department";
	public static final String VERIFYTYPE = "verifytype";
	public static final String ISVALID = "isvalid";

	protected void initDao() {
		// do nothing
	}

	public void save(StaffInfo transientInstance) {
		log.debug("saving StaffInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(StaffInfo persistentInstance) {
		log.debug("deleting StaffInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StaffInfo findById(java.lang.Long id) {
		log.debug("getting StaffInfo instance with id: " + id);
		try {
			StaffInfo instance = (StaffInfo) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.StaffInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<StaffInfo> findByExample(StaffInfo instance) {
		log.debug("finding StaffInfo instance by example");
		try {
			List<StaffInfo> results = (List<StaffInfo>) getHibernateTemplate()
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
		log.debug("finding StaffInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from StaffInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<StaffInfo> findByStfname(Object stfname) {
		return findByProperty(STFNAME, stfname);
	}

	public List<StaffInfo> findByStfnumber(Object stfnumber) {
		return findByProperty(STFNUMBER, stfnumber);
	}

	public List<StaffInfo> findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List<StaffInfo> findByLoginname(Object loginname) {
		return findByProperty(LOGINNAME, loginname);
	}

	public List<StaffInfo> findByPwd(Object pwd) {
		return findByProperty(PWD, pwd);
	}

	public List<StaffInfo> findByDepartment(Object department) {
		return findByProperty(DEPARTMENT, department);
	}

	public List<StaffInfo> findByVerifytype(Object verifytype) {
		return findByProperty(VERIFYTYPE, verifytype);
	}

	public List<StaffInfo> findByIsvalid(Object isvalid) {
		return findByProperty(ISVALID, isvalid);
	}

	public List findAll() {
		log.debug("finding all StaffInfo instances");
		try {
			String queryString = "from StaffInfo as c where c.isvalid=true";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPage(final int start, final int limit) {
		log.debug("finding all StaffInfo instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from StaffInfo as d where d.isvalid=:valid";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					query.setBoolean("valid", true);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all StaffInfo with boundary failed", re);
			throw re;
		}
	}

	public Long count() {
		log.debug("count CountyInfos");
		String hql = "select count(*) from StaffInfo where isvalid=true";
		return (Long) getHibernateTemplate().find(hql).listIterator().next();
	}

	public void remove(Long id) {
		StaffInfo stf = new StaffInfo();
		stf.setStfid(id);
		stf.setIsvalid(false);
		attachDirty(stf);
	}

	public StaffInfo merge(StaffInfo detachedInstance) {
		log.debug("merging StaffInfo instance");
		try {
			StaffInfo result = (StaffInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(StaffInfo instance) {
		log.debug("attaching dirty StaffInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StaffInfo instance) {
		log.debug("attaching clean StaffInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StaffInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (StaffInfoDAO) ctx.getBean("StaffInfoDAO");
	}
}
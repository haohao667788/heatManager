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
 * ProjectInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.ProjectInfo
 * @author MyEclipse Persistence Tools
 */

public class ProjectInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ProjectInfoDAO.class);
	// property constants
	public static final String PJTNUM = "pjtnum";
	public static final String PJTNAME = "pjtname";
	public static final String MIDDLE = "middle";
	public static final String DEPARTMENTNAME = "departmentname";
	public static final String DESP = "desp";

	protected void initDao() {
		// do nothing
	}

	public void save(ProjectInfo transientInstance) {
		log.debug("saving ProjectInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ProjectInfo persistentInstance) {
		log.debug("deleting ProjectInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProjectInfo findById(java.lang.Long id) {
		log.debug("getting ProjectInfo instance with id: " + id);
		try {
			ProjectInfo instance = (ProjectInfo) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.ProjectInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ProjectInfo> findByExample(ProjectInfo instance) {
		log.debug("finding ProjectInfo instance by example");
		try {
			List<ProjectInfo> results = (List<ProjectInfo>) getHibernateTemplate()
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
		log.debug("finding ProjectInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ProjectInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ProjectInfo> findByPjtnum(Object pjtnum) {
		return findByProperty(PJTNUM, pjtnum);
	}

	public List<ProjectInfo> findByPjtname(Object pjtname) {
		return findByProperty(PJTNAME, pjtname);
	}

	public List<ProjectInfo> findByMiddle(Object middle) {
		return findByProperty(MIDDLE, middle);
	}

	public List<ProjectInfo> findByDepartmentname(Object departmentname) {
		return findByProperty(DEPARTMENTNAME, departmentname);
	}

	public List<ProjectInfo> findByDesp(Object desp) {
		return findByProperty(DESP, desp);
	}

	public List findAll() {
		log.debug("finding all ProjectInfo instances");
		try {
			String queryString = "from ProjectInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPage(final int start, final int limit) {
		log.debug("finding all ProjectInfo instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from ProjectInfo";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all ProjectInfo with boundary failed", re);
			throw re;
		}
	}

	public ProjectInfo merge(ProjectInfo detachedInstance) {
		log.debug("merging ProjectInfo instance");
		try {
			ProjectInfo result = (ProjectInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ProjectInfo instance) {
		log.debug("attaching dirty ProjectInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProjectInfo instance) {
		log.debug("attaching clean ProjectInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProjectInfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProjectInfoDAO) ctx.getBean("ProjectInfoDAO");
	}
}
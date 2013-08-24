package org.heatmanagment.hibernate.domain;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class ProjectInfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ProjectInfoDAO.class);
	// property constants
	public static final String PJTNAME = "pjtname";
	public static final String DESP = "desp";
	public static final String COMM = "comm";

	public void save(ProjectInfo transientInstance) {
		log.debug("saving ProjectInfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ProjectInfo persistentInstance) {
		log.debug("deleting ProjectInfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProjectInfo findById(java.lang.Long id) {
		log.debug("getting ProjectInfo instance with id: " + id);
		try {
			ProjectInfo instance = (ProjectInfo) getSession().get(
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
			List<ProjectInfo> results = (List<ProjectInfo>) getSession()
					.createCriteria(
							"org.heatmanagment.hibernate.domain.ProjectInfo")
					.add(create(instance)).list();
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
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ProjectInfo> findByPjtname(Object pjtname) {
		return findByProperty(PJTNAME, pjtname);
	}

	public List<ProjectInfo> findByDesp(Object desp) {
		return findByProperty(DESP, desp);
	}

	public List<ProjectInfo> findByComm(Object comm) {
		return findByProperty(COMM, comm);
	}

	public List findAll() {
		log.debug("finding all ProjectInfo instances");
		try {
			String queryString = "from ProjectInfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ProjectInfo merge(ProjectInfo detachedInstance) {
		log.debug("merging ProjectInfo instance");
		try {
			ProjectInfo result = (ProjectInfo) getSession().merge(
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
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProjectInfo instance) {
		log.debug("attaching clean ProjectInfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
package com.zfh.app.core.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.zfh.app.core.BathUpdateOptions;
import com.zfh.app.core.service.BaseMongoService;

public abstract class BaseMongoServiceImpl<T> implements BaseMongoService<T> {

	protected abstract Class<T> getEntityClass();

	@Autowired
	protected MongoTemplate mongoTemplate;

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public T save(T entity) {
		mongoTemplate.save(entity);
		return entity;
	}

	@Override
	public T update(T entity) {
		Map<String, Object> map = null;
		try {
			map = parseEntity(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String id = null;
		Object value = null;
		Update update = new Update();
		if (map != null && map.size() > 0) {
			for (String key : map.keySet()) {
				if (key.startsWith("{")) {
					id = key.substring(key.indexOf("{") + 1, key.indexOf("}"));
					value = map.get(key);
				} else {
					if (map.get(key) != null && map.get(key) != "")
						update.set(key, map.get(key));
				}
			}
		}
		mongoTemplate.updateFirst(new Query().addCriteria(Criteria.where(id).is(value)), update, getEntityClass());
		return entity;
	}

	@Override
	public void delete(Object[] ids) {
		if (ids != null && ids.length > 0) {
			for (Object id : ids) {
				mongoTemplate.remove(mongoTemplate.findById(id, getEntityClass()));
			}
		}
	}

	@Override
	public void delete(Object id) {
		mongoTemplate.remove(mongoTemplate.findById(id, getEntityClass()));
	}

	@Override
	public T getById(Object id) {
		return mongoTemplate.findById(id, getEntityClass());
	}

	@Override
	public List<T> findAll() {
		return mongoTemplate.findAll(getEntityClass());
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<T> findAll(String order) {
		List<Order> orderlist = parseOrder(order);
		if (orderlist == null || orderlist.size() == 0) {
			return findAll();
		}

		return mongoTemplate.find(new Query().with(new Sort(orderlist)), getEntityClass());
	}

	@Override
	public List<T> findByProp(String propName, Object value) {
		return findByProp(propName, value, null);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<T> findByProp(String propName, Object value, String order) {
		Query query = new Query();
		query.addCriteria(Criteria.where(propName).is(value));
		List<Order> orderlist = parseOrder(order);
		if (orderlist != null && orderlist.size() > 0) {
			query.with(new Sort(orderlist));
		}
		return mongoTemplate.find(query, getEntityClass());
	}

	@Override
	public List<T> findByProps(String[] propName, Object[] values) {
		return findByProps(propName, values, null);
	}

	@Override
	public List<T> findByProps(String[] propName, Object[] values, String order) {
		Query query = createQuery(propName, values, order);
		return mongoTemplate.find(query, getEntityClass());
	}

	@Override
	public T uniqueByProp(String propName, Object value) {
		return mongoTemplate.findOne(new Query().addCriteria(Criteria.where(propName).is(value)), getEntityClass());
	}

	@Override
	public T uniqueByProps(String[] propName, Object[] values) {
		Query query = createQuery(propName, values, null);
		return mongoTemplate.findOne(query, getEntityClass());
	}

	@Override
	public int countByCondition(String[] params, Object[] values) {
		Query query = createQuery(params, values, null);
		Long count = mongoTemplate.count(query, getEntityClass());
		return count.intValue();
	}

	protected Map<String, Object> parseEntity(T t) throws Exception {
		Map<String, Object> map = new HashMap<>();
		String id = "";
		Field[] declaredFields = getEntityClass().getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.isAnnotationPresent(Id.class)) {
				field.setAccessible(true);
				map.put("{" + field.getName() + "}", field.get(t));
				id = field.getName();
				break;
			}
		}

		Method[] declaredMethods = getEntityClass().getDeclaredMethods();
		if (declaredFields != null && declaredFields.length > 0) {
			for (Method method : declaredMethods) {
				if (method.getName().startsWith("get") && method.getModifiers() == Modifier.PUBLIC) {
					String fieldName = parse2FieldName(method.getName());
					if (!fieldName.equals(id)) {
						map.put(fieldName, method.invoke(t));
					}
				}
			}
		}
		return map;
	}

	private String parse2FieldName(String method) {
		String name = method.replace("get", "");
		name = name.substring(0, 1).toLowerCase() + name.substring(1);
		return name;
	}

	@SuppressWarnings("deprecation")
	public Query createQuery(String[] propName, Object[] values, String order) {
		Query query = new Query();
		// where
		if (propName != null && values != null) {
			for (int i = 0; i < propName.length; i++) {
				query.addCriteria(Criteria.where(propName[i]).is(values[i]));
			}
		}

		List<Order> orderlist = parseOrder(order);
		if (orderlist != null && orderlist.size() > 0) {
			query.with(new Sort(orderlist));
		}
		return query;
	}

	public List<Order> parseOrder(String order) {
		List<Order> list = null;
		if (order != null && !"".equals(order)) {
			list = new ArrayList<>();
			String[] fields = order.split(",");
			Order o = null;
			String[] items = null;
			for (int i = 0; i < fields.length; i++) {
				if (fields[i] == null) {
					continue;
				}
				items = fields[i].split(" ");
				if (items.length == 1) {
					o = new Order(Direction.ASC, items[0]);
				} else if (items.length == 2) {
					o = new Order("desc".equalsIgnoreCase(items[1]) ? Direction.DESC : Direction.ASC, items[0]);
				} else {
					throw new RuntimeException("order field parse error");
				}
				list.add(o);
			}
		}
		return list;
	}

	@Override
	public Page<T> findPage(PageRequest pageReq) {
		Query query = new Query();
		query.with(pageReq);
		List<T> resultList = mongoTemplate.find(query, getEntityClass());
		Long total = mongoTemplate.count(new Query(), getEntityClass());
		if (resultList == null)
			resultList = new ArrayList<T>();
		return new PageImpl<T>(resultList, pageReq, total.intValue());
	}

	@Override
	public Page<T> findPage(PageRequest pageReq, String propName, Object value) {
		Query query = new Query();
		query.addCriteria(Criteria.where(propName).is(value));
		query.with(pageReq);
		List<T> resultList = mongoTemplate.find(query, getEntityClass());
		int total = countByCondition(new String[] { propName }, new Object[] { value });
		return new PageImpl<T>(resultList, pageReq, total);
	}

	@Override
	public Page<T> findPage(PageRequest pageReq, String[] propName, Object[] values, String order) {
		Query query = createQuery(propName, values, order);
		query.with(pageReq);
		List<T> resultList = mongoTemplate.find(query, getEntityClass());
		int total = countByCondition(propName, values);
		return new PageImpl<T>(resultList, pageReq, total);
	}

	@Override
	public List<T> findByProp(String propName, Object value, String[] includeFields, String[] excludeFields) {
		Query query = new Query();
		query.addCriteria(Criteria.where(propName).is(value));
		query.with(new Sort(new Order(Direction.DESC, "_id")));
		if (includeFields != null && includeFields.length > 0) {
			for (int i = 0; i < includeFields.length; i++) {
				query.fields().include(includeFields[i]);
			}
		} else {
			if (excludeFields != null && excludeFields.length > 0) {
				for (int i = 0; i < excludeFields.length; i++) {
					query.fields().exclude(excludeFields[i]);
				}
			}
		}

		return mongoTemplate.find(query, getEntityClass());
	}

	@Override
	public List<T> findByProps(String[] propName, Object[] values, String[] includeFields, String[] excludeFields) {
		Query query = new Query();
		if (propName != null && values != null) {
			for (int i = 0; i < propName.length; i++) {
				query.addCriteria(Criteria.where(propName[i]).is(values[i]));
			}
		}
		// includeFields 和 excludeFields 不能同时使用
		if (includeFields != null && includeFields.length > 0) {
			for (int i = 0; i < includeFields.length; i++) {
				query.fields().include(includeFields[i]);
			}
		} else {
			if (excludeFields != null && excludeFields.length > 0) {
				for (int i = 0; i < excludeFields.length; i++) {
					query.fields().exclude(excludeFields[i]);
				}
			}
		}

		return mongoTemplate.find(query, getEntityClass());
	}

	@Override
	public Page<T> findPage(PageRequest pageReq, String[] includeFields, String[] excludeFields) {
		Query query = new Query();
		query.with(pageReq);
		// includeFields 和 excludeFields 不能同时使用
		if (includeFields != null && includeFields.length > 0) {
			for (int i = 0; i < includeFields.length; i++) {
				query.fields().include(includeFields[i]);
			}
		} else {
			if (excludeFields != null && excludeFields.length > 0) {
				for (int i = 0; i < excludeFields.length; i++) {
					query.fields().exclude(excludeFields[i]);
				}
			}
		}
		List<T> resultList = mongoTemplate.find(query, getEntityClass());
		Long total = mongoTemplate.count(new Query(), getEntityClass());
		if (resultList == null)
			resultList = new ArrayList<T>();
		return new PageImpl<T>(resultList, pageReq, total.intValue());
	}

	@Override
	public Page<T> findPage(PageRequest pageReq, String propName, Object value, String[] includeFields,
			String[] excludeFields) {
		Query query = new Query();
		query.addCriteria(Criteria.where(propName).is(value));
		query.with(pageReq);
		// includeFields 和 excludeFields 不能同时使用
		if (includeFields != null && includeFields.length > 0) {
			for (int i = 0; i < includeFields.length; i++) {
				query.fields().include(includeFields[i]);
			}
		} else {
			if (excludeFields != null && excludeFields.length > 0) {
				for (int i = 0; i < excludeFields.length; i++) {
					query.fields().exclude(excludeFields[i]);
				}
			}
		}
		List<T> resultList = mongoTemplate.find(query, getEntityClass());
		int total = countByCondition(new String[] { propName }, new Object[] { value });
		return new PageImpl<T>(resultList, pageReq, total);
	}

	@Override
	public Page<T> findPage(PageRequest pageReq, String[] propName, Object[] values, String[] includeFields,
			String[] excludeFields) {
		Query query = createQuery(propName, values, null);
		query.with(pageReq);
		// includeFields 和 excludeFields 不能同时使用
		if (includeFields != null && includeFields.length > 0) {
			for (int i = 0; i < includeFields.length; i++) {
				query.fields().include(includeFields[i]);
			}
		} else {
			if (excludeFields != null && excludeFields.length > 0) {
				for (int i = 0; i < excludeFields.length; i++) {
					query.fields().exclude(excludeFields[i]);
				}
			}
		}
		List<T> resultList = mongoTemplate.find(query, getEntityClass());
		int total = countByCondition(propName, values);
		return new PageImpl<T>(resultList, pageReq, total);
	}

	@Override
	public int bathUpdate(List<BathUpdateOptions> options) {
		String collName = mongoTemplate.getCollectionName(getEntityClass());
		return doBathUpdate(mongoTemplate.getCollection(collName), collName, options, false);
	}

	public static int doBathUpdate(DBCollection dbCollection, String collName, List<BathUpdateOptions> options,
			boolean ordered) {
		DBObject command = new BasicDBObject();
		command.put("update", collName);
		List<BasicDBObject> updateList = new ArrayList<BasicDBObject>();
		for (BathUpdateOptions option : options) {
			BasicDBObject update = new BasicDBObject();
			update.put("q", option.getQuery().getQueryObject());
			update.put("u", option.getUpdate().getUpdateObject());
			update.put("upsert", option.isUpsert());
			update.put("multi", option.isMulti());
			updateList.add(update);
		}
		command.put("updates", updateList);
		command.put("ordered", ordered);
		CommandResult commandResult = dbCollection.getDB().command(command);
		if (commandResult.get("n") == null) {
			throw new RuntimeException("commandResult error: " + JSON.toJSONString(commandResult));
		}
		return Integer.parseInt(commandResult.get("n").toString());
	}
}
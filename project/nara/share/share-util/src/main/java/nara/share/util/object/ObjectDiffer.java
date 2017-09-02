package nara.share.util.object;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nara.share.exception.NaraException;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;

public class ObjectDiffer {
	
	private PropertyUtilsBean propertyBean;
	private Object object1;
	private Object object2;
	private Set<String> properties;
	
	public ObjectDiffer() {
		 this.propertyBean = BeanUtilsBean.getInstance().getPropertyUtils();
	}
	
	public ObjectDiffer(Object object1, Object object2) {
		this();
		this.object1 = object1;
		this.object2 = object2;
	}
	
	public ObjectDiffer(Object object1, Object object2, Set<String> properties) {
		this(object1, object2);
		this.properties = properties;
	}
	
	/**
	 * setter
	 */
	public void setObjects(Object object1, Object object2) {
		this.object1 = object1;
		this.object2 = object2;
	}

	public void setProperties(Set<String> properties) {
		this.properties = properties;
	}
	
	public void addProperty(String property) {
		if (this.properties == null || this.properties.isEmpty()) {
			this.properties = new HashSet<>();
		}
		properties.add(property);
	}

	public boolean hasDifference()  {
		try {
			if (object1 == null || object2 == null){
				throw new IllegalArgumentException("The object must not be null");
			}
			
			if (object1.getClass()  != object2.getClass()){
				throw new IllegalArgumentException("The objects type must same");
			}
			
			if (isPrimitive(object1)) 
				throw new IllegalArgumentException("The objects type must not be primitive");

			if (properties == null || properties.isEmpty()) {
				return isDifferentObjects(object1, object2);
			}

			else {
				PropertyDescriptor[] targetPds = propertyBean.getPropertyDescriptors(object1.getClass());
				for (PropertyDescriptor desc : targetPds) {
					if (desc.getName().equals("class") || desc.getName().equals("declaringClass"))
						continue;
					for (String property : properties) {
						if (property.equals(desc.getName())) {
							Object objectValue1 = propertyBean.getProperty(object1, property);
							Object objectValue2 = propertyBean.getProperty(object2, property);
							
							if (isDifferentObjects(objectValue1, objectValue2)) {
								return true;
							}
						}
					}
				}
				return false;
			}
		}
		catch (IllegalAccessException e) {
			throw new NaraException("ObjectDiffer Fail");
		} catch (InvocationTargetException e) {
			throw new NaraException("ObjectDiffer Fail");
		} catch (NoSuchMethodException e) {
			throw new NaraException("ObjectDiffer Fail");
		} 
	}

	public List<String> getDifferences() {
		try {
			List<String> differentPropertyList = new ArrayList<String>();
			
			if (object1 == null || object2 == null)
				throw new IllegalArgumentException("The object must not be null");
			
			if (object1.getClass()  != object2.getClass())
				throw new IllegalArgumentException("The objects type must same");
			
			if (isPrimitive(object1)) 
				throw new IllegalArgumentException("The objects type must not be primitive");
			
			PropertyDescriptor[] targetPds = propertyBean.getPropertyDescriptors(object1.getClass());
			
			for (PropertyDescriptor desc : targetPds) {
				if (desc.getName().equals("class") || desc.getName().equals("declaringClass"))
					continue;
				//ã�����ϴ� property�� ���ξ�����
				if (properties == null || properties.isEmpty()) {
					Object objectValue1 = propertyBean.getProperty(object1, desc.getName()); 
					Object objectValue2 = propertyBean.getProperty(object2, desc.getName());
					
					if (isDifferentObjects(objectValue1, objectValue2)) {
						differentPropertyList.add(desc.getName());
					}
				}
				// ���� ������
				else {
					for (String property : properties) {
						if (property.equals(desc.getName())) {
							Object objectValue1 = propertyBean.getProperty(object1, property); 
							Object objectValue2 = propertyBean.getProperty(object2, property);
							
							if (isDifferentObjects(objectValue1, objectValue2)) {
								differentPropertyList.add(property);
							}
						}
					}
				}
			}
			return differentPropertyList;
		}
		catch (IllegalAccessException e) {
			throw new NaraException("ObjectDiffer Fail");
		} catch (InvocationTargetException e) {
			throw new NaraException("ObjectDiffer Fail");
		} catch (NoSuchMethodException e) {
			throw new NaraException("ObjectDiffer Fail");
		} 
	}
	
	private boolean isDifferentObjects(Object object1, Object object2){
		try {
			if ((object1 == null && object2 != null)) {
				return isEmpty(object2);
			}
			if ((object1 != null && object2 == null)) {
				return isEmpty(object1);
			}
			
			if (object1 != null && object2 != null) {
				//Primitive
				if (isPrimitive(object1)) {
					if (!object1.equals(object2))
						return true; 
				}
				
				//Collection 
				if (object1 instanceof Collection) {
					if (isDifferentForCollection((Collection)object1, (Collection)object2))
						return true;
				}
				
				//Map
				if (object1 instanceof Map) {
					if (isDifferentForMap((Map)object1, (Map)object2))
						return true;
				}
				
				//Object
				PropertyDescriptor[] targetPds = propertyBean.getPropertyDescriptors(object1.getClass());
				for (PropertyDescriptor desc : targetPds) {
					if (desc.getName().equals("class") || desc.getName().equals("declaringClass"))
						continue;
					
					Object objectValue1 = propertyBean.getProperty(object1, desc.getName()); 
					Object objectValue2 = propertyBean.getProperty(object2, desc.getName()); 
				
					if (isDifferentObjects(objectValue1, objectValue2))
						return true;
				}
			}
			return false;
		}
		catch (IllegalAccessException e) {
			throw new NaraException("ObjectDiffer Fail");
		} catch (InvocationTargetException e) {
			throw new NaraException("ObjectDiffer Fail");
		} catch (NoSuchMethodException e) {
			throw new NaraException("ObjectDiffer Fail");
		} 
	}

	@SuppressWarnings({ "rawtypes" })
	private boolean isDifferentForCollection(Collection collection, Collection collection2)  {
		if ((collection == null)) {
			if (collection2 != null && !collection2.isEmpty()) {
				return true;
			}
		}
		if ((collection2 == null)) {
			if (collection != null && !collection.isEmpty()) {
				return true;
			}
		}
		
		if (collection != null && collection2 != null) {
			if (collection.isEmpty() && collection2.isEmpty()) {
				return false;
			}
			
			if (collection.size() != collection2.size()) {
				return true;
			}
			Object generic =collection.iterator().next(); 
			Iterator it = collection.iterator();
			Iterator it2 = collection2.iterator();

			if (isPrimitive(generic)) {
				it = sortIterator(collection.iterator());
				it2 = sortIterator(collection2.iterator());
			}
			
			while (it.hasNext() && it2.hasNext()){
				Object value1 = it.next();
				Object value2 = it2.next();
				if (isDifferentObjects(value1, value2))
					return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings({ "rawtypes" })
	private boolean isDifferentForMap(Map map1, Map map2) {
		if ((map1 == null)) {
			if (map2 != null && !map2.isEmpty()) {
				return true;
			}
		}
		if ((map2 == null)) {
			if (map1 != null && !map1.isEmpty()) {
				return true;
			}
		}
		
		if (map1 != null && map2 != null) {
			if (map1.isEmpty() && map2.isEmpty()) {
				return false;
			}
			
			if (map1.size() != map2.size()) {
				return true;
			}
			
			Set set = map1.keySet();
			Iterator it = set.iterator();
			
			while (it.hasNext()){
				String key = (String)it.next();
				if (isDifferentObjects(map1.get(key), map2.get(key)))
					return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Iterator sortIterator(Iterator iterator) {
		List list = new ArrayList();
		
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		Collections.sort(list);
		
		return list.iterator();
	}
	
	private boolean isPrimitive(Object object) {
		if ((object instanceof String) || (object instanceof Integer) || (object instanceof Long) ||  (object instanceof Double) || object.getClass().isPrimitive()) {
			return true;
		}
		return false;
	}

	private boolean isEmpty(Object object) {
		if (isPrimitive(object)) {
			if (!object.equals(""))
				return false;
		}
		else if (object instanceof Collection) {
			if (!((Collection)object).isEmpty())
				return false;
		}
		else if (object instanceof Map) {
			if (!((Map)object).isEmpty())
				return false;
		}
		return true;
	}
}

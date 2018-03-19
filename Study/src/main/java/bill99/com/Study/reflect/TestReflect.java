package bill99.com.Study.reflect;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class TestReflect {

	public static void main(String[] args) throws IntrospectionException {
		BeanInfo beanInfo=Introspector.getBeanInfo(ReflectDemo.class);
		for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
			System.out.println("pd.getName()="+pd.getName());
//			System.out.println("pd.getReadMethod()="+pd.getReadMethod().getName());
//			System.out.println("pd.getWriteMethod()="+pd.getWriteMethod().getName());
		}
	}

}

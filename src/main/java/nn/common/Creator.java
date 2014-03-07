package nn.common;

import java.lang.reflect.ParameterizedType;

public class Creator {

	@SuppressWarnings("unchecked")
	protected <T> T createObject(int i) {
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[i];
		T result = null;
		try {
			result = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}

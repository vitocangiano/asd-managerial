package it.authentication.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import it.authentication.model.dto.ResponseDTO;

@Service
@Primary
public class BaseService {
	int MAX_RESULTS = 5;
	Duration TIMEOUT = Duration.ofSeconds(20L);

	@Autowired
	Validator validator;

	protected it.authentication.model.dto.ResponseDTO response = new ResponseDTO();

	protected void setError(Exception e) {
		setError(e.getMessage());
	}

	protected void setError(InvocationTargetException e) {
		setError(e.getCause().toString());
	}

	protected void setError(String error) {
		contentReset();
		response.setStatus(false);
		response.setError(response.getError() + "\r\n" + error);
	}

	protected void contentReset() {
		response.setContent(new HashMap<String, Object>());
	}

	protected void responseReset() {
		response = new ResponseDTO();
	}

	protected void checkOrThrow(String message) throws Exception {
		if (!response.getStatus()) {
			throw new Exception(message);
		}
	}

	protected StringBuilder valid(Object entity) {
		StringBuilder eLst = new StringBuilder();
		Set<ConstraintViolation<Object>> valid = validator.validate(entity);
		for (ConstraintViolation<Object> e : valid) {
			eLst.append(e.getPropertyPath() + ": " + e.getMessage() + "\r\n");
		}
		return eLst;
	}

	public ResponseDTO exec(Object service, String method, Object param) {
		return exec(service, method, param, true);
	}

	public ResponseDTO exec(Object service, String method, Object param, Boolean validation) {
		responseReset();
		try {
			if (validation) {
				StringBuilder eLst = valid(param);
				if (eLst.length() > 0) {
					throw new Exception(eLst.toString());
				}
			}
			Method m = service.getClass().getMethod(method, param.getClass());
			response = (ResponseDTO) m.invoke(service, param);
		} catch (InvocationTargetException e) {
			setError(e);
		} catch (Exception ex) {
			setError(ex);
		}
		return response;
	}

	public void setFieldValue(Object source, String property, String value, Boolean throwEx) throws Exception {
		try {
			Field f = source.getClass().getDeclaredField(property);
			f.setAccessible(true);
			f.set(source, value);
		} catch (Exception e) {
			if (throwEx) {
				throw e;
			}
			System.out.println(e);
		}
	}

}

package org.heatmanagment.hibernate.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.heatmanagment.spring.entity.SuccessOut;
import org.springframework.stereotype.Service;

@Service("mapperTool")
public class MapperTool {

	private ObjectMapper mapper;
	private SuccessOut out;

	private static final String SUC_RETORT = "{\"success\":true,\"message\":\"\"}";

	public MapperTool() {
		this.mapper = new ObjectMapper();
		this.out = new SuccessOut();
	}

	public String successRetort() {
		return SUC_RETORT;
	}

	public String failRetort(String msg) {
		out.setSuccess(false);
		out.setMessage(msg);
		try {
			return this.mapper.writeValueAsString(this.out);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.out.setMessage(e.getMessage());
				return this.mapper.writeValueAsString(this.out);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public String result(Object out) {
		String outCome = null;
		try {
			outCome = this.mapper.writeValueAsString(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outCome;
	}
}

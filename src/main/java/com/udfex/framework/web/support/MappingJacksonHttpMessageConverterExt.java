package com.udfex.framework.web.support;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.udfex.framework.mybatis.page.PageModel;
import com.udfex.framework.web.vo.JsonVo;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class MappingJacksonHttpMessageConverterExt extends MappingJackson2HttpMessageConverter {

	private	String jsonPrefix;

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
		// The following has been deprecated as late as Jackson 2.2 (April 2013);
		// preserved for the time being, for Jackson 2.0/2.1 compatibility.
		@SuppressWarnings("deprecation")
		JsonGenerator jsonGenerator =
				super.getObjectMapper().getFactory().createGenerator(outputMessage.getBody(), encoding);

		// A workaround for JsonGenerators not applying serialization features
		// https://github.com/FasterXML/jackson-databind/issues/12
		if (super.getObjectMapper().isEnabled(SerializationFeature.INDENT_OUTPUT)) {
			jsonGenerator.useDefaultPrettyPrinter();
		}

		try {
			if (this.jsonPrefix != null) {
				jsonGenerator.writeRaw(this.jsonPrefix);
			}
			if(object instanceof JsonVo){
				JsonVo jsonVo = (JsonVo) object;
				if(!jsonVo.isFormat()){
					super.getObjectMapper().writeValue(jsonGenerator, jsonVo.getResult());
				}else{
					super.getObjectMapper().writeValue(jsonGenerator, object);
				}
			}else if(object instanceof PageModel){
				PageModel pageInfo = (PageModel) object;
				JsonVo jsonVo = new JsonVo(object);
				jsonVo.setPageInfo(pageInfo.getPageInfo());
				jsonVo.setResult(pageInfo.getData());
				super.getObjectMapper().writeValue(jsonGenerator, jsonVo);
			}else{
				JsonVo jsonVo = new JsonVo(object);
				super.getObjectMapper().writeValue(jsonGenerator, jsonVo);
			}
		}
		catch (JsonProcessingException ex) {
			throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
		}
	}
	
}

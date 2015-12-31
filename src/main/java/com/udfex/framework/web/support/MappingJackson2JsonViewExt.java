package com.udfex.framework.web.support;

import com.udfex.framework.web.vo.JsonVo;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;
import java.util.Map;

/**
 * Created by Jeng on 2015/3/24.
 */
public class MappingJackson2JsonViewExt extends MappingJackson2JsonView {

    /**
     * <p>重载MappingJacksonJsonView类的filterModel方法
     * <p>修改json返回结果的显示效果
     * <p>修改之前的效果：{demoQuery:{id:123,name:‘demo’}}
     * <p>修改之后的效果：{id:123,name:‘demo’}
     */
    protected Object filterModel(final Map<String, Object> model) {
        Object result = super.filterModel(model);
        if(result instanceof Map){
            Map<?, ?> mapResult = (Map<?, ?>) result;
            if (mapResult.size() == 1) {
                Object resultModel = mapResult.values().iterator().next();
                if(resultModel instanceof List){
                    return resultModel;
                }else if(resultModel instanceof JsonVo){
                    return resultModel;
                }else{
                    return mapResult;
                }
            } else {
                return mapResult;
            }
        }else if(result instanceof JsonVo){
            return result;
        }else{
            return new JsonVo(result);
        }
    }
}

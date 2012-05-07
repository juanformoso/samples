package com.seniorgeek.samples.jetty_springmvc_json.controller.support;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;
import org.springframework.web.util.UrlPathHelper;

/**
 * Helper class that configures correctly the missing properties in the &lt;mvc:annotation-driven /&gt; spring xml
 * context tag.<br/>
 * <br/>
 * Uses {@link Autowired} to detect the default configuration, if you need to configure more than one
 * {@link AnnotationMethodHandlerAdapter} (pretty absurd), you can always use
 * {@link #setAnnotationMethodHandlerAdapter(AnnotationMethodHandlerAdapter)} to set it manually.
 * 
 * @author gkondolf
 * @author jformoso
 */
public class AnnotationMethodHandlerAdapterConfigurer
    implements InitializingBean {

    private AnnotationMethodHandlerAdapter adapter;

    private WebBindingInitializer webBindingInitializer;
    private HttpMessageConverter<?>[] messageConverters;
    private PathMatcher pathMatcher;
    private UrlPathHelper urlPathHelper;
    private MethodNameResolver methodNameResolver;
    private WebArgumentResolver[] customArgumentResolvers;
    private ModelAndViewResolver[] customModelAndViewResolvers;

    private boolean replaceMessageConverters = false;

    @Autowired
    public void setAnnotationMethodHandlerAdapter(AnnotationMethodHandlerAdapter adapter) {
        this.adapter = adapter;
    }

    public void afterPropertiesSet() throws Exception {
        if (this.webBindingInitializer != null) {
            this.adapter.setWebBindingInitializer(this.webBindingInitializer);
        }

        if (this.messageConverters != null) {
            if (this.replaceMessageConverters) {
                this.adapter.setMessageConverters(this.messageConverters);
            } else {
                this.adapter.setMessageConverters(this.mergeMessageConverters());
            }
        }

        if (this.pathMatcher != null) {
            this.adapter.setPathMatcher(this.pathMatcher);
        }

        if (this.urlPathHelper != null) {
            this.adapter.setUrlPathHelper(this.urlPathHelper);
        }

        if (this.methodNameResolver != null) {
            this.adapter.setMethodNameResolver(this.methodNameResolver);
        }

        if (this.customArgumentResolvers != null) {
            this.adapter.setCustomArgumentResolvers(this.customArgumentResolvers);
        }

        if (this.customModelAndViewResolvers != null) {
            this.adapter.setCustomModelAndViewResolvers(this.customModelAndViewResolvers);
        }
    }

    private HttpMessageConverter<?>[] mergeMessageConverters() {
        return (HttpMessageConverter<?>[]) ArrayUtils.addAll(this.messageConverters, this.adapter.getMessageConverters());
    }

    public void setWebBindingInitializer(WebBindingInitializer webBindingInitializer) {
        this.webBindingInitializer = webBindingInitializer;
    }

    public void setPathMatcher(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    public void setUrlPathHelper(UrlPathHelper urlPathHelper) {
        this.urlPathHelper = urlPathHelper;
    }

    public void setMethodNameResolver(MethodNameResolver methodNameResolver) {
        this.methodNameResolver = methodNameResolver;
    }

    public void setCustomArgumentResolver(WebArgumentResolver argumentResolver) {
        this.customArgumentResolvers = new WebArgumentResolver[] {argumentResolver};
    }

    public void setCustomArgumentResolvers(WebArgumentResolver[] argumentResolvers) {
        this.customArgumentResolvers = argumentResolvers;
    }

    public void setCustomModelAndViewResolver(ModelAndViewResolver customModelAndViewResolver) {
        this.customModelAndViewResolvers = new ModelAndViewResolver[] {customModelAndViewResolver};
    }

    public void setCustomModelAndViewResolvers(ModelAndViewResolver[] customModelAndViewResolvers) {
        this.customModelAndViewResolvers = customModelAndViewResolvers;
    }

    public void setMessageConverters(HttpMessageConverter<?>[] messageConverters) {
        this.messageConverters = messageConverters;
    }

    public void setReplaceMessageConverters(boolean replaceMessageConverters) {
        this.replaceMessageConverters = replaceMessageConverters;
    }

}

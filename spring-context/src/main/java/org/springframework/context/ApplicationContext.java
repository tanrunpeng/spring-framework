/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context;

import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.lang.Nullable;

/**
 * English:
 * <p>
 * Central interface to provide configuration for an application.
 * This is read-only while the application is running, but may be
 * reloaded if the implementation supports this.
 *
 * <p>An ApplicationContext provides:
 * <ul>
 * <li>Bean factory methods for accessing application components.
 * Inherited from {@link org.springframework.beans.factory.ListableBeanFactory}.
 * <li>The ability to load file resources in a generic fashion.
 * Inherited from the {@link org.springframework.core.io.ResourceLoader} interface.
 * <li>The ability to publish events to registered listeners.
 * Inherited from the {@link ApplicationEventPublisher} interface.
 * <li>The ability to resolve messages, supporting internationalization.
 * Inherited from the {@link MessageSource} interface.
 * <li>Inheritance from a parent context. Definitions in a descendant context
 * will always take priority. This means, for example, that a single parent
 * context can be used by an entire web application, while each servlet has
 * its own child context that is independent of that of any other servlet.
 * </ul>
 *
 * <p>In addition to standard {@link org.springframework.beans.factory.BeanFactory}
 * lifecycle capabilities, ApplicationContext implementations detect and invoke
 * {@link ApplicationContextAware} beans as well as {@link ResourceLoaderAware},
 * {@link ApplicationEventPublisherAware} and {@link MessageSourceAware} beans.
 *
 * <p></p>
 * 中文：
 * <p>Spring容器的顶层接口，这个接口在容器运行时是只读的，但是如果有这个接口的实现，在实现中是可以重新加载的
 *
 * <p>Spring容器提供的能力或者规定：
 * <ul>
 *     <li>访问容器中的组件的Bean工厂方法，继承于{@link org.springframework.beans.factory.ListableBeanFactory}接口
 *     <li>以通用方式加载文件资源的能力，继承于{@link org.springframework.core.io.ResourceLoader}接口.
 *     <li>能将事件发布到已经注册的监听器，继承于{@link ApplicationEventPublisher}接口.
 *     <li>解析消息的能力，支持国际化，继承于 {@link MessageSource} 接口
 *     <li>如果从父类容器继承，那么子类的容器定义始终是具有最高优先级。例如，父类容器可以作用于整个Web应用，同时每个servlet
 *     都有自己的子类容器，这个子类容器与其他servlet的子类容器是独立的
 * </ul>
 *
 * <p>除了支持标准的{@link org.springframework.beans.factory.BeanFactory}生命周期功能以外，
 * 容器的实现可以监测和调用{@link ApplicationContextAware}bean以及{@link ResourceLoaderAware},
 * {@link ApplicationEventPublisherAware}和{@link MessageSourceAware} beans.
 *
 * <p></p>
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @see ConfigurableApplicationContext
 * @see org.springframework.beans.factory.BeanFactory
 * @see org.springframework.core.io.ResourceLoader
 */
public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, HierarchicalBeanFactory,
		MessageSource, ApplicationEventPublisher, ResourcePatternResolver {

	/**
	 * English:
	 * <p>
	 * Return the unique id of this application context.
	 * <p></p>
	 * 中文：
	 * <p>返回唯一的容器id，如果没有定义id则返回 {@code null}
	 * @return the unique id of the context, or {@code null} if none
	 */
	@Nullable
	String getId();

	/**
	 * English:
	 * <p>
	 * Return a name for the deployed application that this context belongs to.
	 * <p></p>
	 * 中文：
	 * <p>返回唯一的容器id，如果没有定义id则返回{@code null}
	 * @return a name for the deployed application, or the empty String by default
	 */
	String getApplicationName();

	/**
	 * English:
	 * <p>
	 * Return a friendly name for this context.
	 * <p></p>
	 * 中文：
	 * <p>返回一个友好的容器名
	 * @return a display name for this context (never {@code null})
	 */
	String getDisplayName();

	/**
	 * English:
	 * <p>
	 * Return the timestamp when this context was first loaded.
	 * <p></p>
	 * 中文：
	 * <p>返回首次加载此容器时的时间戳。
	 * @return the timestamp (ms) when this context was first loaded
	 */
	long getStartupDate();

	/**
	 * English:
	 * <p>
	 * Return the parent context, or {@code null} if there is no parent
	 * and this is the root of the context hierarchy.
	 * <p></p>
	 * 中文：
	 * <p>返回父类容器，如果没有父类容器且当前是容器层次的根时返回{@code null}
	 * @return the parent context, or {@code null} if there is no parent
	 */
	@Nullable
	ApplicationContext getParent();

	/**
	 * English:
	 * <p>
	 * Expose AutowireCapableBeanFactory functionality for this context.
	 * <p>This is not typically used by application code, except for the purpose of
	 * initializing bean instances that live outside of the application context,
	 * applying the Spring bean lifecycle (fully or partly) to them.
	 * <p>Alternatively, the internal BeanFactory exposed by the
	 * {@link ConfigurableApplicationContext} interface offers access to the
	 * {@link AutowireCapableBeanFactory} interface too. The present method mainly
	 * serves as a convenient, specific facility on the ApplicationContext interface.
	 * <p><b>NOTE: As of 4.2, this method will consistently throw IllegalStateException
	 * after the application context has been closed.</b> In current Spring Framework
	 * versions, only refreshable application contexts behave that way; as of 4.2,
	 * all application context implementations will be required to comply.
	 *
	 * <p></p>
	 * 中文：
	 * <p>暴露这个容器的AutowireCapableBeanFactory功能
	 *
	 * <p>这个通常不是在容器代码使用，而是在初始化一个容器之外的bean时，
	 * 使用Spring bean的生命周期（全部的生命周期或者部分生命周期）作用于这个bean
	 *
	 * <p>或者， {@link ConfigurableApplicationContext}接口暴露出来的内部BeanFactory
	 * 也提供对{@link AutowireCapableBeanFactory}接口的访问。当前这个方法主要是在容器接口上提供一个
	 * 方便、特定的工具
	 *
	 * <p><b>说明：从4.2版本开始，如果容器已经关闭，这个方法会抛出IllegalStateException异常</b>。在当前
	 *	Spring Framework版本中只有可刷新的容器才会抛出这个异常；但是从4.2版本开始，所有的容器都应该要遵守
	 *
	 * @return the AutowireCapableBeanFactory for this context
	 * @throws IllegalStateException if the context does not support the
	 * {@link AutowireCapableBeanFactory} interface, or does not hold an
	 * autowire-capable bean factory yet (e.g. if {@code refresh()} has
	 * never been called), or if the context has been closed already
	 * @see ConfigurableApplicationContext#refresh()
	 * @see ConfigurableApplicationContext#getBeanFactory()
	 */
	AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException;

}

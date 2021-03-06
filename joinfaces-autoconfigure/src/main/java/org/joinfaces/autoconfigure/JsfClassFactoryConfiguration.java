/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.autoconfigure;

import javax.servlet.ServletContainerInitializer;

/**
 * Configuration of Jsf Class Factory.
 * @author Marcelo Fernandes
 */
public interface JsfClassFactoryConfiguration {

	/**
	 * Servlet container initializer that contains handleTypes.
	 * @return servlet container initializer
	 */
	ServletContainerInitializer getServletContainerInitializer();

	/**
	 * Another faces config resource to include in search.
	 * @return name of another faces-config.xml
	 */
	String getAnotherFacesConfig();

	/**
	 * Inform if exclude scoped annotations in search.
	 * @return if exclude scoped annotations in search
	 */
	boolean isExcludeScopedAnnotations();
}

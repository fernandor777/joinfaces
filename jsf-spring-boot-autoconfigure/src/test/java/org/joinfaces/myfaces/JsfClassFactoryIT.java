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

package org.joinfaces.myfaces;

import java.util.Set;

import javax.faces.component.html.HtmlPanelGroup;

import org.apache.myfaces.renderkit.html.HtmlGridRenderer;
import org.joinfaces.JsfClassFactory;
import org.joinfaces.NullAnotherJsfClassFactoryConfiguration;
import org.joinfaces.NullJsfClassFactoryConfiguration;
import org.junit.Test;

import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@WebAppConfiguration
public class JsfClassFactoryIT {

	@Test
	public void testJavaxFacesHtmlPanelGroupWithMyfaces() {
		MyfacesJsfClassFactoryConfiguration configuration = new MyfacesJsfClassFactoryConfiguration();

		Set<Class<?>> classes = new JsfClassFactory(configuration).getOtherClasses();
		assertThat(classes).contains(HtmlPanelGroup.class);
	}

	@Test
	public void testMyfacesHtmlGridRendererWithMyfaces() {
		MyfacesJsfClassFactoryConfiguration configuration = new MyfacesJsfClassFactoryConfiguration();

		Set<Class<?>> classes = new JsfClassFactory(configuration).getOtherClasses();
		assertThat(classes).contains(HtmlGridRenderer.class);
	}

	@Test
	public void testNullServletContextInitializer() {
		NullJsfClassFactoryConfiguration configuration = new NullJsfClassFactoryConfiguration();

		Set<Class<?>> classes = new JsfClassFactory(configuration).getAllClasses();
		assertThat(classes).doesNotContain(HtmlGridRenderer.class);
	}

	@Test
	public void testNullAnotherServletContextInitializer() {
		NullAnotherJsfClassFactoryConfiguration configuration = new NullAnotherJsfClassFactoryConfiguration();

		Set<Class<?>> classes = new JsfClassFactory(configuration).getAllClasses();
		assertThat(classes).doesNotContain(HtmlGridRenderer.class);
	}

}

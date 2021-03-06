/*
 * Copyright 2016-2016 the original author or authors.
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

package org.joinfaces.autoconfigure.primefaces;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.primefaces.webapp.filter.FileUploadFilter;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class PrimefacesFileUploadServletContextInitializerIT {

	@Autowired
	private MultipartConfigElement multipartConfigElement;

	@Autowired(required = false)
	private FileUploadFilter fileUploadFilter;

	@Autowired
	private PrimefacesFileUploadServletContextAutoConfiguration primefacesFileUploadServletContextAutoConfiguration;

	@Test
	public void testOnStartup() throws ServletException {
		PrimefacesFileUploadServletContextInitializer primefacesServletContextInitializer
			= new PrimefacesFileUploadServletContextInitializer(this.multipartConfigElement);

		MockServletRegistrationDynamic servletRegistration = new MockServletRegistrationDynamic();
		ServletContext servletContext = Mockito.mock(ServletContext.class);
		Mockito.when(servletContext.getServletRegistration("FacesServlet")).thenReturn(servletRegistration);

		primefacesServletContextInitializer.onStartup(servletContext);

		assertThat(servletRegistration.getMultipartConfig())
			.isEqualTo(this.multipartConfigElement);
	}

	@Test
	public void testOnStartup2() throws ServletException {
		PrimefacesFileUploadServletContextInitializer primefacesServletContextInitializer
			= new PrimefacesFileUploadServletContextInitializer(this.multipartConfigElement);

		MockServletRegistration servletRegistration = new MockServletRegistration();
		ServletContext servletContext = Mockito.mock(ServletContext.class);
		Mockito.when(servletContext.getServletRegistration("FacesServlet")).thenReturn(servletRegistration);

		primefacesServletContextInitializer.onStartup(servletContext);

		assertThat(servletRegistration.getMultipartConfig())
			.isNotEqualTo(this.multipartConfigElement);
	}

	@Test
	public void testFileUploadFilterNull() {
		assertThat(this.fileUploadFilter)
			.isNull();
	}

	@Test(expected = NoSuchBeanDefinitionException.class)
	public void testFileUploadFilter() throws ServletException {
		assertThat(this.primefacesFileUploadServletContextAutoConfiguration.fileUploadFilter())
			.isNotNull();
	}

	@TestConfiguration
	public static class TestConfig {
		@Bean
		public MultipartConfigElement multipartConfigElement() {
			return new MultipartConfigElement("myLocation");
		}
	}
}

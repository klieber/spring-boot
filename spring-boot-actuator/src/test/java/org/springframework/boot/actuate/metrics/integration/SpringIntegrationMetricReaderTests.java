/*
 * Copyright 2012-2015 the original author or authors.
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

package org.springframework.boot.actuate.metrics.integration;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.integration.SpringIntegrationMetricReaderTests.TestConfiguration;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.monitor.IntegrationMBeanExporter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link SpringIntegrationMetricReader}.
 *
 * @author Dave Syer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestConfiguration.class)
@IntegrationTest("spring.jmx.enabled=true")
@DirtiesContext
public class SpringIntegrationMetricReaderTests {

	@Autowired
	private SpringIntegrationMetricReader reader;

	@Test
	public void test() {
		assertTrue(this.reader.count() > 0);
	}

	@Configuration
	@Import({ JmxAutoConfiguration.class, IntegrationAutoConfiguration.class })
	protected static class TestConfiguration {
		@Bean
		public SpringIntegrationMetricReader reader(IntegrationMBeanExporter exporter) {
			return new SpringIntegrationMetricReader(exporter);
		}
	}

}

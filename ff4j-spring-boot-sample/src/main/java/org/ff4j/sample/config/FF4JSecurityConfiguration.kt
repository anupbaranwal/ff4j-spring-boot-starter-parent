/*-
 * #%L
 * ff4j-spring-boot-autoconfigure
 * %%
 * Copyright (C) 2013 - 2022 FF4J
 * %%
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
 * #L%
 */
package org.ff4j.sample.config

import org.ff4j.spring.boot.autoconfigure.common.FF4JConfigurationProperties
import org.ff4j.spring.boot.autoconfigure.webmvc.FF4JWebConsoleConfiguration
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@ConditionalOnProperty(
  value = ["ff4j.web-console.security.enabled"], havingValue = "true", matchIfMissing = false
)
@ConditionalOnBean(FF4JWebConsoleConfiguration::class)
@AutoConfigureAfter(FF4JWebConsoleConfiguration::class)
@EnableWebSecurity
class FF4JSecurityConfiguration() {

  @Bean
  @Throws(Exception::class)
  fun filterChain(http: HttpSecurity): SecurityFilterChain {
    http.csrf().disable().authorizeHttpRequests().anyRequest().permitAll()
    return http.build()
  }
}


/*-
 * #%L
 * ff4j-spring-rest-api
 * %%
 * Copyright (C) 2013 - 2023 FF4J
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

package org.ff4j.spring.rest.api.utils

import org.ff4j.services.model.FeatureActions
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import reactor.core.publisher.Mono
import java.lang.Boolean.TRUE

/**
 * Created by Paul
 *
 * @author [Paul Williams](mailto:paul58914080@gmail.com)
 */
object FeatureWebUtils {

  fun getBooleanResponseEntityByHttpStatus(featureActions: FeatureActions): ResponseEntity<Mono<Boolean>> {
    return when (featureActions) {
      FeatureActions.CREATED -> ResponseEntity(Mono.just(TRUE), HttpStatus.CREATED)
      FeatureActions.UPDATED -> ResponseEntity(Mono.just(TRUE), HttpStatus.NO_CONTENT)
    }
  }
}

/*-
 * #%L
 * ff4j-spring-services
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
package org.ff4j.services.domain

import org.ff4j.property.Property
import org.ff4j.property.util.PropertyFactory
import java.util.stream.Collectors

/**
 * Created by Paul
 *
 * @author <a href="mailto:paul58914080@gmail.com">Paul Williams</a>
 */
class PropertyApiBean {

  companion object {
    private const val serialVersionUID = -5366099799518640405L
  }

  var name: String? = null
  var description: String? = null
  var type: String? = null
  var value: String? = null
  var fixedValues: MutableSet<String> = HashSet()

  constructor() : super()

  constructor(property: Property<*>) {
    property.let {
      this.name = it.name
      this.description = it.description
      this.type = it.type
      this.value = it.asString()
      it.fixedValues?.let {
        this.fixedValues.addAll(it.stream().map({ it.toString() }).collect(Collectors.toList()))
      }
    }
  }

  fun asProperty(): Property<*> {
    return PropertyFactory.createProperty(name, type, value, description, fixedValues)
  }
}

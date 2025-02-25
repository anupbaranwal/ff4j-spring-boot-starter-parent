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
package org.ff4j.services.propertystore

import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import org.ff4j.FF4j
import org.ff4j.cache.FF4jCacheProxy
import org.ff4j.cache.InMemoryCacheManager
import org.ff4j.services.FF4JTestHelperUtils
import org.ff4j.services.InitializerStepDef
import org.ff4j.services.PropertyStoreServices
import org.ff4j.services.representation.PropertyPojo

/**
 * Created by Paul
 *
 * @author <a href="mailto:paul58914080@gmail.com">Paul Williams</a>
 */
class PropertyStoreServicesStepDef(ff4j: FF4j, propertyStoreServices: PropertyStoreServices) : En {

  private val testUtils = FF4JTestHelperUtils(ff4j)
  private lateinit var actualResponse: Any
  private lateinit var exception: Throwable

  init {
    InitializerStepDef().initDataTable()

    Given("the property store is cleared") {
      testUtils.clearPropertyStore()
    }
    Given("the following properties exists in the property store") { dataTable: DataTable ->
      dataTable.asList(PropertyPojo::class.java).map {
        testUtils.convertToPropertyModel(it)
      }.forEach { ff4j.createProperty(it) }
    }
    Given("the property store is cached") {
      val proxy = FF4jCacheProxy(ff4j.featureStore, ff4j.propertiesStore, InMemoryCacheManager())
      ff4j.propertiesStore = proxy
      ff4j.featureStore = proxy
    }
    Given("the following properties are cached") { dataTable: DataTable ->
      dataTable.asList(PropertyPojo::class.java).map {
        testUtils.convertToPropertyModel(it)
      }.forEach { (ff4j.propertiesStore as FF4jCacheProxy).cacheManager.putProperty(it) }
    }
    When("the user requests for the property store") {
      actualResponse = propertyStoreServices.getPropertyStore()
    }
    When("the user requests for all the properties from the property store") {
      actualResponse = propertyStoreServices.getAllProperties()
    }
    When("the user requests to delete all the properties from the property store") {
      propertyStoreServices.deleteAllProperties()
    }
    When("the user requests to get the cached property store") {
      try {
        actualResponse = propertyStoreServices.getPropertiesFromCache()
      } catch (t: Throwable) {
        exception = t
      }
    }
    When("the user requests to clear the cached property store") {
      try {
        propertyStoreServices.clearCachedPropertyStore()
      } catch (t: Throwable) {
        exception = t
      }
    }
    Then("the user gets the response as") { expectedResponse: String ->
      testUtils.assertLenientResponse(expectedResponse, actualResponse)
    }
    Then("the user gets an exception {string}") { className: String ->
      testUtils.assertException(exception, className)
    }
  }
}

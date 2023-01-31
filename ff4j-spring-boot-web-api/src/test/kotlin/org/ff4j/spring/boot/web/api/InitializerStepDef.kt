package org.ff4j.spring.boot.web.api

import io.cucumber.java8.En
import org.ff4j.core.Feature
import org.ff4j.spring.boot.web.api.representation.PropertyPojo
import org.ff4j.spring.boot.web.api.representation.TestAuthorizationsManager
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.servlet.client.MockMvcWebTestClient
import org.springframework.web.context.WebApplicationContext

/**
 * Created by Paul
 *
 * @author <a href="mailto:paul58914080@gmail.com">Paul Williams</a>
 */
class InitializerStepDef : En {

  fun getTestClient(context: WebApplicationContext): WebTestClient =
    MockMvcWebTestClient.bindToApplicationContext(context).build()

  fun initDataTable() {
    DataTableType { row: Map<String, String> ->
      Feature(
        row["uid"].toString(),
        row["enable"].toString().toBooleanStrict(),
        row["description"].toString(),
        if ("null" != row["group"].toString()) row["group"].toString() else "",
        row["permissions"].toString().split(",")
      )
    }

    DataTableType { row: Map<String, String> ->
      TestAuthorizationsManager(
        row["currentUserPermissions"].toString(), row["allPermissions"].toString()
      )
    }

    DataTableType { row: Map<String, String> ->
      PropertyPojo(
        row["name"].toString(),
        row["description"].toString(),
        row["type"].toString(),
        row["value"].toString(),
        row["fixedValueCSV"].toString()
      )
    }
  }
}

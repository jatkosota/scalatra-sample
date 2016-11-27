package sample.app

import org.scalatra._

import slick.driver.H2Driver.api._
import scala.concurrent.ExecutionContext.Implicits.global

trait SlickRoutes extends ScalatraBase with FutureSupport {

  def db: Database

  get("/db/create-tables") {
    db.run(Tables.createSchemaAction)
  }

  get("/db/load-data") {
    db.run(Tables.insertSupplierAndCoffeeData)
  }

  get("/db/drop-data") {
    db.run(Tables.dropSchemaAction)
  }

  get("/coffees") {
    db.run(Tables.findCoffeesWithSuppliers.result) map { xs =>
      contentType = "text/plain"
      xs map { case (s1, s2) => f"  $s1 supplied by $s2" } mkString "\n"
    }
  }
}

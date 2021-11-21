package pl.jacekkosy

object BuilderPattern {
  def main(args: Array[String]): Unit = {
    val clientBuilder = new ClientBuilderImpl()
      .withFirstName("Magda")
      .withLastName("Gessler")
      .withPhoneNumber(777888999)
      .withVip(true)
    val client = clientBuilder.build
    println(client)
  }
}

abstract class Person

abstract class ClientBuilder {
  var firstName: String
  var lastName: String
  var phoneNumber: Long
  var vip: Boolean

  def withFirstName(firstName: String): ClientBuilder
  def withLastName(lastName: String): ClientBuilder
  def withPhoneNumber(phoneNumber: Long): ClientBuilder
  def withVip(vip: Boolean): ClientBuilder

  def build: Person
}
class Client(clientBuilder: ClientBuilder) extends Person {
  val firstName: String = clientBuilder.firstName
  val lastName: String = clientBuilder.lastName
  val phoneNumber: Long = clientBuilder.phoneNumber
  val vip: Boolean = clientBuilder.vip

  override def toString: String = {
    "Name: " + firstName + " " + lastName + " phone: " + phoneNumber + " is VIP: " + vip
  }
}
class ClientBuilderImpl extends ClientBuilder {
  override var firstName: String = _
  override var lastName: String = _
  override var phoneNumber: Long = _
  override var vip: Boolean = _

  override def withFirstName(firstName: String): ClientBuilder = {
    this.firstName = firstName
    this
  }

  override def withLastName(lastName: String): ClientBuilder = {
    this.lastName = lastName
    this
  }

  override def withPhoneNumber(phoneNumber: Long): ClientBuilder = {
    this.phoneNumber = 797234596
    this
  }

  override def withVip(vip: Boolean): ClientBuilder = {
    this.vip = vip
    this
  }

  override def build: Person = new Client(this)
}

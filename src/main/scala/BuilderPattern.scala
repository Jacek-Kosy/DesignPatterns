package pl.jacekkosy

object BuilderPattern {
  def main(args: Array[String]): Unit = {
    val clientBuilder = new ClientBuilder()
      .withFirstName("Magda")
      .withLastName("Gessler")
      .withPhoneNumber(797234596)
      .withVip(true)
    val magda = clientBuilder.build
    println(magda)
    val prisonerBuilder = new PrisonerBuilder()
      .withFirstName("Jan")
      .withLastName("Kowalski")
      .withCrime("fraud")
      .withSentenceLength(3)
    val prisoner = prisonerBuilder.build
    println(prisoner)
  }
}

abstract class Person {
  val firstName: String
  val lastName: String
}
abstract class PersonBuilder {
  var firstName: String
  var lastName: String

  def withFirstName(firstName: String): PersonBuilder
  def withLastName(lastName: String): PersonBuilder

  def build: Person
}

class Client(clientBuilder: ClientBuilder) extends Person {
  override val firstName: String = clientBuilder.firstName
  override val lastName: String = clientBuilder.lastName
  val phoneNumber: Long = clientBuilder.phoneNumber
  val vip: Boolean = clientBuilder.vip

  override def toString: String = {
    "Name: " + firstName + " " + lastName + " phone: " + phoneNumber + " is VIP: " + vip
  }


}
class Prisoner(prisonerBuilder: PrisonerBuilder) extends Person {
  override val firstName: String = prisonerBuilder.firstName
  override val lastName: String = prisonerBuilder.lastName
  val crime: String = prisonerBuilder.crime
  val sentenceLength: Int = prisonerBuilder.sentenceLength
  override def toString: String = {
    "Name: " + firstName + " " + lastName + " crime: " + crime + ",in the can for " + sentenceLength + " years"
  }
}
class PrisonerBuilder extends PersonBuilder {
  override var firstName: String = _
  override var lastName: String = _
  var crime: String = _
  var sentenceLength: Int = _

  override def withFirstName(firstName: String): PrisonerBuilder = {
    this.firstName = firstName
    this
  }

  override def withLastName(lastName: String): PrisonerBuilder = {
    this.lastName = lastName
    this
  }
  def withCrime(crime: String): PrisonerBuilder = {
    this.crime = crime
    this
  }
  def withSentenceLength(sentenceLength: Int): PrisonerBuilder = {
    this.sentenceLength = sentenceLength
    this
  }

  override def build: Prisoner = new Prisoner(this)
}
class ClientBuilder extends PersonBuilder {
  override var firstName: String = _
  override var lastName: String = _
  var phoneNumber: Long = _
   var vip: Boolean = _

  override def withFirstName(firstName: String): ClientBuilder = {
    this.firstName = firstName
    this
  }

  override def withLastName(lastName: String): ClientBuilder = {
    this.lastName = lastName
    this
  }

  def withPhoneNumber(phoneNumber: Long): ClientBuilder = {
    this.phoneNumber = 797234596
    this
  }

  def withVip(vip: Boolean): ClientBuilder = {
    this.vip = vip
    this
  }

  override def build: Client = new Client(this)
}

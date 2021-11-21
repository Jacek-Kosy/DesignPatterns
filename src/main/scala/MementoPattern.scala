package pl.jacekkosy

object MementoPattern {
  def main(args: Array[String]): Unit = {
    val game = Game("Knight", 5, 11.3, 2, "Sharp")
    val handler = new GameHandler(null)
    handler.makeBackup(game)
    println(game)
    game.lives = 4
    game.money = 77.8
    game.level = 9
    game.swordType = "Laser"
    println(game)
    handler.undo()
    println(game)
  }
}

case class Game(heroName: String, var lives: Int, var money: Double, var level: Int, var swordType:String) extends Originator {
  override def save(): Memento = {
    new GameMemento(this, this.lives, this.money, this.level, this.swordType)
  }

}
trait Originator {
  def save() : Memento
}
trait Memento {
  def restore(): Unit
}
trait Handler {
  def makeBackup(originator: Originator): Unit
  def undo(): Unit
}
class GameMemento(game: Game,lives: Int, money: Double, level: Int, swordType: String) extends Memento {
  override def restore(): Unit = {
    game.lives = this.lives
    game.money = this.money
    game.level = this.level
    game.swordType = this.swordType
  }
}
class GameHandler (var memento: Memento) extends Handler {
  override def makeBackup(originator: Originator): Unit = {
    this.memento = originator.save()
  }

  override def undo(): Unit = {
    if (memento != null) {
      memento.restore()
    }
  }
}

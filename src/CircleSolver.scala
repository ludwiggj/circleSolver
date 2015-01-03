import scala.collection.immutable.ListMap

trait Solution {
  def circleTotal: Int = ???

  def isCorrect: Boolean = ???
}

class FourCircleSolution(val c: Int,
                         val a1: Int, val a2: Int, val a3: Int, val a4: Int,
                         val b1: Int, val b2: Int, val b3: Int, val b4: Int,
                         val c1: Int, val c2: Int, val c3: Int, val c4: Int) extends Solution {

  val circle1 = c + a1 + a2 + a4 + b1 + b4 + c1

  def circle2 = c + a1 + a2 + a3 + b1 + b2 + c2

  def circle3 = c + a2 + a3 + a4 + b2 + b3 + c3

  def circle4 = c + a1 + a3 + a4 + b3 + b4 + c4

  override def isCorrect = {
    val correct = (circle1 == circle2) && (circle1 == circle3) && (circle1 == circle4)
    if (correct) println(toString)
    correct
  }

  override def circleTotal = circle1

  override def toString = s"(c=$c a1=$a1 a2=$a2 a3=$a3 a4=$a4 b1=$b1 b2=$b2 b3=$b3 b4=$b4 c1=$c1 c2=$c2 c3=$c3 c4=$c4)"
}

object FourCircleSolution {
  def apply(x: List[Int]) = {
    new FourCircleSolution(x(0), x(1), x(2), x(3), x(4), x(5), x(6), x(7), x(8), x(9), x(10), x(11), x(12))
  }
}

class ThreeCircleSolution(val c: Int,
                          val a1: Int, val a2: Int, val a3: Int,
                          val b1: Int, val b2: Int, val b3: Int) extends Solution {

  val circle1 = c + a1 + a3 + b1

  def circle2 = c + a2 + a3 + b2

  def circle3 = c + a1 + a2 + b3

  override def isCorrect = (circle1 == circle2) && (circle1 == circle3)

  override def circleTotal = circle1

  override def toString = s"(c=$c a1=$a1 a2=$a2 a3=$a3 b1=$b1 b2=$b2 b3=$b3)"
}

object ThreeCircleSolution {
  def apply(x: List[Int]) = {
    new ThreeCircleSolution(x(0), x(1), x(2), x(3), x(4), x(5), x(6))
  }
}

object CircleSolver extends App {
  def solveCircleProblem(selectableNumbers: List[Int], createPotentialSolution: (List[Int]) => Solution) = {
    val permutations = selectableNumbers.permutations
    for {
      permutation <- permutations
      potentialSolution = createPotentialSolution(permutation)
      if (potentialSolution.isCorrect)
    } yield (potentialSolution.circleTotal -> potentialSolution)
  }

  def solveThreeCircleProblemWithFixedCentre(centre: Int) = {
    solveCircleProblem(
      List(1, 2, 3, 4, 5, 6, 7) diff List(centre),
      (permutation: List[Int]) => ThreeCircleSolution(centre :: permutation)
    )
  }

  def solveFourCircleProblem = {
    solveCircleProblem(
      List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13),
      (permutation: List[Int]) => FourCircleSolution(permutation)
    )
  }

  def solveFourCircleProblemWithFixedCentre(centre: Int) = {
    solveCircleProblem(
      List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13) diff List(centre),
      (permutation: List[Int]) => FourCircleSolution(centre :: permutation)
    )
  }

  // Now try them out...
  println(ListMap(solveThreeCircleProblemWithFixedCentre(7).toSeq.sortBy(_._1): _*))
  println(ListMap(solveFourCircleProblem.toSeq.sortBy(_._1): _*))

  for {
    centre <- 1 to 13
  } println(ListMap(solveFourCircleProblemWithFixedCentre(centre).toSeq.sortBy(_._1): _*))
}
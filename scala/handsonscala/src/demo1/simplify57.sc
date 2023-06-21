
sealed trait Expr

case class BinOp(left: Expr, op: String, right: Expr) extends Expr

case class Literal(value: Int) extends Expr

case class Variable(name: String) extends Expr

println("a")

def stringify(expr: Expr): String = expr match {
  case BinOp(left, op, right) => s"(${stringify(left)} $op ${stringify(right)}"
  case Literal(value) => value.toString
  case Variable(name) => name
}

def simplify(expr: Expr): Expr = {
  val res = expr match {
    case BinOp(Literal(left), "+", Literal(right)) => Literal(left + right)
    case BinOp(Literal(left), "-", Literal(right)) => Literal(left + right)
    case BinOp(Literal(left), "*", Literal(right)) => Literal(left + right)

    case BinOp(left, "*", Literal(1)) => simplify()
  }
}
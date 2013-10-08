package example

import Lists._
object Executer extends App{
    var list2 = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    1 to 15 foreach( x => list2 = list2 ++ list2 )
    
    println (sumRegularRecursive(list2)) //This causes a stack overflow
    println (sumTailRecursive(list2))

}
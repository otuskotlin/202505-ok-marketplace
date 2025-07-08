import kotlin.test.Test

abstract class BaseClass {

}

interface IClass {

}

@Suppress("unused")
class InheritedClass(
    arg: String,
    val prop: String = arg
) : IClass, BaseClass() {
    val x: String = arg
    init {
        println("Init in constructor with $arg")
    }

    fun some() {
        println("Some is called with: ${this.prop}")
    }
}

class BaseTest() {
    @Test
    fun baseTest() {
        val obj = InheritedClass("some")
        obj.some()
    }
}
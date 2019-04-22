package frp

open class Ketai<T:Any>(init: () -> T){
    lateinit var myCurrentValue:T
    lateinit var myExpression: ()-> T
    private var observer:Set<Ketai<*>> = emptySet()

    init{
        change(init)
    }

    operator fun invoke():T{
        observer += caller.value()
        return myCurrentValue
    }



    fun change(change:()->T){
        myExpression = change
        computeValue()
    }

    open fun computeValue(){
        val newValue = caller.withValue(this, myExpression)

        if( !this::myCurrentValue.isInitialized || newValue != myCurrentValue){
            myCurrentValue = newValue
            val obs = observer
            observer = emptySet()
            obs.map { it.computeValue() }
        }

    }

    companion object {
        val caller = Stackable<Ketai<*>>(emptySignal)
    }
}

object emptySignal:Ketai<Any>({-1}){
    override fun computeValue() {}
}


class Stackable<T>(val init:T){
    private var values:List<T> = listOf(init)
    fun value() :T{
        return values.first()
    }
    fun <R>withValue(newValue:T, op:()-> R):R{
        values = listOf(newValue) + values

        val valueJing:R
        try{
            valueJing = op()
        }finally {
            values = values.drop(1)
        }

        return valueJing
    }

}
package pubSub

import java.util.HashSet

interface Publisher{
    fun addSubscriber(sub:Subscriber)
    fun removeSubscriber(sub:Subscriber)
    fun publish()
}


interface Subscriber{
    fun handling(pub:Publisher)
}
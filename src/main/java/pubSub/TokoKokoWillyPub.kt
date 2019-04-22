package pubSub

class TokoKokoWillyPub:Subscriber{
    override fun handling(pub: Publisher) {
        if(pub is BarangRusakTomyPub){
            val sampah = pub.sampah
            println("koko willy beli ${sampah.namaBarang}: ${sampah.price}")
        }
    }
}


class BarangRusakTomyPub:Publisher{
    val set = mutableSetOf<Subscriber>()
    var sampah = Sampah("",0)

    override fun addSubscriber(sub: Subscriber) {
        set.add(sub)
    }

    override fun removeSubscriber(sub: Subscriber) {
        set.remove(sub)
    }

    override fun publish() {
        set.forEach { it.handling(this) }
    }

    fun jual(nama:String, harga:Int){
        sampah = Sampah(nama, harga)
        publish()
    }
}


data class Sampah(val namaBarang:String, val price:Int)
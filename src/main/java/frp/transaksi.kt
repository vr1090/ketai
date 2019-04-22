package frp

import pubSub.Sampah

fun main(args:Array<String>){
    val tomy = Ketai{ Sampah("keyboard", 100)}
    val willy = Ketai{
        val sampah = tomy()
        println("willy nampung barang ${sampah.namaBarang}:: ${sampah.price}")
        Unit
    }

    tomy.change { Sampah("tv", 10) }

    val wp = Ketai{Sampah("s10", 5)}

    willy.change { val barangWp = wp()
        val barangTomy = tomy()
        println(" wp: ${barangWp.namaBarang} :: ${barangWp.price}")
        println(" tomy ${barangTomy.namaBarang}:: ${barangTomy.price}")
    }

    tomy.change { Sampah("speaker",1) }


}
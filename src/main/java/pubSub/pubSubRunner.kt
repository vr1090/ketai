package pubSub

fun main(args:Array<String>){
    val willy = TokoKokoWillyPub()
    val tomy = BarangRusakTomyPub()
    tomy.addSubscriber(willy)

    tomy.jual("keyboard rusak", 100)
    tomy.jual("papan rusak", 101)
}